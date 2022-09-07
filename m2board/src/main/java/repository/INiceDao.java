package repository;

import java.sql.Connection;

import vo.Nice;

public interface INiceDao {
	// 반환값 : int 
	// 기능 : 좋아요 추가
	int insertNice(Connection conn, Nice nice) throws Exception;
	// 반환값 : int 
	// 기능 : 좋아요 삭제
	int deleteNice(Connection conn, int boardNo) throws Exception;
}
