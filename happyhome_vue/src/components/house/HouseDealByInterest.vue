<template>
  <div>

    <div class="form-box">
      <select v-model="interIdx" class="form-select" id="interestSel">
        <option value="">관심 지역 선택</option>
        <option
          :key="index"
          :value="index"
          v-for="(interest, index) in fetchInterestList"
        >
          {{ interest.area }}
        </option>
      </select>

      <a id="list-btn" @click="searchInterest" class="usallyBnt btn-primary">
        <i class="fa-sharp fa-solid fa-magnifying-glass"></i>
      </a>
    </div>
    <div id="contentBox">
      <div id="map" style="width: 100%; height: 100vh">
        <!--=================================검색 카테고리==================================== -->
        <ul id="category">
          <li
            v-for="(cate, index) of categories"
            :key="index"
            :id="`${cate.code}`"
            :data-order="`${index}`"
            @click="onClickCategory($event, index)"
          >
            <span class="category_bg" :class="`${cate.cName}`"></span>
            {{ cate.name }}
          </li>
        </ul>
        <!-- ============================== 아파트 오버레이 ============================= -->
        <div ref="over">
          <div class="wrap">
            <div class="info">
              <div class="title">
                {{ detail.apartmentName }}
                <div class="close" @click="closeOverlay('H')" title="닫기"></div>
              </div>
              <div class="body">
                <div class="desc">
                  <div>
                    <div class="ellipsis">{{ detail.addr }}</div>
                    <div class="jibun ellipsis">
                      <p v-for="(sub, index) in subway" :key="index">
                        <i class="fa-solid fa-train-subway"></i>
                        <small>
                          {{ sub.name }}
                          <i class="fa-solid fa-arrow-right"></i> 약
                          {{ sub.dis }}m
                        </small>
                      </p>
                    </div>
                    <div>
                      <small
                        >최근: {{ detail.recentPrice | won(detail.recentPrice)
                        }}<br
                      /></small>
                    </div>
                  </div>
                  <div class="heartBox">
                    <img @click="clkHeart" v-if="isGood_H" src="@/assets/heart-f.png" alt=""/>
                    <img @click="clkHeart" v-else src="@/assets/heart-e.png" alt=""/>
                    <small @click="openDetailTab">자세히 보기></small>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!-- ============================== 장소 오버레이 ============================= -->
        <div ref="placeinfo" class="placeinfo_wrap">
          <div class="placeinfo">
            
            <div class="title">
              <p>
                {{ placeDetail.place_name }}
              </p> 
              <span @click="closeOverlay('P')">X</span>
            </div>
            <div id="placeinfoContent">
              <div>
                <div v-if="placeDetail.road_address_name">
                  <span :title="`${placeDetail.road_address_name}`">
                    {{ placeDetail.road_address_name }}
                  </span>
                  <span class="jibun" :title="`${placeDetail.address_name}`">(지번:{{ placeDetail.address_name }})</span>
                </div>
                <div v-else>
                  <span :title="`${placeDetail.address_name}`">{{
                    placeDetail.address_name
                  }}</span>
                </div>
                <span class="tel">{{ placeDetail.phone }}</span>
              </div>

              <div class="heartBox">
                  <img @click="clkHeart('D')" v-if="isGood_P" src="@/assets/heart-f.png" alt=""/>
                  <img @click="clkHeart('I')" v-else src="@/assets/heart-e.png" alt=""/>
                  <small>
                    <a :href="`${placeDetail.place_url}`" target="_blank" :title="`${placeDetail.place_name}`">
                      자세히 보기>
                    </a>
                  </small>
              </div>
            </div>
          
          </div>
        </div>

        <!-- ==============================디테일 박스============================= -->
        <div id="detailBox" v-show="selDetail">
          <span id="closeTab" @click="closeTab"
            ><i class="fa-solid fa-xmark"></i
          ></span>

          <div
            id="roadview"
            ref="roadview"
            style="width: 100%; height: 200px"
          ></div>
          <div id="detailContent">

            <div id="detailHeader">
              <h6>
                <b>{{ detail.apartmentName }}</b>
              </h6>
              <div class="heartBox">
                <img @click="clkHeart('D')"  v-if="isGood_H" src="@/assets/heart-f.png" alt=""/>
                <img @click="clkHeart('I')" v-else src="@/assets/heart-e.png" alt=""/>
              </div>
            </div>

            <p class="gray">
              <i class="fa-sharp fa-solid fa-house-chimney"></i> {{ detail.addr }}
            </p>

            <small><b>근처 지하철</b></small>
            <div class="subway gray" v-if="subway.length > 0">
              <p v-for="(sub, index) in subway" :key="index">
                <i class="fa-solid fa-train-subway"></i>
                <small>
                  {{ sub.name }} <i class="fa-solid fa-arrow-right"></i> 약
                  {{ sub.dis }}m
                </small>
              </p>
            </div>
            <div v-else>
              <small class="gray">정보 없음</small>
            </div>

            <div style="margin-top: 20px">
              <small><b>매매 정보</b></small>
            </div>
            <small
              >최근: {{ detail.recentPrice | won(detail.recentPrice) }}<br />
            </small>
            <small>
              최고·최저: {{ detail.maxPrice | won(detail.maxPrice) }} /
              {{ detail.minPrice | won(detail.minPrice) }}
            </small>
            <div id="dealBox">
              <table>
                <thead>
                  <tr>
                    <th scope="col">거래일</th>
                    <th scope="col">면적</th>
                    <th scope="col">층</th>
                    <th scope="col">금액</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="deal in fetchDeals" :key="deal.no">
                    <td>
                      {{ deal.dealYear }}-{{ deal.dealMonth }}-{{ deal.dealDay }}
                    </td>
                    <td>{{ deal.area }}㎡</td>
                    <td>{{ deal.floor }}층</td>
                    <td>{{ deal.dealAmount | won(deal.dealAmount) }}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <!-- ================================리스트 박스============================ -->

        <div id="listBox" v-if="openListBox">
          <b-list-group v-if="fetchMymaps.length > 0">
            <b-list-group-item  
            :id="`list${index}`" v-for="(place, index) in fetchMymaps" :key="index"
            href="#" class="flex-column align-items-start"
            >
              <!-- 아파트 매물일때 -->
              <div v-if="place.category_group_code == '000'"
              @click="itemElClick(place)"
              @mouseover="itemMouseover(place)"
              @mouseout="itemMouseout">
                <div><img src="@/assets/flat.png" alt=""></div>
                <div class="d-flex w-100 justify-content-between">
                  <h6 class="mb-1">{{ place.apartmentName }}</h6>
                  <small class="tag apt">{{place.category_group_name}}</small>
                </div>

                <small>{{ place.addr }}</small>
              </div>
              
              <!-- 다른 장소일때 -->
              <div v-else
              @click="itemElClick(place)"
              @mouseover="itemMouseover(place)"
              @mouseout="itemMouseout">
                <div><img :src="require(`@/assets/img${place.category_group_code}.png`)" alt=""></div>
                <div class="d-flex w-100 justify-content-between">
                  <h6 class="mb-1">{{ place.place_name }}</h6>
                  <small :class="`tag ${place.category_group_code}`" >{{place.category_group_name}}</small>
                </div>

                <small>{{ place.address_name }}</small>
              </div >
            </b-list-group-item>

          </b-list-group>

          <div id="noContent" v-else>
            <p>좋아하는 장소가 없습니다.</p>
          </div>
        </div>
      </div>
      <!-- ===================================================================== -->
    </div>
  </div>
