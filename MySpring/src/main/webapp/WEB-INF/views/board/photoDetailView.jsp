<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8" />
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>${keyPhoto.photoTitle }</title>
	
	<!-- header 부분 -->
	<%@ include file="/WEB-INF/inc/header.jsp"%>

	<style type="text/css">
	
		.board-head{
			border-bottom: 1px solid #1abc9c;
		}
	
	
		.board-title{
			font-weight: bold;
			font-size: 22px;
			
			margin-bottom: 10px
		}
		
		.name-date{
			display: flex;
			justify-content: space-between;
			margin-bottom: 10px;
		}

		.board-name{
			font-size: 20px
		}
		
		.board-date{
			font-size: 20px;
		}
		
		.board-body{
			padding-top: 10px;
			min-height: 300px;
			font-size: 24px;
			font-family: "Nanum Brush Script", cursive;
			font-weight: 400;
			font-style: normal;
		}
		
		.reply-box{
			border-top: 2px solid black;
			border-bottom: 1px solid #DDDDDD;
			margin-top:10px;
			margin-bottom:10px;
		}
		
		.reply-box img{
			cursor:pointer;
		}
		
		.reply-span{
			font-size: 12px;
			cursor: pointer;
		}
		
		.img-box{
			margin-top: 30px;
			margin-left: 10px;
		}
		
		
	</style>

</head>

