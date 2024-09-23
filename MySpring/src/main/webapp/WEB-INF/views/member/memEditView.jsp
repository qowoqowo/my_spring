<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>회원 정보 수정</title>


<!-- Favicon-->
<%@ include file="/WEB-INF/inc/header.jsp" %>

	<style type="text/css">

		.profile-box{
			display:flex;
			justify-content:center;
			align-items: center;
		}
		
		.profile-img{
			width: 300px;
			height: 300px;
			border-radius: 50%;
			overflow: hidden;
			
			cursor: pointer;
		}

	</style>

</head>

<body id="page-top">
	<!-- Navigation-->
	<%@ include file="/WEB-INF/inc/top.jsp" %>

	<section class="page-section" id="contact">
		<!-- 부트스트랩으로 padding-top 을 좀 주고자 한다. -->
		<div class="container pt-5">
			<!-- Contact Section Heading-->
			<h2
				class="page-section-heading text-center text-uppercase text-secondary mb-0">회원정보 수정</h2>
			<!-- Icon Divider-->
			<div class="divider-custom">
				<div class="divider-custom-line"></div>
				<div class="divider-custom-icon">
					<i class="fas fa-star"></i>
				</div>
				<div class="divider-custom-line"></div>
			</div>
			
			
			<!-- Contact Section Form-->
			<div class="row justify-content-center">
				<div class="col-lg-8 col-xl-7">
				
					<!-- 프로필 이미지 -->
					<div class="profile-box">
						<c:if test="${sessionScope.login.memProfileImg == null }">
							<img id="profileImg" class="profile-img"src="${pageContext.request.contextPath }/assets/img/defaultImg.jpg">
						</c:if>
						<c:if test="${sessionScope.login.memProfileImg != null }">
							<img id="profileImg" class="profile-img"src="${pageContext.request.contextPath }/displayImage?fileName=${sessionScope.login.memProfileImg}">
						</c:if>
						
						<input id="inputImg" type="file" hidden="true" accept="image/*" onchange="f_sandImg()">
					</div>
				
					<!-- type=submit인 버튼 클릭시 form 태그의 action 링크가 실행됨 -->
					<!-- 회원가입 요청시 서버에서 이를 받아줘야함 -->
					<!-- /registDo 주소로 요청시 서버에서 회원가입 진행 -->
					<!-- 회원가입은 GET 방식으로 하면 문제가 있을수 있으니 POST 방식 사용 -->
					<form id="memEditForm"
						action="${pageContext.request.contextPath }/memEditDo"
						method="POST">
						<input type="hidden"  name="from" value="${keyFrom}">
						
						
						<!-- 아이디 input-->
						<div class="form-floating mb-3">
							<input class="form-control" id="inputId" type="text" name="memId" value="${sessionScope.login.memId }" ${cookie.rememberId.value == null ? "autofocus" : ""} readonly/>
							<label for="inputId">아이디</label>
						</div>

						<!-- 비밀번호 input-->
						<div class="form-floating mb-3">
							<!-- inputPw의 값이 pw=value 형태로 전송되어야 함. pw를 name속성에 넣어주기 -->
							<input class="form-control" id="inputPw" type="password"
								name="memPassword" /> <label for="inputPw">비밀번호</label>
						</div>

						<!-- 닉네임 input-->
						<div class="form-floating mb-3">
							<input class="form-control" id="inputName" type="text" name="memName" value="${sessionScope.login.memName }"/> 
								<label for="inputName">닉네임</label>
						</div>

						<!-- 휴대폰 번호 input -->
						<div class="form-floating mb-3">
							<input class="form-control" id="inputPhone" type="tel" name="memPhone" value="${sessionScope.login.memPhone }"/> 
							<label for="inputPhone">휴대폰</label>
						</div>

						<!-- 이메일 input -->
						<div class="form-floating mb-3">
							<input class="form-control" id="inputEmail" type="email" name="memEmail" value="${sessionScope.login.memEmail }"/> 
							<label for="inputEmail">이메일</label>
						</div>


					</form>
					
					<form id="memDelForm" action="${pageContext.request.contextPath }/delMemberDo" method="POST"></form>
					
					<div class="d-flex justify-content-center">
						<button class="btn btn-primary me-2" id="memEditBtn" type="button">수정</button>
						<button class="btn btn-danger" id="memDelBtn" type="button">회원탈퇴</button>
					</div>

				</div>
			</div>
		</div>
	</section>

	<!-- Footer-->
	<%@ include file="/WEB-INF/inc/footer.jsp" %>

	<script type="text/javascript">
		
	let v_memId = '${sessionScope.login.memId}';
	
	
	document.getElementById("memDelBtn").addEventListener("click", ()=>{
		
		let v_input = prompt("정말로 삭제하시겠습니까? 삭제를 원하시면 아이디를 입력해주세요.");
		
		console.log(v_input);
		
		if(v_input == v_memId){
			// action=/memDelDo 인 form 태그의 submit 실행
			document.getElementById("memDelForm").submit();
		}
	});
	
	document.getElementById("memEditBtn").addEventListener("click", ()=>{

		const v_pw = document.getElementById("inputPw").value;
		const v_name = document.getElementById("inputName").value;
		const v_phone = document.getElementById("inputPhone").value;
		const v_email = document.getElementById("inputEmail").value;
		
		if(v_pw == null || v_pw == ""){
			alert("비밀번호를 입력해주세요.");
			return;
		}else if(v_name == null || v_name == "" ){
			alert("닉네임을 입력해주세요.");
			return;
		}else if(v_phone == null || v_phone == "" ){
			alert("휴대폰번호를 입력해주세요.");
			return;
		}else if(v_email == null || v_email == "" ){
			alert("이메일을 입력해주세요.");
			return;
		}
		
			// action=/memDelDo 인 form 태그의 submit 실행
			document.getElementById("memEditForm").submit();
	});
	</script>

	<!-- 프로필 사진관련 JS코드 -->
	<script type="text/javascript">
		// img 태그 불러오기
		const v_profileImg = document.getElementById("profileImg");
		// input file 태그 불러오기
		const v_inputImg = document.getElementById("inputImg");
	
		// img 태그 클릭 시, 파일 첨부 창 열기 (= input file 태그 클릭 시)
		v_profileImg.addEventListener("click", ()=>{
			// input file 태그 클릭
			v_inputImg.click();
			
		})
		
		// 파일 첨부시 실행
		function f_sandImg(){
			// event.target = input file 태그 객체
			console.log(event.target);
			console.log(event.target.files);
			console.log(event.target.files[0]);  // 첨부된 파일 1개 선택
			
			// AJAX 통신으로 첨부된 파일을 서버에 전송
			// form 태그가 갖는 데이터 형태(=FormData)로 보내기
			let v_formData = new FormData();
			v_formData.append('file',event.target.files[0]);
			
			// 요청보낼 주소
			let v_url = '${pageContext.request.contextPath}/uploadProfile';
			
			// AJAX 통신 요청
			$.ajax({
				type: 'POST',
				url: v_url,
				contentType: false,
				processData: false,
				enctype: 'multipart/form-data',
				data: v_formData,
				success: function(resp){
					
					// 저장된 이미지의 파일명 (7fe29986-7fa8-4ff8-80dc-6eda1cd44150)
					console.log(resp);
					
					// img 태그의 src 를 변경
					// 서버에 저장된 이미지 파일을 요청(/displayImage)
					// 이미지 파일명을 같이 보낸다.
					v_profileImg.src = "${pageContext.request.contextPath}/displayImage?fileName=" + resp;
				}
				
			});
			
			
		}
		
	</script>


	<!-- 입력값 확인  -->
	<script type="text/javascript">

	
	
	</script>

</body>
</html>
