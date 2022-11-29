<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

    <title>공지사항 작성</title>

	<style>

	.nbody{
	    background-color: white;
	    height: 580px;
	    width: 60%;
	    margin: 100px auto;
	    border-radius: 20px;
	}
	form{
		position:relative;
	}
	.vbtn{
	 	color: #fff;
	 	box-shadow: 0 0.1875rem 0.1875rem 0 rgb(0 0 0 / 10%) !important;
	   	padding: 3px;
	 	border-radius: 6px;
	 	text-decoration: none;
	 	border: 0;
	}
   	.black{
   		background-color: #646464;
   	}
   	.red{
   		background-color: #bb5b5b;
   	}
 	.form-foot{
		position : absolute;
	    right: 0px;
	    margin-top: 20px
	}
	</style>
    
</head>
<body>
	<%@ include file="/WEB-INF/views/include/header.jsp" %>
    <div class="nbody">
        <h2>공지사항 작성</h2>
    
        <form class="formwarp" method="post" action="/notice">
       		<input type="hidden" name="action" value="regist">
            <div class="form-group">
                <label>제목</label>
                <input type="text" id="title" name="title" class="form-control" placeholder="제목" />
            </div>
            <div class="form-group">
                <label>내용</label>
                <textarea id="content" name="content" class="form-control"></textarea>
                <!-- <input type="text" id="content" name="content" class="form-control" placeholder="내용" /> -->
            </div>
            <div class="form-foot form-group text-right">
                <!-- <a type="button" id="noticeInsertBtn" class="btn btn-primary " onclick="noticeInsert()" href="./noticeMain.jsp">생성</a> -->
                <input id="noticeInsertBtn" class="vbtn black" type="submit" value="등록">
                <a type="button" class="vbtn red" href="/notice?page=1">닫기</a>
            </div>
        </form>
    
    </div>

</body>
</html>