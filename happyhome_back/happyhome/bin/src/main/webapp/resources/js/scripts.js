

window.addEventListener('DOMContentLoaded', event => {

  // Navbar shrink function
  var navbarShrink = function () {
      const navbarCollapsible = document.body.querySelector('#mainNav');
      if (!navbarCollapsible) {
          return;
      }
      if (window.scrollY === 0) {
          navbarCollapsible.classList.remove('navbar-shrink')
      } else {
          navbarCollapsible.classList.add('navbar-shrink')
      }

  };

  // Shrink the navbar 
  navbarShrink();

  // Shrink the navbar when page is scrolled
  document.addEventListener('scroll', navbarShrink);

  // Activate Bootstrap scrollspy on the main nav element
  const mainNav = document.body.querySelector('#mainNav');
  if (mainNav) {
      new bootstrap.ScrollSpy(document.body, {
          target: '#mainNav',
          offset: 74,
      });
  };

  // Collapse responsive navbar when toggler is visible
  const navbarToggler = document.body.querySelector('.navbar-toggler');
  const responsiveNavItems = [].slice.call(
      document.querySelectorAll('#navbarResponsive .nav-link')
  );
  responsiveNavItems.map(function (responsiveNavItem) {
      responsiveNavItem.addEventListener('click', () => {
          if (window.getComputedStyle(navbarToggler).display !== 'none') {
              navbarToggler.click();
          }
      });
  });

});

///////////////////////// select box 설정 (지역, 매매기간) /////////////////////////
let date = new Date();

window.onload = function () {
  let yearEl = document.querySelector("#year");
  let yearOpt = `<option value="">매매년도선택</option>`;
  let year = date.getFullYear();
  for (let i = year; i > year - 20; i--) {
    yearOpt += `<option value="${i}">${i}년</option>`;
  }
  yearEl.innerHTML = yearOpt;

  //로그인 상태 정보 불러오기 
//  let loginStatus = JSON.parse(localStorage.getItem("loginStatus")); // 
//  if (loginStatus == 1) { // 로그인된 상태
//    document.querySelector("#before-login").style.display = "none";
//    document.querySelector("#after-login").style.display = "";
//  } else {
//    document.querySelector("#before-login").style.display="";
//    document.querySelector("#after-login").style.display="none";
//  }

  // 브라우저가 열리면 시도정보 얻기.
  
  
  sendRequest("sido", "*00000000");
};


function logout() {
  localStorage.setItem("loginStatus", 0); // 로그인 상태로 지정
  document.querySelector("#before-login").style.display="";
  document.querySelector("#after-login").style.display="none";
  localStorage.removeItem("loginUser");
  alert("로그아웃되었습니다.");
}


document.querySelector("#year").addEventListener("change", function () {
  let month = date.getMonth() + 1;
  let monthEl = document.querySelector("#month");
  let monthOpt = `<option value="">매매월선택</option>`;
  let yearSel = document.querySelector("#year");
  let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
  for (let i = 1; i < m; i++) {
    monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
  }
  monthEl.innerHTML = monthOpt;
});

// https://juso.dev/docs/reg-code-api/
// let url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
// let regcode = "*";
// 전국 특별/광역시, 도
// https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000

// 시도가 바뀌면 구군정보 얻기.
document.querySelector("#sido").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
    sendRequest("gugun", regcode);
  } else {
    initOption("gugun");
    initOption("dong");
  }
});

// 구군이 바뀌면 동정보 얻기.
document.querySelector("#gugun").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
    sendRequest("dong", regcode);
  } else {
    initOption("dong");
  }
});
//sendRequest("sido", "*00000000");
function sendRequest(selid, regcode) {
  const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
  let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
  fetch(`${url}?${params}`)
    .then((response) => response.json())
    .then((data) => addOption(selid, data));
}

