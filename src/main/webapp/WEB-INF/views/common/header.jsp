<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div id="header">
	<h1><a href="${pageContext.request.contextPath}/${blogVo.id }">${blogVo.title }</a></h1>
	
	<ul>
	<c:if test="${authUser eq null }">
		<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
	</c:if>
	<c:if test="${authUser ne null }">
		<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
	</c:if>
	<c:if test="${authUser.id eq blogVo.id }">
		<li>
			
		<a href="${pageContext.request.contextPath}/${blogVo.id }/admin/basic">블로그 관리</a></li>
	</c:if>
	</ul>
	${authUser.id }
	${blogVo.id }
</div>