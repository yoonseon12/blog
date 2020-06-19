<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${subjectName} Post</title>
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
		.hover:hover{
			font-weight:bolder;
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
				<div class="width_fixing">
					<h1>${subjectName} Post</h1>
				</div>	
				<div>
					<div class="width_fixing">
						<h3>포스트 목록</h3>
					</div>
					<c:if test="${postList.size() != 0}">
						<table id="table">
							<tr style="border: 1px soild red !important;">
								<th width="150">No</th>
								<th width="300">제목</th>
								<th>작성자</th>
								<th width="250">작성일</th>
							</tr>
							<c:forEach var="post" items="${postList}">
								<tr>
									<td>${post.postNo}</td>
									<td>
										<a class="hover" href="${pageContext.request.contextPath}/SelectPostOneServlet?postNo=${post.postNo}">${post.postTitle}</a>
									</td>
									<td>${post.memberId}</td>
									<td>${post.postDate}</td>
								</tr>
							</c:forEach>
						</table>
						
						<!-- 페이지링크 -->
						<div class="page" style="text-align: center;">
							<a class="aTag" href="${pageContext.request.contextPath}/SelectPostBySubjectServlet?subjectName=${subjectName}&currentPage=1">처음</a>
							<c:if test="${currentPage==1}">
								<a class="aTag" href="${pageContext.request.contextPath}/SelectPostBySubjectServlet?subjectName=${subjectName}&currentPage=1">이전</a>
							</c:if>
							<c:if test="${currentPage > 1}">
								<a class="aTag" href="${pageContext.request.contextPath}/SelectPostBySubjectServlet?subjectName=${subjectName}&currentPage=${currentPage-1}">이전</a>
							</c:if>
							-${currentPage}-
							<c:if test="${currentPage < lastPage}">
								<a class="aTag" href="${pageContext.request.contextPath}/SelectPostBySubjectServlet?subjectName=${subjectName}&currentPage=${currentPage+1}">다음</a>
							</c:if>
							<c:if test="${currentPage == lastPage}">
								<a class="aTag" href="${pageContext.request.contextPath}/SelectPostBySubjectServlet?subjectName=${subjectName}&currentPage=${lastPage}">다음</a>
							</c:if>
							<a class="aTag" href="${pageContext.request.contextPath}/SelectPostBySubjectServlet?subjectName=${subjectName}&currentPage=${lastPage}">마지막</a>
						</div>
					</div>
				</c:if>
				<c:if test="${postList.size() == 0}">
				<div>
					<p><strong>작성된 글이 없습니다.</strong></p>
				</div>
				</c:if>
			</div>	
		</div>
	</body>
</html>

