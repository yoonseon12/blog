<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원가입</title>
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
			height: 3000px;
			position: fixed;
			background: #353535;
			overflow: hidden;
			float: left;
		}
		#section {
			margin-left: 200px;
			background: white;
			width:1720px;
			text-align: center;
		}
		#main {
			padding-top:100px;
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
		.text{
			width: 200px;
			height: 30px;
			margin-top: 5px;
			margin-bottom: 10px;
			font-size: 19px;
		}
		#btn{
			width: 80px;
			height: 30px;
		}
		.left{
			text-align: left !important;
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
				<!-- 제목 -->
				<div>
					<h2>회원가입</h2>
				</div>
				<!-- 회원가입 란 -->
				<div>
					<form method="post" action="${pageContext.request.contextPath}/SignUp">
						<div style="width:1575px;">
							<span>아이디</span>
							<span style="color:orange;">*</span>
						</div>
						<div>	
							<input class="text" type="text" name="memberId">
						<div>
						<div style="width:1590px;">
							<span>비밀번호</span>
							<span style="color:orange;">*</span>
						</div>	
							<input class="text" type="password" name="memberPw1">
						</div>
						<div>
						<div style="width:1630px;">
							<span>비밀번호 확인</span>
							<span style="color:orange;">*</span>
						</div>	
							<input class="text" type="password" name="memberPw2">
						</div>
						<div style="margin-top:20px; margin-bottom:20px">
							<button id="btn" type="submit">가입하기</button>
						</div>
						<div>
							<c:if test="${IdMsg!=null}">
								<span><Strong>${IdMsg}</Strong></span>
							</c:if>
							<c:if test="${msg!=null}">
								<p><strong>${msg}</strong></p>
							</c:if>
						</div>
					</form>	
				</div>
			</div>	
		</div>
	</body>
</html>

