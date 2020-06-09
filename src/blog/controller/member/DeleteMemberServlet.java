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
import blog.dao.MemberIdDao;
import blog.service.MemberService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;

@WebServlet("/member/DeleteMemberServlet")
public class DeleteMemberServlet extends HttpServlet {
	private MemberService memberService;
	private SubjectService subjectService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")==null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			System.out.println("로그인없이 DeleteMemberServlet 접근. 로그인 페이지로 강제이동시킴.");
			return;
		}
		String MemberId = ((Member)session.getAttribute("loginMember")).getMemberId();
		System.out.println(MemberId+" <-- DeleteMemberServlet 현재 로그인중인 아이디");
		//메뉴(Subject)
		subjectService = new SubjectService();
		List<Subject> list = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", list);
		request.getRequestDispatcher("/WEB-INF/views/member/deleteMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+" <- DeleteMemberServlet.doPost() memberId");
		String memberPw = request.getParameter("memberPw");
		System.out.println(memberPw+" <- DeleteMemberServlet.doPost() memberPw(입력한 비밀번호)");
		memberService = new MemberService();
		Member DBPw = memberService.getSelectMemberOne(memberId, memberPw);
		System.out.println(DBPw+" <- DeleteMemberServlet.doPost() DBPw");
		if(DBPw==null) {
			//메뉴
			subjectService = new SubjectService();
			List<Subject> list = subjectService.getSubjectListAll();
			request.setAttribute("subjectList", list);
			//출력문구
			String msg="비밀번호를 확인해주세요.";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/views/member/deleteMember.jsp").forward(request, response);
			return;
		}
		Member member = (Member)request.getSession().getAttribute("loginMember");
		System.out.println(member.getMemberId()+" <- DeleteMemberServlet.doPost() member.getMemberId()");
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		memberService.removeMember(member);
		HttpSession session = request.getSession();
		session.invalidate(); // 저장된 세션 삭제
		response.sendRedirect(request.getContextPath()+"/LoginServlet");
	}
}
