package blog.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.commons.DBUtil;
import blog.dao.SubjectDao;
import blog.vo.Subject;

public class SubjectService {
	// 서브젝트 목록
	private SubjectDao subjectDao;
	public List<Subject> getSubjectListAll(){
		subjectDao = new SubjectDao();
		List<Subject> list = new ArrayList<Subject>();
		Connection conn = null;
		try {
			conn = DBUtil.getConection();
			list = subjectDao.seleteSubjectListAll(conn);
		} catch (Exception e) {
			System.out.println("SubjectService.getSubjectListAll() 예외발생");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("SubjectService.getSubjectListAll() finally절 예외발생");
				e.printStackTrace();
			}
		}
		return list;
	}
	// 서브젝트 추가
	public void getInsertSubject(Subject subject) {
		Connection conn = null;
		try {
			conn = DBUtil.getConection();
			subjectDao = new SubjectDao();
			subjectDao.insertSubject(conn, subject);
		} catch (Exception e) {
			System.out.println("SubjectService.getInsertSubject() 예외발생");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("SubjectService.getInsertSubject() finally절 예외발생");
				e.printStackTrace();
			}
		}
	}
	// 서브젝트 추가시 중복되는 서브젝트 이름이 있는지 select
	public boolean getselectSubjectName(Subject subject) {
		Connection conn = null;
		subjectDao = new SubjectDao();
		try {
			conn = DBUtil.getConection();
			if(subjectDao.selectSubjectName(conn, subject)) { // 리턴값이 true라면-> 중복이 아니면
				subjectDao.selectSubjectName(conn, subject);
				return true;
			}
		} catch (Exception e) {
			System.out.println("SubjectService.getselctSujectName() 예외발생");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("SubjectService.getselctSujectName() finally절 예외발생");
				e.printStackTrace();
			}
		}
		return false;
	}
	//서브젝트 삭제
	public int getDeleteSubject(int subjectNo) {
		System.out.println(subjectNo+" <-- 매개변수확인 subjectNo");
		Connection conn = null;
		int exp = 0;
		try {
			conn = DBUtil.getConection();
			subjectDao = new SubjectDao();
			subjectDao.deleteSubject(conn, subjectNo);
		} catch(Exception e){
			System.out.println("SubjectService.getDeleteSubject() 예외발생");
			e.printStackTrace();
			exp=1;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("SubjectService.getDeleteSubject() finally절 예외발생");
				e.printStackTrace();
			}
		}
		return exp;
	}
	// 서브젝트no로 서브젝트 이름을 알아내는 메소드
	public Subject getSelectSubjectNameOne(int subjectNo) {
		Connection conn = null;
		Subject subject = new Subject();
		try {
			conn= DBUtil.getConection();
			subjectDao = new SubjectDao();
			subject = subjectDao.selectSubjectNameOne(conn, subjectNo);
		}catch(Exception e) {
			System.out.println("SubjectService.selectSubjectNameOne 예외발생.");
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("SubjectService.selectSubjectNameOne finally절 예외발생.");
				e.printStackTrace();
			}
		}
		return subject;
	}
	// 서브젝트 수정
	public int getUpdateSubject(String subjectName, String newSubjectName) {
		System.out.println(subjectName+" <- serviceName 매개변수 확인 ");
		System.out.println(newSubjectName+" <- newSubjectName 매개변수 확인");
		Connection conn = null;
		int Exp = 0;
		try {
			conn = DBUtil.getConection();
			subjectDao = new SubjectDao();
			subjectDao.updateSubject(conn, subjectName, newSubjectName);
		} catch (Exception e) {
			System.out.println("SubjectService.getUpdateSubject 예외발생.");
			e.printStackTrace();
			Exp = 1;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("SubjectService.getUpdateSubject finally절 예외발생.");
				e.printStackTrace();
			}
		}
		return Exp;
	}
}
