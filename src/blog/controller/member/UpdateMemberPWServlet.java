package blog.controller.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.MemberIdService;
import blog.service.MemberService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;


@WebServlet("/member/UpdateMemberPWServlet")
public class UpdateMemberPWServlet extends HttpServlet {
	private SubjectService subjectService;
	private MemberService memberService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//로그인없이 접속시 로그인페이지로 강제이동
		if(session.getAttribute("loginMember")==null) {
			System.out.println("로그인없이 UpdateMemberPWServlet접근. 로그인페이지로 강제이동시킴.");
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		//메뉴데이터
		subjectService = new SubjectService();
		List<Subject> list = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", list);
		request.getRequestDispatcher("/WEB-INF/views/member/updateMemberPW.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+" <-- UpdateMemberPWServlet.doPost() memberId");
		String memberPw1 = request.getParameter("memberPw1");
		System.out.println(memberPw1+" <-- UpdateMemberPWServlet.doPost() memberPw1(입력한 비밀번호)");
		String memberPw2 = request.getParameter("memberPw2");
		System.out.println(memberPw2+" <-- UpdateMemberPWServlet.doPost() memberPw2(입력한 확인 비밀번호)");
		// 입력한 비밀번호와 확인 비밀번호 일치 유무
		if(memberPw1.equals(memberPw2)==false) {
			System.out.println("비밀번호 불일치");
			//메세지
			String msg="비밀번호를 확인해주세요.";
			request.setAttribute("msg", msg);
			//메뉴
			subjectService = new SubjectService();
			List<Subject> list = subjectService.getSubjectListAll();
			request.setAttribute("subjectList", list);
			request.getRequestDispatcher("/WEB-INF/views/member/updateMemberPW.jsp").forward(request, response);
			return;
		}
		System.out.println("비밀번호 일치");
		Member member = new Member();	
		member.setMemberId(memberId);
		// 변경할 비밀번호와 현재 비밀번호 일치 유무
		memberService = new MemberService();
		Member dbPw = memberService.getSelectMemberPw(member);
		System.out.println(dbPw.getMemberPw()+" <-- UpdateMemberPWServlet.doPost() db안에 저장된 비밀번호");
		String dbPw2 = (dbPw.getMemberPw()).toString();
		if(dbPw2.equals(memberPw1)) {
			System.out.println("변경할 비밀번호와 현재 비밀번호 같음");
			//메세지
			String msg = "현재비밀번호와 같습니다. 새로운 비밀번호를 입력해주세요.";
			request.setAttribute("msg", msg);
			//메뉴값
			this.subjectService = new SubjectService();
			List<Subject> list = subjectService.getSubjectListAll();
			request.setAttribute("subjectList", list);
			//뷰
			request.getRequestDispatcher("/WEB-INF/views/member/updateMemberPW.jsp").forward(request, response);
			return;
		}
		member.setMemberPw(memberPw1);
		memberService.getUpdateMemberPw(member);
		System.out.println("수정완료");
		HttpSession session = request.getSession();
		session.invalidate(); // 세션 초기화
		//로그인창으로
		response.sendRedirect(request.getContextPath()+"/LoginServlet");
		
	}

}
