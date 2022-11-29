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
        <!-- <link href="./css/style.css" rel="stylesheet" type="text/css"/> -->
        <script src="https://kit.fontawesome.com/41f9630866.js" crossorigin="anonymous"></script>
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
            .submitBtn{
	            padding: 11px;
	    		border-radius: 24px;
	    		box-shadow: 0 0.1875rem 0.1875rem 0 rgb(0 0 0 / 10%) !important;
			    font-family: "Varela Round", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
			    font-size: 80%;
			    text-transform: uppercase;
			    letter-spacing: 0.15rem;
			    border: 0;
			    color: #fff;
			    background-color: #64a19d;
			    border-color: #64a19d;
    		}

        </style>
        
    </head>
    <body id="page-top">
    <%@ include file="/WEB-INF/views/include/header.jsp" %>

        <!-- Masthead-->
        <header class="masthead">
            <div class="container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center">
                <div class="d-flex justify-content-center">
                    <div class="text-center">
                        <h1 class="mx-auto my-0 text-uppercase">WhereIsMyHome</h1>
                        <h2 class="text-white-50 mx-auto mt-2 mb-5">SSAFY WEB-FRONT PJT</h2>
                        <div class="row col-md-12 justify-content-center mb-2">
                            <div class="form-group col-md-2">
                                <select class="form-select" id="sido">
                                  <option value="">시도선택</option>
                                </select>
                            </div>
                            <div class="form-group col-md-2">
                                <select class="form-select " id="gugun">
                                    <option value="">구군선택</option>
                                </select>
                            </div>
                            <div class="form-group col-md-2">
                                <select class="form-select " id="dong">
                                    <option value="">동선택</option>
                                </select>
                            </div>
                            <div class="form-group col-md-2">
                                <select class="form-select" id="year"></select>
                            </div>
                            <div class="form-group col-md-2">
                                <select class="form-select" id="month">
                                    <option value="">매매월선택</option>
                                </select>
                            </div>
                            <a id="list-btn" class="btn btn-primary form-group col-md-1"><i class="fa-sharp fa-solid fa-magnifying-glass"></i></a>
							<form class="col-md-1" action="house">
	                        	<input type="hidden" name="action" value="interest_view">
	            				<input type="hidden" name="userid" value=${loginUser.id }>
	            				<input class="submitBtn" type="submit" value = "관심지역으로 검색">
	                        </form>
                        </div>
                        <div id="dealListBox" style="overflow: scroll; overflow-x: hidden; width: 1100px; height: 600px; margin: 0 auto;"> 
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <!-- Contact-->
        <section class="contact-section">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5">
                    <div class="col-md-4 mb-3 mb-md-0">
                        <div class="card py-4 h-100">
                            <div class="card-body text-center">
                                <i class="fa-solid fa-people-group text-primary mb-2"></i>
                                <h4 class="text-uppercase m-0">Team</h4>
                                <hr class="my-4 mx-auto" />
                                <div class="small text-black-50">정다은 이승현</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3 mb-md-0">
                        <div class="card py-4 h-100">
                            <div class="card-body text-center">
                                <i class="fas fa-envelope text-primary mb-2"></i>
                                <h4 class="text-uppercase m-0">Email</h4>
                                <hr class="my-4 mx-auto" />
                                <div class="small text-black-50"><a href="#!">ssafy@ssafy.com</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 mb-3 mb-md-0">
                        <div class="card py-4 h-100">
                            <div class="card-body text-center">
                                <i class="fas fa-mobile-alt text-primary mb-2"></i>
                                <h4 class="text-uppercase m-0">Phone</h4>
                                <hr class="my-4 mx-auto" />
                                <div class="small text-black-50">+1 (555) 010-0000</div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="social d-flex justify-content-center">
                    <a class="mx-2" href="#!"><i class="fab fa-twitter"></i></a>
                    <a class="mx-2" href="#!"><i class="fab fa-facebook-f"></i></a>
                    <a class="mx-2" href="#!"><i class="fab fa-github"></i></a>
                </div>
            </div>
        </section>
        <!-- Footer-->
        <footer class="footer small text-center text-white-50"><div class="container px-4 px-lg-5">Copyright © Your Website 2022</div></footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ee5880aa0baf63a2eb6034f4c608eea3&libraries=services"></script>
    </body>
</html>