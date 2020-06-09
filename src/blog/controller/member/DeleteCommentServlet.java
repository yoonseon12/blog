package blog.controller.member;

import java.io.IOException;	
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.CommentService;
import blog.vo.Comment;
import blog.vo.Member;

@WebServlet("/member/DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
	private CommentService commentService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		System.out.println(loginMember+" << DeleteCommentServlet.doGet() loginMember");
		if(loginMember==null||loginMember.getMemberLevel()>=10) {
			System.out.println("로그인하지 않았거나 관리자 권한없이 DeleteCommentServlet접근. 로그인 페이지로 강제이동");
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		//requset 받기
		int postNo = Integer.parseInt(request.getParameter("postNo")); // 삭제 후 포스트 글 페이지로 돌아가기위해 받아옴
		System.out.println(postNo+" <-- DeleteCommentServlet.doDet postNo(포스트 글 번호)");
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		System.out.println(commentNo+" <-- DeleteCommentServlet.doDet commentNo(삭제하려는 댓글 번호)");
		
		//모델
		commentService = new CommentService();
		commentService.getDeleteComment(commentNo);
		System.out.println(commentNo+"번의 댓글 삭제 완료");
		//뷰
		response.sendRedirect(request.getContextPath()+"/SelectPostOneServlet?postNo="+postNo);
	}

}
