package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import blog.commons.DBUtil;
import blog.vo.Subject;

public class SubjectDao {
	// 서브젝트 목록
	public List<Subject> seleteSubjectListAll(Connection conn) throws Exception{
		List<Subject> list = new ArrayList<Subject>(); // 현재 size ->0
		String sql="SELECT * FROM subject ORDER BY subject_name asc";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Subject subject = new Subject();
				subject.setSubjectNo(rs.getInt("subject_no"));
				subject.setSubjectName(rs.getString("subject_name"));
				subject.setSubjectDate(rs.getString("subject_date"));
				list.add(subject);
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return list;
	}
	// 서브젝트 추가 
	public void insertSubject(Connection conn, Subject subject) throws SQLException {
		System.out.println(subject.getSubjectName());
		String sql="INSERT INTO subject(subject_name, subject_date) VALUES(?,now())";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, subject.getSubjectName());
			stmt.executeUpdate();
		}finally {
			stmt.close();
		}
	}
	// 서브젝트 추가시 중복되는 서브젝트 이름이 있는지 select 
	public boolean selectSubjectName(Connection conn, Subject subject) throws SQLException {
		System.out.println(subject.getSubjectName()+" <-- SubjectDao.selectSubjectName() 매개변수 값 확인");
		boolean flag = true; // 중복값 없으면(사용할 수 있으면) ture 있으면 (사용할 수 없으면) false
		String sql="SELECT subject_name FROM subject WHERE subject_name=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, subject.getSubjectName());
			rs = stmt.executeQuery();
			if(rs.next()) {
				flag = false;
			}
			System.out.println(flag+" << 영향받은행(있으면 true)");
		}finally {
			stmt.close();
			rs.close();
		}
		return flag;
	}
	//서브젝트 삭제
	public void deleteSubject(Connection conn, int subjectNo) throws Exception {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM Subject WHERE subject_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, subjectNo);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
	//서브젝트no로 서브젝트 이름을 알아내는 메소드
	public Subject selectSubjectNameOne(Connection conn, int subjectNo) throws SQLException {
		Subject subject = null;
		String sql = "SELECT subject_name FROM subject WHERE subject_no=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, subjectNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				subject = new Subject();
				subject.setSubjectName(rs.getString("subject_name"));
				System.out.println(subject.getSubjectName()+" <- SubjectDao에서 리턴할 값 확인");
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return subject;
	}
	// 서브젝트 수정
	public void updateSubject(Connection conn, String subjectName, String newSubjectName) throws Exception {
		System.out.println(subjectName+" <- subjectName Dao매개변수 확인");
		System.out.println(newSubjectName+" <- newSubjectName Dao매개변수 확인");
		String sql = "UPDATE subject SET subject_name=? WHERE subject_name=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, newSubjectName);
			stmt.setString(2, subjectName);
			stmt.executeUpdate();
			System.out.println("DAO 수정 확인");
		} finally {
			stmt.close();
		}
	}
}
