package com.noteCup.contents.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.noteCup.contents.model.domain.ContentWrapper;
import com.noteCup.member.model.domain.MemberInfo;


@Repository
public interface IContentWrapperRepository extends JpaRepository<ContentWrapper,  Long>{


//	void deleteByCid(long cid);

	ContentWrapper findById(long cid);

//	@Query("select ")
	List<ContentWrapper> findAllByMemberInfo(MemberInfo member);
	
	
}
