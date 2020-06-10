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
			width: 130px;
			height: 81px;
			vertical-align: middle;
		}
		textarea {
			vertical-align: middle;
		}
		.color{
			color:#4641D9;
			padding:5px 15px;
		}
		.commentLink{
			font-size:12px;
			position: absolute;
			bottom:0px;
		}
		.commentLink:hover {
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
				</div>
				<!-- 좋아요 -->
				<div>
					<div style="margin-bottom: 20px;">
						<span style="color:#4641D9;">좋아요 </span>
						<span>${likeyMap.goodCount}</span>
						&nbsp;
						<span style="color:#4641D9;">싫어요 </span>
						<span>${likeyMap.badCount}</span>
					</div>
				</div>
				<!-- 댓글 입력 -->
				<div style="margin-bottom: 20px;">
					<input type="hidden" name="postNo" value="${post.postNo}">
					<div>
						<textarea readonly="readonly" rows="5" cols="120" name="commentContent"></textarea>
						<button id="commentBtn" type="button">등록</button>
					</div>	
				</div>
				<!-- 댓글 목록 -->
				<div class="comment">
					<table class="comment" style="margin-bottom: 20px;">
						<thead>
							<tr>
								<th style="width:700px;">댓글</th>
								<th>작성자</th>
								<th>작성일</th>
							</tr>
						</thead>
					</table>
					<!-- 댓글 수정 폼 -->
					<div>
						<form method="post" action="${pageContext.request.contextPath}/member/UpdateCommentServlet">
							<div style="margin-bottom: 20px;">
								<input type="hidden" name="postNo" value="${post.postNo}">
								<input type="hidden" name="commentNo" value="${comment.commentNo}">
								<div>
									<textarea rows="5" cols="120" name="commentContent">${comment.commentContent}</textarea>
									<button id="commentBtn" type="submit">댓글 수정</button>
								</div>	
							</div>
						</form>
					</div>
				</div>
			</div>	
		</div>
	</body>
</html>

