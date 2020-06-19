package blog.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.service.SubjectService;
import blog.vo.Member;
import blog.vo.Subject;


@WebServlet("/admin/InsertSubjectServlet")
public class InsertSubjectServlet extends HttpServlet {
	private SubjectService subjectService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		System.out.println(loginMember+" << InsertSubjectServlet.doGet() loginMember");
		if(loginMember==null||loginMember.getMemberLevel()>=10) {
			System.out.println("로그인하지 않았거나 관리자 권한없이 InsertSubjectServlet접근. 로그인 페이지로 강제이동");
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		//입력 request 받기
		String subjectName = request.getParameter("subjectName");
		System.out.println(subjectName+" <-- InsertSubjectServlet.doPost() subjectName(입력한 서브젝트 이름)");
		
		//모델
		Subject subject = new Subject();
			//서브젝트 이름 중복 확인
		subjectService = new SubjectService();
		subject.setSubjectName(subjectName);
		boolean flag = subjectService.getselectSubjectName(subject);
		System.out.println(flag+" <-- InsertSubjectServlet.doPost() flag boolean 값 ");
		if(!flag) {
			//메뉴값
			subjectService = new SubjectService();
			List<Subject> list = subjectService.getSubjectListAll();
			request.setAttribute("subjectList", list);
			//메세지
			String msg = "이미 있는 서브젝트이름입니다. 다른 이름을 입력해주세요.";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("/WEB-INF/views/admin/selectSubjectList.jsp").forward(request, response);
			return;
		}
			//추가
		subjectService.getInsertSubject(subject);
		System.out.println("서브젝트 이름 추가 완료");
		// 뷰
		response.sendRedirect(request.getContextPath()+"/admin/SelectSubjectListServlet");
	}

}
