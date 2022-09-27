<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!-- 상단 메뉴 -->
<nav class="navbar navbar-expand-md bg-dark navbar-dark fixed-top">
	<div class="container-fluid pl-5 pr-5">
		<a class="navbar-brand font-weight-bold" href="${contextPath}">Codingjoa</a>
		<div class="collapse navbar-collapse" id="navMenu">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a href="#" class="nav-link">게시판</a>
				</li>
			</ul>
			
			<ul class="navbar-nav ml-auto">
				<li class="nav-item">
					<a href="#" class="nav-link">정보수정</a>
				</li>
				<li class="nav-item">
					<a href="${contextPath}/member/logout" class="nav-link">로그인</a>
				</li>
				<li class="nav-item">
					<a href="${contextPath}/member/login" class="nav-link">로그인</a>
				</li>
				<li class="nav-item">
					<a href="${contextPath}/member/join" class="nav-link">회원가입</a>
				</li>
			</ul>
		</div>
	</div>
</nav>