</template>
<script>
import http from "@/api/http";
export default {
  data() {
    return {
      // 관심지역
      interIdx: 0,
      isGood_H: true,
      isGood_P: false,

      // 지도관련
      openListBox:false,
      kakao: window.kakao,
      map: {},
      infowindow: {},
      ps: {},
      overlay: {},
      markers: [],
      selDetail: false,
      detail: {
        aptCode: 0,
        apartmentName: "",
        addr: "",
        recentPrice: "0",
        maxPrice: "0",
        minPrice: "0",
      },
      subway: [],
      placeOverlay: {},
      
      // contentNode : document.createElement('div'), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
      placeDetail: {},
      markers_p: [], // 장소마커
      currCategory: "", // 현재 선택된 카테고리를 가지고 있을 변수입니다
      cateOnOff: [false,false,false,false],
      categories: [
        {
          code: "BK9",
          cName: "bank",
          name: "은행",
        },
        {
          code: "PM9",
          cName: "pharmacy",
          name: "약국",
        },
        {
          code: "CE7",
          cName: "cafe",
          name: "카페",
        },
        {
          code: "CS2",
          cName: "store",
          name: "편의점",
        },
      ],
    };
  },
  created() {
    this.openListBox = false;
  },
  mounted() {
    // let $vm = this;

    var mapContainer = document.getElementById("map"), // 지도를 표시할 div
      mapOption = {
        center: new this.kakao.maps.LatLng(
          // 지도 중심 좌표 (초기값: 서울시청 좌표)
          37.5642135,
          127.0016985
        ),
        level: 4, // 지도의 확대 레벨
      };

    // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
    this.map = new this.kakao.maps.Map(mapContainer, mapOption);
    this.ps = new this.kakao.maps.services.Places(this.map);
    this.infowindow = new this.kakao.maps.InfoWindow({ zIndex: 99 });

    //프로그램이 아이들 상태(쉬고 있을때) 발생
    // 지도에 idle 이벤트를 등록합니다
    this.kakao.maps.event.addListener(this.map, "idle", this.searchPlaces);
    this.placeOverlay = new this.kakao.maps.CustomOverlay({ zIndex: 99 });


    this.displayPlaces(this.fetchMymaps);
  },
  filters: {
    won(str) {
      let tmp = str.split(",")[0];
      let result = str;

      if (tmp.length >= 2) {
        result = tmp.substr(0, tmp.length - 1) + "억";

        if (tmp[tmp.length - 1] != 0) {
          result = result + " " + tmp[tmp.length - 1] + "천";
        }
      } else if (tmp.length == 1) {
        result = tmp + "천";
      }
      return result;
    },
    limitStr(str) {
      let result = str;

      if (str.length >= 13) {
        result = str.substr(0, 13) + "...";
      }
      return result;
    },
  },
  computed: {
    fetchInterestList() {
      return this.$store.state.interestArea;
    },
    fetchInterest() {
      return this.$store.state.interestArea[this.interIdx];
    },
    fetchMymaps(){
      return this.$store.state.mymaps;
    },
    // fetchHomes() {
    //   return this.$store.state.homes;
    // },
    fetchDeals() {
      return this.$store.state.deals;
    },
    fetchAddr() {
      return this.detail.addr + " ";
    },
  },
  methods: {
    searchInterest(){
      this.openListBox = true;
      this.selDetail = false;

      const params = {
        id:this.$store.state.loginUser.id,
        dongcode:this.fetchInterest.dongCode
      }
      this.$store.dispatch("getMyMapList", params);

      // 지도 센터 관심지역으로 바꾸기 
      let $vm = this;

      var geocoder = new $vm.kakao.maps.services.Geocoder();

      var callback = function (result, status) {
        //동이름 -> 좌표
        if (status === $vm.kakao.maps.services.Status.OK) {
          var moveLatLon = new $vm.kakao.maps.LatLng(result[0].y, result[0].x);
          $vm.map.panTo(moveLatLon);
        }
      };

      //동이름 -> 좌표
      geocoder.addressSearch(this.fetchInterest.area, callback);

      this.removeMarker("H"); //마커제거하고
      this.displayPlaces(this.fetchMymaps); //다시 가져온거 마킹

    },
    displayPlaces(positions) {
      let $vm = this;

      for (var i = 0; i < positions.length; i++) {
        var marker = this.addMarker(positions[i]);

        (function (marker, position) {
          $vm.kakao.maps.event.addListener(marker, "mouseover", function () {
            if (position.category_group_code == '000'){
              $vm.displayInfowindow(marker, position.apartmentName);
            }else{
              $vm.displayInfowindow(marker, position.place_name);
            }
          });

          $vm.kakao.maps.event.addListener(marker, "mouseout", function () {
            $vm.infowindow.close();
          });

          $vm.kakao.maps.event.addListener(marker, "click", function () {
            $vm.itemElClick(position);
          });
        })(marker, positions[i]);
      }
    },
    
    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position) {
      if (position.category_group_code == '000'){
        var imageSrc = require(`@/assets/home.png`);
        var imageSize = new this.kakao.maps.Size(35, 35); 

      }else{
        imageSrc = require(`@/assets/${position.category_group_code}like.png`);
        imageSize = new this.kakao.maps.Size(40, 45); 
      }
      var markerImage = new this.kakao.maps.MarkerImage(imageSrc, imageSize);
      var latlng = new this.kakao.maps.LatLng(position.lati, position.longi);
      
      let marker = new this.kakao.maps.Marker({
            position: latlng, 
            image: markerImage,
            zIndex:6
      });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다
      return marker;
    },
    // displayPlaces(positions) {
    //   let $vm = this;

    //   for (var i = 0; i < positions.length; i++) {
    //     var marker = this.addMarker(positions[i]);

    //     (function (marker, position) {
    //       $vm.kakao.maps.event.addListener(marker, "mouseover", function () {
    //         $vm.displayInfowindow(marker, position.apartmentName);
    //       });

    //       $vm.kakao.maps.event.addListener(marker, "mouseout", function () {
    //         $vm.infowindow.close();
    //       });

    //       $vm.kakao.maps.event.addListener(marker, "click", function () {
    //         $vm.itemElClick(position, marker);
    //       });
    //     })(marker, positions[i]);
    //   }
    // },
    //----------------------표시하고 지우고-----------------------
    removeMarker(tag) {
      if (tag == "H") {
        for (let i = 0; i < this.markers.length; i++) {
          this.markers[i].setMap(null);
        }
        this.markers = [];
      } else if (tag == "P") {
        for (let i = 0; i < this.markers_p.length; i++) {
          this.markers_p[i].setMap(null);
        }
        this.markers_p = [];
      }
    },
    closeOverlay(tag) {
      if (tag == "H"){
        this.overlay.setMap(null);
      }else if(tag == "P"){
        this.placeOverlay.setMap(null);
      }
    },
    displayInfowindow(marker, title) {
      var content = '<div style="padding:5px;z-index:1;">' + title + "</div>";

      this.infowindow.setContent(content);
      this.infowindow.open(this.map, marker);
    },
    closeTab() {
      this.selDetail = false;
      // 지도 중심 이동
      var coord = new this.kakao.maps.LatLng(this.detail.lati, this.detail.longi);
      var move = new this.kakao.maps.LatLng(
        coord.getLat(),
        coord.getLng() 
      );
      this.map.panTo(move);
    },
    openDetailTab(){
      this.selDetail = true;
      // 지도 중심 이동
      var coord = new this.kakao.maps.LatLng(this.detail.lati, this.detail.longi);
      var move = new this.kakao.maps.LatLng(
        coord.getLat(),
        coord.getLng() +0.003
      );
      this.map.panTo(move);
    },
    //---------------------이벤트 연결 함수들---------------------
    
    itemElClick(position) {
      
      this.selDetail = false;

      if(position.category_group_code == '000'){
        this.detail = position;
        var params = {
          id:this.$store.state.loginUser.id,
          lati:position.lati,
          longi:position.longi,
        }
        //좋아요 여부 확인
        http.get(`/mymapdidgood`, { params })
        .then(({data}) => {
          if(data == 1){
            this.isGood_H = true;
          }else if(data == 0){
            this.isGood_H = false;
          }
        }).catch((error) => {
          console.log(error);
        })

      }else{
        this.placeDetail = position;
        params = {
          id:this.$store.state.loginUser.id,
          lati:position.lati,
          longi:position.longi,
        }
        //좋아요 여부 확인
        http.get(`/mymapdidgood`, { params })
        .then(({data}) => {
          if(data == 1){
            this.isGood_P = true;
          }else if(data == 0){
            this.isGood_P = false;
          }
        }).catch((error) => {
          console.log(error);
        })
      }


      // 로드뷰
      var coord = new this.kakao.maps.LatLng(position.lati, position.longi);
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div

      var roadview = new this.kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new this.kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(coord, 50, function (panoId) {
        roadview.setPanoId(panoId, coord); //panoId와 중심좌표를 통해 로드뷰 실행
      });


      //오버레이 표시
      if(position.category_group_code == '000'){
        this.$store.dispatch("getDeals", position.aptCode);

        this.overlay = new this.kakao.maps.CustomOverlay({
          content: this.$refs.over,
          map: this.map,
          position: coord,
        });
  
      this.overlay.setMap(this.map);

      }else{
        this.placeOverlay = new this.kakao.maps.CustomOverlay({
          content: this.$refs.placeinfo,
          map: this.map,
          position: coord,
        });
  
        this.placeOverlay.setMap(this.map);
      }
      
      // 지도 중심을 이동 시킵니다
      var move = new this.kakao.maps.LatLng(
        coord.getLat(),
        coord.getLng() 
      );
      this.map.setCenter(move);
      this.map.setLevel(3);
      this.findSubway(position);

    },
    // itemElClick(position, marker) {
    //   let $vm = this;
    //   let obj = position;
    //   this.detail = obj;

    //   var geocoder = new this.kakao.maps.services.Geocoder();

    //   var coord = new this.kakao.maps.LatLng(position.lat, position.lng);
    //   var callback = function (result, status) {
    //     if (status === $vm.kakao.maps.services.Status.OK) {
    //       $vm.detail.addr = result[0].address.address_name;
    //     }
    //   };

    //   geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);

    //   // 로드뷰
    //   var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div

    //   var roadview = new this.kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
    //   var roadviewClient = new this.kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

    //   // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
    //   roadviewClient.getNearestPanoId(coord, 50, function (panoId) {
    //     roadview.setPanoId(panoId, coord); //panoId와 중심좌표를 통해 로드뷰 실행
    //   });

    //   this.overlay = new this.kakao.maps.CustomOverlay({
    //     content: this.$refs.over,
    //     map: this.map,
    //     position: marker.getPosition(),
    //   });

    //   this.overlay.setMap(this.map);

    //   // 지도 중심을 이동 시킵니다
    //   var move = new this.kakao.maps.LatLng(
    //     coord.getLat(),
    //     coord.getLng() 
    //   );
    //   this.map.setCenter(move);
    //   this.map.setLevel(3);
    //   this.findSubway(position);
    //   this.$store.dispatch("getDeals", position.aptCode);
    // },
    itemMouseover(position) {
      var marker = this.addMarker(position);
      if(position.category_group_code == "000"){
        this.displayInfowindow(marker, position.apartmentName);
      }else{
        this.displayInfowindow(marker, position.place_name);
      }
    },
    itemMouseout() {
      this.infowindow.close();
    },
    //------------------지하철역--------------------------
    findSubway(position) {
      // 장소 검색 객체를 생성합니다
      let $vm = this;
      this.subway = [];

      this.ps.categorySearch("SW8", placesSearchCB, { useMapBounds: true });

      // 키워드 검색 완료 시 호출되는 콜백함수 입니다
      function placesSearchCB(data, status) {
        if (status === $vm.kakao.maps.services.Status.OK) {
          for (var i = 0; i < data.length; i++) {
            if (i == 2) break; //지하철 2개만 제한(오버레이창 좁음)
            let sub = {
              lat: data[i].y,
              lng: data[i].x,
            };
            let obj = {
              name: data[i].place_name,
              dis: $vm.calDistance(position, sub),
            };
            $vm.subway.push(obj);
          }
        }
      }
    },
    calDistance(position, sub) {
      let lng1 = position.longi;
      let lng2 = sub.lng;
      let lat1 = position.lati;
      let lat2 = sub.lat;

      function deg2rad(deg) {
        return deg * (Math.PI / 180);
      }

      var R = 6371; // Radius of the earth in km
      var dLat = deg2rad(lat2 - lat1); // deg2rad below
      var dLon = deg2rad(lng2 - lng1);
      var a =
        Math.sin(dLat / 2) * Math.sin(dLat / 2) +
        Math.cos(deg2rad(lat1)) *
          Math.cos(deg2rad(lat2)) *
          Math.sin(dLon / 2) *
          Math.sin(dLon / 2);
      var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
      var d = R * c; // Distance in km
      return Math.floor(d * 1000);
    },
    clkHeart(cmd) {
      let userId = this.$store.state.loginUser.id;
      if(userId == ""){    
        this.$toast.info("로그인 후 이용 가능합니다. :->");
        return;
      }
      if(cmd == 'I'){
        const obj = {
          id:userId,
          dongcode:this.fetchInterest.dongCode,
          category_group_code:this.placeDetail.category_group_code,
          category_group_name:this.placeDetail.category_group_name,
          lati: this.placeDetail.lati,
          longi: this.placeDetail.longi,

          addr: "",
          apartmentName:"",
          aptCode: "",
          maxPrice:"",
          minPrice: "",
          recentPrice: "",
          
          address_name:this.placeDetail.address_name,
          phone:this.placeDetail.phone,
          place_name:this.placeDetail.place_name,
          place_url:this.placeDetail.place_url,
          road_address_name:this.placeDetail.road_address_name
        }

        http.post(`/mymap`, obj)
        .then((response) => {
          if (response.status == 200) {
            this.$toast.success("등록되었습니다.");
            this.isGood_P = true;
            //기존 마커 삭제
            for(let marker of this.markers){

              if((marker.getPosition().getLat()+"").substr(0,8) == (this.placeDetail.lati+"").substr(0,8) &&
              (marker.getPosition().getLng()+"").substr(0,8) == (this.placeDetail.longi+"").substr(0,8)){
                marker.setMap(null);
              }
            }

            //리스트에 바로 반영
            const params = {
              id:this.$store.state.loginUser.id,
              dongcode:this.fetchInterest.dongCode
            }
            this.$store.dispatch("getMyMapList", params);

            //마커 하트 붙은 걸로 교체
            var imageSrc = require(`@/assets/${this.placeDetail.category_group_code}like.png`);
            var imageSize = new this.kakao.maps.Size(40, 45); 
            var markerImage = new this.kakao.maps.MarkerImage(imageSrc, imageSize);
            var latlng = new this.kakao.maps.LatLng(this.placeDetail.lati, this.placeDetail.longi);

            let marker = new this.kakao.maps.Marker({
                  position: latlng, 
                  image: markerImage,
                  zIndex:6
            });

            marker.setMap(this.map); // 지도 위에 마커를 표출합니다
            this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

          }
        }).catch((error) => {
          console.log(error);
        })
      }
      else if(cmd == 'D'){
        console.log("placeDetail",this.placeDetail)
        const params = {
          lati:this.placeDetail.lati,
          longi:this.placeDetail.longi,
        }
        http.delete(`/mymap`,{params})
        .then((response) => {
          if (response.status == 200) {
            this.$toast.success("삭제되었습니다.");
            this.isGood_P = false;

            //기존 마커 삭제
            for(let marker of this.markers){

              if((marker.getPosition().getLat()+"").substr(0,8) == (this.placeDetail.lati+"").substr(0,8) &&
              (marker.getPosition().getLng()+"").substr(0,8) == (this.placeDetail.longi+"").substr(0,8)){
                marker.setMap(null);
              }
            }
            //리스트에 바로 반영
            const params = {
              id:this.$store.state.loginUser.id,
              dongcode:this.fetchInterest.dongCode
            }
            this.$store.dispatch("getMyMapList", params);

            //마커 하트 붙은 걸로 교체
            var imageSrc = require(`@/assets/${this.placeDetail.category_group_code}.png`);
            var imageSize = new this.kakao.maps.Size(40, 45); 
            var markerImage = new this.kakao.maps.MarkerImage(imageSrc, imageSize);
            var latlng = new this.kakao.maps.LatLng(this.placeDetail.lati, this.placeDetail.longi);

            let marker = new this.kakao.maps.Marker({
                  position: latlng, 
                  image: markerImage,
                  zIndex:6
            });

            marker.setMap(this.map); // 지도 위에 마커를 표출합니다
            this.markers.push(marker); // 배열에 생성된 마커를 추가합니다


          }
        }).catch((error) => {
          console.log(error);
        })
      }
    },
    //-----------------------장소검색 오버레이--------------------
    onClickCategory(event, index) {
      var el = event.currentTarget;
      var id = el.id;
      this.placeOverlay.setMap(null);

      if (this.cateOnOff[index]) {
        this.currCategory = "";
        this.changeCategoryClass();
        this.removeMarker("P");
      } else {
        this.currCategory = id;
        this.changeCategoryClass(index);
        this.searchPlaces();
      }

    },
    // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
    changeCategoryClass(index) {
      for (let i = 0; i < this.cateOnOff.length; i++) {
        if(i == index){
          this.cateOnOff[index] = true;
        }else{
          this.cateOnOff[i] = false;
        }
      }

    },
    searchPlaces() {
      if (!this.currCategory) {
        return;
      }

      this.placeOverlay.setMap(null);

      this.removeMarker("P");

      this.ps.categorySearch(this.currCategory, this.placesSearchCB, {
        useMapBounds: true,
      });
    },
    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status) {
      if (status === this.kakao.maps.services.Status.OK) {
        this.displayPlaces_P(data);
      }
    },
    // 지도에 마커를 표출하는 함수입니다
    displayPlaces_P(places) {
      let $vm = this;
      // var order = document.getElementById(this.currCategory).getAttribute('data-order');

      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var marker = this.addMarker_P(
          new this.kakao.maps.LatLng(places[i].y, places[i].x),
          this.currCategory
        );

        (function (marker, place) {
          $vm.kakao.maps.event.addListener(marker, "click", function () {
            const params = {
              id:$vm.$store.state.loginUser.id,
              lati:place.y,
              longi:place.x,
            }

            http.get(`/mymapdidgood`, { params })
            .then(({data}) => {
              if(data == 1){
                $vm.isGood_P = true;
              }else if(data == 0){
                $vm.isGood_P = false;
              }
            }).catch((error) => {
              console.log(error);
            })

            const obj = {
              category_group_code:place.category_group_code,
              category_group_name:place.category_group_name,

              lati: place.y,
              longi: place.x,
              address_name:place.address_name,
              phone:place.phone,
              place_name:place.place_name,
              place_url:place.place_url,
              road_address_name:place.road_address_name
            }


            $vm.placeDetail = obj;
            $vm.placeOverlay.setContent($vm.$refs.placeinfo);
            $vm.placeOverlay.setPosition(
              new $vm.kakao.maps.LatLng(place.y, place.x)
            );
            
            // 지도 중심 이동
            var coord = new $vm.kakao.maps.LatLng(place.y, place.x);
            var move = new $vm.kakao.maps.LatLng(
              coord.getLat(),
              coord.getLng() 
            );
            $vm.map.setCenter(move);

      
            $vm.placeOverlay.setMap($vm.map);
          });
        })(marker, places[i]);
      }
    },
    addMarker_P(position, cate) {
      var imageSrc = require(`@/assets/${cate}.png`), // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new this.kakao.maps.Size(40, 45), // 마커 이미지의 크기
        // imgOptions =  {
        //     spriteSize : new this.kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
        //     spriteOrigin : new this.kakao.maps.Point(46, (order*36)), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        //     offset: new this.kakao.maps.Point(11, 28) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        // },
        markerImage = new this.kakao.maps.MarkerImage(imageSrc, imageSize),
        marker = new this.kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
          zIndex:5
        });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers_p.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },
  },
};
</script>
<style scoped>
#interestSel {
  width: 15.3rem;
}

