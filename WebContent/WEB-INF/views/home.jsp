<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Home</title>
		<style>
			body {
			padding: 0;
			margin: 0;
			width: 100%;
			height: 100%;
			overflow: hidden;
			background-position: 0 0;
			background-repeat: no-repeat;
			background-attachment: fixed;
			background-size: cover;
			position: relative;
			overflow-y: auto;
		}
		#aside {
			width: 200px;
			height: 100%;
			position: fixed;
			background: #353535;
			overflow: hidden;
			float: right;
		}
		#section {
			margin-left: 220px;
			background: white;
		}
		#main {
			margin : 0px auto;
			width:1000px;
			padding-top:50px;
			padding-bottom:50px;
		}
		#menu{
			text-align: center; 
			margin-top: 30px;
			font-size: 30px;
			color: white;
		}
		.menuLink{
			color:white;
			text-decoration:none;
			font-size:19px;
		}
		.menuLink:hover{
			font-size:20px;
			font-weight: 700;
		}
		.hover:hover{
			font-weight: bolder;
		}
		a{
			text-decoration: none !important;
			color: black;
		}
		.color{
			color:blue;
		}
		</style>
	</head>
	<body>
		<!-- subject(나라이름) list 출력 -->
		<div id="aside">
			<jsp:include page="/WEB-INF/views/inc/sideMenu.jsp"></jsp:include>
		</div>
		<div id="section">
			<div id="main">
				<h1>Home</h1>
				<div style="margin-bottom:20px;">
					<!-- 로그아웃 상태 -->
					<c:if test="${loginMember==null}">
						<a class="color hover" href="${pageContext.request.contextPath}/LoginServlet">로그인</a>
						<a class="color hover" href="${pageContext.request.contextPath}/SignUp">회원가입</a>
					</c:if>
					<!-- 로그인 상태 -->
					<c:if test="${loginMember!=null}">
						${loginMember.memberId}님 안녕하세요.
						<a class="color hover" href="${pageContext.request.contextPath}/LogoutServlet">로그아웃</a>
						<a class="color hover" href="${pageContext.request.contextPath}/member/SelectMemberServlet">회원정보조회</a>
					</c:if>
				</div>
				<!-- 메인 내용 -->
				<div>
					<!-- 관리자만 확인 가능 -->
					<div style="margin-bottom: 30px;">
						<c:if test="${(loginMember != null) && (loginMember.memberLevel < 10)}">
						<h3>관리자 메뉴</h3>
							<div>
								<a class="color hover" href ="${pageContext.request.contextPath}/admin/AdminHomeServlet">관리자 페이지</a>
							</div>
						</c:if>
					</div>
					<!-- 누구나 확인가능 -->
					<div>
						<!-- 등급별 권한 소개 -->
						<div style="margin-bottom: 50px;">
							<h3>등급별 권한</h3>
							<p>일반회원 : 댓글작성 가능, 좋아요 가능</p>
							<p>관리자 : 일반회원 권한 +회원목록확인, 서브젝트 CRUD 가능, 포스팅 글 CRUD가능, 일반회원 댓글삭제 가능</p>
							<p>운영자 : 관리자 권한 + 회원레벨수정 가능(일반회원 -> 관리자 승격 가능)</p>
						</div>
						<!-- 개발 프로그램 -->
						<div>
							<div style="margin-bottom:20px;">
								<span style="font-size: 20px; font-weight: 700;">MODEL2방식으로 구현하였으며 JSP / JSTL을 사용한 프로젝트입니다.</span>
							</div>
							<div style="margin-bottom:20px;">
								<span style="font-size:20px;"><strong>개발 환경</strong></span>
							</div>
							<div>
								<div style="margin-bottom: 40px;">
									<img width='150' height='150' src="${pageContext.request.contextPath}/imgs/java.jpg">
									<span>JAVA 1.80_241</span>
									<span style="padding-left:100px;">
										<img width='150' height='150' src="${pageContext.request.contextPath}/imgs/eclipse.jpg">
										<span>ECLIPSE 4.14.0</span>
									</span>	
								</div>
								<div>
									<img width='150' height='150' src="${pageContext.request.contextPath}/imgs/mariadb.jpg">
									<span>MARIA DB 10.4</span>
									<span style="padding-left:100px;">
										<img width='150' height='150' src="${pageContext.request.contextPath}/imgs/tomcat.jpg">
										<span>APACHE-TOMCAT 9.0.30</span>
									</span>	
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>		
	</body>
</html>