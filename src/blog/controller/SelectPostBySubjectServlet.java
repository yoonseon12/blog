package blog.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.service.PostService;
import blog.service.SubjectService;
import blog.vo.Post;
import blog.vo.Subject;


@WebServlet("/SelectPostBySubjectServlet")
public class SelectPostBySubjectServlet extends HttpServlet {
	private SubjectService subjectService;
	private PostService postService;
	final int ROW_PER_PAGE = 10;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request받기
		String subjectName = request.getParameter("subjectName");
		System.out.println(subjectName+" <-- SelectPostBySubjectServlet.doGet() subjectName(선택한 서브젝트)");
		request.setAttribute("subjectName", subjectName);
		subjectService = new SubjectService();
		// 모델
			// 메뉴
		List<Subject> subjectList= subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
		postService = new PostService();
			// 포스트 글 목록 + 페이징
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		System.out.println(currentPage+" <- currentPage");
		request.setAttribute("currentPage", currentPage);
		
		Map<String, Object> map = postService.getPostBySubject(subjectName, currentPage, ROW_PER_PAGE);
		request.setAttribute("postList", map.get("postList"));
		request.setAttribute("lastPage", map.get("lastPage"));
		// 뷰
		request.getRequestDispatcher("/WEB-INF/views/selectPostBySubject.jsp").forward(request, response);
	}
}
