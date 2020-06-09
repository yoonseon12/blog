 package blog.controller.admin;

import java.io.IOException;
import java.util.List;

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


@WebServlet("/admin/InsertPostServlet")
public class InsertPostServlet extends HttpServlet {
	private SubjectService subjectService;
	private PostService postService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		System.out.println(loginMember+" << InsertPostServlet.doGet() loginMember");
		if(loginMember==null||loginMember.getMemberLevel()>=10) {
			System.out.println("로그인하지 않았거나 관리자 권한없이 InsertPostServlet접근. 로그인 페이지로 강제이동");
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		// 모델
			//메뉴
		subjectService = new SubjectService();
		List<Subject> list = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", list);
		// 뷰
		request.getRequestDispatcher("/WEB-INF/views/admin/insertPost.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String subjectName = request.getParameter("subjectName");
		System.out.println(subjectName+" <-- InsertPostServlet.doPost() subjectName(입력한 서브젝트이름)");
		String postTitle = request.getParameter("postTitle");
		System.out.println(postTitle+" <-- InsertPostServlet.doPost() postTitle(입력한 제목)");
		String postContent = request.getParameter("postContent");
		System.out.println(postContent+" <-- InsertPostServlet.doPost() postContent(입력한 내용)");
		String memberId = request.getParameter("memberId");
		System.out.println(memberId+" <-- InsertPostServlet.doPost() memberId(작성자)");
		if(subjectName.equals("")||postTitle.equals("")||postContent.equals("")) {
			// 메세지
			String msg="입력하지 않은 항목이 있습니다.";
			request.setAttribute("msg", msg);
			subjectService = new SubjectService();
			// 메뉴
			List<Subject> list = subjectService.getSubjectListAll();
			request.setAttribute("subjectList", list);
			request.getRequestDispatcher("/WEB-INF/views/admin/insertPost.jsp").forward(request, response);
			return;
		}
		Post post = new Post();
		post.setSubjectName(subjectName);
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);
		post.setMemberId(memberId);
		
		postService = new PostService();
		postService.getInsertPost(post);
		
		response.sendRedirect(request.getContextPath()+"/SelectPostBySubjectServlet?subjectName="+post.getSubjectName());
	}

}
