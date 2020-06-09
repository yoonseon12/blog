package blog.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import blog.commons.DBUtil;
import blog.dao.LikeyDao;
import blog.vo.Likey;

public class LikeyService {
	private LikeyDao likeyDao;
	// 좋아요 & 싫어요 개수
	public Map<String, Integer> getLikeyCount(int postNo) {
		Map<String, Integer> map = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConection();
			likeyDao = new LikeyDao();
			map = likeyDao.selectLikeyCount(conn, postNo);
		} catch (Exception e) {
			System.out.println("LikeyService.getLikeyCount 예외발생");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("LikeyService.getLikeyCount finally절 예외발생");
				e.printStackTrace();
			}
		}
		return map;
	}
	// 좋아요&싫어요 추가
	public void addLikey(Likey likey) {
		Connection conn = null;
		int count = 0; 
		try {
			conn = DBUtil.getConection();
			likeyDao = new LikeyDao();
			if(likeyDao.isInsertLikey(conn, likey)) {
				likeyDao.insertLikey(conn, likey);
			}else {
				likeyDao.updateLikey(conn, likey);
			}
		} catch (Exception e) {
			System.out.println("LikeyService.getLikeyCount 예외발생");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("LikeyService.getLikeyCount finally절 예외발생");
				e.printStackTrace();
			}
		}
		return;
	}
}
