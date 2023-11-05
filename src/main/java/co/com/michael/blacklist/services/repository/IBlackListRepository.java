package co.com.michael.blacklist.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.michael.blacklist.services.model.dao.BlackList;

public interface IBlackListRepository extends JpaRepository<BlackList, Long> {

	BlackList findByChain(String chain);

}
