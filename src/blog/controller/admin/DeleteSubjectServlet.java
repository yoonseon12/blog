package blog.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;

@WebServlet("/admin/DeleteSubjectServlet")
public class DeleteSubjectServlet extends HttpServlet {
	private SubjectService subjectService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		System.out.println(loginMember+" << DeleteSubjectServlet.doGet() loginMember");
		if(loginMember==null||loginMember.getMemberLevel()>=10) {
			System.out.println("로그인하지 않았거나 관리자 권한없이 DeleteSubjectServlet접근. 로그인 페이지로 강제이동");
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		// request 받기
		int subjectNo = 0;
		if(request.getParameter("subjectNo")!=null) { // url로 넘기는 get방식에서 고의로 매개변수값을 지워서 들어오는것 방지
			subjectNo = Integer.parseInt(request.getParameter("subjectNo"));
		}
		System.out.println(subjectNo+" <<< DeleteSubjectServlet.doGet() subjectNo");
		// 모델
		subjectService = new SubjectService();
		int exp = subjectService.getDeleteSubject(subjectNo);
		if(exp==1) {
			// 메세지
			String msg = "삭제하려는 서브젝트에 포스팅된 글이 있습니다. 삭제할 수 없습니다.";
			request.setAttribute("deleteMsg", msg);
			// 메뉴
			List<Subject> list = subjectService.getSubjectListAll();
			request.setAttribute("subjectList", list);
			
			request.getRequestDispatcher("/WEB-INF/views/admin/selectSubjectList.jsp").forward(request, response);
			return;
		}
		System.out.println("서브젝트 삭제 완료.");
		// 뷰
		response.sendRedirect(request.getContextPath()+"/admin/SelectSubjectListServlet");
	}
}
