package blog.service;

import java.sql.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;


import blog.commons.DBUtil;
import blog.dao.CommentDao;
import blog.dao.LikeyDao;
import blog.dao.MemberDao;
import blog.dao.MemberIdDao;
import blog.dao.PostDao;
import blog.vo.Comment;
import blog.vo.Member;

public class MemberService {
	private MemberDao memberDao;
	private MemberIdDao memberIdDao;
	//로그인
	public Member getLogin(String memberId, String memberPw) {
		memberDao = new MemberDao();
		Member returnMember = memberDao.login(memberId, memberPw);
		return returnMember;
	}
	//회원정보
	public Member getSelectMemberOne(String memberId) {
		memberDao = new MemberDao();
		Member member = new Member();
		try {
			member = memberDao.selectMemberOne(memberId);
		} catch (Exception e) {
			System.out.println("MemberService.getSelectMemberOne() 예외발생.");
			e.printStackTrace();
		}
		return member;
	}
	//비밀번호 변경
	public void getUpdateMemberPw(Member member) {
		memberDao = new MemberDao();
		try {
			memberDao.updateMemberPw(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//비밀번호 변경할때  입력한 비밀번호와 DB안의 비밀번호 비교하기위해 DB안 비밀번호를 확인하는 메서드(회원탈퇴와 달리 member에 담아서 해보았다)
	public Member getSelectMemberPw(Member member) {
		memberDao = new MemberDao();
		Member dbPw = new Member();
		try {
			dbPw = memberDao.SelectMemberPw(member);
		} catch (Exception e) {
			System.out.println("MemberService.getSelectMemberPw() 예외발생.");
			e.printStackTrace();
		}
		return dbPw;
	}
	//회원탈퇴
	public void removeMember(Member member){
		System.out.println("removeMember");
		Connection conn = null;
		try {
			conn = DBUtil.getConection();
			conn.setAutoCommit(false); // 기본값이 true이지만 커밋을 막기위해 false
				// 삭제하려는 멤버 댓글삭제
			CommentDao commentDao = new CommentDao();
			commentDao.deleteMemberByPostByComment(conn, member.getMemberId());
				// 삭제하려는 멤버 좋아요삭제
			LikeyDao likeyDao = new LikeyDao();
			likeyDao.deleteMemberByPostByLikey(conn, member.getMemberId());
				// 삭제하려는 멤버 포스트 글삭제
			PostDao postDao = new PostDao();
			postDao.deleteMemberByPost(conn, member.getMemberId());
			int row = memberDao.deleteMember(conn, member);
			if(row==1) { // 비밀번호가 같아서 삭제 성공하면 
				System.out.println("member테이블 데이터 삭제 성공");
				System.out.println(member.getMemberId()+" <- MemberService.removeMember member.getMemberId()");
				memberIdDao = new MemberIdDao();
				memberIdDao.insertMemberId(conn, member.getMemberId());
				System.out.println("memberId테이블 데이터 추가 성공");
			}
			conn.commit(); // 문제가 생기지 않으면 여기서 커밋 활성화
		}catch(Exception e){
			try {
				conn.rollback(); //  커밋전 예외가 나면 롤백(저장하지 않고 커밋 막은 후 부터 실행한 것 초기화)
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("MemberService.removeMeber rollback전 예외");
			} 
			e.printStackTrace();
			System.out.println("MemberService.removeMeber 예외발생");

		}finally {
			DBUtil.close(null, null, conn);
		}
	}
	//회원탈퇴 시 입력한 비밀번호와 DB안의 비밀번호 비교(member인스턴스에 담지않고 매개변수로 가져왔다)
	public Member getSelectMemberOne(String memberId,String memberPw) {
		System.out.println("MemberService.getSelectMemberOne");
		memberDao = new MemberDao();
		Member DBPw = new Member();
		try {
			DBPw = memberDao.selectMemberOnePassword(memberId, memberPw);
		} catch (Exception e) {
			System.out.println("MemberService.getSelectMemberOne 예외발생.");
			e.printStackTrace();
		}
		return DBPw;
	}
	//회원가입시 아이디 중복확인(수업 내용)
			// true : 회원가입 가능한 id
			// false : 회원가입 불가능한 id
	public boolean AddMember(Member member) {
		memberDao = new MemberDao();
		Connection conn = null;
		try {
			conn = DBUtil.getConection();
			if(memberDao.selectMemberId(conn, member.getMemberId())) {
				memberDao.insertMember(conn, member);
				return true;
			}
		} catch(Exception e){
			e.printStackTrace();
			System.out.println("MemberService.AddMember() 예외발생.");
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("MemberService.AddMember() finally절 예외발생.");
			}
		}
		return false;
	}
	/*
	//회원가입(혼자 수업전 먼저 만들어 본 것)
	public void getInsertMember(Member member) {
		memberDao = new MemberDao();
		memberDao.insertMember(member);
	}
	*/
	/*
	//회원가입시 아이디 중복확인(혼자 수업전 먼저 만들어 본 것)
	public int getSelectMemberCount(Member member) {
		memberDao = new MemberDao();
		int selectMemberCount = memberDao.selectMemberCount(member);
		System.out.println(selectMemberCount+" <- MemberService.getInsertMember returnNember");
		return selectMemberCount;
	}
	*/
	// 페이징 + 전체맴버 목록
	public Map<String, Object> getMemberList(int currentPage, int rowPerPage){ // 여러개를 리턴하고싶을때 Map이 좋다.
		Map<String, Object> map = new HashMap<String, Object>();
		Connection conn = null;
		List<Member> list = null;
		try {
			conn = DBUtil.getConection();
			memberDao = new MemberDao();
			int beginRow = (currentPage-1)*rowPerPage; // before DAO 호출 
			list = memberDao.seleteMemberListAll(conn, beginRow, rowPerPage);
			System.out.println(list+" < list");
			int count = memberDao.countMember(conn);
			int lastPage = count/rowPerPage; // after DAO 호출
			if(count%rowPerPage != 0) {
				lastPage += 1;
			}
			map.put("list",list);
			map.put("lastPage", lastPage);
		} catch (Exception e) {
			System.out.println("MemberService.getMemberList() 예외발생.");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("MemberService.getMemberList() finally절 예외발생.");
				e.printStackTrace();
			}
		}
		return map;
	}
	// 맴버레벨변경
	public void getUpdateMemberLevel(Member member) {
		Connection conn = null;
		try {
			conn = DBUtil.getConection();
			memberDao = new MemberDao();
			memberDao.updateMemberLevel(conn, member);
		} catch (Exception e) {
			System.out.println("MemberService.getUpdateMemberLevel() 예외발생.");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("MemberService.getUpdateMemberLevel() finally절 예외발생.");
				e.printStackTrace();
			}
		}
	}
}
