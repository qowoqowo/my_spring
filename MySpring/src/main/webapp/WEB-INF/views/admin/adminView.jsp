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
<title>회원관리</title>

	<%@ include file="/WEB-INF/inc/header.jsp" %>
	

</head>

<body id="page-top">

	<%@ include file="/WEB-INF/inc/top.jsp" %>

	<section class="page-section" id="contact">
		<!-- 부트스트랩으로 padding-top 을 좀 주고자 한다. -->
		<div class="container pt-5">
			<!-- Contact Section Heading-->
			<h2
				class="page-section-heading text-center text-uppercase text-secondary mb-0">회원관리</h2>
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

<table class="table table-hover">
					  <thead>
					    <tr>
					      <th scope="col">아이디</th>
					      <th scope="col">이름</th>
					      <th scope="col">핸드폰</th>
					      <th scope="col">이메일</th>
					      <th scope="col">등급</th>
					      <th scope="col">권한변경</th>
					      <th scope="col">계정삭제</th>
					    </tr>
					  </thead>
					  
					  <tbody>
 					  	<c:forEach items="${keyMemList}"  var="memberDTO">
 						    <tr>
						      <td>${memberDTO.memId }</td>
						      <td>${memberDTO.memName }</td>
						      <td>${memberDTO.memPhone }</td>
						      <td>${memberDTO.memEmail }</td>
						      
						      <c:if test="${memberDTO.memLv == '1' }">
							      <td>일반</td>
						      </c:if>
						      <c:if test="${memberDTO.memLv == '0' }">
							      <td>관리자</td>
						      </c:if>

						      <c:if test="${memberDTO.memLv == '1'}">
							      <td><button class="btn btn-primary" onclick="f_edit('${memberDTO.memId }')" type="button" >추가</button></td>
						      </c:if>
						      <c:if test="${memberDTO.memLv == '0' && memberDTO.memId != 'admin'}">
							      <td><button class="btn btn-warning" onclick="f_out('${memberDTO.memId }')" type="button" >제거</button></td>
						      </c:if>
						      
						      <c:if test="${memberDTO.memId == 'admin' }">
						      	<td></td>
						      </c:if>
						      
						      <c:if test="${memberDTO.memId != 'admin' }">
							      <td><button class="btn btn-danger" onclick="f_delete('${memberDTO.memId }')" type="button" >삭제</button></td>
						      </c:if>
						      <c:if test="${memberDTO.memId == 'admin' }">
							      <td>관리자</td>
						      </c:if>
						    </tr>
					  	</c:forEach>
					  	
					  </tbody>
					</table>					
					
				</div>
			</div>
		</div>
	</section>
	<!-- Footer-->
	<%@ include file="/WEB-INF/inc/footer.jsp" %>
	
	<script type="text/javascript">
		
		function f_delete(memId){
			console.log(memId);
			if(confirm("정말 삭제하시겠습니까?")){
				
				const v_url = "${pageContext.request.contextPath}/delAdminDo";
				
				$.ajax({
					type: 'POST',
					url: v_url,
					contentType : "application/json; charset:UTF-8",
					data: memId,
					success: function(resp){
						alert("삭제 완료");
						location.reload();
					}
				});
			};
		}
	
	 	
		function f_edit(memId){
			if(confirm("관리자로 추가하시겠습니까?")){
				
				const v_url = "${pageContext.request.contextPath}/updateAdminDo";
				
				$.ajax({
					type: 'POST',
					url: v_url,
					contentType : "application/json; charset:UTF-8",
					data: memId,
					success: function(resp){
						alert("추가 완료");
						location.reload();
					}
				})
			}
		};

		
		function f_out(memId){
			if(confirm("관리자에서 제외시키겠습니까?")){
				
				const v_url = "${pageContext.request.contextPath}/outAdminDo";
				
				$.ajax({
					type: 'POST',
					url: v_url,
					contentType : "application/json; charset:UTF-8",
					data: memId,
					success: function(resp){
						alert("제외 완료");
						location.reload();
					}
				})				
				
			}
			
		}
	
	
		
		
	</script>
	

</body>
</html>
