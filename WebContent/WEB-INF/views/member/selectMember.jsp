<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원 정보</title>
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
		table{
			width:500px;
		    border-collapse: collapse;
		    text-align: center;
		    position: sticky;
		}
		th{
			font-size: 18px;
			border: 1px soild red !important;
			border-collapse: collapse;
			padding : 10px 0px;
		}
		.borderLine{
			border-right: 1px solid #BDBDBD;
			border-bottom: 1px solid #BDBDBD;
		}
		.borderBottomLine{
			border-bottom: 1px solid #BDBDBD !important; 
		}
		td{
			padding : 20px 20px;
			font-size: 17px;
		}
		.width_fixing{
			width:1575px;
		}
		a{
			text-decoration: none !important;
			color: black;
		}
		.textColor{
			color:blue;
		}
		.textColor:hover{
			font-weight: bolder;
		}
		.aTag{
			padding-right: 15px;
			padding-left: 15px;
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
				<div class="width_fixing">
					<h1>회원 정보</h1>
				</div>
				<div class="width_fixing">
					<div>
						
					</div>
					<table id="table" style="text-align: center; margin-bottom: 20px;">
						<tr>
							<th width='125' class="borderLine">아이디</th>
							<td class="borderBottomLine">${member.memberId}</td>
						</tr>	
						<tr>
							<th class="borderLine">등급</th>
							<td class="borderBottomLine">
								<c:if test="${member.memberLevel>=10}">
									<span>&nbsp;일반 회원</span>
								</c:if>
								<c:if test="${member.memberLevel<10}">
									<span>&nbsp;관리자</span>
								</c:if>
								<c:if test="${member.memberLevel==0}">
									<span>(운영자)</span>
								</c:if>
							</td>
						</tr>
						<tr>
							<th style="border-right: 1px solid #BDBDBD;">가입일</th>
							<td>${member.memberDate}</td>
						</tr>
					</table>
					<!-- 링크 -->
					<div>
						<span>	
							<a class="textColor aTag" href="${pageContext.request.contextPath}/member/UpdateMemberPWServlet">비밀번호 변경</a>
						</span>
						<span>
							<a class="textColor aTag" href="${pageContext.request.contextPath}/member/DeleteMemberServlet">회원탈퇴</a>
						</span>
					</div>
				</div>
			</div>	
		</div>
	</body>
</html>

