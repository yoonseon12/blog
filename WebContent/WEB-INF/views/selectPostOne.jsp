<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${post.postTitle}</title>
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
		.content{
			width:1000px;
		    border: 1px solid #A6A6A6;
		}
		.comment{
			width:1000px;
		}
		th{
			font-size: 17px;
			border-bottom: 1px solid black;
			border-collapse: collapse;
			padding : 10px 0px;
		}
		.contentTd{
			padding : 10px 15px;
			font-size: 17px;
			height: 200px;
		}
		.commentTd{
			padding : 10px 20px 30px 20px;
			font-size: 15px;
			height: 40px;
		}
		#commentBtn{
			width: 118px;
			height: 97px;
			vertical-align: middle;
		}
		textarea {
			padding : 10px 10px;
			vertical-align: middle;
			width: 850px;
		}
		.aTag{
			padding-right: 15px;
			padding-left: 15px;
		}
		a{
			text-decoration: none;
			color: black;
		}
		.color{
			color:#4641D9;
		}
		.color:hover {
			font-weight: bolder;
		}
		.button{
			text-decoration: none;
			background-color: green;
			color:black;
			padding:5px 15px 5px 15px;
			margin:20px;
			display:inline-block;
			border-radius: 15px;
		}
		.commentLink{
			font-size:12px;
			position: absolute;
			bottom:0px;
		}
		.commentLink:hover {
			font-weight: bolder;
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
							<span style="font-size: 40px;">${post.postTitle}</span>
							<span style="font-size: 25px; padding-left: 20px; padding-right: 20px;">|</span>
							<span>${post.subjectName}</span>
						</div>
						<div class="col-sm-3" style="position:absolute; bottom:0px; right:0px;">
							<div>
								<span style="float:right; font-size: 14px;">${post.postDate}</span>
							</div>	
							<div>
								<span style="float:right; font-size: 14px;">작성자 ${post.memberId}</span>
							</div>
						</div>
					</div>
				</div>
				<hr>
				<!-- 포스트 내용 출력 -->
				<div style="margin-bottom: 20px; margin-top:20px;">
					<table class="content">
						<tr style="vertical-align: top;">
							<td class="contentTd">${post.postContent}</td>
						</tr>
					</table>
					<!-- 작성자만 볼 수 있는 게시글 수정 삭제 -->
					<div style="margin-top:15px;">
						<c:if test="${(loginMember.memberId==post.memberId)||(loginMember.memberLevel<10)}">
							<div style="float: right;">
								<a class="color" href="${pageContext.request.contextPath}/member/UpdatePostOneServlet?postNo=${post.postNo}&subjectName=${post.subjectName}">게시글수정</a>
								&nbsp;&nbsp;
								<a class="color" href="${pageContext.request.contextPath}/member/DeletePostServlet?postNo=${post.postNo}&subjectName=${post.subjectName}">게시글삭제</a>
								&nbsp;
							</div>	
						</c:if>
					</div>
				</div>
				<!-- 좋아요 -->
				<div>
					<div style="margin-bottom: 20px;">
						<!-- 좋아요 -->
						<c:if test="${loginMember==null}">
								<span style="color:#4641D9;">좋아요 </span>
								<span>${likeyMap.goodCount}</span>
						</c:if>
						<c:if test="${loginMember!=null}">
							<a class="color" href="${pageContext.request.contextPath}/member/AddLikeyServlet?postNo=${post.postNo}&likeyCk=1">좋아요</a>
							<span>${likeyMap.goodCount}</span>
						</c:if>
						<!-- 싫어요 -->
						&nbsp;
						<c:if test="${loginMember==null}">
								<span style="color:#4641D9;">싫어요 </span>
								<span>${likeyMap.badCount}</span>
						</c:if>
						<c:if test="${loginMember!=null}">
							<a class="color" href="${pageContext.request.contextPath}/member/AddLikeyServlet?postNo=${post.postNo}&likeyCk=0">싫어요 </a>
							<span>${likeyMap.badCount}</span>
						</c:if>
						<span><strong>${msg}</strong></span>
					</div>
				</div>
				<!-- 댓글 입력 -->
				<div style="margin-bottom: 20px;">
					<form method="post" action="${pageContext.request.contextPath}/member/AddCommentServlet">
						<input type="hidden" name="postNo" value="${post.postNo}">
						<div>
							<textarea rows="5" name="commentContent"></textarea>
							<button id="commentBtn" type="submit">등록</button>
						</div>	
					</form>
				</div>
				<!-- 댓글 목록 -->
				<div class="comment">
					<table class="comment">
						<thead>
							<tr>
								<th style="width:700px;">댓글</th>
								<th>작성자</th>
								<th>작성일</th>
							</tr>
						</thead>
						<c:forEach var="comment" items="${commentList}">
							<tr>
								<td class="commentTd" style="position: relative;">
									${comment.commentContent}
										<c:if test="${(loginMember.memberId==post.memberId)||(loginMember.memberLevel<10)}">
									<a class="commentLink" style="left:5px;" href="${pageContext.request.contextPath}/member/DeleteCommentServlet?postNo=${post.postNo}&commentNo=${comment.commentNo}">삭제</a>
									<a class="commentLink" style="left:40px;" href="${pageContext.request.contextPath}/member/UpdateCommentServlet?postNo=${post.postNo}&commentNo=${comment.commentNo}">수정</a>
									</c:if>
								</td>
								<td class="commentTd" style="text-align: center;">${comment.memberId}</td>
								<td class="commentTd" style="text-align: center;">${comment.commentDate}</td>
							</tr>
						</c:forEach>
					</table>
					<!-- 댓글 페이지 -->
					<c:if test="${count>5}">
						<div style="text-align: center;">
							<a class="aTag" href="${pageContext.request.contextPath}/SelectPostOneServlet?postNo=${post.postNo}&commentCurrentPage=1">처음</a>
							<c:if test="${commentCurrentPage==1}">
								<a class="aTag" href="${pageContext.request.contextPath}/SelectPostOneServlet?postNo=${post.postNo}&commentCurrentPage=1">이전</a>
							</c:if>
							<c:if test="${commentCurrentPage>1}">
								<a class="aTag" href="${pageContext.request.contextPath}/SelectPostOneServlet?postNo=${post.postNo}&commentCurrentPage=${commentCurrentPage-1}">이전</a>
							</c:if>
							-${commentCurrentPage}-
							<c:if test="${commentCurrentPage<lastPage}">
								<a class="aTag" href="${pageContext.request.contextPath}/SelectPostOneServlet?postNo=${post.postNo}&commentCurrentPage=${commentCurrentPage+1}">다음</a>
							</c:if>
							<c:if test="${commentCurrentPage==lastPage}">
								<a class="aTag" href="${pageContext.request.contextPath}/SelectPostOneServlet?postNo=${post.postNo}&commentCurrentPage=${lastPage}">다음</a>
							</c:if>
							<a class="aTag" href="${pageContext.request.contextPath}/SelectPostOneServlet?postNo=${post.postNo}&commentCurrentPage=${lastPage}">마지막</a>
						</div>
					</c:if>
				</div>
			</div>	
		</div>
	</body>
</html>

