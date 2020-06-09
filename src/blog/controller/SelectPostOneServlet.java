package blog.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.service.CommentService;
import blog.service.LikeyService;
import blog.service.PostService;
import blog.service.SubjectService;
import blog.vo.Comment;
import blog.vo.Post;
import blog.vo.Subject;

@WebServlet("/SelectPostOneServlet")
public class SelectPostOneServlet extends HttpServlet {
	private SubjectService subjectService;
	private PostService postService;
	private CommentService commentService;
	private LikeyService likeyService;
	final int ROW_PER_PAGE=5;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// requset 받기
		if(request.getParameter("postNo")==null) { // 선택한 항목 없이 URL주소로 접근하면 강제로 홈으로 이동시킴
			System.out.println("선택한 항목 없이 URL주소로 접근. 홈으로 강제이동"); 
			response.sendRedirect(request.getContextPath()+"/HomeServlet");
			return;
		}
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+" <--SelectPostOneServlet.doGot() postNo");
		//모델
			// 서브젝트목록(메뉴창출력)
		subjectService = new SubjectService();
		List<Subject> subjectList = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", subjectList);
			// 포스트내용
		postService = new PostService();
		Post post = postService.getPostOne(postNo);
		/*
		System.out.println(post.getPostNo()+"<<< getPostNo");
		System.out.println(post.getMemberId()+"<<< getMemberId");
		System.out.println(post.getSubjectName()+"<<< getSubjectName");
		System.out.println(post.getPostTitle()+"<<< getPostTitle");
		System.out.println(post.getPostContent()+"<<< getPostContent");
		System.out.println(post.getPostDate()+"<<< getPostDate");
		*/
		request.setAttribute("post", post);
			// 댓글 목록
		int commentCurrentPage=1;
		if(request.getParameter("commentCurrentPage")!=null) {
			System.out.println(request.getParameter("commentCurrentPage")+"<번째 댓글 페이지");
			commentCurrentPage = Integer.parseInt(request.getParameter("commentCurrentPage"));
		}
		request.setAttribute("commentCurrentPage", commentCurrentPage);
		commentService = new CommentService();
		Map<String, Object> map = commentService.getCommentListByPostNo(postNo, commentCurrentPage, ROW_PER_PAGE);
		request.setAttribute("commentList", map.get("list"));
		request.setAttribute("lastPage", map.get("lastPage"));
		request.setAttribute("count", map.get("count"));
			// 좋아요&싫어요  count
		likeyService = new LikeyService();
		Map<String, Integer> likeyMap = new HashMap<String, Integer>();
		likeyMap = likeyService.getLikeyCount(postNo);
		System.out.println(likeyMap.get("badCount")+" <-- bad");
		System.out.println(likeyMap.get("goodCount")+" <-- good");
		request.setAttribute("likeyMap", likeyMap);
		if(request.getParameter("likey")!=null) {
			String msg="좋아요는 한번만 하실 수 있습니다.";
			request.setAttribute("msg", msg);
		}
		// 뷰 
		request.getRequestDispatcher("/WEB-INF/views/selectPostOne.jsp").forward(request, response); // 서버안에서 requset, response 클라이언트에서
	}
}