.gray {
  color: gray;
  font-size: 14px;
}

#detailBox {
  overflow: auto;
  text-align: left;
  position: absolute;
  top: 0px;
  right: 255px;
  z-index: 100;
  background-color: rgb(255 255 255 / 82%);
  height: 100%;
  width: 19rem;
}
#detailContent{
  padding: 10px 21px;
}

#detailHeader{
  display: flex;
  justify-content: space-between;
  align-items: center;
}
#detailHeader h6{
  margin: 0;
}
#detailHeader img{
  width:25px;
}

.subway p {
  margin: 0;
}
#closeTab {
  cursor: pointer;
  color: #939393;
  padding: 10px;
}

#dealBox {
  margin-top: 5px;
  font-size: 13px;
  width: 100%;
  overflow: auto;
  height: 345px;
}
#dealBox table {
  border-radius: 12px;
  padding: 3px;
  width: 100%;
  background-color: rgb(239 239 239 / 59%);
  color: black;
}
th {
  text-align: center;
  border-bottom: 1px solid gray;
}
th,
td {
  padding: 5px;
}

#listBox {
  font-family: 'Noto Sans KR', sans-serif;
  background-color: #ffffffc9;
  top: 0;
  position: absolute;
  right: 0px;
  z-index: 100;

  height: 100%;
  width: 16rem;
  overflow: auto;
  text-align: left;
}
#listBox img{
  width:20px;
}
#listBox h6{
  font-weight: 500;
  font-size: 0.89rem;
}
#listBox small{
  font-size: 0.8em;
  color: #9d9d9d;
  font-weight: 300;
}
.list-group-item{
  border:var(--bs-list-group-border-width) solid rgb(157 157 157 / 8%);
}

