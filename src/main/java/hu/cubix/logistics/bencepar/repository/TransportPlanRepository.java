package hu.cubix.logistics.bencepar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cubix.logistics.bencepar.model.TransportPlan;

public interface TransportPlanRepository extends JpaRepository<TransportPlan, Long>{

}
