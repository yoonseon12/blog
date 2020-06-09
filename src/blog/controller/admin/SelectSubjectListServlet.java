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

@WebServlet("/admin/SelectSubjectListServlet")
public class SelectSubjectListServlet extends HttpServlet {
	private SubjectService subjectService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		System.out.println(loginMember+" << SelectSubjectListServlet.doGet() loginMember");
		if(loginMember==null||loginMember.getMemberLevel()>=10) {
			System.out.println("로그인하지 않았거나 관리자 권한없이 SelectSubjectListServlet접근. 로그인 페이지로 강제이동");
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		// 모델
		subjectService = new SubjectService();
		List<Subject> list = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", list);
		// 뷰
		request.getRequestDispatcher("/WEB-INF/views/admin/selectSubjectList.jsp").forward(request, response);
	}

}
