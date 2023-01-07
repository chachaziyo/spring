package com.bs.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring.member.service.MemberService;
import com.bs.spring.member.vo.Member;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/member")
public class MemberController {

	//controller는 service에 의존한다! 
	private MemberService service;
	
	//암호화
	private BCryptPasswordEncoder passwordEncoder;
	
	
	
	@Autowired //service메소드를 이용하기 위하여!
	public MemberController(MemberService service, BCryptPasswordEncoder passwordEncoder) {
		this.service=service;
		this.passwordEncoder=passwordEncoder;
	}
//	@RequestMapping("/test/")
//	public void test() {
//		System.out.println("controller - test() 실행");
//		service.test();
//		
//	}
	
	//로그인구현
//	@RequestMapping("/loginMember.do")
//	public String login(@RequestParam String userId, @RequestParam String password,Model model) {
//		Member m = Member.builder().userId(userId).password(password).build();
//		model.addAttribute("member",m);
//		return("redirect:/demo/demo.do");
//	}
	
	//로그인구현 + 암호화 후 로그인
	@RequestMapping("/loginMember.do")
	//public String login(Member m, HttpSession session) {
	public String login(Member m, Model model) {
		//session에 데이터를 저장하고 관리
		Member loginMember = service.selectMemberById(m);
		
		//암호화된 패스워드를 원본값이랑 비교하기 위해서는
		//BCryptPasswordEncoder클래스가 제공하는 메소드를 이용해서 동등비교를 해야한다.
		//mathches("원본값",암호화값)메소드를 이용
		if(loginMember!=null && passwordEncoder.matches(m.getPassword(), loginMember.getPassword())) { //암호화후!
				//loginMember.getPassword().equals(m.getPassword())) { -> 이렇게 사용x 암호화전임!
			//로그인성공
			model.addAttribute("loginMember",loginMember);//1회용!
		}
		
		return "redirect:/";
	}
	
//	@RequestMapping("/logout.do")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "redirect:/";
//	}
	
	//위에 sessionattribute사용시 로그아웃하는법!
	@RequestMapping("/logout.do")
	public String logout(SessionStatus session) {
		if(!session.isComplete()) {//세션확인ㄴ
			session.setComplete();//session삭제함!
		}
		return "redirect:/";
	}
	
	@RequestMapping("/enrollMember.do")
	public String enrollMember() {
		return "member/enrollmember";
	}
	
//	@RequestMapping("/enrollMemberEnd.do")
//	public String enrollMemberEnd(Member m) {
//		int result = service.enrollMemberEnd(m);
//		
//		return "redirect:/";
//		
//		
//	}
	
	//회원가입 + password암호화처리
	@RequestMapping("/enrollMemberEnd.do")
	public ModelAndView enrollMemberEnd(Member m, ModelAndView mv) {
		System.out.println(m);
		
		//암호화처리하기!단방향!
		String encodePassword = passwordEncoder.encode(m.getPassword());
		m.setPassword(encodePassword);
		
		int result = service.insertMember(m);
		if(result>0) {
			mv.addObject("msg", "회원가입 완료");
			mv.addObject("loc","/");
		}else {
			mv.addObject("msg", "회원가입 실패");
			mv.addObject("loc","/member/enrollMember.do");
		}
		mv.setViewName("common/msg");
		return mv;
	}
	
//	@RequestMapping("/memberList.do")
//	public String memberView(String userId,Model m) {
//		Member member = service.memberList(userId);
//		m.addAttribute("member", member);
//		return "member/enrollmember";
//		
//	}
	//상세화면구현
	@RequestMapping("/memberList.do")
	public String memberView(Member m , Model model) {
		Member viewMember = service.selectMemberById(m);
		model.addAttribute("member", viewMember);
		return "member/memberView";
		
	}
	
}
