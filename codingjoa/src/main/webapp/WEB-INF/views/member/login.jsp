<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>로그인</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<style>
	.container {
		margin-top: 200px;
		margin-bottom: 100px;
	}
	
	input::placeholder {
		font-size: 14px;
	}

	.error {
		color: red;
		font-size: 13px;
	}
</style>
</head>
<body>

<c:import url="/WEB-INF/views/include/top-menu.jsp"/>

<div class="container">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<form:form action="${contextPath}/member/loginProc" method="POST" modelAttribute="loginRequestDTO">
						<div class="form-group mb-4">
							<form:label path="memberId" class="font-weight-bold">아이디</form:label>
							<form:input path="memberId" class="form-control" placeholder="아이디"/>
						</div>
						<div class="form-group mb-4">
							<form:label path="memberPassword" class="font-weight-bold">비밀번호</form:label>
							<form:password path="memberPassword" class="form-control" placeholder="비밀번호"/>
						</div>
						<div class="form-group pt-4">
							<form:button class="btn btn-primary btn-lg btn-block">로그인</form:button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>

<c:import url="/WEB-INF/views/include/bottom-menu.jsp"/>

</body>
</html>