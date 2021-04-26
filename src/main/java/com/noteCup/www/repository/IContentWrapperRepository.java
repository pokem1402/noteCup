package com.noteCup.www.repository;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noteCup.www.model.ContentWrapperInput;


@Repository
public interface IContentWrapperRepository extends JpaRepository<ContentWrapperInput,  Long>{


//	void deleteByCid(long cid);

	ContentWrapperInput findById(long cid);

}
