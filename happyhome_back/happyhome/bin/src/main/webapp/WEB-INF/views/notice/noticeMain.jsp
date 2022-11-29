<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- jstl 태그 사용하기 위해 설정 -->
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <title>공지사항</title>
    
    <style>
    	.vbtn{
    	 	color: #fff;
    		background-color: #64a19d;
    		box-shadow: 0 0.1875rem 0.1875rem 0 rgb(0 0 0 / 10%) !important;
		    padding: 3px;
    		border-radius: 6px;
    		text-decoration: none;
    	}
    	.addbtn{
    		float:right;
    	}
    	.fa-circle-plus{
    	    color: #7e7e7e;
    	}
    	.noticeWrap {
    	    margin-top: 65px;
    	}
    	#indexBox{
    		text-align: center;
   		    margin: 35px auto;	
    	}
    	#indexBox a{
	    	border: 1px solid #6a8381;
	    	padding: 5px 10px;
	    	text-decoration: none;
	    	border-radius: 7px;
	    	color: #6a8381;
    	}
    	#indexBox a:hover{
			background-color: #9ab7b5;
			border: 1px solid white;
			color: white;
    	}
    	
    </style>
    <script type="text/javascript" src="/js/paging.js"></script>
</head>

<body>
	<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <section class="noticeWrap container align-middle p-5">

        <a type="button" class="addbtn" href="/notice/noticeInsert.jsp" data-bs-toggle="modal"> <i class="fa-solid fa-circle-plus fa-2x"></i></a>

        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">번호</th>
                    <th scope="col">제목</th>
                    <th scope="col">내용</th>
                    <th scope="col">조회수</th>
                    <th scope="col">조회</th>
                </tr>
            </thead>
            <tbody id="noticeBody">
	            <c:forEach items="${attr['list']}" var="notice" varStatus="vs">
				    <tr>
				        <td>${notice.id}</td>
				        <td>${notice.title}</td>
				        <td>${notice.content}</td>
				        <td>${notice.view}</td>
				        <td><a type="button" class="vbtn" href="/notice?action=view&id=${notice.id}">조회</a></td>
				        <td></td>
				    </tr>
				</c:forEach>
            </tbody>
        </table>
        
        <div id="indexBox">
        	<a href = "/notice?action=list&page=1">처음으로</a>
			<c:forEach var="page" begin="1" end="${attr['pageCount']}">
				<a href = "/notice?action=list&page=${page}">${page}</a>
			</c:forEach>
			<a href = "/notice?action=list&page=${attr['pageCount']}">끝으로</a>
        </div>
    </section>

    



</body>
</html>