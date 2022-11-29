<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta http-equiv='cache-control' content='no-cache'>
  <meta http-equiv='expires' content='0'>
  <meta http-equiv='pragma' content='no-cache'>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  
  <title>관심지역 설정</title>
  <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />

  <style>
    #wrap{
      height: 100vh;
    }
    .modal-dialog{
      margin: 0 auto;
      padding: 350px 0;
    }

  </style>
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
  <section id="wrap">
    <div class="modal modal-signin position-static d-block bg-secondary py-5" tabindex="-1" role="dialog" id="modalSignin">
      <div class="modal-dialog" role="document">
        <div class="modal-content rounded-5 shadow">
          <div class="modal-header p-5 pb-4 border-bottom-0">
            <!-- <h5 class="modal-title">Modal title</h5> -->
            <h2 class="fw-bold mb-0">관심지역 설정</h2>
          </div>

          <div class="modal-body p-5 pt-0">
            <form class="" action="house" >
            <input type="hidden" name="action" value="interest">
            <input type="hidden" name="userid" value=${loginUser.id }>
              <select class="form-select p-2 mb-1" id="sido" name="sido">
                <option value="">시도선택</option>
              </select>
              <select class="form-select p-2 mb-1" id="gugun" name="gugun">
                <option value="">구군선택</option>
              </select>
              <select class="form-select p-2 mb-3" id="dong" name="dong">
                <option value="">동선택</option>
              </select>
              <input type="submit" value="선택">
              
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- Bootstrap core JS-->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <!-- Core theme JS-->
  <script src="js/ROIscript.js"></script>
  <script>
  <!-- 
  document.querySelector("#submitBnt").addEventListener("click", function () {
      event.preventDefault();
      let id = document.cookie.split("=");
      id = id[1];
      let sidoSel = document.querySelector("#sido");
      let sido = sidoSel[sidoSel.selectedIndex].value;
      let gugunSel = document.querySelector("#gugun");
      let gugun = gugunSel[gugunSel.selectedIndex].value;
      let dongSel = document.querySelector("#dong");
      let dong = dongSel[dongSel.selectedIndex].value;
      
      alert("ajax 통신 시작");
      
      let url = "./house?action=interest";
	  
      let obj = {
    			sido:sido,
    			gugun:gugun,
    			dong:dong,
    			id:id
    		};
    		
    		let data = new URLSearchParams(obj);
    		let param = {
    				method: 'POST',
    				body: data,
    		};
    		
    	  console.log(param);

    	  fetch(url, param)
    	  .then(response => response.json())
    	  .then(data => display(data));

    });
  -->

  </script>
</body>
</html>