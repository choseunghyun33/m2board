<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>상세보기</h1>
		<table>
			<tr>
				<th>번호</th>
				<td>${m.boardNo}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${m.title}</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${m.id}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${m.createDate}</td>
			</tr>
			<tr>
				<th>조회</th>
				<td>${m.readCnt}</td>
			</tr>
			<tr>
				<th>
					<a href="${pageContext.request.contextPath}/addNice?boardNo=${m.boardNo}">
						좋아요
					</a>
				</th>
				<td>${m.nice}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${m.content}</td>
			</tr>
		</table>
</body>
</html>