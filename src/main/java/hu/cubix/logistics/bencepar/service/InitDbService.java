package hu.cubix.logistics.bencepar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.cubix.logistics.bencepar.model.Address;
import hu.cubix.logistics.bencepar.repository.AddressRepository;
import hu.cubix.logistics.bencepar.repository.MilestoneRepository;
import hu.cubix.logistics.bencepar.repository.SectionRepository;
import hu.cubix.logistics.bencepar.repository.TransportPlanRepository;
import jakarta.transaction.Transactional;

@Service
public class InitDbService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	TransportPlanRepository transportPlanRepository;

	@Autowired
	MilestoneRepository milestoneRepository;
	
	@Autowired
	SectionRepository sectionRepository;

	public void clearDb() {
		sectionRepository.deleteAllInBatch();
		milestoneRepository.deleteAllInBatch();
		addressRepository.deleteAllInBatch();
		transportPlanRepository.deleteAllInBatch();

	}

//	@Transactional
//	public void initDb() {
//		
//		Address newAddress1 = addressRepository.save(new Address(0, "abc", "Jósika utca", 1135L, 12, 555.5, 666.6));
//	}
}
