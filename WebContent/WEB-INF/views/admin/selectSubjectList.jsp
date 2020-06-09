<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>관리자-서브젝트 관리</title>
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
		table{
			width:1000px;
		    border-collapse: collapse;
		    text-align: center;
		    position: sticky;
		}
		th{
			font-size: 20px;
			border: 1px soild red !important;
			border-bottom: 1px solid black;
			border-collapse: collapse;
			padding : 10px 0px;
		}
		td{
			padding : 20px 20px;
			font-size: 17px;
		}
		.page{
			width:1000px;
			margin: 20px auto;
		}
		.width_fixing{
			width:1000px;
		}
		a{
			text-decoration: none !important;
			color: black;
		}
		.textColor{
			color:blue;
			display: block;
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
				<!-- 제목 -->
				<div>
					<h1>서브젝트 관리</h1>
				</div>
				<!-- 서브젝트 목록 -->
				<div>
					<div>
						<h3>서브젝트 목록</h3>
					</div>
					<div>
						<c:if test="${subjectList.size() != 0}">
							<div>
								<table id="table">
									<tr>
										<th>번호</th>
										<th>서브젝트 이름</th>
										<th>삭제</th>
										<th>수정</th>
									</tr>
									<c:forEach var="subject" items="${subjectList}">
										<tr>
											<td>${subject.subjectNo}</td>	
											<td>${subject.subjectName}</td>
											<td>
												<a class="textColor" href="${pageContext.request.contextPath}/admin/DeleteSubjectServlet?subjectNo=${subject.subjectNo}">삭제</a>
											</td>
											<td>
												<a class="textColor" href="${pageContext.request.contextPath}/admin/UpdateSubjectServlet?subjectNo=${subject.subjectNo}">수정</a>
											</td>
										</tr>
									</c:forEach>
								</table>
							</div>
						</c:if>	
					</div>
				</div>	
				<!--경고 문구 -->
				<div>
					<c:if test="${subjectList.size() == 0}">
						<div>
							<p><strong>등록된 글이 없습니다.</strong></p>
						</div>
					</c:if>
					<c:if test="${deleteMsg == null}">
						<p>삭제하려는 서브젝트에 포스팅된 글이 있으면 삭제할 수 없습니다.</p>
					</c:if>
					<c:if test="${deleteMsg != null}">
						<p><strong>${deleteMsg}</strong></p>
					</c:if>
				</div>
				<!-- 데이터 추가 -->
				<div style="margin-top:70px;">
					<div>
						<h3>데이터 추가</h3>
					</div>
					<div>
						<form method="post" action="${pageContext.request.contextPath}/admin/InsertSubjectServlet">
							<div>
								<div>
									서브젝트 이름
									<span style="color:orange;">*</span>
								</div>
								<div>
									<input class="text" type="text" name="subjectName" placeholder="추가할 서브젝트 이름을 입력하세요.">
								</div>
								<div>
									<button id="btn" type="submit">추가</button>
								</div>
								<div>
									<p><strong>${msg}</strong></p>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>	
		</div>
	</body>
</html>

