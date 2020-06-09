package blog.controller.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.CommentService;
import blog.service.LikeyService;
import blog.service.PostService;
import blog.service.SubjectService;
import blog.vo.Comment;
import blog.vo.Member;
import blog.vo.Post;
import blog.vo.Subject;


@WebServlet("/member/UpdateCommentServlet")
public class UpdateCommentServlet extends HttpServlet {
	private CommentService commentService;
	private SubjectService subjectService;
	private PostService postService;
	private LikeyService likeyService;
	final int ROW_PER_PAGE=5;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		System.out.println(loginMember+" << UpdateCommentServlet.doGet() loginMember");
		if(loginMember==null||loginMember.getMemberLevel()>=10) {
			System.out.println("로그인하지 않았거나 관리자 권한없이 UpdateCommentServlet접근. 로그인 페이지로 강제이동");
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		//requset 받기
		int postNo = Integer.parseInt(request.getParameter("postNo")); // 삭제 후 포스트 글 페이지로 돌아가기위해 받아옴
		System.out.println(postNo+" <-- UpdateCommentServlet.doGet postNo(포스트 글 번호)");
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		System.out.println(commentNo+" <-- UpdateCommentServlet.doGet commentNo(수정하려는 댓글 번호)");
		
		//모델
			// 서브젝트목록(메뉴창출력)
		subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
			// 포스트내용
		postService = new PostService();
		Post post = postService.getPostOne(postNo);
		request.setAttribute("post", post);
			// 수정할 댓글
		commentService = new CommentService();
		Comment comment = commentService.getSelectCommentOne(commentNo);
		System.out.println(comment.getCommentContent()+"<<<<");
		request.setAttribute("comment", comment);
			// 좋아요 count
		likeyService = new LikeyService();
		Map<String, Integer> likeyMap = new HashMap<String, Integer>();
		likeyMap = likeyService.getLikeyCount(postNo);
		request.setAttribute("likeyMap", likeyMap);
		//뷰
		request.getRequestDispatcher("/WEB-INF/views/member/updateComment.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//request 받기
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+" <-- UpdateCommentServlet.doPost postNo(수정한 댓글의 포스트 번호)");
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		System.out.println(commentNo+" <-- UpdateCommentServlet.doPost commentNo(수정한 댓글 번호)");
		String commentContent = request.getParameter("commentContent");
		System.out.println(commentContent+" <-- UpdateCommentServlet.doPost commentContent(수정한 댓글 내용)");
		
		Comment comment = new Comment();
		comment.setCommentContent(commentContent);
		comment.setCommentNo(commentNo);
		//모델
		commentService = new CommentService();
		commentService.getUpdateComment(comment);
		//뷰
		response.sendRedirect(request.getContextPath()+"/SelectPostOneServlet?postNo="+postNo);
	}

}
