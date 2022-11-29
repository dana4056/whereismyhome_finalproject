<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ssafy.happyhome.model.dto.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>사용자 정보 관리 사이트</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
  </head>
  <body>
  <%@ include file="/WEB-INF/views/include/simple_header.jsp" %>
    <main class="container">
      <div class="login-page">
        <h1>마이페이지</h1>
        <div class="form">
          <form name="register-form" class="register-form" action = "/user/modify" method= "post">
            <input id="id" name="id" type="text" value="${loginUser.id}" required readonly />
            <input
              id="password"
              name="password"
              type="password"
              value="${loginUser.pass}"
              required readonly
            />
            <input id="name" name="name" type="text" value="${loginUser.name}" required readonly />
            <input id="address" name="address" type="text" value="${loginUser.address}" required readonly >
            <input id="phone" name="phone" type="text" value="${loginUser.phone}" required readonly />
<!--             <form name="del_user" class="del_user" action = "/user/delete" method= "post">
          		<input name="btn" type="submit" value = "탈퇴하기">
			</form> --> 
		    <input type="submit" value = "내정보수정~">
<!--             <form name="update_user" class="update_user" action = "/user/modify" method= "post">
			</form> -->
          	</form>
        </div>
      </div>
    </main>
    <footer>
      <ul class="footer-list">
        <li><a href="#">개인정보 처리방침</a></li>
        <li><a href="#">이용 약관</a></li>
        <li><a href="#">오시는 길</a></li>
        <li>&copy; SSAFY</li>
      </ul>
    </footer>
  </div>
    <script type="text/javascript" src="js/user.js"></script>
  </body>
</html>
