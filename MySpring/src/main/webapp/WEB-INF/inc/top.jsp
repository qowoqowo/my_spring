<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   

<style>
		.min-profile-box{
			display:flex;
			justify-content:center;
			align-items: center;
		}
		
		.min-profile-img{
			width: 50px;
			height: 50px;
			border-radius: 50%;
			overflow: hidden;
			
			cursor: pointer;
		}


</style>   


<!-- Navigation-->
<nav class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top" id="mainNav">
    <div class="container">
        <a class="navbar-brand" href="<c:url value="/" />">스프링 프로젝트</a>
        <button class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto">
            
            	<c:if test="${sessionScope.login.memLv eq 0}">
	                <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="${pageContext.request.contextPath }/adminView" >회원관리</a></li>
            	</c:if>

                <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="<%=request.getContextPath() %>/boardView" >자유게시판</a></li>

                <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="${pageContext.request.contextPath }/boardPhotoView" >사진게시판</a></li>
                
            	<!-- 현재 페이지를 요청한 클라이언트가 로그인을 안 한 상태면 네비게이션에 로그인, 회원가입 버튼 표기 -->
            	<!-- 현재 페이지를 요청한 클라이언트가 로그인을 한 상태면 네비게이션에 호빵맨님, 로그아웃 버튼 표기 -->
                <!-- JSP구문으로 조건에 따라 다른 화면이 나타나게 구현 -->
                <!-- c:if 문은 test 속성의 값이 true면 내부 html코드를 화면에 나타냄 -->
                <!-- c:if 문은 test 속성의 값이 false면 내부 html코드를 화면에 안 나타냄 -->
                <c:if test="${sessionScope.login == null}">
	                <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="${pageContext.request.contextPath }/loginView">로그인</a></li>
	                <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="<c:url value="/registView" />">회원가입</a></li>
               </c:if>
               
                <c:if test="${sessionScope.login != null}">
                
					<c:if test="${sessionScope.login.memProfileImg == null }">
						<div class="min-profile-box">
							<img class="min-profile-img" src="${pageContext.request.contextPath }/assets/img/defaultImg.jpg">
						</div>
					</c:if>
					
					<c:if test="${sessionScope.login.memProfileImg != null}">
						<div class="min-profile-box">
							<img class="min-profile-img" src="${pageContext.request.contextPath }/displayImage?fileName=${sessionScope.login.memProfileImg}">
						</div>
					</c:if>
					                 
	                <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="${pageContext.request.contextPath }/memEditView">${sessionScope.login.memName} 님</a></li>
	                <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="<c:url value="/logoutDo" />">로그아웃</a></li>
                </c:if>
                
            </ul>
        </div>
    </div>
    
    <script type="text/javascript">
    	console.log("${sessionScope.login.memId}");
    
    
    </script>
    
</nav>

