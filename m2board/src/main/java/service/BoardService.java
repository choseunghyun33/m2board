package service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.DBUtil;
import repository.BoardDao;
import repository.IBoardDao;
import vo.Board;

public class BoardService implements IBoardService {
	private DBUtil dbUtil;
	private IBoardDao boardDao;
	
	// 리턴값 : List<Board>, int lastPage
	@Override
	public Map<String, Object> getBoardList(int rowPerPage, int currentPage) {
		// 리턴값 초기화
		Map<String, Object> map = new HashMap<>();
		
		// 초기화
		Connection conn = null;
		this.dbUtil = new DBUtil();
		this.boardDao = new BoardDao();
		
		// beginRow 구하기
		int beginRow = (currentPage - 1) * rowPerPage;
		
		try {
			conn = this.dbUtil.getConnection();
			// 디버깅
			System.out.println("BoardService.java getBoardList conn : " + conn);
			
			// 자동 커밋해제
			conn.setAutoCommit(false);
			
			// 1. board 메서드
			List<Map<String, Object>> list = this.boardDao.selectBoardListByPage(conn, rowPerPage, beginRow);
			
			// 제대로 들어오지 못함 exception 발생시키기
			if(list.size() < 1) {
				// 디버깅
				System.out.println("BoardService.java getBoardList selectBoardListByPage() : 실패");
				throw new Exception();
			}
			
			// map에 담기
			map.put("list", list);
			
			// 2. boardCnt 메서드
			int boardCnt = this.boardDao.selectBoardCnt(conn);
			
			// 제대로 들어오지 못함 exception 발생시키기
			if(boardCnt == 0) {
				// 디버깅
				System.out.println("BoardService.java getBoardList selectBoardCnt() : 실패");
				throw new Exception();
			}
			
			// 3. lastPage 구하기
			int lastPage = (int) Math.ceil (boardCnt / (double)rowPerPage); 

			// map에 담기
			map.put("lastPage", lastPage);
			
			// 디버깅
			System.out.println(map.toString());
			
			// 4. 문제 없으면 커밋
			conn.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			// 문제 있으면 롤백
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			// 5. 자원해제
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return map;
	}

	@Override
	public Map<String, Object> getBoardOneByBoardNo(int boardNo) {
		// 리턴값 초기화
		Map<String, Object> m = null;
		
		// 초기화
		Connection conn = null;
		this.dbUtil = new DBUtil();
		this.boardDao = new BoardDao();
		
		try {
			conn = this.dbUtil.getConnection();
			// 디버깅
			System.out.println("BoardService.java getBoardOneByBoardNo conn : " + conn);
			
			// 자동 커밋해제
			conn.setAutoCommit(false);
			
			// 1. board 메서드
			m = this.boardDao.selectBoardOneByBoardNo(conn, boardNo);
			
			// 제대로 들어오지 못함 exception 발생시키기
			if(m == null) {
				// 디버깅
				System.out.println("BoardService.java getBoardOneByBoardNo selectBoardOneByBoardNo() : 실패");
				throw new Exception();
			}
			
			// 2. 조회수 메서드
			int row = this.boardDao.updateplusReadCnt(conn, boardNo);
			
			// 제대로 들어오지 못함 exception 발생시키기
			if(row == 0) {
				// 디버깅
				System.out.println("BoardService.java getBoardOneByBoardNo updateReadCnt() : 실패");
				throw new Exception();
			}
			
			// 3. 문제 없으면 커밋
			conn.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			// 문제 있으면 롤백
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			// 4. 자원해제
			if(conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return m;
	}

	
	@Override
	public void addBoard(Board board) {
		// 초기화
		Connection conn = null;
		this.dbUtil = new DBUtil();
		this.boardDao = new BoardDao();
		
		try {
			conn = this.dbUtil.getConnection();
			// 디버깅
			System.out.println("BoardService.java addBoard conn : " + conn);
			
			// 자동 커밋해제
			conn.setAutoCommit(false);
			
			// 1. updateNice 메서드 - 1리턴 성공
			int row = this.boardDao.insertBoard(conn, board);
			
			// 제대로 들어오지 못함 exception 발생시키기
			if(row == 0) {
				// 디버깅
				System.out.println("BoardService.java addBoard insertBoard() : 실패");
				throw new Exception();
			}
			
			// 2. 문제 없으면 커밋
			conn.commit();
			
		} catch(Exception e) {
			e.printStackTrace();
			// 문제 있으면 롤백
			try {
				conn.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} finally {
			// 4. 자원해제
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
