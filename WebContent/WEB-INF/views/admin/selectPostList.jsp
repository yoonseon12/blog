<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>관리자-포스트관리</title>
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
			color:blue;
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
				<h1>포스팅 관리</h1>
				<div>
					<h3>전체 포스트 목록</h3>
					<c:if test="${postList.size() != 0}">
						<table id="table">
							<tr>
								<th>No</th>
								<th>서브젝트</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>삭제</th>
							</tr>
							<c:forEach var="post" items="${postList}">
								<tr>
									<td width=60>${post.postNo}</td>
									<td width=100>${post.subjectName}</td>
									<td>
										<a class="hover" href="${pageContext.request.contextPath}/SelectPostOneServlet?postNo=${post.postNo}">${post.postTitle}</a>
									</td>
									<td width =60>${post.memberId}</td>
									<td width=200>${post.postDate}</td>
									<td width=60>
										<a class="hover" href="${pageContext.request.contextPath}/admin/DeletePostServlet?postNo=${post.postNo}">삭제</a>
									</td>
								</tr>
							</c:forEach>
						</table>
						<div>
							<a class="hover" style="padding-left:20px;" href="${pageContext.request.contextPath}/admin/InsertPostServlet">글쓰기</a>
						</div>
						<div class="page" style="text-align: center;">
							<a class="aTag" href="${pageContext.request.contextPath}/admin/SelectPostListServlet?currentPage=1">처음</a>
							<c:if test="${currentPage == 1}">
								<a class="aTag" href="${pageContext.request.contextPath}/admin/SelectPostListServlet?currentPage=1">이전</a>
							</c:if>
							<c:if test="${currentPage > 1}">
								<a class="aTag" href="${pageContext.request.contextPath}/admin/SelectPostListServlet?currentPage=${currentPage-1}">이전</a>
							</c:if>
							-${currentPage}-
							<c:if test="${currentPage < lastPage}">
								<a class="aTag" href="${pageContext.request.contextPath}/admin/SelectPostListServlet?currentPage=${currentPage+1}">다음</a>
							</c:if>
							<c:if test="${currentPage == lastPage}">
								<a class="aTag" href="${pageContext.request.contextPath}/admin/SelectPostListServlet?currentPage=${lastPage}">다음</a>
							</c:if>
							<a class="aTag" href="${pageContext.request.contextPath}/admin/SelectPostListServlet?currentPage=${lastPage}">마지막</a>
						</div>
					</c:if>	
					<c:if test="${postList.size() == 0}">
						<div>
							<p><strong>등록된 글이 없습니다.</strong></p>
						</div>
					</c:if>
				</div>
			</div>	
		</div>
	</body>
</html>

