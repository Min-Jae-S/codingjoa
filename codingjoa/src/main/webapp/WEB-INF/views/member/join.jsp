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
<title>회원가입</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>

<c:import url="/WEB-INF/views/include/top-menu.jsp"/>

<div class="container" style="margin-top:150px">
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6">
			<div class="card shadow">
				<div class="card-body">
					<form:form action="${contextPath}/member/joinProc" method="POST" modelAttribute="memberVO">
						<div class="form-group">
							<form:label path="memberId" class="font-weight-bold">아이디</form:label>
							<div class="input-group">
								<form:input path="memberId" class="form-control" onkeypress="resetCheckId()"/>
								<div class="input-group-append">
									<button type="button" class="btn btn-secondary" onclick="checkId">중복확인</button>
								</div>
							</div>
							<form:errors path="memberId"/>
						</div>
						<div class="form-group">
							<form:label path="memberPassword1" class="font-weight-bold">비밀번호</form:label>
							<form:password path="memberPassword1" class="form-control"/>
							<form:errors path="memberPassword1"/>
						</div>
						<div class="form-group">
							<form:label path="memberPassword2" class="font-weight-bold">비밀번호 확인</form:label>
							<form:password path="memberPassword2" class="form-control"/>
							<form:errors path="memberPassword2"/>
						</div>
						<div class="form-group">
							<form:label path="memberEmail" class="font-weight-bold">이메일</form:label>
							<div class="input-group">
								<form:input path="memberEmail" class="form-control" onkeypress="resetCheckEmail()"/>
								<div class="input-group-append">
									<button type="button" class="btn btn-secondary" onclick="checkEmail">인증요청</button>
								</div>
							</div>
							<form:errors path="memberEmail"/>
						</div>
						<div class="form-group">
							<form:button class="btn btn-primary">회원가입</form:button>
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