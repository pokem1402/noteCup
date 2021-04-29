package com.noteCup.contents.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

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

	@Autowired
	private IContentWrapperRepository contentWrapperRepository;
	@Autowired
	private IMemberRepository memberRepository;
	@Autowired
	private IPostRepository postRepository;
	@Autowired
	private IScriptRepository scriptRepository;

	@Transactional
	public void create(ContentInput contentInput, String email) {

		log.warn(contentInput.toString());
		Optional<MemberInfo> member = memberRepository.findByEmail(email);

		log.warn(member.toString());

		ContentWrapper contentWrapper = new ContentWrapper();
		contentWrapper.setMemberInfo(member.get());
		
		log.warn(contentWrapper.toString());

		contentWrapper = contentWrapperRepository.save(contentWrapper);

		log.warn(contentWrapper.toString());
		log.warn(contentInput.toString());


		switch (ContentType.findType(contentInput.getContentType())) {

		case POST:
			//@formatter:off
			ContentPost post = ContentPost.builder()
									.contentWrapper(contentWrapper)
									.ctitle(contentInput.getTitle())
									.ctext(contentInput.getText())
									.build();
			
			log.warn(post.getContentWrapper().toString());

			postRepository.save(post);
			
			
			//@formatter:on
			break;
		case SCRIPT:
			//@formatter:off
			ContentScript script = ContentScript.builder()
										.contentWrapper(contentWrapper)
										.ctext(contentInput.getText())
										.build();

			scriptRepository.save(script);
			
			//@formatter:on
			break;
		}

	}

	@Transactional
	public void delete(String cid) {

		long id = Long.parseLong(cid);

		postRepository.deleteById(id);

	}
	
	/**
	 * @author daniel
	 * read : 컨텐츠를(post or script) 읽는 메소드
	 */	
	public String read(Map<Object, Object> hm) {
		String result = "";
		switch (hm.get("ctype").toString()) {

		case "post":
			//@formatter:off
			ContentPost cp = new ContentPost();
			cp = postRepository.findByCid( Long.valueOf( (String) hm.get("cid") ) );
			result = makeHtml(cp);
			
			//@formatter:on
			break;
		case "script":
			//@formatter:off
			ContentScript cs  = new ContentScript();
			cs = scriptRepository.findByCid( Long.valueOf((String) hm.get("cid")) );
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

	public List<ContentWrapper> getContentsList(long mid) {

		MemberInfo member = MemberInfo.builder()
							.mid(mid)
							.build();
		
		List<ContentWrapper> contentList = contentWrapperRepository.findAllByMemberInfo(member);

		
		
		return null;
		
		
	}



}
