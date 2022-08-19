package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vo.Member;

public class MemberDao implements IMemberDao {

	@Override
	public Member selectMemberLogin(Connection conn, Member paramMember) throws Exception {
		// 리턴값 초기화
		Member member = null;
		
		// 쿼리
		String sql = "select id, name from member where id = ? and pw = password(?)";
		
		// 초기화
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			// stmt setter
			stmt.setString(1, paramMember.getId());
			stmt.setString(2, paramMember.getPw());
			
			// 디버깅
			System.out.println("MemberDao.java selectMemberLogin stmt : " + stmt);
			
			// 쿼리실행
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				member = new Member();
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				
				// 디버깅
				System.out.println("MemberDao.java member : " + member.toString());
			}
		} finally {
			if(rs != null) { rs.close(); }
			if(stmt != null) { stmt.close();}
		}
		
		return member;
	}

	
	// 회원가입
	@Override
	public int insertMember(Connection conn, Member paramMember) throws Exception {
		// 리턴값 초기화
		int row = 0;
		
		// 쿼리
		String sql = "insert into member (id, pw, name, addr) values (?, password(?), ?, ?)";
		
		// 초기화
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(sql);
			// stmt setter
			stmt.setString(1, paramMember.getId());
			stmt.setString(2, paramMember.getPw());
			stmt.setString(3, paramMember.getName());
			stmt.setString(4, paramMember.getAddr());
			
			// 디버깅
			System.out.println("MemberDao.java insertMember stmt : " + stmt);
			
			// 쿼리 실행
			row = stmt.executeUpdate();
			
		} finally {
			if(stmt != null) { stmt.close(); }
		}
		
		return row;
	}

}
