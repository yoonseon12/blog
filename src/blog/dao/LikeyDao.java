package blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import blog.vo.Likey;

public class LikeyDao {
	// 좋아요 $ 싫어요 총 개수 (count)
	public Map<String, Integer> selectLikeyCount(Connection conn, int postNo) throws SQLException {
		System.out.println(postNo+"<<<<<<<<<");
		Map<String, Integer> map = new HashMap<String, Integer>();
		int goodCount = 0;
		int badCount = 0;
		String sql="SELECT likey_ck, COUNT(likey_ck) cnt FROM likey WHERE post_no = ? GROUP BY likey_ck";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			rs = stmt.executeQuery();
			while(rs.next()) {
				if(rs.getInt("likey_ck")==0) {
					badCount = rs.getInt("cnt");
				}
				if(rs.getInt("likey_ck")==1) {
					goodCount = rs.getInt("cnt");
				}
			}
			map.put("badCount", badCount);
			map.put("goodCount", goodCount);
		} finally {
			rs.close();
			stmt.close();
		}
		return map;
	}
	// 좋아요 추가
	public void insertLikey(Connection conn, Likey likey) throws SQLException {
		System.out.println(likey.getLikeyCk()+" <--LikeyDao 매개변수값 확인 0이면 싫어요클릭, 1이면 좋아요클릭.");
		System.out.println(likey.getLikeyNo());
		String sql ="INSERT INTO likey(post_no, member_id, likey_ck, likey_date) "
				+ "VALUES(?,?,?,now())";
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likey.getPostNo());
			stmt.setString(2, likey.getMemberId());
			stmt.setInt(3, likey.getLikeyCk());
			stmt.executeUpdate();
		} finally {
			conn.close();
		}
	}
	// 좋아요 클릭 여부확인
	// SELECT * FROM likey WHERE post_no =? AND member_id=?
	public boolean isInsertLikey(Connection conn, Likey likey) throws SQLException {
		//좋아요 가능하면 ture 가능하지않으면 false
		boolean flag = true;
		String sql="SELECT * FROM likey WHERE post_no =? AND member_id=?";
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likey.getPostNo());
			stmt.setString(2, likey.getMemberId());
			rs = stmt.executeQuery();
			if(rs.next()) {
				flag = false;
			}
		} finally {
			rs.close();
			stmt.close();
		}
		return flag;
	}
	// 좋아요 수정(싫어요 누르면 눌렀던 좋아요가 0으로바뀌고 싫어요가 1로바뀜)
	public void updateLikey(Connection conn, Likey likey) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "UPDATE likey SET likey_ck=? WHERE post_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, likey.getLikeyCk());
			stmt.setInt(2, likey.getPostNo());
			stmt.executeUpdate();
		}finally {
			stmt.close();
		}
	}
	// 좋아요 삭제(포스트 글이 삭제될 때 좋아요도 자동으로 삭제)
	public void deletePostByLikey(Connection conn, int postNo) throws SQLException {
		PreparedStatement stmt = null;
		String sql = "DELETE FROM likey WHERE post_no=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, postNo);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
	// 포스트 삭제될때 좋아요 삭제(포스트에 있는 댓글 전부 삭제되도록 매개변수값만 member_id로 받음)
	public void deleteMemberByPostByLikey(Connection conn, String memberId) throws SQLException {
		PreparedStatement stmt = null;
		String sql ="DELETE FROM likey WHERE member_id=?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, memberId);
			stmt.executeUpdate();
		} finally {
			stmt.close();
		}
	}
}
