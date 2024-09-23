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
				
					<!-- 표시할 게시글의 수 -->
					<div class="d-flex justify-content-end">
						<select onchange="f_change()" >
						  <option value="10" ${keySearch.rowSizePerPage == 10 ? 'selected' : '' }>10개</option>
						  <option value="20" ${keySearch.rowSizePerPage == 20 ? 'selected' : '' }>20개</option>
						  <option value="30" ${keySearch.rowSizePerPage == 30 ? 'selected' : '' }>30개</option>
						</select>	
					</div>				
				
					<!-- 게시글 그리기(table) -->
					<table class="table table-hover">
					  <thead>
					    <tr>
					      <th scope="col">번호</th>
					      <th scope="col">제목</th>
					      <th scope="col">작성자</th>
					      <th scope="col">작성일</th>
					      <th scope="col">조회수</th>
					    </tr>
					  </thead>
					  <tbody>
					  	<!-- DB에서 가져온 게시글 데이터로 html 코드 생성 -->
					  	<!-- BoardController 에서 boardView.jsp로 게시글 데이터를 보내줌 -->
					  	<!-- BoardDTO가 3개 들어 있는 리스트 boardLise 를 이용 -->
					  	<!-- for문을 돌려서 <tr>한 덩어리씩 생성 -->
					  	<!-- for문을 돌릴 대상을 items속성에 넣음 -->
					  	<!-- items의 리스트 내 요소가 순서대로 담길 변수를 var 속성에 선언 -->
					  	<c:forEach items="${keyBoardList}"  var="boardDTO">
						    <tr>
						      <td>${boardDTO.boardNo}</td>
						      <td>
						      <!-- 글 제목 클릭시 /boardDetailView?no=글번호 형태로 요청 -->
						      	<a href="${pageContext.request.contextPath}/boardDetailView?no=${boardDTO.boardNo}">${boardDTO.boardTitle}</a>
						      </td>
						      <td>${boardDTO.memName}</td>
						      <td>${boardDTO.boardDate}</td>
						      <td>${boardDTO.boardCount}</td>
						    </tr>
					  	</c:forEach>
					  	
						<!-- keyBoardList 의 사이즈가 0이면 검색 결과를 찾을 수 없습니다. -->
					  	<c:if test="${keyBoardList.size() == 0}">
					  		<tr>
						  		<!-- 4개의 td를 하나의 td로 그려줌 -->
					  			<td colspan="4">검색 결과를 찾을 수 없습니다.</td>
					  		</tr>
					  	</c:if>					  	
					  	
					  </tbody>
					</table>
					
					<!-- 글쓰기 버튼 -->
					<div class="d-flex justify-content-end">
						<button id="writeBtn" class="btn btn-primary" href="${pageContext.request.contextPath }/boardWriteView">글쓰기</button>
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

							    	<c:if test="${keySearch.searchWord != null}">
								      <a class="page-link" href="${pageContext.request.contextPath }/boardView?pageNo=${keySearch.firstPage - 1 }&rowSizePerPage=${keySearch.rowSizePerPage}&searchOption=${keySearch.searchOption}&searchWord=${keySearch.searchWord}" aria-label="Previous">
								        <span aria-hidden="true">&laquo;</span>
								      </a>
							    	</c:if>
							    	<c:if test="${keySearch.searchWord == null}">
								      <a class="page-link" href="${pageContext.request.contextPath }/boardView?pageNo=${keySearch.firstPage - 1 }&rowSizePerPage=${keySearch.rowSizePerPage}" aria-label="Previous">
								        <span aria-hidden="true">&laquo;</span>
								      </a>
							    	</c:if>
					    	

							    </li>
						    
						    <!-- 중간 페이지 번호 부분 -->
						    <!-- model에 keySearch 이름으로 searchVO를 담음 -->
						    <!-- searchVO 내 pageNo, firstPage, lastPage 채워져있음 -->
					    			    
							    <c:forEach begin="${keySearch.firstPage }" end="${keySearch.lastPage }" var="num">
									    <li class="page-item ${keySearch.pageNo == num ? 'active' : '' } ">
									    	<c:if test="${keySearch.searchWord != null}">
											    <a class="page-link" href="${pageContext.request.contextPath }/boardView?pageNo=${num }&rowSizePerPage=${keySearch.rowSizePerPage}&searchOption=${keySearch.searchOption}&searchWord=${keySearch.searchWord}">${num }</a>
									    	</c:if>						    
									    	<c:if test="${keySearch.searchWord == null}">
											    <a class="page-link" href="${pageContext.request.contextPath }/boardView?pageNo=${num }&rowSizePerPage=${keySearch.rowSizePerPage}">${num }</a>
									    	</c:if>						    
									    </li>
							    </c:forEach>
						   
						    <!-- 다음 페이지 -->
						    <!-- 마지막 페이지 도달 시 disabled 추가 -->
							    <li class="page-item ${keySearch.pageNo == keySearch.finalPage ? 'disabled' : ''  }">
								    <c:if test="${keySearch.searchWord == null}">
								     <a class="page-link" href="${pageContext.request.contextPath }/boardView?pageNo=${keySearch.lastPage + 1 }&rowSizePerPage=${keySearch.rowSizePerPage}" aria-label="Next">
								    	 <span aria-hidden="true">&raquo;</span>
								     </a>
								    </c:if>
								    <c:if test="${keySearch.searchWord != null}">
								     <a class="page-link" href="${pageContext.request.contextPath }/boardView?pageNo=${keySearch.lastPage + 1 }&rowSizePerPage=${keySearch.rowSizePerPage}&searchOption=${keySearch.searchOption}&searchWord=${keySearch.searchWord}" aria-label="Next">
								    	 <span aria-hidden="true">&raquo;</span>
								     </a>
								    </c:if>
							    </li>

						    
						  </ul>
						</nav>
					</div>					
					<!-- 검색기능 -->
					<div class="d-flex justify-content-center">
						<form class="d-flex" action="${pageContext.request.contextPath }/boardView" method="GET" >
							<select class="form-select me-1" name="searchOption">
								<option value="title" selected>제목</option>
								<option value="content">내용</option>
								<option value="name">작성자</option>
							</select>

							<input class="form-control me-1" type="text" name="searchWord">
							<button class="btn btn-primary" type="submit">
								<svg xmlns="http:www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
									<path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12">
								</svg>
							</button>
						</form>
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
				location.href = '${pageContext.request.contextPath }/boardWriteView';
			}
			else{
				alert("로그인 후 글쓰기가 가능합니다.");
				location.href = '${pageContext.request.contextPath}/loginView';
			}
			
		})
	
		let v_search = '${keySearch.searchWord}';
		
		function f_change() {
			console.log(event.target);
			console.log(event.target.value);
			
			let v_url = "${pageContext.request.contextPath}/boardView";
			let v_query = "?rowSizePerPage=" + event.target.value;
				v_query += "&pageNo=${keySearch.pageNo}";
			
			if(v_search){
				v_query += "&searchOption=${keySearch.searchOption}";
				v_query += "&searchWord=${keySearch.searchWord}";
			}
				
			
			location.href = v_url + v_query;
			
			
			
		}	
	</script>

</body>
</html>
