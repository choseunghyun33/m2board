<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시판 목록</h1>
	
	<a href="${pageContext.request.contextPath}/addBoard">글쓰기</a>
	
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회</th>
				<th>좋아요</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="l" items="${list}">
					<tr>
						<td>${l.boardNo}</td> <!-- 실제 생성되는 것은 b.getBoardNo() -->
						<td>
							<a href="${pageContext.request.contextPath}/boardOne?boardNo=${l.boardNo}">
								${l.title}
							</a>
						</td>
						<td>${l.id}</td>
						<td>${l.createDate}</td>
						<td>${l.readCnt}</td>
						<td>${l.nice}</td>
					</tr>
			</c:forEach>
		</tbody>
	</table>
	<!-- 페이징 -->
	<div>
		<c:if test="${currentPage > 1}">
			<a href="${pageContext.request.contextPath}/boardList?currentPage=${currentPage-1}">이전</a>
		</c:if>
		<c:if test="${currentPage < lastPage}">
			<a href="${pageContext.request.contextPath}/boardList?currentPage=${currentPage+1}">다음</a>
		</c:if>
	</div>
</body>
</html>