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
			height: 100%;
			position: fixed;
			background: #353535;
			overflow: hidden;
			float: left;
		}
		#section {
			margin-left: 220px;
			text-align: center;
		}
		#main {
			width: 400px;
			margin: 0px auto;
			padding-top:100px;
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
		table{
			width: 400px;
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
				<div>
					<h1>LOGIN</h1>
					<div style="padding: 0px 100px;">
						<form method="post" action="${pageContext.request.contextPath}/LoginServlet">
							<div style="text-align: left;">
								<span>아이디</span>
							</div>
							<div>	
								<input class="text" type="text" name="memberId" value="user">
							</div>
							<div style="text-align: left;">
								<span>비밀번호</span>
							</div>
							<div>	
								<input class="text" type="password" name="memberPw" value="1111">
							</div>
							<div style="margin-top:20px; margin-bottom:20px">
								<button id="btn" type="submit">로그인</button>
							</div>
							<p><strong>${msg}</strong></p>	
						</form>
						<div>
							<a style="text-decoration: none; text-decoration: underline; color:blue;" href="${pageContext.request.contextPath}/SignUp">아이디가 없으신가요?</a>
						</div>
					</div>
					<br>
					<div>
						<table>
							<tr>
								<td>
									<span>DEMO용 관리자 계정</span><br>
									<span>ID : admin | PW : 1111</span>
								</td>
								<td>
									<span>DEMO용 회원 계정</span><br>
									<span>ID : user | PW : 1111</span>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>

