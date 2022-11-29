<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>공지사항 조회</title>
    
    <script>
	function noticeDelete(){
		if(confirm("삭제하시겠습니까?")){
			location.replace("./notice?action=delete&id=${notice.id}");
		}
	}
</script>
<style>
		#noticewrap{
			background-color: #f5f5f5;
		}
		.nbody{
			background-color: white;
		    height: 580px;
		    width: 550px;
		    margin: 100px auto;
		    padding: 35px;
		    border-radius: 20px;
		    position : relative;
		}
		.ncontent{
			border-top: solid 1px lightgrey;
		    padding-top: 15px;
		    height: 50%;
		    margin: 15px 0;
		}
		.nfoot{
			position : absolute;
		    bottom: 29px;
		    right: 29px;
		}
		.vbtn{
    	 	color: #fff;
    		box-shadow: 0 0.1875rem 0.1875rem 0 rgb(0 0 0 / 10%) !important;
		    padding: 3px;
    		border-radius: 6px;
    		text-decoration: none;
    	}
    	.black{
    		background-color: #646464;
    	}
    	.red{
    		background-color: #bb5b5b;
    	}

</style>
    
</head>

<body id="noticewrap">
	<%@ include file="/WEB-INF/views/include/header.jsp" %>
    
    <div class="col-lg-8 mx-auto p-4 mt-5">
        <div id="card">
            <div class="nbody">
                <h5 class="card-title" id="title">${notice.title}</h5>
                <small>조회수:${notice.view} </small>
                <p class="ncontent" id="content">${notice.content}</p>
               
                <div class="nfoot">
	                <a type="button" id = "noticeUpdateBtn" class="vbtn black" href="/notice?action=modify&id=${notice.id}">수정</a>
	                <a type="button" class="vbtn red" name="removeBnt" onclick="javascript:noticeDelete()">삭제</a>
                </div>

            </div>
        </div>
    </div>
</body>
</html>