package service;

public interface ICounterService {
	// 오늘 날짜에 날짜가 있다면 그리고 없다면
	void count();
	
	// 전체접속자 수
	int getTotalCount();
	
	// 오늘접속자 수
	int getTodayCount();
}
