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
		sb.append("<h3>" + cp.getContentWrapper().getMemberInfo().getNickname() + "</h3>");
		sb.append("<article>" + cp.getCtext() + "</article>");

		return sb.toString();
	}

	public String makeHtml(ContentScript cs) {
		StringBuilder sb = new StringBuilder();
		sb.append("<h3>" + cs.getContentWrapper().getMemberInfo().getNickname() + "</h3>");
		sb.append("<article>" + cs.getCtext() + "</article>");
		return sb.toString();
	}

	public void getContentList(ModelAndView mav, String nickname) {
		MemberInfo mi = mdao.findByNickname(nickname);
		Long mid = mi.getMid();
		//List <ContentWrapper> cwList = crdao.findByMid(mid);
		List <ContentPost> cpList;
		List <ContentScript> csList;
		
		/*for(ContentWrapper cw : cwList ) {//cdao post , sdao script
			//cpList = cdao.findByCid(cw.getCid());
			//csList = sdao.findByCid(cw.getCid());
		}*/
		
		//mav.addObject("cpList",cpList);
			
	}

}
