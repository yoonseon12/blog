<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>관리자-전체회원조회</title>
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
				<h1>회원 관리</h1>
				<div>
					<h3>회원목록</h3>
					<p>레벨 0  관리자 (운영자)</p>
					<p>레벨 1 ~ 9 관리자</p>
					<p>레벨 10 ~ 일반회원</p>
					<c:if test="${memberList.size() != 0}">
						<table id="table">
							<tr>
								<th>아이디</th>
								<th>등급</th>
								<th>레벨</th>
								<th>
									<c:if test="${loginMember.memberLevel==0}">
										회원 레벨 수정
									</c:if>
								</th>
							</tr>
							<c:forEach var="member" items="${memberList}">
								<tr>
									<td>${member.memberId}</td>
									<td>
										<c:if test="${member.memberLevel>=10}">
											<span>일반회원</span>
										</c:if>
										<c:if test="${member.memberLevel<10}">
											<span>관리자</span>
										</c:if>
										<c:if test="${member.memberLevel==0}">
											<span>(운영자)</span>
										</c:if>
									</td>	
									<td>
										<span>${member.memberLevel}</span>
									</td>
									<td>
										<c:if test="${loginMember.memberLevel==0}">
											<a class="textColor" href="${pageContext.request.contextPath}/admin/UpdateMemberLevel?memberId=${member.memberId}&memberLevel=${member.memberLevel}">레벨 변경</a>
										</c:if>	
									</td>
								</tr>
							</c:forEach>		
						</table>
						<div>
							<p><strong>${msg}</strong></p>
						</div>
						<div class="page" style="text-align: center;">
							<a class="aTag" href="${pageContext.request.contextPath}/admin/SelectMemberListServlet?currentPage=1">처음</a>
							<c:if test="${currentPage==1}">
								<a class="aTag" href="${pageContext.request.contextPath}/admin/SelectMemberListServlet?currentPage=${currentPage=1}">이전</a>
							</c:if>
							<c:if test="${currentPage>1}">
								<a class="aTag" href="${pageContext.request.contextPath}/admin/SelectMemberListServlet?currentPage=${currentPage-1}">이전</a>
							</c:if>
							-${currentPage}-
							<c:if test="${currentPage<lastPage}">
								<a class="aTag" href="${pageContext.request.contextPath}/admin/SelectMemberListServlet?currentPage=${currentPage+1}">다음</a>
							</c:if>
							<c:if test="${currentPage==lastPage}">
								<a class="aTag" href="${pageContext.request.contextPath}/admin/SelectMemberListServlet?currentPage=${lastPage}">다음</a>
							</c:if>
							<a class="aTag" href="${pageContext.request.contextPath}/admin/SelectMemberListServlet?currentPage=${lastPage}">마지막</a>
						</div>
					</c:if>
					<c:if test="${memberList.size() == 0}">
						<div>
							<p><strong>가입한 멤버가 없습니다.</strong></p>
						</div>
					</c:if>
				</div>
			</div>	
		</div>
	</body>
</html>

