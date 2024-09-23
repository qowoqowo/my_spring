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
		.card-container{
			display: flex;
			justify-content: start;
			align-items:center;
			
			width: 800px;

			flex-wrap: wrap;
			
			border: 1px solid #dddddd;
			border-radius: 5px;
		}
		
		.card-box{
	 		width: calc(100% / 5 + 20px);
			height: 300px;
            box-shadow: 0px 0px 5px 2px gray;
            border-radius: 10px;	
            margin: 9px;
            
            cursor: pointer;
            
		}
		
		.card-box img{
			width: 100%;
			height: 200px;
			border-radius: 10px;
			overflow: hidden;
		}
		
		.card-body{
			height: 100px;
			display: flex;
            flex-direction: column;
            justify-content: space-around;
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
				
					<div class="card-container">
					
						<c:forEach items="${keyPhotoList}"  var="photoDTO">
							<div id="cardBox" class="card-box" onclick='window.location.href = "${pageContext.request.contextPath }/photoDetailView?no=${photoDTO.photoNo }"'>
								<div class="card-img">
									<img src="${pageContext.request.contextPath }/displayImage?fileName=${photoDTO.photoPath}">
								</div>
								<div class="card-body">
									<b style="text-align:center">${photoDTO.photoTitle }</b>
									<span style="text-align:end; margin-right:5px">${photoDTO.memName}</span>
									<span style="text-align:center">${photoDTO.photoDate} | 조회수:${photoDTO.photoCount }</span>
								</div>
							</div>
						</c:forEach>
						
					</div>
					
					<!-- 페이징 -->
					<div class="d-flex justify-content-center">
						<nav aria-label="Page navigation example">
						  <ul class="pagination">
						  
						  
						  	<!-- 검색중이면 검색옵션과 검색어를 유지하면서 페이징 처리 -->
							<!-- 검색중이지 않으면 검색 옵션과 검색어가 주소창에 나타나지 않게 하기 -->
							<!-- searchWord가 null이면 a태그의 href에서 searchOption 과 searchWord 떼어내기 -->

					    	<!-- 이전 페이지 -->
							    <li class="page-item ${keySearch.firstPage == 1 ? 'disabled' : '' }">
								      <a class="page-link" href="${pageContext.request.contextPath }/boardPhotoView?pageNo=${keySearch.firstPage - 1 }&rowSizePerPage=${keySearch.rowSizePerPage}" aria-label="Previous">
								        <span aria-hidden="true">&laquo;</span>
								      </a>
							    </li>
						    
						    <!-- 중간 페이지 번호 부분 -->
						    <!-- model에 keySearch 이름으로 searchVO를 담음 -->
						    <!-- searchVO 내 pageNo, firstPage, lastPage 채워져있음 -->
					    			    
							    <c:forEach begin="${keySearch.firstPage }" end="${keySearch.lastPage }" var="num">
									    <li class="page-item ${keySearch.pageNo == num ? 'active' : '' } ">
										    <a class="page-link" href="${pageContext.request.contextPath }/boardPhotoView?pageNo=${num }&rowSizePerPage=${keySearch.rowSizePerPage}">${num }</a>
									    </li>
							    </c:forEach>
						   
						    <!-- 다음 페이지 -->
						    <!-- 마지막 페이지 도달 시 disabled 추가 -->
							    <li class="page-item ${keySearch.pageNo == keySearch.finalPage ? 'disabled' : ''  }">
								     <a class="page-link" href="${pageContext.request.contextPath }/boardPhotoView?pageNo=${keySearch.lastPage + 1 }&rowSizePerPage=${keySearch.rowSizePerPage}" aria-label="Next">
								    	 <span aria-hidden="true">&raquo;</span>
								     </a>
							    </li>
						  </ul>
						</nav>
					</div>						
					
					<div class="d-flex justify-content-end mt-2" style="width:800px">
						<button id="writeBtn" class="btn btn-primary" >글쓰기</button>
					</div>					
					
				</div>
			</div>

			
		</div>
	</section>

	<!-- Footer-->
	<%@ include file="/WEB-INF/inc/footer.jsp" %>


	<script type="text/javascript">
		let v_id = '${sessionScope.login.memId}';
		
		document.getElementById("writeBtn").addEventListener("click", ()=>{
			
			
			if(v_id){
				location.href = '${pageContext.request.contextPath }/photoWriteView';
			}
			else{
				alert("로그인 후 글쓰기가 가능합니다.");
				location.href = '${pageContext.request.contextPath}/loginView';
			}
			
		})
		
	</script>


</body>
</html>
