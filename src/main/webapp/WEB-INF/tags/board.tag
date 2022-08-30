<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

<%@ attribute name="midCheck" %>
<%@ attribute name="bid" %>

<c:if test="${mid==midCheck}">
<a href="deleteB.do?bid=${bid}&cnt=${cnt}">[삭제]</a>
</c:if>

<a href="fav.do?bid=${bid}&cnt=${param.cnt}">&nbsp;♥</a>

 
 
