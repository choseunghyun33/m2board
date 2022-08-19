package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import vo.Nice;

public class NiceDao implements INiceDao {

	@Override
	public int insertNice(Connection conn, Nice nice) throws Exception {
		// 리턴값 초기화
		int row = 0;
		
		// 쿼리
		String sql = "insert into nice (board_no, id, create_date) values (?, ?, now())";
		
		// 초기화
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			// stmt setter
			stmt.setInt(1, nice.getBoardNo());
			stmt.setString(2, nice.getId());
			
			// 디버깅
			System.out.println("NiceDao.java insertNice stmt : " + stmt);
			
			// 쿼리 실행
			row = stmt.executeUpdate();
			
		} finally {
			if(stmt != null) { stmt.close(); }
		}
		
		return row;
	}

}
