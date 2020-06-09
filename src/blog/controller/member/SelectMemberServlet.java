package blog.controller.member;

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

@WebServlet("/member/SelectMemberServlet")
public class SelectMemberServlet extends HttpServlet {
	private MemberService memberService;
	private SubjectService subjectService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(); // 세션 객체 생성
		if(session.getAttribute("loginMember")==null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			System.out.println("로그인없이 SelectMemberServlet 접근 . 로그인 페이지로 강제이동시킴.");
			return;
		}
		// 메뉴 데이터
		subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		
		// 회원정보 데이터
		String memberId = ((Member)(session.getAttribute("loginMember"))).getMemberId(); // session.getAttribute는 object타입으로 넘어온다
		System.out.println(memberId+" <-- SelectMemberServlet.doGet() 현재 로그인된 아이디");
		memberService = new MemberService();
		Member member = memberService.getSelectMemberOne(memberId);
		request.setAttribute("member", member);
		
		// 뷰
		request.getRequestDispatcher("/WEB-INF/views/member/selectMember.jsp").forward(request, response);
	}
}
