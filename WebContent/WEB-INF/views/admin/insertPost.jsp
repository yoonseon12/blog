<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>관리자-포스팅 글 입력</title>
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
		.menuLink:hover{
			font-size:20px;
			font-weight: 700;
		}
		.text{
			height:30px;
			width: 500px;
			font-size:17px;
		}
		#btn{
			width: 80px;
			height: 30px;
		}
		.content{
			width:994px;
			height:240px;
		    border-collapse: collapse;
		    border:1px soild gray;
		    font-size:17px;
		}
		.select{
			height:30px;
			width: 200px;
			font-size:14px;
		}
		.bottomMargin10{
			margin-bottom: 10px;
		}
		.bottomMargin5{
			margin-bottom: 5px;
		}
		#admin{
			height:30px;
			width: 100px;
			font-size:17px;
			text-align: center;
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
				<h1>포스팅 글쓰기</h1>
				<hr>
				<div>
					<form method="post" action="${pageContext.request.contextPath}/admin/InsertPostServlet">
						<div class="bottomMargin10" style="margin-top:20px;">
							<div class="bottomMargin5">
								<span>서브젝트</span>
								<span style="color:orange;">*</span>
							</div>
							<div>
								<select class="select" name="subjectName">
									<option value="">==서브젝트를 선택하세요==</option>
									<c:forEach var="subject" items="${subjectList}">
										<option class="select" value="${subject.subjectName}">${subject.subjectName}</option>
									</c:forEach>
								</select>
							</div>
						</div>	
						<div class="bottomMargin10">
							<div class="bottomMargin5">
								<span>제목</span>
								<span style="color:orange;">*</span>
							</div>
							<div>
								<input class="text" type="text" name="postTitle">
							</div>	
						</div>
						<div class="bottomMargin10">
							<div class="bottomMargin5">
								<span>내용</span>
								<span style="color:orange;">*</span>
							</div>	
							<div>
								<textarea class="content" name="postContent"></textarea>
							</div>
						</div>
						<div class="bottomMargin10">
							<div class="bottomMargin5">
								<span>작성자</span>
							</div>	
							<div>
								<input id="admin" type="text" name="memberId" value="${loginMember.memberId}" readonly="readonly">
							</div>	
						</div>
						<div style="text-align: center;">
							<button id="btn" type="submit">등록</button>
						</div>
					</form>
					<div>
						<p><strong>${msg}</strong></p>
					</div>
				</div>
			</div>	
		</div>
	</body>
</html>

