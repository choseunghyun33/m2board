package service;

import vo.Member;

public interface IMemberService {
	Member getMemberLogin(Member paramMember);
	
	void addMember(Member paramMember);
}
