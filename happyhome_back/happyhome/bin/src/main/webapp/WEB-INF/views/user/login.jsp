<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <h1>Welcome</h1>
        <div class="form">
          <form name="login-form" class="login-form" action = "login" method= "post">
            <input id="id" name="id" type="text" placeholder="ID" />
            <input id="password" name="password" type="password" placeholder="password"/>
            <input type="submit" value = "로그인">
            <button onclick="location.href='/signup'" type="button">
              	회원가입
            </button>
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
    <script type="text/javascript" src="js/user.js"></script>
  </body>
</html>
