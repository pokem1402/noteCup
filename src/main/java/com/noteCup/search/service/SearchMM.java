package com.noteCup.search.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.contents.constant.ContentType;
import com.noteCup.contents.model.domain.ContentPost;
import com.noteCup.contents.model.domain.ContentScript;
import com.noteCup.contents.repository.IPostRepository;
import com.noteCup.contents.repository.IScriptRepository;
import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.vo.MemberView;
import com.noteCup.member.repository.IMemberRepository;
import com.noteCup.reply.repository.IReplyRepository;

@Service
public class SearchMM {
	
	ModelAndView mav;

	@Autowired 
	IMemberRepository memberRepository;

	@Autowired
	IPostRepository postRepository;

	@Autowired
	IScriptRepository scriptRepository;


	@Transactional 
	public List<MemberView> searchMember(String member) {

		//List<MemberInfo> mInfo =

		return null;
	}


	/**
	 * descript : 메소드로 타입별로 검색할려함.. 그런데 타입은 이렇게 나누면 되는데
	 * 			내용이나 제목은 어떻게 검색할건지? 그것도 policy가 필요하고
	 * 			query도 단어 하나만 검색할수도 있고 두개이상으로검색할수도 있음 ex) "자바" , "자료구조 알고리즘"
	 * 			이건 어떻게 할건지?...띄어쓰기가 있으면 배열로 만들어서 던져야하나?
	 * ------------------------------------
	 * Ref : http://yoonbumtae.com/?p=2867
	 * @param hm
	 * daniel
	 * 2021. 4. 30.
	 * searchSomething
	 */
	public ModelAndView searchSomething(Map<String, String> hm) {
		String query = hm.get("query");
		mav = new ModelAndView();
		switch (ContentType.findType(hm.get("type"))) { 

		case POST:
			// @formatter:off
		    List<ContentPost> contentPostList = 
		    postRepository.findByCtitleContainingIgnoreCaseOrCtextContainingIgnoreCase(query, query);
		    mav.addObject("searchList",  makeHtmlPostList(contentPostList));
		    mav.setViewName("searchResult");
			break;
			// @formatter:on
		case SCRIPT:
			// @formatter:off
			List<ContentScript> contentScriptList = 
			scriptRepository.findByCtextContainingIgnoreCase(query);			
			mav.addObject("searchList",  makeHtmlScriptList(contentScriptList));
		    mav.setViewName("searchResult");
			break;
			// @formatter:on
		}
		return mav;
		/* 이부분은 지워도 될듯
		 * if(query.contains(" ")) { String[] queries = query.split(" "); switch
		 * (ContentType.findType(hm.get("type"))) { //queries 다중검색?..
		 * 
		 * case POST: ContentPost contentPost = ContentPost.builder() .ctitle(query)
		 * .ctext(query).build(); postRepository.findByContentPost(contentPost); break;
		 * case SCRIPT: break; } }else { //query : 하나검색 switch
		 * (ContentType.findType(hm.get("type"))) { case POST: ContentPost contentPost =
		 * ContentPost.builder() .ctitle(query) .ctext(query).build(); break;
		 * 
		 * case SCRIPT: break;
		 * 
		 * } }
		 */	
	}


	/**
	 * 컨텐츠중에 타입이 포스트인 것들을 리스트들을 mav에 setattribute해줄려고 만든 메소드
	 * @param contentPostList
	 * @return
	 * daniel
	 * 2021. 4. 30.
	 * makeHtmlPostList
	 */
	private String makeHtmlPostList(List<ContentPost> contentPostList) {
		// TODO Auto-generated method stub
		//~나중에 추가할 사항~//
		return "";
	}
	
	/**
	 * 컨텐츠중에 타입이 스크립트인것들의 리스트들을 mav에 setattribute해줄려고 만든 메소드
	 * @param contentScriptList
	 * @return
	 * daniel
	 * 2021. 4. 30.
	 * makeHtmlScriptList
	 */
	private String makeHtmlScriptList(List<ContentScript> contentScriptList) {
		// TODO Auto-generated method stub
		//~나중에 추가할 사항~//
		return "";
	}	
	

}