#listBox .tag{
  color: white;
  border-radius: 4px;
  padding: 3px 4px;
  font-size: 8px;
}
.apt{
  background-color: #2076bb;
}
.BK9{
  background-color: #6868ac;
}
.CE7{
  background-color: #009192;
}
.CS2{
  background-color: #00916e;
}
.PM9{
  background-color: #b76eb0;
}

#noContent {
  display: flex;
  justify-content: center;
  margin: 113% 0;
  align-content: center;
}
#noContent p {
  margin: 0;
}

#detailBox::-webkit-scrollbar,
#dealBox::-webkit-scrollbar,
#listBox::-webkit-scrollbar {
  width: 7px;
}
#detailBox::-webkit-scrollbar-thumb,
#dealBox::-webkit-scrollbar-thumb,
#listBox::-webkit-scrollbar-thumb {
  background-color: #96969648;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}

#contentBox {
  width: 100%;
  margin: 0 auto;
  display: flex;
  flex-direction: row;
}
.form-box {
  position: absolute;
  margin: 13px 35%;
  z-index: 99;
  display: flex;
  justify-content: center;
  flex-direction: row;
  padding: 2px 19px;
  border-radius: 39px;
  background-color: #818181d1;
}
.form-select,
.btn-primary {
  margin: 13px 6px;
  width: 9.3rem;
  border-radius: 20px;
  padding-left: 20px;
  color: black;
}
table {
  background-color: rgb(52 52 52 / 59%);
  color: white;
}
.usallyBnt {
  padding: 11px;
  border-radius: 24px;
  box-shadow: 0 0.1875rem 0.1875rem 0 rgb(0 0 0 / 10%) !important;
  font-family: "Varela Round", -apple-system, BlinkMacSystemFont, "Segoe UI",
    Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
  font-size: 80%;
  text-transform: uppercase;
  letter-spacing: 0.15rem;
  border: 0;
  color: #fff;
  background-color: #3a3f5c;
  border-color: #3a3f5c;
}
.submitBtn {
  font-family: "Varela Round", -apple-system, BlinkMacSystemFont, "Segoe UI",
    Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
  font-size: 80%;
  text-transform: uppercase;
  letter-spacing: 0.15rem;
  border: 0;
  color: #fff;
  background-color: #64a19d;
  border-color: #64a19d;
}
/* =============================overlay=============================== */
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  display: flex;
  justify-content: space-between;
  position: relative;
  margin: 12px 0 0 17px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
