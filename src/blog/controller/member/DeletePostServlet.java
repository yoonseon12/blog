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
import blog.vo.Subject;

@WebServlet("/member/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
	public PostService postService;
	public SubjectService subjectService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember==null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			System.out.println("로그인없이 DeletePostServlet 접근 . 로그인 페이지로 강제이동시킴.");
			return;
		}
		//request 받기
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+" <-- member/DeletePostServlet.doGet() postNo(삭제하려는 포스트글 번호)");
		String subjectName = request.getParameter("subjectName");
		System.out.println(subjectName+" <-- member/DeletePostServlet.doGet() subjectName(삭제하려는 포스트글의 서브젝트이름)");
		//모델
			//서브젝트 목록
		subjectService = new SubjectService();
			//포스트 삭제
		postService = new PostService();
		postService.getDeletePost(postNo);
		response.sendRedirect(request.getContextPath()+"/SelectPostBySubjectServlet?subjectName="+subjectName);
	}
}
