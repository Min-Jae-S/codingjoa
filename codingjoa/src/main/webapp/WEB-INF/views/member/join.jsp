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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
	.form-control:read-only {
 		 background: white;
	}
	
	.form-group span {
		color: red;
		font-size: 13px;
	}
</style>
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
						<div class="form-group mb-4">
							<form:label path="memberId" class="font-weight-bold">아이디</form:label>
							<div class="input-group">
								<form:input path="memberId" class="form-control" onkeypress="resetCheckId()"/>
								<div class="input-group-append">
									<button type="button" class="btn btn-outline-secondary btn-sm" onclick="checkId()">중복확인</button>
								</div>
							</div>
							<form:errors path="memberId"/>
						</div>
						<div class="form-group mb-4">
							<form:label path="memberPassword1" class="font-weight-bold">비밀번호</form:label>
							<form:password path="memberPassword1" class="form-control"/>
							<form:errors path="memberPassword1"/>
						</div>
						<div class="form-group mb-4">
							<form:label path="memberPassword2" class="font-weight-bold">비밀번호 확인</form:label>
							<form:password path="memberPassword2" class="form-control"/>
							<form:errors path="memberPassword2"/>
						</div>
						<div class="form-group mb-4">
							<form:label path="memberEmail" class="font-weight-bold">이메일</form:label>
							<div class="input-group">
								<form:input path="memberEmail" class="form-control" onkeypress="resetCheckEmail()"/>
								<div class="input-group-append">
									<button type="button" class="btn btn-outline-secondary btn-sm" onclick="checkEmail()">인증요청</button>
								</div>
							</div>
							<form:errors path="memberEmail"/>
						</div>
						<div class="form-group">
							<form:label path="memberZipcode" class="font-weight-bold">주소</form:label>
						    <div class="input-group w-75">
						    	<form:input path="memberZipcode" class="form-control" readonly="true" placeholder="우편번호" onclick="execPostcode()"/>
								<div class="input-group-append">
									<button type="button" class="btn btn-outline-secondary btn-sm" onclick="execPostcode()">우편번호 찾기</button>
								</div>
							</div>
							<form:errors path="memberZipcode"/>
						</div>
						<div class="form-group">
							<form:input path="memberAddr1" class="form-control" readonly="true" placeholder="기본주소" onclick="execPostcode()"/>
							<form:errors path="memberAddr1"/>
						</div>
						<div class="form-group mb-5">
							<form:input path="memberAddr2" class="form-control" placeholder="상세주소"/>
							<form:errors path="memberAddr2"/>
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

<script>
	$(function() {
		
	});

    function execPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('memberZipcode').value = data.zonecode;
                document.getElementById("memberAddr1").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("memberAddr2").focus();
            }
        }).open();
    }
</script>
</body>
</html>