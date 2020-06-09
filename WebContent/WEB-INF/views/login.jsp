<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Login</title>
		<meta charset="UTF-8">
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
		<div id="aside">
			<!-- subject(나라이름) list 출력 -->
			<jsp:include page="/WEB-INF/views/inc/sideMenu.jsp"></jsp:include>
		</div>
		<div id="section">
			<div id="main">
				<h1>LOGIN</h1>
				<div>
					<form method="post" action="${pageContext.request.contextPath}/LoginServlet">
						<div style="width:1575px;">
							<span>아이디</span>
						</div>
						<div>	
							<input class="text" type="text" name="memberId">
						</div>
						<div style="width:1590px; margin-top:20px;">
							<span>비밀번호</span>
						</div>
						<div>	
							<input class="text" type="password" name="memberPw">
						</div>
						<div style="margin-top:20px; margin-bottom:20px">
							<button id="btn" type="submit">로그인</button>
						</div>
						<p><strong>${msg}</strong></p>	
					</form>
					<a href="${pageContext.request.contextPath}/SignUp">회원가입</a>
				</div>
			</div>
		</div>
	</body>
</html>

