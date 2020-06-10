package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import blog.commons.DBUtil;
import blog.vo.Member;
import blog.vo.MemberId;

public class MemberIdDao {
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	//회원 탈퇴시 삭제된 아이디를 추가
	public void insertMemberId(Connection conn, String memberId) throws SQLException {
		System.out.println(memberId+" <- MemberIdDao 매개변수 memberId");
		String sql="INSERT INTO blog_memberid(memberid, memberid_date) VALUES(?, now())";
		try { // try절에서 예외가 발생하면 무조건 finally절을 실행한다.
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
	/*
	(혼자 만든것인데 강의듣고 다른방법으로 대체)
	//회원가입할때 이미 삭제된 아이디도 사용하지 못하도록 막기위해 있는지 검사하는 메소드
	public int selectMemberIdCount(Member member) {
		String sql ="SELECT count(*) AS count FROM memberId WHERE memberId=?";
		int selectMemberIdCount = 0;
		try {
			conn = DBUtil.getConection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, member.getMemberId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				selectMemberIdCount = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectMemberIdCount;
	}
	*/
}
