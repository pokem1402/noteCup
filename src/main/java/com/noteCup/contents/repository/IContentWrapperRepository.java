package com.noteCup.contents.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.noteCup.contents.model.domain.ContentWrapper;


@Repository
public interface IContentWrapperRepository extends JpaRepository<ContentWrapper,  Long>{


//	void deleteByCid(long cid);

	ContentWrapper findById(long cid);

	List<ContentWrapper> findByMid(Long mid);

}
