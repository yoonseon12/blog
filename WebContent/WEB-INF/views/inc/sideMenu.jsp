<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="menu">
	<h3>MENU</h3>
	<hr>
</div>
<c:forEach var="subject" items="${subjectList}">
	<div style="text-align:center; font-size:18px; margin-top:20px; margin-bottom: 20px;">
		<a class="menuLink" href="${pageContext.request.contextPath}/SelectPostBySubjectServlet?subjectName=${subject.subjectName}">${subject.subjectName}</a>
	</div>
</c:forEach>
<hr>
<div style="text-align:center; margin-top:20px;">
	<a class="menuLink" href="${pageContext.request.contextPath}/HomeServlet">HOME</a>
</div>