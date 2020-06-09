package blog.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blog.commons.DBUtil;
import blog.dao.CommentDao;
import blog.vo.Comment;

public class CommentService {
	private CommentDao commentDao;
	// 댓글 입력(추가)
	public void addComment(Comment comment) {
		System.out.println(comment.getMemberId()+"< 서비스 매개변수 확인");
		Connection conn = null;
		try {
			conn=DBUtil.getConection();
			commentDao = new CommentDao();
			commentDao.insertComment(conn, comment);
		} catch(Exception e){
			System.out.println("CommentService.addComment() 예외발생");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("CommentService.addComment() finally절 예외발생");
				e.printStackTrace();
			}
		}
	}
	// 댓글 목록 출력
	public Map<String, Object> getCommentListByPostNo(int postNo, int commentCurrentPage, int rowPerPage) {
		Connection conn = null;
		Map<String, Object> map = new HashMap<String, Object>();
		List<Comment> list = new ArrayList<Comment>();
		try {
			conn=DBUtil.getConection();
			commentDao = new CommentDao();
			int beginRow=(commentCurrentPage-1)*rowPerPage;
			// 포스트 글 내용
			list = commentDao.selectCommentListByPostNo(conn, postNo, beginRow, rowPerPage);
			map.put("list", list);
			// 데이터 총 개수
			int count = commentDao.selctPostByCommentCount(conn, postNo);
			System.out.println(count+" <- CommentService.getCommentListByPostNo() count");
			map.put("count", count);
			int lastPage = count/rowPerPage;
			if(count%rowPerPage!=0) {
				lastPage+=1;
			}
			map.put("lastPage", lastPage);
		} catch(Exception e){
			System.out.println("CommentService.addComment() 예외발생");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("CommentService.addComment() finally절 예외발생");
				e.printStackTrace();
			}
		}
		return map;
	}
	// 댓글 삭제
	public void getDeleteComment(int commentNo) {
		Connection conn = null;
		try {
			conn=DBUtil.getConection();
			commentDao = new CommentDao();
			commentDao.deleteComment(conn, commentNo);
		} catch(Exception e){
			System.out.println("CommentService.getDeleteComment() 예외발생");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("CommentService.getDeleteComment() finally절 예외발생");
				e.printStackTrace();
			}
		}
	}
	// 댓글 수정폼에서 댓글번호를 이용해 수정할 댓글 출력
	public Comment getSelectCommentOne(int commentNo) {
		Connection conn = null;
		Comment comment = new Comment();
		try {
			conn=DBUtil.getConection();
			commentDao = new CommentDao();
			comment = commentDao.selectCommentOne(conn, commentNo);
		} catch(Exception e){
			System.out.println("CommentService.getSelectCommentOne() 예외발생");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("CommentService.getSelectCommentOne() finally절 예외발생");
				e.printStackTrace();
			}
		}
		return comment;
	}
	// 댓글 수정
	public void getUpdateComment(Comment comment){
		Connection conn = null;
		try {
			conn=DBUtil.getConection();
			commentDao = new CommentDao();
			commentDao.updateComment(conn, comment);
		} catch(Exception e){
			System.out.println("CommentService.getUpdateComment() 예외발생");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("CommentService.getUpdateComment() finally절 예외발생");
				e.printStackTrace();
			}
		}
	}
}
