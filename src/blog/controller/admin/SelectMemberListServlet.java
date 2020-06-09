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


@WebServlet("/admin/SelectMemberListServlet")
public class SelectMemberListServlet extends HttpServlet {
	private SubjectService subjectService;
	private MemberService memberService;
	private final int ROW_PER_PAGE = 5;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null || loginMember.getMemberLevel()>=10) { // or연산자는 앞에 조건이 참이면 뒤에조건을 보지 않는다.
			System.out.println("로그인하지 않았거나 관리자 권한없이 SelectMemberListServlet접근. 로그인 페이지로 강제이동");
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		if(request.getParameter("msg")!=null) {
			String msg = "운영자의 레벨은 변경할 수 없습니다.";
			request.setAttribute("msg", msg);
		}
		// 페이징 변수
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage =  Integer.parseInt(request.getParameter("currentPage"));
		}
		request.setAttribute("currentPage", currentPage);
		// 모델
			// 메뉴
		subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
			// 멤버 리스트
		memberService = new MemberService();
		Map<String, Object> map = memberService.getMemberList(currentPage, ROW_PER_PAGE); // 두개의 값을 리턴하기위해 HashMap 사용
		request.setAttribute("memberList", map.get("list"));
		request.setAttribute("lastPage", map.get("lastPage"));
		// 뷰
		request.getRequestDispatcher("/WEB-INF/views/admin/selectMemberList.jsp").forward(request, response);
	}
}
