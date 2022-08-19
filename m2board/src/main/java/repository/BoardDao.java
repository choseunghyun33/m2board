package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vo.Board;

public class BoardDao implements IBoardDao {

	@Override
	public List<Map<String,Object>> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws Exception {
		// 리턴값 초기화
		List<Map<String,Object>> list = new ArrayList<>();
		
		// 쿼리
		String sql = "select board_no boardNo, title, id, create_date createDate, read_cnt readCnt, ifnull(n.cnt,0) nice from board left join (select board_no, count(*) cnt from nice group by board_no) n using(board_no) order by create_date desc limit ?,?";
		
		// 초기화
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			// stmt setter
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			// 디버깅
			System.out.println("BoardDao.java selectBoardListByPage stmt" + stmt);
			
			// 쿼리실행
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> m = new HashMap<>();
				m.put("boardNo", rs.getInt("boardNo"));
				m.put("title", rs.getString("title"));
				m.put("id", rs.getString("id"));
				m.put("createDate", rs.getString("createDate"));
				m.put("readCnt", rs.getInt("readCnt"));
				m.put("nice", rs.getInt("nice"));
				
				list.add(m);
				// 디버깅
				System.out.println("BoardDao.java selectBoardListByPage list" + list.toString());
			}
		} finally {
			if(rs != null) { rs.close(); }
			if(stmt != null) { stmt.close(); }
		}
		
		return list;
	}

	@Override
	public int selectBoardCnt(Connection conn) throws Exception {
		// 리턴값 초기화
		int boardCnt = 0;
		
		// 쿼리
		String sql = "select count(*) cnt from board";
		
		// 초기화
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			// 디버깅
			System.out.println("BoardDao.java selectBoardCnt stmt" + stmt);
			
			// 쿼리실행
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				boardCnt = rs.getInt("cnt");
				
				// 디버깅
				System.out.println("BoardDao.java selectBoardCnt stmt" + boardCnt);
			}
		} finally {
			if(rs != null) { rs.close(); }
			if(stmt != null) { stmt.close(); }
		}
		
		
		return boardCnt;
	}

	@Override
	public Map<String,Object> selectBoardOneByBoardNo(Connection conn, int BoardNo) throws Exception {
		// 리턴값 초기화
		Map<String,Object> m = null;
				
		// 쿼리
		String sql = "select board_no boardNo, title, id, content, create_date createDate, read_cnt readCnt, ifnull(n.cnt,0) nice from board left join (select board_no, count(*) cnt from nice group by board_no) n using(board_no) where board_no = ?";
		
		// 초기화
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			// stmt setter
			stmt.setInt(1, BoardNo);
			// 디버깅
			System.out.println("BoardDao.java selectBoardOneByBoardNo stmt" + stmt);
			
			// 쿼리실행
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				m = new HashMap<>();
				m.put("boardNo", rs.getInt("boardNo"));
				m.put("title", rs.getString("title"));
				m.put("id", rs.getString("id"));
				m.put("content", rs.getString("content"));
				m.put("createDate", rs.getString("createDate"));
				m.put("readCnt", rs.getInt("readCnt"));
				m.put("nice", rs.getInt("nice"));
				
				// 디버깅
				System.out.println("BoardDao.java selectBoardListByPage map" + m.toString());
			}
		} finally {
			if(rs != null) { rs.close(); }
			if(stmt != null) { stmt.close(); }
		}
		
		return m;
	}

	@Override
	public int updateReadCnt(Connection conn, int BoardNo) throws Exception {
		int row = 0;
		
		// 쿼리
		String sql = "update board set read_cnt = read_cnt + 1 where board_no = ?";
		
		// 초기화
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			// stmt setter
			stmt.setInt(1, BoardNo);
			// 디버깅
			System.out.println("BoardDao.java updateReadCnt stmt" + stmt);
			
			// 쿼리실행
			row = stmt.executeUpdate();
			
		} finally {
			if(stmt != null) { stmt.close(); }
		}	
		
		return row;
	}

	@Override
	public int insertBoard(Connection conn, Board board) throws Exception {
		int row = 0;
		
		// 쿼리
		String sql = "insert into board (title, id, content, create_date) values (?, ?, ?, now())";
		
		// 초기화
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			//stmt setter
			stmt.setString(1, board.getTitle());
			stmt.setString(2, board.getId());
			stmt.setString(3, board.getContent());
			// 디버깅
			System.out.println("BoardDao.java insertBoard stmt" + stmt);
			
			// 쿼리실행
			row = stmt.executeUpdate();
			
		} finally {
			if(stmt != null) { stmt.close(); }
		}	
		
		return row;
	}

}
