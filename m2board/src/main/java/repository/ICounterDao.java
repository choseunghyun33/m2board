package repository;

import java.sql.Connection;

public interface ICounterDao {
	// 오늘 날짜에 날짜가 있다면
	void updateCounter(Connection conn) throws Exception;
	
	// 입력
	void insertCounter(Connection conn) throws Exception; 
	
	// select (오늘 날짜에 있는지)
	String selectCounterToday(Connection conn) throws Exception;
	
	// 전체접속자 수
	int selectTotalCount(Connection conn) throws Exception;
	
	// 오늘접속자 수
	public int selectTodayCount(Connection conn) throws Exception;
}
