<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ssafy.happyhome.model.dto.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
    <head>
        <meta http-equiv='cache-control' content='no-cache'>
        <meta http-equiv='expires' content='0'>
        <meta http-equiv='pragma' content='no-cache'>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>WhereIsMyHome</title>
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Varela+Round" rel="stylesheet" />
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="/css/style.css" rel="stylesheet" type="text/css"/>
        <script src="https://kit.fontawesome.com/41f9630866.js" crossorigin="anonymous"></script>
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ee5880aa0baf63a2eb6034f4c608eea3&libraries=services"></script>
        <style>
            .form-select{
                border-radius: 20px;
                padding-left: 20px;
                border: white;
                color: black;
            }
            table{
                background-color: rgb(52 52 52 / 59%);
                color: white;
            }
            th{
            	color: black;
            }
            .temp{
            	border-left:1px solid white;
            }
            
            #goBack{
	            padding: 9px;
	    		border-radius: 24px;
			    font-size: 80%;
			    border: 0;
			    color: #fff;
			    background-color: #64a19d;
			    border-color: #64a19d;
			    margin:20px;
            }

        </style>
        <script type="text/javascript" src="/js/houseDetail.js"></script>
    </head>
    <body id="page-top">
    <%@ include file="/include/header.jsp" %>
        <!-- Masthead-->
        <header class="masthead">
            <div class="container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center">
                <div class="d-flex justify-content-center">
                    <div class="text-center">
                        <h2 class=" mx-auto mt-2 mb-5">상세보기</h2>
						<div id="detailBox" style="width: 1100px; margin: 0 auto;">
						</div>
			 			<div id="mapBox" class="col-md-9" style="width: 1100px; height: 500px; margin: 0px auto;">

                        </div>
                        <button id="goBack" onClick="goBack()">목록으로</button>
                    </div>
                </div>
            </div>
        </header>

        <!-- Footer-->
        <footer class="footer small text-center text-white-50"><div class="container px-4 px-lg-5">Copyright © Your Website 2022</div></footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <!-- <script src="js/scripts.js"></script> -->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ee5880aa0baf63a2eb6034f4c608eea3&libraries=services"></script>
    </body>
</html>