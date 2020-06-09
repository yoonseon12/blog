<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${post.postTitle} 수정</title>
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
			padding-top:80px;
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
		.content{
			width:994px;
			height:240px;
		    border-collapse: collapse;
		    border:1px soild gray;
		    font-size:17px;
		}
		.comment{
			width:1000px;
			border:1px soild red;
		}
		th{
			font-size: 17px;
			border: 1px soild red !important;
			border-bottom: 1px solid black;
			border-collapse: collapse;
			padding : 10px 0px;
		}
		.contentTd{
			padding : 20px 20px;
			font-size: 17px;
			height: 200px;
		}
		#btn{
			width: 80px;
			height: 40px;
		}
		.color{
			color:#4641D9;
			padding:5px 15px;
		}
		.text{
			height:30px;
			width: 500px;
			font-size:17px;
		}
		.select{
			height:30px;
			width: 170px;
			font-size:17px;
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
				<!-- 제목, 서브젝트이름, 작성일자, 작성자 -->
				<div style="margin-bottom: 10px;">
					<div class="row" style="height:50px; position:relative; margin-bottom: 10px;">
						<div class="col-sm-9"style="">
							<span style="font-size: 40px; padding-right:10px;">${post.postTitle}</span>
							<span style="font-size:20px;">글 수정</span>
							<span style="font-size: 25px; padding-left: 20px; padding-right: 20px;">|</span>
							<span>${post.subjectName}</span>
						</div>
						<div class="col-sm-3" style="position:absolute; bottom:0px; right:0px;">
							<div>
								<span style="float:right; font-size: 14px;">작성자 ${post.memberId}</span>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<!-- 수정 란 -->
				<form method="post" action="${pageContext.request.contextPath}/member/UpdatePostOneServlet">
					<input type="hidden" name="postNo" value="${post.postNo}">
					<div>
						<!-- 서브젝트 이름 -->
						<div style="margin: 15px 0px;">
							<span style="padding: 0px 10px;">서브젝트</span>
							<select class="select" name="subjectName">
								<option value="${post.subjectName}">${post.subjectName}</option>
								<option disabled="disabled">==서브젝트 목록==</option>
								<c:forEach var="subject" items="${subjectList}">
									<option class="select" value="${subject.subjectName}">${subject.subjectName}</option>
								</c:forEach>
							</select>
						</div>	
						<!-- 포스팅 제목 -->
						<div style="margin: 10px 0px;">
							<span style="padding-left:10px; padding-right:42px;">제목</span>
							<input class="text" type="text" name="postTitle" value="${post.postTitle}">
						</div>
						<!-- 포스팅 내용 -->
						<div style="margin-bottom: 20px; margin-top:20px;">
							<textarea class="content" name="postContent">${post.postContent}</textarea>
						</div>
						<div style="text-align: center;">
							<button id="btn"type="submit">수정완료</button>
						</div>
					</div>
				</form>
			</div>	
		</div>
	</body>
</html>