.heartBox {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: flex-end;
}
.heartBox small {
  margin: 0 13px 5px 0;
  cursor: pointer;
}
.heartBox img{
  width: 20px;
  margin-right: 15px;
  cursor: pointer;
}
/* ==============================================장소 오버레이=============================== */
#category {
  position: absolute;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  /* border: 1px solid #909090; */
  box-shadow: 0 1px 1px rgb(0 0 0 / 40%);
  background: #ffffffd6;
  overflow: hidden;
  z-index: 99;
  font-size: 11px;
  padding:0;
}
#category li {float:left;list-style: none;width:50px;border-right:1px solid #acacac;padding:6px 0;text-align: center; cursor: pointer;}
#category li.on {background: #eee;}
#category li:hover {background: #ffe6e6;border-left:1px solid #acacac;margin-left: -1px;}
#category li:last-child{margin-right:0;border-right:0;}
#category li span {display: block;margin:0 auto 3px;width:27px;height: 28px;}
#category li .category_bg {background:url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png) no-repeat;}
#category li .bank {background-position: -10px 0;}
#category li .mart {background-position: -10px -36px;}
#category li .pharmacy {background-position: -10px -72px;}
#category li .oil {background-position: -10px -108px;}
#category li .cafe {background-position: -10px -144px;}
#category li .store {background-position: -10px -180px;}
#category li.on .category_bg {background-position-x:-46px;}
.placeinfo_wrap {position: absolute;

    bottom: 44px;
    left: -150px;
    width: 285px;
    text-align: left;
    }
#placeinfoContent{
  justify-content: space-between;
  display: flex;
}
#placeinfoContent .heartBox img{
  margin-top: 6px;
}
.placeinfo {position:relative;width:100%;border-radius:6px;border: 1px solid #ccc;border-bottom:2px solid #ddd;background: #fff;}
.placeinfo:nth-of-type(n) {border:0; box-shadow:0px 1px 2px #888;}

.placeinfo a, .placeinfo a:hover, .placeinfo a:active{
  color: #0c0c0c;
  text-decoration: none;
  font-size: 11px;
  }
.placeinfo a, .placeinfo span {display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
.placeinfo span {margin:5px 5px 0 5px;cursor: default;font-size:13px;}
.placeinfo .title {
  font-weight: bold; 
  font-size:14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding:10px; 
  color: #fff;
  background: #7781c9;
  display: flex;
  justify-content: space-between;
  }
.placeinfo .title p, .placeinfo .title span{
  margin: 0 7px 0 0;
}
.placeinfo .tel {color:#0f7833;}
.placeinfo .jibun {color:#999;font-size:11px;margin-top:0;}
</style>
