package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.vo.Post;

public class PostDao {
	// 선택한 포스트 내용 출력
	public Post selectPostOne(Connection conn, int postNo) throws SQLException {
		Post post = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT * FROM post WHERE post_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				post = new Post();
				post.setPostNo(rs.getInt("post_no"));
				post.setMemberId(rs.getString("member_id"));
				post.setSubjectName(rs.getString("subject_name"));
				post.setPostTitle(rs.getString("post_title"));
				post.setPostContent(rs.getString("post_content"));
				post.setPostDate(rs.getString("post_date"));
			}
		}finally {
			rs.close();
			stmt.close();
		}
		return post;
	}
	// 포스트 목록 출력
	public List<Post> selectPostListAll(Connection conn, int beginRow, int rowPerPage) throws Exception{
		System.out.println("PostDao");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Post> list = new ArrayList<Post>();
		String sql="SELECT * FROM post ORDER BY post_no DESC LIMIT ?, ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Post post = new Post();
				post.setPostNo(rs.getInt("post_no"));
				post.setMemberId(rs.getString("member_id"));
				post.setSubjectName(rs.getString("subject_name"));
				post.setPostTitle(rs.getString("post_title"));
				post.setPostDate(rs.getString("post_date"));
				list.add(post);
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return list;
	}
	// 페이징-데이터 총개수
	public int countPost(Connection conn) throws SQLException {
		int count = 0;
		String sql = "SELECT count(*) from post";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			stmt.close();
			rs.close();
		}
		return count;
	}
	// 메뉴에서 선택한 포스트의 내용 출력
	public List<Post> selectPostBySubject(Connection conn,String subjectName, int beginRow, int rowPerPage) throws Exception{
		System.out.println("PostDao");
		System.out.println(beginRow+" <- beginRow");
		System.out.println(rowPerPage+"<- rowPerPage");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Post> list = new ArrayList<Post>();
		String sql="SELECT * FROM post WHERE subject_name=? ORDER BY post_no desc LIMIT ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, subjectName);
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Post post = new Post();
				post.setPostNo(rs.getInt("post_no"));
				post.setMemberId(rs.getString("member_id"));
				post.setSubjectName(rs.getString("subject_name"));
				post.setPostTitle(rs.getString("post_title"));
				post.setPostDate(rs.getString("post_date"));
				list.add(post);
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return list;
	}
	//선택한 서브젝트의 포스팅 글 총 개수
	public int selectPostBySubjectCount(Connection conn, String subjectName) throws SQLException {
		System.out.println(subjectName+" <-- PostDao.selectPostBySubjectCount subjectName(선택한 서브젝트이름)");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql ="SELECT count(*) FROM post WHERE subject_name=?";
		int count = 0;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, subjectName);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
				System.out.println(count+" << count");
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return count;
	}
	// 포스트 글 추가
	public void insertPost(Connection conn, Post post) throws SQLException {
		PreparedStatement stmt = null;
		String sql ="INSERT INTO "
				+ "post(member_id, subject_name, post_title, post_content, post_date) "
				+ "VALUES(?,?,?,?,NOW())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, post.getMemberId());
			stmt.setString(2, post.getSubjectName());
			stmt.setString(3, post.getPostTitle());
			stmt.setString(4, post.getPostContent());
			stmt.executeUpdate();
		}finally {
			stmt.close();
		}
	}
	// 포스트 삭제
	public void deletePost(Connection conn, int postNo) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM post WHERE post_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
	// 포스트 삭제될때 좋아요 삭제(포스트에 있는 댓글 전부 삭제되도록 매개변수값만 member_id로 받음)
	public void deleteMemberByPost(Connection conn, String memberId) throws SQLException {
		PreparedStatement stmt = null;
		String sql ="DELETE FROM post WHERE member_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
	// 포스트 수정
	public void updatePost(Connection conn, Post post) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "UPDATE post SET subject_name=?, post_title=?, post_content=? WHERE post_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, post.getSubjectName());
			stmt.setString(2, post.getPostTitle());
			stmt.setString(3, post.getPostContent());
			stmt.setInt(4, post.getPostNo());
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
}
