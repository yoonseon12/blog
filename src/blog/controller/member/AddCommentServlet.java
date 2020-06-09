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


@WebServlet("/member/AddCommentServlet")
public class AddCommentServlet extends HttpServlet {
	private CommentService commentService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 세션 확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember == null) {
			System.out.println("비 로그인 상태로 AddCommentServlet접근. 로그인페이지로 강제이동.");
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		
		//request 받기
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+" <-- AddCommentServlet.doPost() postNo");
		String memberId = loginMember.getMemberId();
		System.out.println(memberId+" <-- AddCommentServlet.doPost() memberId");
		String commentContent = request.getParameter("commentContent");
		System.out.println(commentContent+" <-- AddCommentServlet.doPost() commentContent");
		// 객체에 넣기
		Comment comment = new Comment();
		comment.setPostNo(postNo);
		comment.setMemberId(memberId);
		comment.setCommentContent(commentContent);
		
		//모델
		commentService = new CommentService();
		commentService.addComment(comment);
		System.out.println("댓글 입력 완료");
		//뷰
		response.sendRedirect(request.getContextPath()+"/SelectPostOneServlet?postNo="+postNo);
	}

}
