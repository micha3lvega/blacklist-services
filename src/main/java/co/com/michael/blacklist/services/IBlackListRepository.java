package co.com.michael.blacklist.services;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.michael.blacklist.services.model.dao.BlackList;

public interface IBlackListRepository extends JpaRepository<BlackList, String> {

	BlackList findByChain(String chain);

}
