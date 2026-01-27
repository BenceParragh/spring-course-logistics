package hu.cubix.logistics.bencepar;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import hu.cubix.logistics.bencepar.dto.AddressDto;
import hu.cubix.logistics.bencepar.dto.MilestoneDto;
import hu.cubix.logistics.bencepar.dto.SectionDto;
import hu.cubix.logistics.bencepar.dto.TransportPlanDto;
import hu.cubix.logistics.bencepar.model.Address;
import hu.cubix.logistics.bencepar.model.Milestone;
import hu.cubix.logistics.bencepar.model.Section;
import hu.cubix.logistics.bencepar.model.TransportPlan;
import hu.cubix.logistics.bencepar.repository.MilestoneRepository;
import hu.cubix.logistics.bencepar.repository.SectionRepository;
import hu.cubix.logistics.bencepar.repository.TransportPlanRepository;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TransportPlanDelayEndpointIT {

    private static final String BASE_URI = "/api/transportPlans";

    @Autowired
    WebTestClient webTestClient;
    
    @Autowired
    MilestoneRepository milestoneRepository;
    
    @Autowired
    SectionRepository sectionRepository;
    
    @Autowired
    TransportPlanRepository transportPlanRepository;
    
    private TransportPlan testPlan;
    private Milestone startMilestone;
    private Milestone endMilestone;
    private Section testSection;

//    @BeforeEach
//    void setUp() {
//        // 1. Create managed milestones FIRST
//        startMilestone = new Milestone();
//        startMilestone.setMilestoneId(1L);
//        startMilestone.setPlannedTime(LocalDateTime.of(2026, 1, 27, 10, 0));
//        startMilestone = milestoneRepository.save(startMilestone);
//
//        endMilestone = new Milestone();
//        endMilestone.setMilestoneId(2L);
//        endMilestone.setPlannedTime(LocalDateTime.of(2026, 1, 27, 12, 0));
//        endMilestone = milestoneRepository.save(endMilestone);
//
//        testPlan = new TransportPlan();
//        testPlan.setPlanId(1L);
//        testPlan.setExpectedIncome(10000.0);
//        testPlan = transportPlanRepository.save(testPlan); // Save empty plan first
//
//        testSection = new Section();
//        testSection.setStartMilestone(startMilestone);  // Reference managed entity
//        testSection.setEndMilestone(endMilestone);     // Reference managed entity
//        sectionRepository.save(testSection);
//
//        testPlan.setSections(List.of(testSection));
//        transportPlanRepository.save(testPlan); // This works - all entities managed
//    }

    @Test
    void shouldPropagateDelayFromStartMilestoneToEndMilestone() {
        // Given
        MilestoneDto delayRequest = new MilestoneDto(1L, 45);
        LocalDateTime originalEndTime = LocalDateTime.of(2026, 1, 26, 12, 0);
        webTestClient.post()
            .uri("/api/transportPlans/1/delay", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(delayRequest)
            .exchange()
            .expectStatus().isOk()
            .expectBody(TransportPlan.class)
            .value(updatedPlan -> {
                assertThat(updatedPlan.getExpectedIncome()).isLessThan(9500.0);
            });
        
        endMilestone = milestoneRepository.findById(2L).orElseThrow();
        assertThat(endMilestone.getPlannedTime())
            .isEqualTo(originalEndTime.plusMinutes(45));
        assertThat(endMilestone.getExpectedDelay()).isEqualTo(45);
        
        TransportPlan savedPlan = transportPlanRepository.findById(1L).orElseThrow();
        assertThat(savedPlan.getExpectedIncome()).isLessThan(9500.0);
    }

    @Test
    void shouldReturn404WhenPlanNotFound() {
        MilestoneDto delayRequest = new MilestoneDto(1L, 30);
        
        webTestClient.post()
            .uri("/api/transportPlans/999/delay")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(delayRequest)
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    void shouldReturn400WhenMilestoneNotInPlan() {
        Milestone orphanMilestone = new Milestone();
        orphanMilestone.setMilestoneId(999L);
        milestoneRepository.save(orphanMilestone);
        
        MilestoneDto delayRequest = new MilestoneDto(999L, 30);
        
        webTestClient.post()
            .uri("/api/transportPlans/{planId}/delay", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(delayRequest)
            .exchange()
            .expectStatus().isBadRequest();
    }
}
