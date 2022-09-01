package repository;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import vo.Board;

public interface IBoardDao {
	Map<String,Object> selectBoardOneByBoardNo(Connection conn, int BoardNo) throws Exception;
	
	List<Map<String,Object>> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws Exception;
	
	int selectBoardCnt(Connection conn) throws Exception;
	
	int updateplusReadCnt(Connection conn, int BoardNo) throws Exception;
	
	int updateminusReadCnt(Connection conn, int BoardNo) throws Exception;
	
	int insertBoard(Connection conn, Board board) throws Exception;
}
