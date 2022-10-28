<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="principal" value="${SPRING_SECURITY_CONTEXT.authentication.principal}" />
<!-- 상단 메뉴 -->
<nav class="navbar navbar-expand-md bg-dark navbar-dark fixed-top">
	<div class="container-fluid pl-5 pr-5">
		<a class="navbar-brand font-weight-bold" href="${contextPath}">Codingjoa</a>
		<div class="collapse navbar-collapse" id="navMenu">
			<ul class="navbar-nav">
				<li class="nav-item">
					<a href="${contextPath}" class="nav-link">HOME</a>
				</li>
				<li class="nav-item">
					<a href="#" class="nav-link">게시판</a>
				</li>
			</ul>
			
			<ul class="navbar-nav ml-auto">
				<security:authorize access="isAnonymous()">
					<li class="nav-item">
						<a href="${contextPath}/member/join" class="nav-link">회원가입</a>
					</li>
					<li class="nav-item">
						<a href="${contextPath}/member/login" class="nav-link">로그인</a>
					</li>
				</security:authorize>
				<security:authorize access="isAuthenticated()">
					<li class="nav-item">
						<span class="nav-link text-white">
							<span class="font-weight-bold">${principal.member.memberId}</span>
							<span>님</span>
						</span>
					</li>
					<li class="nav-item">
						<span class="nav-link">|</span>
					</li>
					<li class="nav-item">
						<a href="${contextPath}/member/myInfo" class="nav-link">내 정보</a>
					</li>
					<li class="nav-item">
						<a href="${contextPath}/member/logout" class="nav-link">로그아웃</a>
					</li>
				</security:authorize>
			</ul>
		</div>
	</div>
</nav>
