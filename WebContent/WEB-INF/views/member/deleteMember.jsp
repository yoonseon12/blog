<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 탈퇴</title>
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
		.text{
			width: 230px;
			height: 30px;
			margin-top: 5px;
			margin-bottom: 10px;
			font-size: 15px;
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
				<h1>회원 탈퇴</h1>
				<div>
					<form method="post" Action="${pageContext.request.contextPath}/member/DeleteMemberServlet">
						<div>
							<input class="text" type="hidden" name="memberId" value="${loginMember.memberId}">
							<input class="text" type="password" name="memberPw" placeholder="비밀번호를 입력해주세요.">
						</div>
						<div>
							<button id="btn" type="submit">회원탈퇴</button>
						</div>
					</form>
					<div style="margin-top: 10px;">
						<span>탈퇴하면 [ ${loginMember.memberId} ] 아이디로 재가입 할 수 없습니다.</span>
					</div>
					<div>
						<p><strong>${msg}</strong></p>
					</div>
				</div>
			</div>	
		</div>
	</body>
</html>

