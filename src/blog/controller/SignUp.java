package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.dao.MemberDao;
import blog.dao.SubjectDao;
import blog.service.MemberIdService;
import blog.service.MemberService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private SubjectService subjectService;
	private MemberService memberService;
	private MemberIdService memberIdService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member sessionId = (Member)session.getAttribute("loginMember");
		if(sessionId != null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		request.getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
	}
	// 혼자 생각한대로 먼저 해본 것 + 수업내용
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// request 받기
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+" <- InsertMemberServlet.doPost() memberId");
		String memberPw1 = request.getParameter("memberPw1");
		System.out.println(memberPw1+" <- InsertMemberServlet.doPost() memberPw1");
		String memberPw2 = request.getParameter("memberPw2");
		System.out.println(memberPw2+" <- InsertMemberServlet.doPost() memberPw2");
		System.out.println(memberPw1.equals(memberPw2)+" <- memberPw1, memberPw2 비교 boolean값");
		// 비밀번호 비교
		if(!memberPw1.equals(memberPw2)) {
			System.out.println("비밀번호 불일치");
			subjectService = new SubjectService();
			List<Subject> subjectList = subjectService.getSubjectListAll();
			request.setAttribute("subjectList", subjectList);
			String msg ="비밀번호가 일치하지 않습니다.";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
			return;
		}
		//request로 받은값을  member에 넣기
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw1);
		//사용할 수 있는 아이디인지 비교
		memberService = new MemberService();
		boolean flag = this.memberService.AddMember(member);
		if(!flag) {
			System.out.println("회원가입 할 수 없는 아이디");
			//메세지
			String IdMsg = "가입 할 수 없는 아이디입니다. 새로운 아이디를 입력하세요.";
			request.setAttribute("IdMsg", IdMsg);
			//메뉴
			subjectService = new SubjectService();
			List<Subject> list = subjectService.getSubjectListAll();
			request.setAttribute("subjectList", list);
			request.getRequestDispatcher("/WEB-INF/views/signUp.jsp").forward(request, response);
			return;
		}
		
		System.out.println("회원가입 완료");
		response.sendRedirect(request.getContextPath()+"/LoginServlet");
	}
	/*
	//수업 내용
	protected void doPost2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember != null) {
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+" <- InsertMemberServlet.doPost() memberId");
		String memberPw1 = request.getParameter("memberPw1");
		System.out.println(memberPw1+" <- InsertMemberServlet.doPost() memberPw1");
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw1);
		this.memberService = new MemberService(); // DI를 배우면 나중에 이코드는 안쓴다.
		boolean flag = memberService.AddMember(member);
		if(flag) { //성공하면 로그인 창으로
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
		} else { // 실패하면 회원가입 창으로
			response.sendRedirect(request.getContextPath()+"/InsertMemberServlet");
		}
	}
	//강의 듣기전 나혼자 만든 메서드
	protected void doPost3(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// request 받기
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+" <- InsertMemberServlet.doPost() memberId");
		String memberPw1 = request.getParameter("memberPw1");
		System.out.println(memberPw1+" <- InsertMemberServlet.doPost() memberPw1");
		String memberPw2 = request.getParameter("memberPw2");
		System.out.println(memberPw2+" <- InsertMemberServlet.doPost() memberPw2");
		int memberLevel = Integer.parseInt(request.getParameter("memberLevel"));
		System.out.println(memberLevel+" <- InsertMemberServlet.doPost() memberLevel");
		System.out.println(memberPw1.equals(memberPw2)+" <- memberPw1, memberPw2 비교 boolean값");
		//request로 받은값을  member에 넣기
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw1);
		member.setMemberLevel(memberLevel);
		//사용할 수 있는 아이디인지 비교
		memberService = new MemberService();
		int selectMemberCount = memberService.getSelectMemberCount(member);
		System.out.println(selectMemberCount+" <- 회원가입 member테이블(현재 가입중인 아이디)영향받은 아이디");
		memberIdService = new MemberIdService();
		memberIdService.selectMemberIdCount(member);
		int selectMemberIdCount = memberIdService.selectMemberIdCount(member);
		System.out.println(selectMemberIdCount+" <- 회원가입 memberId(탈퇴한 아이디)테이블 영향받은 아이디");
		if((selectMemberCount==1 || selectMemberIdCount==1)) {
			System.out.println("회원가입 할 수 없는 아이디");
			//메세지
			String IdMsg = "가입 할 수 없는 아이디입니다. 새로운 아이디를 입력하세요.";
			request.setAttribute("IdMsg", IdMsg);
			//메뉴
			subjectService = new SubjectService();
			List<Subject> list = subjectService.getSubjectListAll();
			request.setAttribute("subjectList", list);
			request.getRequestDispatcher("/WEB-INF/views/insertMember.jsp").forward(request, response);
			return;
		}
		// 비밀번호 비교
		if(memberPw1.equals(memberPw2)==false) {
			System.out.println("비밀번호 불일치");
			subjectService = new SubjectService();
			List<Subject> subjectList = subjectService.getSubjectListAll();
			request.setAttribute("subjectList", subjectList);
			String msg ="비밀번호가 일치하지 않습니다.";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/views/insertMember.jsp").forward(request, response);
			return;
		}
		System.out.println("비밀번호 일치");
		// 회원 등록
		memberService.getInsertMember(member);
		System.out.println("회원가입 완료");
		response.sendRedirect(request.getContextPath()+"/LoginServlet");
	}
	*/
}
