<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${param.insertM==false}">
			<h1>아이디가 이미 존재합니다</h1>
		</c:when>
		<c:otherwise>
			<h1>방가워요</h1>
		</c:otherwise>
	</c:choose>
	<button onclick="window.close();">닫기</button>
</body>
</html>