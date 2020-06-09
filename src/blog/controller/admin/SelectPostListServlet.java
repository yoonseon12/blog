package blog.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.PostService;
import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Post;
import blog.vo.Subject;


@WebServlet("/admin/SelectPostListServlet")
public class SelectPostListServlet extends HttpServlet {
	private SubjectService subjectService;
	private PostService postService;
	final int ROW_PER_PAGE = 10;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		System.out.println(loginMember+" << SelectPostListServlet.doGet() loginMember");
		if(loginMember==null||loginMember.getMemberLevel()>=10) {
			System.out.println("로그인하지 않았거나 관리자 권한없이 SelectPostListServlet접근. 로그인 페이지로 강제이동");
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		// 페이징 변수
		int currentPage = 1;
		if(request.getParameter("currentPage")!=null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		request.setAttribute("currentPage", currentPage);
		// 모델	
			// 메뉴
		subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
			// 포스트 리스트+페이징
		postService = new PostService();
		Map<String, Object> map = new HashMap<String, Object>();
		map = postService.getSelectPostListAll(ROW_PER_PAGE, currentPage);
		request.setAttribute("lastPage", map.get("lastPage"));
		request.setAttribute("postList", map.get("postList"));
		 
		// 뷰
		request.getRequestDispatcher("/WEB-INF/views/admin/selectPostList.jsp").forward(request, response);
	}

	

}
