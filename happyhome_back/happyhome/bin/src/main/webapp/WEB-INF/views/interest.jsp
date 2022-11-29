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

    <title>관심지역</title>
    
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
    	
    </style>
</head>

<body>
	<%@ include file="/WEB-INF/views/include/header.jsp" %>

    <section class="noticeWrap container align-middle p-5">

        <a type="button" class="addbtn" href="./setROI.jsp?" data-bs-toggle="modal"> <i class="fa-solid fa-circle-plus fa-2x"></i></a>

        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">시도</th>
                    <th scope="col">구군</th>
                    <th scope="col">동</th>
                </tr>
            </thead>
            <tbody id="noticeBody">
	            <c:forEach items="${interests}" var="interest" varStatus="vs">
				    <tr>
				        <td>${interest.sidoName}</td>
				        <td>${interest.gugunName}</td>
				        <td>${interest.dongName}</td>
				        <td><a href ="./house?action=interest_detail&dongCode=${interest.dongCode }">상세보기</a></td>
				        <td></td>
				    </tr>
				</c:forEach>
            </tbody>
        </table>
    </section>

    



</body>
</html>