<body id="page-top">

	<!-- navigation 부분 -->
	<%@ include file="/WEB-INF/inc/top.jsp"%>


	<!-- Contact Section-->
	<section class="page-section" id="contact">
		<!-- 부트스트랩으로 padding-top 을 좀 주고자 한다. -->
		<div class="container pt-5">
			
			<!-- Contact Section Form-->
			<div class="row justify-content-center">
				<div class="col-lg-8 col-xl-7">
					<!-- 제목, 작성자, 작성일, 내용 -->
					<div>
						<div class="board-head">
							<div class="board-title" >${keyPhoto.photoTitle}</div>
							<div class="name-date">
								<div class="board-name">${keyPhoto.memName}</div>
								<div class="board-date">${keyPhoto.photoDate} </div>
							</div>
						</div>
						
						<div class="d-flex justify-content-end">
							<b>조회수 : ${keyPhoto.photoCount }</b>
						</div>						
						
						<div class="img-box">
							<img src="${pageContext.request.contextPath }/displayImage?fileName=${keyPhoto.photoPath}">
						</div>
						
						
					</div>
					
					<!-- 로그인한 회원의 아이디와 현재 게시글 작성자 아이디가 -->
					<!-- 일치하면 글수정 버튼 그리기 -->
					<div class="d-flex justify-content-end">
						<c:if test="${ sessionScope.login.memId == keyPhoto.memId && sessionScope.login.memId != null}">
							<form action="${pageContext.request.contextPath }/photoEditView" method="POST">
								<!-- 현재 페이지의 글번호를 /boardEditView 에 같이 보냄 -->
								<input type="hidden" value="${keyPhoto.photoNo}" name="no">
								<button class="btn btn-warning" type="submit">수정</button>
							</form>
						</c:if>

						<c:if test="${ sessionScope.login.memId == keyPhoto.memId && sessionScope.login.memId != null || sessionScope.login.memLv == '0'}">
							<form id="delForm" action="${pageContext.request.contextPath }/photoDeleteDo" method="POST">
								<!-- 현재 페이지의 글번호를 /boardDeleteView 에 같이 보냄 -->
								<input type="hidden" value="${keyPhoto.photoNo}" name="no">
								<!-- 삭제버튼 클릭시 삭제확인 메시지를 띄움 -->
								<!-- button에 클릭 이벤트 부여 -->
								<!-- button의 type을 button으로 변경 (클릭해도 submit 안됨) -->
								<button id="delBtn" class="btn btn-danger ms-2" type="button">삭제</button>
							</form>
							
						</c:if>
					</div>
					
					<!-- 댓글창 -->
					<div>
						<div id="replyBox" class="reply-box">
							<c:forEach items="${keyReplyList }" var="replyDTO">
								<div class="row pt-2 pb-2">
									<input type="hidden" value="${replyDTO.replyNo }">
									<div class="col-2">${replyDTO.memName }</div>
									<div class="col-7">${replyDTO.replyContent }</div>
									<div class="col-2">${replyDTO.replyDate }</div>
									<div class="col-1">
										<c:if test="${sessionScope.login.memId == replyDTO.memId && sessionScope.login.memId != null || sessionScope.login.memLv == '0'}">
											<img src="${pageContext.request.contextPath }/assets/img/close.png" width="20px" onclick="f_delete()">
										</c:if>
									</div>
								</div>
							</c:forEach>
						</div>
						
						<!-- 댓글 부분 -->
						<div >
							<form id="replyForm" class="row" action="${pageContext.request.contextPath }/photoReplyWriteDo" method="POST">
								<input type="hidden" name="memId" value="${sessionScope.login.memId }">
								<input type="hidden" name="photoNo" value="${keyPhoto.photoNo }">
								<div class="col-10">
									<input id="replyInput" class="form-control" type="text" name="replyContent">
								</div>
								<button id="replyBtn" class="btn btn-primary col-2" type="button">등록</button>
							</form>
						</div>
						
						
					</div>
					
					
				</div>
			</div>
		</div>
	</section>


	<!-- footer 부분 -->
	<%@ include file="/WEB-INF/inc/footer.jsp"%>
	
	<script type="text/javascript">
	
	let v_delForm = document.getElementById("delForm");
	
		
		if(document.getElementById("delBtn")){
			document.getElementById("delBtn").addEventListener("click", ()=>{
				
				/*  삭제 확인 메시지를 띄움 */
				if(confirm("정말로 삭제하시겠습니까?")){
					/* /boardDeleteDo 에 대한 form action 실행 */ 
					v_delForm.submit();  // submit 버튼을 누른것과 동일
				};
			})
		}
		
		let v_name = '${sessionScope.login.memName}';
		
		/* 댓글 입력 창 클릭 이벤트 */
		document.getElementById("replyInput").addEventListener("click", ()=>{
			
			if(!v_name){
				location.href = "${pageContext.request.contextPath}/loginView";
			}
			
			
		})
		
		let v_replyBox = document.getElementById("replyBox");			

		/* 등록버튼 클릭 이벤트 */
		document.getElementById("replyBtn").addEventListener("click", ()=>{
		let v_replyInput =  document.getElementById("replyInput").value;

		/* form 태그 가져오기 */
		let v_formData = $('#replyForm').serialize(); // replyContent = asdsa
		let v_url = $('#replyForm').attr('action');
		
		// memId, boardNo, replyContent 데이터
		console.log(v_formData);
		
		
		$.ajax({
			type : "POST",
			url : v_url,
			data : v_formData,
			success : function(resp){
				console.log(resp);	  //JSON 객체 (jQuery에서 자동으로 parse 해줌)
				
				/* 댓글창 비워주기 */
				document.getElementById("replyInput").value = "";	
				
				// 댓글 한줄에 대한 html코드 생성
				let v_reply = '<div class="row pt-2 pb-2">';
				v_reply += '     <input type="hidden" value="' + resp['replyNo'] + '">';
				v_reply += '     <div class="col-2">' + resp['memName'] + '</div>';
				v_reply += '     <div class="col-7">' + resp['replyContent'] + '</div>';
				v_reply += '     <div class="col-2">' + resp['replyDate'] + '</div>' ;
				v_reply += '     <div class="col-1">';
				v_reply += '       <img src="${pageContext.request.contextPath }/assets/img/close.png" width="20px" onclick="f_delete()">';
				v_reply += '     </div>'; 
				v_reply += '   </div>'; 
				
				v_replyBox.innerHTML += v_reply; 
			}
		});
		
		
		if(!v_replyInput){
			return;
		}
		

			
			
		});

		function f_delete(){
			if(!confirm("정말로 삭제하시겠습니까?")){
				return;
			}
			
			console.log(event.target.parentElement);
			console.log(event.target.parentElement.parentElement.children[0].value);
			let v_parent = event.target.parentElement.parentElement;
			
			let v_replyNo = event.target.parentElement.parentElement.children[0].value;
			
			// AJAX 통신으로 해당 replyNo에 대한 UPDATE문 실행
			let v_ajax = new XMLHttpRequest();
			
			v_ajax.open('POST', '${pageContext.request.contextPath}/photoDelReplyDo');
			
			// 데이터를 보내기 위해 데이터 형식을 만들어 주기 (form의 serialize 형태) 
			let v_data = "replyNo=" + v_replyNo;
			
			// form의 serialize 형태로 데이터를 보내기 위한 헤더 설정
			v_ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
			
			// AJAX 통신이 끝나고 나면 실행되는 함수 (콜백함수)
			v_ajax.onload = ()=>{
				// 응답은 success 아니면 fail 문자열이 옴
				console.log(v_ajax.response);
				
				if(v_ajax.response == 'success'){
					v_parent.remove();
				}
				
			}
			
			// send 시 데이터를 보낸다.
			v_ajax.send(v_data);
			//event.target.parentElement.parentElement.remove();
			
		}

		
		
	</script>


</body>
</html>