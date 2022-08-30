<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${mid != null}">
		<a href="logout.do">로그아웃</a>
	</c:when>
	<c:otherwise>
	<form action="login.do" method="post">
	<!-- 1로그인 시 페이지 유지하기위해 히든값을 줌 -->
		<input type="hidden" name="cnt" value="${param.cnt}">
		ID&nbsp;<input type="text" name="mid" placeholder="id 입력" required="required">&nbsp;&nbsp;
		PW&nbsp;<input type="password" name="mpw"required="required" placeholder="비빌번호">&nbsp;&nbsp;
		<input type="submit" value="로그인">
	</form>
	</c:otherwise>
</c:choose>
