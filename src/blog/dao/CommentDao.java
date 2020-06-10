package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.vo.Comment;

public class CommentDao {
	//댓글 입력
	public void insertComment(Connection conn, Comment comment) throws SQLException {
		PreparedStatement stmt = null;
		String sql ="INSERT INTO blog_comment(post_no, member_id, comment_content, comment_date)"
				+ "VALUES(?,?,?,now())";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, comment.getPostNo());
			stmt.setString(2, comment.getMemberId());
			stmt.setString(3, comment.getCommentContent());
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
	//댓글 목록 출력
	public List<Comment> selectCommentListByPostNo(Connection conn, int postNo, int beginRow , int rowPerPage) throws SQLException{
		List<Comment> list = new ArrayList<Comment>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM blog_comment WHERE post_no=? ORDER BY comment_no desc LIMIT ?,?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setCommentNo(rs.getInt("comment_no"));
				comment.setCommentContent(rs.getString("comment_content"));
				comment.setMemberId(rs.getString("member_id"));
				comment.setCommentDate(rs.getString("comment_date"));
				list.add(comment);
			}
		}finally {
			rs.close();
			stmt.close();
		}
		return list;
	}
	// 댓글 총 개수
	public int selctPostByCommentCount(Connection conn, int postNo) throws SQLException {
		int count=0;
		String sql="SELECT COUNT(*) FROM blog_comment WHERE post_no=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return count;
	}
	// 댓글삭제
	public void deleteComment(Connection conn, int commentNo) throws SQLException {
		PreparedStatement stmt = null;
		String sql ="DELETE FROM blog_comment WHERE comment_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, commentNo);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
	// 포스트 삭제될때 댓글 삭제(포스트에 있는 댓글 전부 삭제되도록 매개변수값만 postNo로 받음)
	public void deletePostByComment(Connection conn, int postNo) throws SQLException {
		PreparedStatement stmt = null;
		String sql ="DELETE FROM blog_comment WHERE post_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
	// 포스트 삭제될때 댓글 삭제(포스트에 있는 댓글 전부 삭제되도록 매개변수값만 member_id로 받음)
	public void deleteMemberByPostByComment(Connection conn, String memberId) throws SQLException {
		PreparedStatement stmt = null;
		String sql ="DELETE FROM blog_comment WHERE member_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
	// 댓글 수정폼에서 댓글번호를 이용해 수정할 댓글 출력
	public Comment selectCommentOne(Connection conn, int commentNo) throws SQLException {
		Comment comment = null;
		String sql="SELECT * FROM blog_comment WHERE comment_no = ?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, commentNo);
			rs = stmt.executeQuery();
			if(rs.next()) {
				comment = new Comment();
				comment.setCommentNo(rs.getInt("comment_no"));
				comment.setPostNo(rs.getInt("post_no"));
				comment.setMemberId(rs.getString("member_id"));
				comment.setCommentContent(rs.getString("comment_content"));
				comment.setCommentDate(rs.getString("comment_date"));
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return comment;
	}
	// 댓글 수정
	public void updateComment(Connection conn, Comment comment) throws SQLException {
		String sql="UPDATE blog_comment SET comment_content=? WHERE comment_no=?";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, comment.getCommentContent());
			stmt.setInt(2, comment.getCommentNo());
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
}
