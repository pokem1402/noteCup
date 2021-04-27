package com.noteCup.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noteCup.member.model.domain.Privilege;

public interface IPrivilegeRepository extends JpaRepository<Privilege, Long>{

}
