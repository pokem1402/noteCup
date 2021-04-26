package com.noteCup.www.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.noteCup.www.model.ContentInput;
import com.noteCup.www.model.ContentPost;
import com.noteCup.www.model.ContentScript;
import com.noteCup.www.model.ContentType;
import com.noteCup.www.model.ContentWrapperInput;
import com.noteCup.www.model.MemberInput;
import com.noteCup.www.repository.IContentWrapperRepository;
import com.noteCup.www.repository.IMemberRepository;
import com.noteCup.www.repository.IPostRepository;
import com.noteCup.www.repository.IScriptRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@Transactional
public class ContentsMM {

	ModelAndView mav;

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
		List<MemberInput> members = mdao.findByMid("id");

		log.warn(members.toString());

		ContentWrapperInput contentWrapper = new ContentWrapperInput();
		contentWrapper.setMemberInput(members.get(0));
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

	public void delete(Map<Object, Object> hm) {
		switch ( hm.get("ctype").toString() ) {

		case "post":
			//@formatter:off
			cdao.deleteByCid(hm.get("cid"));
			//@formatter:on
			break;
		case "script":
			//@formatter:off
			sdao.deleteByCid(hm.get("cid"));
			//@formatter:on
			break;
		}
		
	}

}
