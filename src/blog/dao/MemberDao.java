package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.commons.DBUtil;
import blog.vo.Member;

public class MemberDao {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	//로그인
	public Member login(String memberId, String memberPw) {
		Member returnMember = null;
		conn = null;
		stmt = null;
		rs = null;
		String sql="SELECT member_id, member_level FROM member WHERE member_id = ? AND member_pw = ?";
		try {
			conn = DBUtil.getConection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, memberPw);
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberId(rs.getString("member_id"));
				returnMember.setMemberLevel(rs.getInt("member_level"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MemberDao.login() 예외발생");
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return returnMember;
	}
	//회원정보
	public Member selectMemberOne(String memberId) throws Exception {
		Member member = null;
		conn = null;
		stmt = null;
		rs = null;
		String sql="SELECT * FROM member WHERE member_id = ?";
		try {
			conn = DBUtil.getConection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				member = new Member();
				member.setMemberNo(rs.getInt("member_no"));
				member.setMemberId(rs.getString("member_id"));
				member.setMemberPw(rs.getString("member_pw"));
				member.setMemberLevel(rs.getInt("member_level"));
				member.setMemberDate(rs.getString("member_date"));
			}
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return member;
	}
	//비밀번호 변경
	public void updateMemberPw(Member member) throws Exception {
		String sql="UPDATE member SET member_pw=? WHERE member_id=?";
		String PW = null;
		try {
			conn = DBUtil.getConection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberPw());
			stmt.setString(2, member.getMemberId());
			stmt.executeUpdate();
			PW = member.getMemberPw();
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
	}
	// 비밀번호 변경할때  입력한 비밀번호와 DB안의 비밀번호 비교하기위해 DB안 비밀번호를 확인하는 메서드(회원탈퇴와 달리 member에 담아서 해보았다)
	public Member SelectMemberPw(Member member) throws Exception {
		System.out.println(member.getMemberId()+" <- MemberDao.SelectMemberPw() member.getMemberId()");
		Member dbPw = null;
		String sql = "SELECT member_pw FROM member WHERE member_id=?";
		try {
			conn = DBUtil.getConection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				dbPw = new Member();
				dbPw.setMemberPw(rs.getString("member_pw"));
			}
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		System.out.println(dbPw.getMemberPw()+" <- MemberDao.SelectMemberPw() 리턴할 비밀번호");
		return dbPw;
	}
	//회원탈퇴시 삭제
	public int deleteMember(Connection conn, Member member) throws SQLException {
		stmt = null;
		String sql="DELETE FROM member WHERE member_id=? AND member_pw=?";
		int row=0;
		try { // try절에서 예외가 발생하면 무조건 finally절을 실행한다.
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			row = stmt.executeUpdate();
		} finally {
			stmt.close();
		}
		return row; // 삭제한 행의 개수. 삭제성공시 1 아닐시 0
	}
	//회원탈퇴 시 입력한 비밀번호와 DB안의 비밀번호 비교하기위해 DB안의 비밀번호 select
	public Member selectMemberOnePassword(String memberId, String memberPw) throws Exception {
		Member DBPw = null;
		conn = null;
		stmt = null;
		rs = null;
		String sql="SELECT * FROM member WHERE member_id = ? AND member_pw=?";
		try {
			conn = DBUtil.getConection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.setString(2, memberPw);
			rs = stmt.executeQuery();
			if(rs.next()) {
				DBPw = new Member();
				DBPw.setMemberPw(rs.getString("member_pw"));
			}
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return DBPw;
	}
	/*
	//회원가입
	//혼자 생각해서 먼저 만들어 본 것
	public void insertMember(Member member) {
		String sql = "INSERT INTO member(member_id, member_pw, member_level, member_date) VALUES(?, ?, 10, NOW())";
		try {
			conn = DBUtil.getConection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			System.out.println(stmt);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MemberDao.insertMember() 예외발생");
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
	} 
	*/
	//회원가입 (수업내용)
	public void insertMember(Connection conn, Member member) throws SQLException {
		PreparedStatement stmt = null;
		String sql="INSERT INTO member(member_id, member_pw, member_level, member_date) VALUES(?, ?, 10, NOW())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
	//회원가입 전 id 사용 여부 검사 (수업내용 -> sql 서브쿼리와 유니온 활용)
	public boolean selectMemberId(Connection conn, String memberId) throws SQLException {
		boolean flag = true; // 아이디 사용할 수 있으면 true(아이디 사용 가능). 사용 불가면  false(아이디 현재 사용중)
							 // 무조건 사용 있다고 보고 초기값 true 리턴
		String sql ="SELECT mi FROM "
				+ "(SELECT member_id as mi FROM member "
				+ "UNION "
				+ "SELECT memberId as mi FROM memberId) as t "
				+ "WHERE t.mi=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			rs = stmt.executeQuery();
			if(rs.next()) {
				flag = false; // 찾은게 있으면 사용 불가, false 리턴
			}
		} finally { // 예외가 발생하면 서비스로 던지되 close하자
			rs.close();
			stmt.close();
		}
		return flag;
	}
	/*
	//회원가입 전 id 사용 여부 (혼자 생각해서 만든 메서드 -> 쿼리 2개를 서비스에서 동시에 불러와서 사용해봄)
	//혼자 생각해서 먼저 만들어 본 것
	public int selectMemberCount(Member member) {
		String sql ="SELECT count(*) FROM member WHERE member_id=?";
		int selectMemberCount=0;
		try {
			conn = DBUtil.getConection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				selectMemberCount = rs.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("MemberDao.selectMember() 예외발생");
		} finally {
			DBUtil.close(rs, stmt, conn);
		}
		return selectMemberCount;
	}
	*/
	//member목록 출력
	public List<Member> seleteMemberListAll(Connection conn,int beginRow, int rowPerPage) throws Exception{
		List<Member> list = new ArrayList<Member>();
		String sql="SELECT member_id, member_level FROM member LIMIT ?,?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Member member = new Member();
				member.setMemberId(rs.getString("member_id"));
				member.setMemberLevel(rs.getInt("member_level"));
				list.add(member);
			}
			
		} finally {
			DBUtil.close(rs, stmt, null);
		}
		return list;
	}
	//페이징
	public int countMember(Connection conn) throws SQLException{
		System.out.println(conn+" < conn");
		int count= 0;		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql="SELECT count(*) FROM member";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1); // rs.getInt("count(*)")
				System.out.println(count+" <- MemberDao.countMember() count");
			}
		} finally {
			DBUtil.close(rs, stmt, null);
		}
		return count;
	}
	//멤버 레벨 변경
	public void updateMemberLevel(Connection conn, Member member) throws SQLException{
		PreparedStatement stmt = null;
		String sql="UPDATE member SET member_level=? WHERE member_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, member.getMemberLevel());
			stmt.setString(2, member.getMemberId());
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
}
