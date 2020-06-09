package blog.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.PostService;
import blog.vo.Member;

@WebServlet("/admin/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
	private PostService postService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		System.out.println(loginMember+" << DeletePostServlet.doGet() loginMember");
		if(loginMember==null||loginMember.getMemberLevel()>=10) {
			System.out.println("로그인하지 않았거나 관리자 권한없이 DeletePostServlet접근. 로그인 페이지로 강제이동");
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		// request받기
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+" <-- /admin/DeletePostServlet.doGet() postNo");
		//모델
		postService = new PostService();
			//삭제
		postService.getDeletePost(postNo);
		//뷰
		response.sendRedirect(request.getContextPath()+"/admin/SelectPostListServlet");
	}
}
