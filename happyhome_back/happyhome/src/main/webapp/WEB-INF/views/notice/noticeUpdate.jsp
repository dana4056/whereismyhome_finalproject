<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>공지사항 수정</title>
    
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
    <div class="nbody" >
    
        <form method="post" action="notice">
        	<input type="hidden" name="action" value="update">
        	<input type="hidden" name="id" value="${notice.id}">
            <div class="form-group">
                <label>제목</label>
                <input type="text" id="title" name="title" class="form-control" value="${notice.title}" />
            </div>
            <div class="form-group mt-3">
                <label>내용</label>
                <textarea id="content" name="content" class="form-control">${notice.content}</textarea>
                <!--<input type="text" id="content" name="content" class="form-control" value="${notice.content}" /> -->
            </div>
            <br>
            <div class="form-foot form-group text-right">
            	<input type="submit" id="noticeUpdateBtn" class="vbtn red" value="수정">
            	<input type="reset"  id="closeBtn" class="vbtn black" href="/notice/noticeRead.jsp" value="취소">
            </div>
        </form>
    </div>
</body>
</html>
