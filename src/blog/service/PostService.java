package blog.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blog.commons.DBUtil;
import blog.dao.CommentDao;
import blog.dao.LikeyDao;
import blog.dao.PostDao;
import blog.vo.Post;

public class PostService {
	private PostDao postDao;
	// 선택한 포스트 내용 출력
	public Post getPostOne(int postNo) {
		Post post = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConection();
			postDao = new PostDao();
			post = postDao.selectPostOne(conn, postNo);
		}catch(Exception e) {
			System.out.println("PostService.getPostOne() 예외발생.");
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("PostService.getPostOne() finally절 예외발생.");
				e.printStackTrace();
			}
		}
		return post;
	}
	// 페이징 + 전체포스팅 목록(관리자 확인용)
	public Map<String, Object> getSelectPostListAll(int rowPerPage, int currentPage){
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("PostService");
		List<Post> list = new ArrayList<Post>();
		Connection conn = null;
		try {
			conn = DBUtil.getConection();
			postDao = new PostDao();
			// 페이징 변수 + 포스팅 목록
			int beginRow = (currentPage-1)*rowPerPage; // dao 가기 전
			list= postDao.selectPostListAll(conn, beginRow, rowPerPage); 
			// 데이터 총 개수
			int count = postDao.countPost(conn);
			System.out.println(count+" <- PostService.getSelectPostListAll() count");
			int lastPage = count/rowPerPage; // dao 갔다온 후
			if(count%rowPerPage != 0) {
				lastPage += 1;
			}
			map.put("postList", list);
			map.put("lastPage", lastPage);
		} catch (Exception e) {
			System.out.println("PostService.getSelectPostListAll() 예외발생.");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("PostService.getSelectPostListAll() finally절 예외발생.");
				e.printStackTrace();
			}
		}
		return map; // 리턴할 것 2개(list, lastPage) 이기 때문에 맵으로 묶어서 한번에 보낸다.
	}
	// 선택한 포스트 글 목록 + 페이징 (아무나 볼 수 있다)
	public Map<String, Object> getPostBySubject(String subjectName, int currentPage, int rowPerPage){
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("PostService");
		List<Post> list = new ArrayList<Post>();
		Connection conn = null;
		try {
			conn = DBUtil.getConection();
			postDao = new PostDao();
			// 서브젝트 리스트
			int beginRow = (currentPage-1)*rowPerPage;
			list = postDao.selectPostBySubject(conn, subjectName, beginRow, rowPerPage);
			map.put("postList", list);
			// 데이터 총 개수 count
			int count = postDao.selectPostBySubjectCount(conn, subjectName);
			System.out.println(count+" <- PostService.getPostBySubject() count");
			int lastPage = count/rowPerPage;
			if(count%rowPerPage!=0) {
				lastPage +=1;
			}
			map.put("lastPage", lastPage);
		} catch (Exception e) {
			System.out.println("PostService.getSelectPostListAll() 예외발생.");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("PostService.getSelectPostListAll() finally절 예외발생.");
				e.printStackTrace();
			}
		}
		return map;
	}
	// 포스트 글 추가
	public void getInsertPost(Post post) {
		Connection conn = null;
		try {
			conn = DBUtil.getConection();
			postDao = new PostDao();
			postDao.insertPost(conn, post);
		} catch (Exception e) {
			System.out.println("PostService.getInsertPost() 예외발생.");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("PostService.getInsertPost() finally절 예외발생.");
				e.printStackTrace();
			}
		}
	}
	// 포스트 삭제
	public void getDeletePost(int postNo) {
		Connection conn = null;
		try {
			conn = DBUtil.getConection();
			CommentDao commentDao = new CommentDao();
			commentDao.deletePostByComment(conn, postNo);
			LikeyDao likeyDao = new LikeyDao();
			likeyDao.deletePostByLikey(conn, postNo);
			postDao = new PostDao();
			postDao.deletePost(conn, postNo);
		} catch (Exception e) {
			System.out.println("PostService.getDeletePost() 예외발생.");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("PostService.getDeletePost() finally절 예외발생.");
				e.printStackTrace();
			}
		}
	}
	// 포스트 수정
	public void getUpdatePost(Post post) {
		Connection conn = null;
		try {
			conn=DBUtil.getConection();
			postDao = new PostDao();
			postDao.updatePost(conn, post);
		} catch(Exception e) {
			System.out.println("PostService.getUpdatePost() finally절 예외발생.");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("PostService.getUpdatePost() finally절 예외발생.");
				e.printStackTrace();
			}	
		}
	}
}
