<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>관리자 페이지</title>
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
		.marginBottom10{
			margin-bottom: 10px;
		}
		.hover:hover{
			color:red;
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
				<h1>관리자 페이지</h1>
				<div class="marginBottom10">
					<a class="color hover" href ="${pageContext.request.contextPath}/admin/SelectMemberListServlet">회원관리</a>
				</div>
				<div class="marginBottom10">
					<a class="color hover" href ="${pageContext.request.contextPath}/admin/SelectSubjectListServlet">서브젝트 관리</a>
				</div>
				<div class="marginBottom10">
					<a class="color hover" href ="${pageContext.request.contextPath}/admin/SelectPostListServlet">포스팅 관리</a>
				</div>
			</div>	
		</div>
	</body>
</html>

