package blog.controller.member;

import java.io.IOException;	
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.LikeyService;
import blog.vo.Likey;
import blog.vo.Member;


@WebServlet("/member/AddLikeyServlet")
public class AddLikeyServlet extends HttpServlet {
	private LikeyService likeyService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		if(loginMember==null) {
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			System.out.println("로그인없이 AddLikeyServlet 접근 . 로그인 페이지로 강제이동시킴.");
			return;
		}
		//request받기
		int likeyCk = Integer.parseInt(request.getParameter("likeyCk")); // 1이면 좋아요 0이면 싫어요
		System.out.println(likeyCk+" <-- AddLikeyServlet.doGet() likeyCk 1이면 좋아요클릭, 0이면 싫어요클릭");
		int postNo = Integer.parseInt(request.getParameter("postNo"));
		System.out.println(postNo+" <-- AddLikeyServlet.doGet() postNo");
		String memberId = loginMember.getMemberId();
		System.out.println(memberId+" <-- AddLikeyServlet.doGet() memberId");
		
		Likey likey = new Likey();
		likey.setPostNo(postNo);
		likey.setMemberId(memberId);
		likey.setLikeyCk(likeyCk);
		//모델
		likeyService = new LikeyService();
		likeyService.addLikey(likey);
		
		//뷰
		response.sendRedirect(request.getContextPath()+"/SelectPostOneServlet?postNo="+postNo);
	}


}
