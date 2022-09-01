package service;

import java.sql.Connection;

import commons.DBUtil;
import repository.BoardDao;
import repository.INiceDao;
import repository.NiceDao;
import vo.Nice;

public class NiceService implements INiceService {
	private DBUtil dbUtil;
	private INiceDao niceDao;
	
	@Override
	public void addNice(Nice nice) {
		// 초기화
		this.dbUtil = new DBUtil();
		this.niceDao = new NiceDao();
		Connection conn = null;
		
		try {
			conn = this.dbUtil.getConnection();
			// 디버깅
			System.out.println("NiceService.java addNice conn : " + conn);
			// 자동커밋 해제
			conn.setAutoCommit(false);
			
			// 메서드실행 (리턴값 1 성공)
			int row = this.niceDao.insertNice(conn, nice);
			
			// 분기 (문제 있을경우 익셉션 발생)
			if(row == 0) {
				// 디버깅
				System.out.println("NiceService.java addNice insertNice() 실패");
				
				throw new Exception();
			}
			
			// (좋아요 시 조회수 증가 방지)
			int row2 = new BoardDao().updateminusReadCnt(conn, row);
			
			// 분기 (문제 있을경우 익셉션 발생)
			if(row2 == 0) {
				// 디버깅
				System.out.println("NiceService.java addNice updateminusReadCnt() 실패");
				
				throw new Exception();
			}
			
			// 완료시 커밋
			conn.commit();
		} catch(Exception e) {
			e.printStackTrace();
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
