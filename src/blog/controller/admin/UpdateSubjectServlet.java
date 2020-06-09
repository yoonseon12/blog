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

//시간남으면 c:if 문으로 데이터 없을때 데이터없다고 표시하는 기능 추가하기
//이미 있는 이름이면 못쓰게.(insertSubject꺼 가져오고 굳이 똑같은거 또 만들지 말기)
@WebServlet("/admin/UpdateSubjectServlet")
public class UpdateSubjectServlet extends HttpServlet {
	private SubjectService subjectService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션확인
		HttpSession session = request.getSession();
		Member loginMember = (Member)session.getAttribute("loginMember");
		System.out.println(loginMember+" << UpdateSubjectServlet.doGet() loginMember");
		if(loginMember==null||loginMember.getMemberLevel()>=10) {
			System.out.println("로그인하지 않았거나 관리자 권한없이 UpdateSubjectServlet접근. 로그인 페이지로 강제이동");
			response.sendRedirect(request.getContextPath()+"/LoginServlet");
			return;
		}
		// 페이징변수
		int subjectNo = 0;
		if(request.getParameter("subjectNo")!=null) { //url로 넘기는 get방식에서 고의로 매개변수값을 지워서 들어오는것 방지
			subjectNo = Integer.parseInt(request.getParameter("subjectNo"));
		}
		System.out.println(subjectNo+" <<< UpdateSubjectServlet.doGet() subjectNo");
		// 모델
		Subject subject = new Subject();
		subjectService = new SubjectService();
			// 선택한 서브젝트no로 서브젝트이름 찾아서 출력
		subject = subjectService.getSelectSubjectNameOne(subjectNo);
		request.setAttribute("subject", subject);
			// 메뉴
		List<Subject> list = subjectService.getSubjectListAll();
		request.setAttribute("subjectList", list);
		// 뷰
		request.getRequestDispatcher("/WEB-INF/views/admin/updateSubject.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// request 받기
		String subjectName = request.getParameter("subjectName");
		System.out.println(subjectName+" <-- UpdateSubjectServlet.doPost() subjectName");
		String newSubjectName = request.getParameter("newSubjectName");
		System.out.println(newSubjectName+" <-- UpdateSubjectServlet.doPost() newSubjectName");
		// 모델
		subjectService = new SubjectService();
		int Exp = subjectService.getUpdateSubject(subjectName, newSubjectName);
		System.out.println(Exp+" <<??");
		if(Exp==1) {
			// 메세지
			String msg =subjectName+" 서브젝트에 포스팅된 글이 있습니다. 삭제할 수 없습니다.";
			request.setAttribute("msg", msg);
			// 메뉴
			List<Subject> list = subjectService.getSubjectListAll();
			request.setAttribute("subjectList", list);
			//선택한 서브젝트 이름
			request.setAttribute("subjectName", subjectName);
			request.getRequestDispatcher("/WEB-INF/views/admin/updateSubject.jsp").forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath()+"/admin/SelectSubjectListServlet");
	}

}
