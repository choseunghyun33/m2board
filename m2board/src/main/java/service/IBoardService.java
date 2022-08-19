package service;

import java.util.Map;

import vo.Board;

public interface IBoardService {
	// 반환값 : List<Map<String, Object>>, int lastPage
	// 기능 : 리스트
	Map<String, Object> getBoardList(int rowPerPage, int currentPage); 
	// 반환값 : Map
	// 기능 : 상세보기
	Map<String, Object> getBoardOneByBoardNo(int boardNo);
	// 반환값 : void
	// 기능 : 글추가
	void addBoard(Board board);
}
