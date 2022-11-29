function getContextPath(){
	let hostIdx = location.href.indexOf(location.host)+location.host.length;
	
	let contextPath = location.href.substring(hostIdx, location.href.indexOf('/', hostIdx+1));
	return contextPath;
}

function goBack(){
	window.history.back();
}

window.onload = function (){
	let url = new URL(window.location.href);
	const urlParams = url.searchParams;
	console.log("url:"+url);
  	let fetchurl = getContextPath()+"/house?action=view"; // JSON 리턴해주는 URL

    let no = urlParams.get('no');
 	console.log("no:"+no);
	let obj = {
		no:no,
	};
	
	let data = new URLSearchParams(obj);
	let param = {
			method: 'POST',
			body: data,
	};
	
  console.log(param);

  fetch(fetchurl, param)
  .then(response => response.json())
  .then(data => display(data));
  	
}
	
function display(detail){
	console.log("detail",detail);
	let tag = `<table class="table table-hover text-center text-white">
	<tr>	
	    <th>아파트이름</th>
	    <th>법정동</th>
	    <th>거래금액</th>
	    <th class="temp">거래일시</th>
	  </tr> 
	  <tr>
	    <td>${detail.apartmentName}</td>
	    <td>${detail.dongName}</td>
	    <td>${detail.dealAmount}</td>
	    <td class="temp">${detail.dealYear}-${detail.dealMonth}-${detail.dealDay}</td>
	  </tr>
	  <tr>
	    <th>건축일</th>
	    <th>층</th>
	    <th>면적</th>
	    <th class="temp">위도/경도</th>
	  </tr> 
	  <tr>
	    <td>${detail.buildYear}</td>
	    <td>${detail.floor}</td>
	    <td>${detail.area}</td>
	    <td class="temp">${detail.lat}/${detail.lng}</td>
	  </tr>
	</table>`;
	        		
	document.querySelector('#detailBox').innerHTML = tag;
	
  //============================== 지도 ================================
  var mapContainer = document.querySelector('#mapBox'); // 지도를 표시할 div 
  console.log(mapContainer);
  mapOption = { 
      center: new kakao.maps.LatLng(detail.lat, detail.lng), // 지도의 중심좌표
      level: 4 // 지도의 확대 레벨
  };

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
	
	//마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(detail.lat, detail.lng); 
	
	//마커를 생성합니다
	var marker = new kakao.maps.Marker({
	  position: markerPosition
	});
	
	//마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);
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


