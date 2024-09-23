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
<title>사진 게시판</title>


<!-- Favicon-->
<%@ include file="/WEB-INF/inc/header.jsp" %>

	<style type="text/css">
	
	.img-container{
		width:100%;
		display: flex;
		justify-content: center;
		align-items: center;
		
		margin-bottom:30px;
	}
	
	.img-box{
		width:500px;
	}
	
	.img-box img{
		width:100%;
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
				class="page-section-heading text-center text-uppercase text-secondary mb-0">사진 게시판</h2>
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
				
					<form action="${pageContext.request.contextPath }/photoEditDo" method="POST" enctype="multipart/form-data">
					
						<input type="hidden" name="photoNo" value="${keyPhoto.photoNo }">
					
						<div class="mb-3">
							<input class="form-control" id="inputId" type="text" name="photoTitle" value="${keyPhoto.photoTitle }" placeholder="제목을 입력해주세요."/>
						</div>
						
						<div class="img-container">
							<div class="img-box">
								<b>아래 이미지를 클릭해 수정해주세요.</b>
								<img id="contentImg" class="upload-img"src="${pageContext.request.contextPath }/displayImage?fileName=${keyPhoto.photoPath}">
								<input id="inputImg" name="imgFile" type="file" hidden="true" accept="image/*" onchange="f_sandImg()">
							</div>
						</div>	
						
						<input id="inputPath" type="hidden" name="photoPath" value="${keyPhoto.photoPath }">
						
						<div class="d-flex justify-content-end">
							<a class="btn btn-secondary me-2" href="${pageContext.request.contextPath }/photoDetailView?no=${keyPhoto.photoNo}">취소</a>
							
							<!-- form 태그의 submit 역할을 함 -> type=submit 넣어주기 -->
							<button class="btn btn-primary" type="submit">등록</button>
						</div>
					</form>
					
				</div>
			</div>

			
		</div>
	</section>

	<!-- Footer-->
	<%@ include file="/WEB-INF/inc/footer.jsp" %>

	<script type="text/javascript">
	
		// img 태그 불러오기
		const v_contentImg = document.getElementById("contentImg");
		// input file 태그 불러오기
		const v_inputImg = document.getElementById("inputImg");
		// inputPath 태그
		const v_inputPath = document.getElementById("inputPath");
	
		// img 태그 클릭 시, 파일 첨부 창 열기 (= input file 태그 클릭 시)
		v_contentImg.addEventListener("click", ()=>{
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
			let v_url = '${pageContext.request.contextPath}/uploadImg';
			
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
					v_contentImg.src = "${pageContext.request.contextPath}/displayImage?fileName=" + resp;
					
					v_inputPath.value = resp;
				}
				
			});
			
			
		}		
		
	
	</script>

</body>
</html>
