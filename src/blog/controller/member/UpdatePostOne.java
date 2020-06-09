package blog.controller.member;

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


@WebServlet("/member/UpdatePostOneServlet")
public class UpdatePostOne extends HttpServlet {
	private PostService postService;
	private SubjectService subjectService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember==null) {
			System.out.println("비 로그인 상태로 UpdatePostOne접근. 로그인페이지로 강제이동.");
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		// request받기
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+" <- UpdatePostOneServlet.doGet postNo(수정하려는 포스트의 번호)");
		String subjectName = request.getParameter("subjectName");
		System.out.println(subjectName+" <- UpdatePostOneServlet.doGet subjectName(수정하려는 포스트의 서브젝트이름)");
		// 모델
			// 서브젝트 목록 (메뉴)
		subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
			// 수정할  post 정보
		postService = new PostService();
		Post post = postService.getPostOne(postNo);
		request.setAttribute("post", post);
		request.getRequestDispatcher("/WEB-INF/views/member/updatePostOne.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 입력 폼에서  requset 받기
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+" <- UpdatePostOneServlet.doPost postNo(수정요청한 포스트의 번호)");
		String subjectName = request.getParameter("subjectName");
		System.out.println(subjectName+" <- UpdatePostOneServlet.doPost subjectName(수정한 포스트의 서브젝트이름)");
		String postTitle = request.getParameter("postTitle");
		System.out.println(postTitle+" <- UpdatePostOneServlet.doPost postTitle(수정한 포스트의 제목)");
		String postContent = request.getParameter("postContent");
		System.out.println(postContent+" <- UpdatePostOneServlet.doPost subjectName(수정한 포스트의 내용)");
		
		Post post = new Post();
		post.setPostNo(postNo);
		post.setSubjectName(subjectName);
		post.setPostTitle(postTitle);
		post.setPostContent(postContent);
		
		//모델
		postService.getUpdatePost(post);
		
		//뷰
		response.sendRedirect(request.getContextPath()+"/SelectPostOneServlet?postNo="+postNo);
	}

}
