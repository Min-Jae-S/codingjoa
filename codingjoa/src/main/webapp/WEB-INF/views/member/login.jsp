<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />  
<!DOCTYPE html>
<html>
<head>
<title>로그인</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="${contextPath}/resources/css/common.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<c:import url="/WEB-INF/views/include/top-menu.jsp"/>

<div class="container login-container">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body p-4">
					<form:form action="${contextPath}/member/loginProc" method="POST" modelAttribute="loginDto">
						<div class="form-group mb-4">
							<form:label path="memberId" class="font-weight-bold">아이디</form:label>
							<form:input path="memberId" class="form-control" placeholder="아이디" autocomplete="off"/>
						</div>
						<div class="form-group mb-4">
							<form:label path="memberPassword" class="font-weight-bold">비밀번호</form:label>
							<form:password path="memberPassword" class="form-control" placeholder="비밀번호" showPassword="true"/>
						</div>
						<c:if test="${not empty errorMessage}">
							<div class="error">${errorMessage}</div>
						</c:if>
						<div class="form-group pt-4 mb-4">
							<form:button class="btn btn-primary btn-lg btn-block">로그인</form:button>
						</div>
					</form:form>
					<p class="text-center small mb-0">
						<span>비밀번호를 잃어버리셨나요?</span>
						<span><a href="#">비밀번호 찾기</a></span>
					</p>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>

<c:import url="/WEB-INF/views/include/bottom-menu.jsp"/>

</body>
</html>