<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="kim" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
<link rel="stylesheet" href="css/main.css" type="text/css" />
<script type="text/javascript">
	function signup(){
		window.open("signup.jsp","회원가입 페이지","width=500,height=200");
	}
</script>
</head>
<body>
	
	<div id="header">
		<h1>즐거운 JSP</h1>
		<c:if test="${mid!=null}">
			<h5>${mid} 님 방가워요</h5>		
		</c:if>
		<div class="gnb">
			<ul>
				<li><a href="main.do">메인으로</a></li>
				
				<li><a href="javascript:signup()">회원가입</a></li>
				<li><kim:login /></li>
				<c:if test="${mid!=null}">
					<li> <a href="myboard.do?mid=${mid}">내글 보기</a> </li>
					
				</c:if>
				<!-- 회원탈퇴기능 글쓴적이 있거나 댓글 쓴적이 있을떼 버튼 생성 -->				
				<c:choose>
				<c:when test="${mid!=null&&mb==0&&rc==0}">
					<li>  
					<label for="dm">회원 탈퇴</label>
					<form action="deleteMember.do?mid=${mid}" method="post">
					<input type="hidden" name="mid" value="${mid}">
					<input type="password" placeholder="비밀번호 입력" required="required" name="mpw" id="dm">
					<input type="submit" value="회원탈퇴하기!">
					</form>
					</li>
				</c:when>
				<c:when test="${mid!=null}">
					<li>회원탈퇴를 원하시면 작성한 글과 댓글을 삭제하시오 ㅋㅋ</li>
				</c:when>
				</c:choose>
				<li>방가방가새로운 친구들^^</li>
			</ul>
				<ol id="flex">
			<c:forEach var="as" items="${mdatas}">
				<!-- <c:set var="newFriend" value="as.mid"/>-->
				<li class="nar"> <a href="myboard.do?mid=${as.mid}">${as.mid}</a></li>
					
			</c:forEach>
			
				</ol>
		</div>
	</div>
	
	<div id="wrapper">
	
		<div id="content">
			<h2>글 등록하기</h2>
			<kim:write type="msg" />
		</div>
		
		<div id="main">
			<h2>글 목록보기</h2>
			<c:forEach var="v" items="${datas}">
				<c:set var="b" value="${v.boardVO}" />
				<h3>[${b.mid}] ${b.msg} [ 좋아요 ${b.favcnt} | 댓글 ${b.rcnt} ] <kim:board midCheck="${b.mid}" bid="${b.bid}" /></h3>
								
				<div class="reply">
					<ul>
						<c:forEach var="r" items="${v.rList}">
							<li>[${r.mid}] ${r.rmsg} <kim:reply midCheck="${r.mid}" rid="${r.rid}" /></li>
						</c:forEach>
					</ul>
				</div>
			
			<div class="reply">
				<kim:write type="rmsg" bid="${b.bid}" />
			</div>
			</c:forEach>
		</div>
		<!--${cnt+5}<-- 숫자를 입력 하면 더보기 페이지 개수가 바뀜  -->
		  <!-- 한번에 보여지는 글개수가가 전체 글개수가 많으면 사라짐  -->
			
			<!--  내글보기에 만약에 url 에 아이디가 있다면-->
			<!-- 내글보기에 더보기 -->
			
			
			<c:choose>
				<c:when test="${param.mid!=null}">
					<c:if test="${cnt < b.mb}">
						<a href="main.do?cnt=${cnt+5}&mid=${b.mid}">5개씩보기[${cnt}/${b.mb}]</a>
					</c:if>
				</c:when>
				<c:otherwise>
			<!--아니면 -->
			<!-- 일반 더보기 -->
				<c:if test="${cnt < b.bcnt}">
			<a href="main.do?cnt=${cnt+5}">5개씩보기[${cnt}/${b.bcnt}]</a>
			
				</c:if>
				</c:otherwise>
		</c:choose>
		<!-- 더보기&gt;&gt; -->
	</div>
		
	<div id="footer">
		회사소개 | 이용약관 | <strong>개인정보처리방침</strong> | 보호정책 | 고객센터 <strong>ⓒ Corp.</strong>
	</div>

</body>
</html>