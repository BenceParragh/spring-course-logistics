package hu.cubix.logistics.bencepar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.cubix.logistics.bencepar.model.LogisticsUser;

public interface LogisticsUserRepository extends JpaRepository<LogisticsUser, String>{

}
