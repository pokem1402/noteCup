package com.noteCup.contents.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.contents.constant.ContentType;
import com.noteCup.contents.model.domain.ContentPost;
import com.noteCup.contents.model.domain.ContentScript;
import com.noteCup.contents.model.domain.ContentWrapper;
import com.noteCup.contents.model.dto.ContentInput;
import com.noteCup.contents.repository.IContentWrapperRepository;
import com.noteCup.contents.repository.IPostRepository;
import com.noteCup.contents.repository.IScriptRepository;
import com.noteCup.member.model.domain.MemberInfo;
import com.noteCup.member.model.dto.MemberInput;
import com.noteCup.member.repository.IMemberRepository;

import lombok.extern.log4j.Log4j2;




@Service
@Log4j2
public class ContentsMM {

	ModelAndView mav;

	private static final Logger log = LoggerFactory.getLogger(ContentsMM.class);

	@Autowired
	private IContentWrapperRepository crdao;
	@Autowired
	private IMemberRepository mdao;
	@Autowired
	private IPostRepository cdao;
	@Autowired
	private IScriptRepository sdao;

	public void create(ContentInput contentInput) {

		log.warn(contentInput.toString());
		Optional<MemberInfo> member = mdao.findById((long) 1);

		log.warn(member.toString());

		ContentWrapper contentWrapper = new ContentWrapper();
		contentWrapper.setMemberInfo(member.get());
		
		System.out.println("멤버 아이디::::: " + contentWrapper.getMemberInfo().getMid() );
		//System.out.println("멤버 아이디"+  contentWrapper.getMemberInfo().getMid());
		//System.out.println("컨텐츠 래퍼포스트" + contentWrapper.getPost().getCtext());
		System.out.println( "컨텐츠 아이디: " + contentWrapper.getCid() );
		
		log.warn(contentWrapper.toString());
		
		
		contentWrapper = crdao.save(contentWrapper);

		
		
		switch (ContentType.findType(contentInput.getContentType())) {

		case POST:
			//@formatter:off
			ContentPost post = ContentPost.builder()
									.contentWrapper(contentWrapper)
									.ctitle(contentInput.getTitle())
									.ctext(contentInput.getText())
									.build();

			cdao.save(post);
			//@formatter:on
			break;
		case SCRIPT:
			//@formatter:off
			ContentScript script = ContentScript.builder()
										.contentWrapper(contentWrapper)
										.ctext(contentInput.getText())
										.build();

			sdao.save(script);
			//@formatter:on
			break;
		}

	}

	@Transactional
	public void delete(String cid) {

		long id = Long.parseLong(cid);
		
		cdao.deleteById(id);	

	}
		
	public String read(Map<Object, Object> hm) {
		String result = "";
		switch (hm.get("ctype").toString()) {

		case "post":
			//@formatter:off
			ContentPost cp = new ContentPost();
			cp = cdao.findByCid( Long.valueOf( (String) hm.get("cid") ) );
			result = makeHtml(cp);
			
			//@formatter:on
			break;
		case "script":
			//@formatter:off
			ContentScript cs  = new ContentScript();
			cs = sdao.findByCid( Long.valueOf((String) hm.get("cid")) );
			result = makeHtml(cs);
			//@formatter:on
			break;
		}
		return result;
	}

	/**
	 * @author daniel
	 * ContentPost : 포스트를 html로 만들어주는 메소드
	 */	
	public String makeHtml(ContentPost cp) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h2>" + cp.getCtitle() + "</h2>");
		sb.append("<h3>" + cp.getContentWrapper().getMemberInfo().getNickname() + "</h3>");
		sb.append("<article>" + cp.getCtext() + "</article>");
		//글 삭제 링크 버튼 만들어 줘야함
		return sb.toString();
	}

	/**
	 * @author daniel
	 * ContentScript : 스크립트를 html로 만들어주는 메소드
	 */
	public String makeHtml(ContentScript cs) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>" + cs.getContentWrapper().getMemberInfo().getNickname() + "</h3>");
		sb.append("<article>" + cs.getCtext() + "</article>");
		//글 삭제 링크 버튼 만들어 줘야함
		return sb.toString();
	}

}
