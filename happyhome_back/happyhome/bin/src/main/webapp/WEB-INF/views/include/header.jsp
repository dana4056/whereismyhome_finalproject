<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
<!-- Google fonts-->
<link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" />
<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="resources/css/style.css" rel="stylesheet" type="text/css"/>
<script src="https://kit.fontawesome.com/41f9630866.js" crossorigin="anonymous"></script>

</head>
       <style>
           .form-select{
               border-radius: 20px;
               padding-left: 20px;
               border: white;
               color: black;
           }
       </style>
<body>
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
      <div class="container px-4 px-lg-5">
          <a class="navbar-brand" href="/index">WhereIsMyHome</a>
          <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
              Menu
              <i class="fas fa-bars"></i>
          </button>
          <div class="collapse navbar-collapse" id="navbarResponsive">
              <ul class="navbar-nav ms-auto">
                  <li class="nav-item"><a class="nav-link" href="/notice?page=1">공지사항</a></li>
                  <li class="nav-item"><a class="nav-link" href="/set-roi">관심지역설정</a></li>
              </ul>
              <c:if test="${empty loginUser}">
	              <ul class="navbar-nav ms-auto" id="before-login">
	                  <li class="nav-item"><a class="nav-link" href="/signup">회원가입</a></li>
	                  <li class="nav-item"><a class="nav-link" href="/login">로그인</a></li>
	              </ul>
              </c:if>
	           <c:if test="${!empty loginUser}">
		           <ul class="navbar-nav ms-auto" id="after-login"">
		               <li class="nav-item"><a class="nav-link" href="/mypage/${loginUser.getId()}">마이페이지</a></li>    
		               <li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
		               <li class="nav-item"><a class="nav-link" href="/user/delete">회원탈퇴</a></li>
		           </ul>
	           </c:if>
          </div>
      </div>
  </nav>

</body>
</html>