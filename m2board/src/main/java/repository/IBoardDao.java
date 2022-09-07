package repository;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import vo.Board;

public interface IBoardDao {
	// 상세보기 메서드
	Map<String,Object> selectBoardOneByBoardNo(Connection conn, int BoardNo) throws Exception;
	// 전체보기
	List<Map<String,Object>> selectBoardListByPage(Connection conn, int rowPerPage, int beginRow) throws Exception;
	// 페이지를 위한 카운트
	int selectBoardCnt(Connection conn) throws Exception;
	// 조회수 늘리기
	int updateplusReadCnt(Connection conn, int BoardNo) throws Exception;
	// 좋아요 누를시 조회수 감소
	int updateminusReadCnt(Connection conn, int BoardNo) throws Exception;
	// 글 쓰기
	int insertBoard(Connection conn, Board board) throws Exception;
	// 글 삭제
	int deleteBoard(Connection conn, int boardNo) throws Exception;
	// 글 수정
	int updateBoard(Connection conn, Board board) throws Exception;
}
