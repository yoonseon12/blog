<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>비밀번호 변경</title>
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
			margin-left: 530px;
			margin-right:500px;
			width:1000px;
			background: white;
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
		</style>
	</head>
	<body>
		<!-- subject(나라이름) list 출력 -->
		<div id="aside">
			<jsp:include page="/WEB-INF/views/inc/sideMenu.jsp"></jsp:include>
		</div>
		<div id="section">
			<div id="main">
				<h1>비밀번호 변경</h1>
				<div>
					<form method="post" action="${pageContext.request.contextPath}/member/UpdateMemberPWServlet">
						<div>
							<input type="hidden" name="memberId" value="${loginMember.memberId}">
						</div>
						<div>
							<div>
								<span>변경 비밀번호</span>
								<span style="color:orange;">*</span>
							</div>
							<div>	
								<input class="text" type="password" name="memberPw1">
							</div>	
						</div>
						<div>
							<div>
								<span>변경 비밀번호 확인</span>
								<span style="color:orange;">*</span>
							</div>
							<div>
								<input class="text" type="password" name="memberPw2">
							</div>	
						</div>
						<div>
							<button id="btn" type="submit">저장</button>
						</div>
						<div>
							<p><strong>${msg}</strong></p>
						</div>
						<div>
							<span>비밀번호를 변경하면 변경된 비밀번호로 재 로그인 해야합니다.</span>
						</div>
					</form>
				</div>
			</div>	
		</div>
	</body>
</html>

