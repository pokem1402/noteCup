package com.noteCup.www.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	public String makeHtml(ContentPost cp) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h2>" + cp.getCtitle() + "</h2>");
		sb.append("<h3>" + cp.getContentWrapper().getMemberInput().getMid() + "</h3>");
		sb.append("<article>" + cp.getCtext() + "</article>");

		return sb.toString();
	}

	public String makeHtml(ContentScript cs) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>" + cs.getContentWrapper().getMemberInput().getMid() + "</h3>");
		sb.append("<article>" + cs.getCtext() + "</article>");
		return sb.toString();
	}

}
