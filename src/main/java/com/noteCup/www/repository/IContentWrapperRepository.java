package com.noteCup.www.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noteCup.www.model.ContentWrapperInput;


@Repository
public interface IContentWrapperRepository extends JpaRepository<ContentWrapperInput,  BigDecimal>{


}
