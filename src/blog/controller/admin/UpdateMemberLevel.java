package blog.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.MemberService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;

@WebServlet("/admin/UpdateMemberLevel")
public class UpdateMemberLevel extends HttpServlet {
	private SubjectService subjectService;
	private MemberService memberService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		System.out.println(loginMember+" << UpdateMemberLevel.doGet() loginMember");
		if(loginMember==null||loginMember.getMemberLevel()>=10) {
			System.out.println("로그인하지 않았거나 관리자 권한없이 UpdateMemberLevel접근. 로그인 페이지로 강제이동");
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		//request받기
		String memberId= request.getParameter("memberId");
		System.out.println(memberId+" <-- UpdateMemberLevel.doGet() memberId");
		int memberLevel = Integer.parseInt(request.getParameter("memberLevel"));
		System.out.println(memberLevel+" <-- UpdateMemberLevel.doGet() memberLevel");
		if(memberLevel==0) {
			System.out.println("운영자의 레벨은 변경할 수 없습니다.");
			response.sendRedirect(request.getContextPath()+"/admin/SelectMemberListServlet?msg=1");
			return;
		}
		// 모델	
			// 메뉴
		subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
			// 선택한 멤버정보
		memberService = new MemberService();
		Member member = memberService.getSelectMemberOne(memberId);
		request.setAttribute("member", member);
		// 뷰
		request.getRequestDispatcher("/WEB-INF/views/admin/updateMemberLevel.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//request 받기
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+" <-- UpdateMemberLevel.doPost() memberId");
		int memberLevel = Integer.parseInt(request.getParameter("memberLevel"));
		System.out.println(memberLevel+" <-- UpdateMemberLevel.doPost() memberLevel");
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberLevel(memberLevel);
		// 모델
		memberService = new MemberService();
		memberService.getUpdateMemberLevel(member);
		System.out.println("레벨 변경 완료");
		//뷰
		response.sendRedirect(request.getContextPath()+"/admin/SelectMemberListServlet?currentPage=1");
	}

}
