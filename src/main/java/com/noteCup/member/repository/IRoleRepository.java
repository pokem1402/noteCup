package com.noteCup.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.noteCup.member.model.domain.Role;

public interface IRoleRepository extends JpaRepository<Role, Long>{

}