function addOption(selid, data) {
  let opt = ``;
  initOption(selid);
  switch (selid) {
    case "sido":
      opt += `<option value="">시도선택</option>`;
      data.regcodes.forEach(function (regcode) {
        opt += `
        <option value="${regcode.code}">${regcode.name}</option>
        `;
      });
      break;
    case "gugun":
      opt += `<option value="">구군선택</option>`;
      for (let i = 0; i < data.regcodes.length; i++) {
        if (i != data.regcodes.length - 1) {
          if (
            data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
            data.regcodes[i].name.split(" ").length !=
              data.regcodes[i + 1].name.split(" ").length
          ) {
            data.regcodes.splice(i, 1);
            i--;
          }
        }
      }
      let name = "";
      data.regcodes.forEach(function (regcode) {
        if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
        else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
        opt += `
        <option value="${regcode.code}">${name}</option>
        `;
      });
      break;
    case "dong":
      opt += `<option value="">동선택</option>`;
      let idx = 2;
      data.regcodes.forEach(function (regcode) {
        if (regcode.name.split(" ").length != 3) idx = 3;
        opt += `
        <option value="${regcode.code}">${regcode.name.split(" ")[idx]}</option>
        `;
      });
  }
  document.querySelector(`#${selid}`).innerHTML = opt;
}

function initOption(selid) {
  let options = document.querySelector(`#${selid}`);
  options.length = 0;
}

