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
import blog.service.MemberService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private SubjectService subjectService;
	private MemberService memberService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 로그인 상태라면 홈으로 보낸다.
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+" <- LoginServIet.doPost() memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw+" <- LoginServIet.doPost() memberPw");
		//모델
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		memberService = new MemberService();
		Member returnMember = memberService.getLogin(memberId, memberPw);
		System.out.println(returnMember+" <- LoginServlet 로그인 리턴값 확인");
		if(returnMember == null){
			System.out.println("로그인 실패");
			String msg = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
			request.setAttribute("msg", msg);
			subjectService = new SubjectService();
			List<Subject> subjectList = subjectService.getSubjectListAll();
			request.setAttribute("subjectList", subjectList);
			//뷰
			request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
			return;
		}
		System.out.println("로그인 성공");
		HttpSession session = request.getSession(); // 세션을 담을 객체생성
		session.setAttribute("loginMember", returnMember); //세션등록
		//뷰
		response.sendRedirect(request.getContextPath()+"/HomeServlet");
	}

}
