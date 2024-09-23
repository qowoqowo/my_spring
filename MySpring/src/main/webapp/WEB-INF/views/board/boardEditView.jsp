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
<title>자유 게시판</title>

<script src="${pageContext.request.contextPath }/nse/js/HuskyEZCreator.js"></script>

<!-- Favicon-->
<%@ include file="/WEB-INF/inc/header.jsp" %>


</head>

<body id="page-top">
	<!-- Navigation-->
	<%@ include file="/WEB-INF/inc/top.jsp" %>

	<section class="page-section" id="contact">
		<!-- 부트스트랩으로 padding-top 을 좀 주고자 한다. -->
		<div class="container pt-5">
			<!-- Contact Section Heading-->
			<h2
				class="page-section-heading text-center text-uppercase text-secondary mb-0">자유 게시판</h2>
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
				
					<input type="file" id="inputImg" accept="image/*" hidden="true" onchange="f_sandImg()">

					<form id="boardEditForm" action="${pageContext.request.contextPath }/boardEditDo" method="POST" enctype="multipart/form-data">
					
						<div class="mb-3">
							<input class="form-control" id="inputId" type="text" name="boardTitle" value="${keyBoard.boardTitle}"/>
						</div>
	
						<div class="mb-3">
							<textarea id="smartEditor" class="form-control"  rows="10" name="boardContent">${keyBoard.boardContent}</textarea>
						</div>					
						
						<div class="mb-3">
						  <label for="formFileMultiple" class="form-label">파일 첨부</label>
						  <input class="form-control" type="file" name="boardFile" id="formFileMultiple"  multiple>
						</div>							

						<input type="hidden" name="boardNo" value="${keyBoard.boardNo }">
						
						<div class="d-flex justify-content-end">
							<a class="btn btn-secondary me-2" href="${pageContext.request.contextPath }/boardDetailView?no=${keyBoard.boardNo}">취소</a>
							
							<!-- form 태그의 submit 역할을 함 -> type=submit 넣어주기 -->
							<button id="writeBtn" class="btn btn-primary" type="button">등록</button>
						</div>
					</form>
					
				</div>
			</div>

			
		</div>
	</section>

	<!-- Footer-->
	<%@ include file="/WEB-INF/inc/footer.jsp" %>

	<script type="text/javascript">
		var oEditors = [];
		
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "smartEditor",  // textarea의 id
			sSkinURI : "${pageContext.request.contextPath }/nse/SmartEditor2Skin.html"
			
		});
		
		// 글 등록 버튼 클릭
		document.getElementById("writeBtn").addEventListener("click", ()=>{
			// 에디터에 작성된 내용(html태그 형태)을 숨겨진 textarea에 반영
			oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []);
			
			// form 태그의 submit 실행
			document.getElementById("boardEditForm").submit();
			
		});
		
		// 이미지 첨부 받을 input 태그 객체 불러오기
		const v_inputImg = document.getElementById("inputImg");
		
		// 스마트에디터가 로드된 후 이미지 첨부 버튼에 클릭 이벤트 추가
		window.onload = function(){
			// 스마트 에디터가 그려진 iframe 가져오기
			const v_iframe = document.querySelector('#boardEditForm > div:nth-child(2) > iframe');
			console.log(v_iframe);
			// iframe 내 document 에 접근
			const v_iframeDocument = v_iframe.contentWindow.document;
			console.log(v_iframeDocument);
			// iframe의 document 안에 있는 이미지 첨부 버튼(id가 photoUploadBtn)에
			// 클릭 이벤트 부여
			v_iframeDocument.querySelector('#photoUploadBtn').addEventListener("click", ()=>{
				// 에디터 내 이미지 첨부 버튼 클릭 시 inputImg 태그 클릭
				v_inputImg.click();
			});
			
		}
		
		// 파일 첨부시 실행
		function f_sandImg(){
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
					// 이미지 태그 생성
					let imgTag = '<img style="width: 400px" src="';
						imgTag += '${pageContext.request.contextPath}/displayImage?fileName=' + resp;
						imgTag += '">'; 
						
					// 에디터 내부에 이미지 태그 넣기
					oEditors.getById['smartEditor'].exec("PASTE_HTML", [imgTag]);
				}
			});
		}
	
		
	</script>


</body>
</html>
