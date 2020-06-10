<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>관리자-${subject.subjectName} 서브젝트 수정</title>
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
			width: 250px;
			height: 30px;
			margin-top: 5px;
			margin-bottom: 10px;
			font-size: 15px;
		}
		#btn{
			width: 60px;
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
				<h1>${subject.subjectName} 서브젝트 수정</h1>
				<div>
					<div>
						<span>선택한 서브젝트</span>
					</div>
					<form method="post" action="${pageContext.request.contextPath}/admin/UpdateSubjectServlet">
						<div>
							<c:if test="${subjectName == null}">
								<input class="text" type="text" name ="subjectName" value="${subject.subjectName}" readonly="readonly">
							</c:if>
							<c:if test="${subjectName != null}">
								<input class="text" type="text" name ="subjectName" value="${subjectName}" readonly="readonly">
							</c:if>	
						</div>
						<div>
							<span>서브젝트 이름</span>
							<span style="color:orange;">*</span>
						</div>
						<div>
							<input class="text" type="text" name="newSubjectName" placeholder="수정할 서브젝트 이름을 입력하세요.">
						</div>
						<div>
							<button id="btn" type="submit">수정</button>
						</div>
					</form>
				</div>
				<c:if test="${msg == null}">
					<div>
						<p>[${subject.subjectName}] 서브젝트에 포스팅된 글이 있다면  수정할 수 없습니다.</p>
					</div>
				</c:if>
				<c:if test="${msg != null}">
					<div>
						<p>${msg}</p>
					</div>
				</c:if>
			</div>	
		</div>
	</body>
</html>

