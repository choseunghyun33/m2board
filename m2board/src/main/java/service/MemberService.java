package service;

import java.sql.Connection;

import commons.DBUtil;
import repository.IMemberDao;
import repository.MemberDao;
import vo.Member;

public class MemberService implements IMemberService {
	private DBUtil dbUtil;
	private IMemberDao memberDao;
		
	@Override
	public Member getMemberLogin(Member paramMember) {
		// 리턴값 초기화
		Member member = null;
		
		// 초기화
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		Connection conn = null;
		
		try {
			conn = this.dbUtil.getConnection();
			// 디버깅
			System.out.println("MemberService.java getMemberLogin conn : " + conn);
			
			// conn 자동커밋해제
			conn.setAutoCommit(false);
			
			// 메서드 실행
			member = this.memberDao.selectMemberLogin(conn, paramMember);
			
			// member == null exception 발생
			if(member == null) {
				// 디버깅
				System.out.println("MemberService.java getMemberLogin selectMemberLogin() 실패");
				
				throw new Exception();
			}
			
			// conn commit
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
			// conn rollback
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			// 자원해제
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return member;
	}

	@Override
	public void addMember(Member paramMember) {
		// 초기화
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		Connection conn = null;
		
		try {
			conn = this.dbUtil.getConnection();
			// 디버깅
			System.out.println("MemberService.java addMember conn : " + conn);
			
			// conn 자동커밋해제
			conn.setAutoCommit(false);
			
			// 메서드 실행
			int row = this.memberDao.insertMember(conn, paramMember);
			
			if(row == 0) {
				// 디버깅
				System.out.println("MemberService.java addMember insertMember() 실패");
				
				throw new Exception();
			}
			
			// conn commit
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
			// conn rollback
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			// 자원해제
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