///////////////////////// 아파트 매매 정보 /////////////////////////
document.querySelector("#list-btn").addEventListener("click", function () {
  let gugunSel = document.querySelector("#gugun");
  let regCode = gugunSel[gugunSel.selectedIndex].value.substr(0, 5);
  let yearSel = document.querySelector("#year");
  let dongSel = document.querySelector("#dong");
  let dongCode = dongSel[dongSel.selectedIndex].value;
  let year = yearSel[yearSel.selectedIndex].value;
  let monthSel = document.querySelector("#month");
  let month = monthSel[monthSel.selectedIndex].value;
  let dealYM = year + month;
	
  let url = "./house?action=list"; // JSON 리턴해주는 URL

	let obj = {
		dongCode:dongCode,
		year:year,
		month:month
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

function display(deals){
	console.log(deals);
	let tag = `<table style="width:1000px; margin: 0 auto;" class="table table-hover text-center text-white">
				<tr>
					<th>번호</th>
					<th>법정동</th>
					<th>아파트 이름</th>
					<th>거래금액</th>
					<th>거래일시</th>
				</tr>`;
	if(deals.length == 0){
		tag += `	<tr>
			<td colspan="5">검색결과가 없습니다.</td>
			</tr>`;	
	}else{
		deals.forEach(function(deal, idx){
			tag += `	<tr>
				<td>${idx+1}</td>
				<td>${deal.dongName}</td>
				<td><a href="house/houseDetail.jsp?no=${deal.no}">${deal.apartmentName}<a></td>
				<td>${deal.dealAmount}</td>
				<td>${deal.dealYear}-${deal.dealMonth}-${deal.dealDay}</td>
				</tr>`;	
			
		});		
	}
	tag += `</table>`;
	
	document.querySelector('#dealListBox').innerHTML = tag;
}

function makeList(data) {
  document.querySelector("table").setAttribute("style", "display: ;");
  document.querySelector(".col-md-9").setAttribute("style", "visibility : ;");
  let tbody = document.querySelector("#aptlist");
  let parser = new DOMParser();
  const xml = parser.parseFromString(data, "application/xml");
  //   console.log(xml);
  initTable();
  let apts = xml.querySelectorAll("item");
  // console.log(apts);
  for (const apt of apts) {
      console.log("1. "+apt.querySelector("법정동읍면동코드").textContent);
      console.log("2. "+document.querySelector("#dong").value.substring(5));
      if (apt.querySelector("법정동읍면동코드").textContent != document.querySelector("#dong").value.substring(5)) continue;

      let tr = document.createElement("tr");

      let nameTd = document.createElement("td");
      nameTd.appendChild(document.createTextNode(apt.querySelector("아파트").textContent));
      tr.appendChild(nameTd);

      let floorTd = document.createElement("td");
      floorTd.appendChild(document.createTextNode(apt.querySelector("층").textContent));
      tr.appendChild(floorTd);

      let areaTd = document.createElement("td");
      areaTd.appendChild(document.createTextNode(apt.querySelector("전용면적").textContent));
      tr.appendChild(areaTd);

      let dongTd = document.createElement("td");
      dongTd.appendChild(document.createTextNode(apt.querySelector("법정동").textContent));
      tr.appendChild(dongTd);

      let priceTd = document.createElement("td");
      priceTd.appendChild(
      document.createTextNode(apt.querySelector("거래금액").textContent + "만원"));
      priceTd.classList.add("text-end");
      tr.appendChild(priceTd);

    tbody.appendChild(tr);
    var marker, infowindow;
    var container = document.getElementById("map");
    var myLatLng = new kakao.maps.LatLng(37.5012743, 127.039585);
    var options = {
      center: myLatLng,
      level: 3,
    };

    var map = new kakao.maps.Map(container, options);
    var geocoder = new kakao.maps.services.Geocoder();
    // 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
    var mapTypeControl = new kakao.maps.MapTypeControl();
    
    // 지도에 컨트롤을 추가해야 지도위에 표시됩니다
    // kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
    map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
    
    // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
    var zoomControl = new kakao.maps.ZoomControl();
    map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

    // HTML5의 geolocation으로 사용할 수 있는지 확인합니다
    if (navigator.geolocation) {
      // GeoLocation을 이용해서 접속 위치를 얻어옵니다
      navigator.geolocation.getCurrentPosition(function (position) {
        var lat = position.coords.latitude, // 위도
          lon = position.coords.longitude; // 경도

        (myLatLng = new kakao.maps.LatLng(lat, lon)), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
          (message = '<div style="padding:5px;">나여기있어요</div>'); // 인포윈도우에 표시될 내용입니다

        // 마커와 인포윈도우를 표시합니다
        displayMarker(myLatLng, message);
      });
    } else {
      // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

      (myLatLng = new kakao.maps.LatLng(37.5012743, 127.039585)),
        (message = "geolocation을 사용할수 없어요..");

      displayMarker(myLatLng, message);
    }

    // 지도에 마커와 인포윈도우를 표시하는 함수입니다
    function displayMarker(myLatLng, message, markerImage) {
      // 마커를 생성합니다
      marker = new kakao.maps.Marker({
        map: map,
        position: myLatLng,
        image: markerImage, // 마커이미지 설정
      });

      var iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;

      // 인포윈도우를 생성합니다
      infowindow = new kakao.maps.InfoWindow({
        content: iwContent,
        removable: iwRemoveable,
      });

      // 인포윈도우를 마커위에 표시합니다
      // infowindow.open(map, marker);

      // 지도 중심좌표를 접속위치로 변경합니다
      map.setCenter(myLatLng);

      // 마커에 마우스오버 이벤트를 등록합니다
      kakao.maps.event.addListener(marker, "mouseover", function () {
        // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
        infowindow.open(map, marker);
      });

      // 마커에 마우스아웃 이벤트를 등록합니다
      kakao.maps.event.addListener(marker, "mouseout", function () {
        // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
        infowindow.close();
      });
}     
    tr.addEventListener("click", function () {

      let addr;
      let citySel = document.querySelector("#sido");
      let gugunSel = document.querySelector("#gugun");
      let dongSel = document.querySelector("#dong");
      let cityname = citySel[citySel.selectedIndex].textContent;
      let gugunname = gugunSel[gugunSel.selectedIndex].textContent;
      let dongname = dongSel[dongSel.selectedIndex].textContent;
      let jibun = apt.querySelector("지번").textContent;
      addr = cityname + " " + gugunname + " " + dongname + " " + jibun;
      geocoder.addressSearch(addr, function(result, status) {

        // 정상적으로 검색이 완료됐으면 
        if (status === kakao.maps.services.Status.OK) {
      
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
      
            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords
            });
      
            // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao.maps.InfoWindow({
                content: `<div style="width:150px;text-align:center;padding:6px 0;">${apt.querySelector("아파트").textContent}</div>`
            });
            infowindow.open(map, marker);
      
            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        } 
      });    
    })
  }
}

function initTable() {
  let tbody = document.querySelector("#aptlist");
  let len = tbody.rows.length;
  for (let i = len - 1; i >= 0; i--) {
    tbody.deleteRow(i);
  }
}

