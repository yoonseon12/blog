package blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.dao.SubjectDao;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private SubjectService subjectService ;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember")!=null) {
		String currentId = ((Member)session.getAttribute("loginMember")).getMemberId();
		System.out.println(currentId+" <- HomeServlet.doGet() 로그인된 아이디");
		}else {
			System.out.println("HomeServlet에서 현재 로그인상태가 아닙니다.");
		}
		//메뉴 창 값
		subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		//뷰
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}
}
