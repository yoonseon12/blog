<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>운영자-멤버레벨수정</title>
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
		.text{
			height:30px;
			width: 200px;
			font-size:17px;
		}
		.select{
			height:30px;
			width: 170px;
			font-size:17px;
		}
		.marginBottom5{
			margin-bottom: 5px;
		}
		.marginBottom10{
			margin-bottom: 10px;
		}
		#btn{
			width: 60px;
			height: 40px;
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
				<h1>회원 레벨 수정</h1>
				<div>
					<form method="post" action="${pageContext.request.contextPath}/admin/UpdateMemberLevel">
						<div class="marginBottom10">
							<div class="marginBottom5">
								<span>회원 아이디</span>
							</div>
							<div>
								<input class="text" type="text" readonly="readonly" name="memberId" value="${member.memberId}">
							</div>
						</div>
						<div class="marginBottom10">	
							<div>
								<div class="marginBottom5">
									<span>회원 레벨</span>
									<span style="color:orange;">*</span>
								</div>	
							</div>
							<div>
								<select class="select" name="memberLevel">
									<option class="select" value="">==선택해주세요==</option>
									<option class="select" value="1">1 : 관리자</option>
									<option class="select" value="10">10 : 일반멤버</option>
								</select>
							</div>
						</div>
						<div class="marginBottom10">	
							<div class="marginBottom5">
								<span>회원가입 일</span>
							</div>
							<div>
								<input class="text" type="text" readonly="readonly" value="${member.memberDate}">
							</div>
						</div>	
						<div>
							<button id="btn" type="submit">저장</button>
						</div>
					</form>
				</div>
			</div>	
		</div>
	</body>
</html>

