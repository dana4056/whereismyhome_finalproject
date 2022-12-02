<template>
  <div>

    <div class="form-box">
      <select v-model="sido" @change="getGugun" class="form-select" id="sido">
        <option value="">시도선택</option>
        <option :key="sido.code" :value="sido.code" v-for="sido in fetchSido">
          {{ sido.name }}
        </option>
      </select>

      <select v-model="gugun" @change="getDong" class="form-select" id="gugun">
        <option value="">구군선택</option>
        <option
          :key="gugun.code"
          :value="gugun.code"
          v-for="gugun in fetchGugun"
        >
          {{ gugun.name.split(" ")[1] }}
        </option>
      </select>

      <select v-model="dong" class="form-select" id="dong">
        <option value="">동선택</option>
        <option :key="dong.code" :value="dong.code" v-for="dong in fetchDong">
          {{ dong.name.split(" ")[2] }}
        </option>
      </select>

      <a id="list-btn" @click="searchHome" class="usallyBnt btn-primary">
        <i class="fa-sharp fa-solid fa-magnifying-glass"></i>
      </a>
    </div>

    <div id="contentBox">
      <div id="map" style="width: 100%; height: 100vh">
        <!--========================== youtube 토글버튼 ==========================-->
        <div class="toggleBox">
          <input type="checkbox" id="toggle" />
          <label class="togglebtn" for="toggle">
            <img style="width: 27px" src="@/assets/live-tv.png" alt="" />
          </label>
          <nav class="nav">
            <ul>
              <div
                class="video"
                v-for="(video, index) in videos.items"
                :key="index"
              >
                <iframe
                  allowfullscreen
                  :src="`https://www.youtube.com/embed/${video.id.videoId}`"
                  frameborder="0"
                ></iframe>
                <small>{{
                  video.snippet.title | limitStr(video.snippet.title)
                }}</small>
              </div>
            </ul>
          </nav>
        </div>
        <!--====================================================================-->
        <div id="detailBox" v-show="selDetail">
          <span id="closeTab" @click="closeTab">
            <i class="fa-solid fa-xmark"></i>
          </span>

          <div id="roadview" ref="roadview" style="width: 100%; height: 200px"></div>
          <div id="detailContent">

            <div id="detailHeader">
              <div id="headerContent1">
                <h6>
                  <b>{{ fetchDetail.apartmentName }}</b>
                </h6>
                <p class="gray">
                  <i class="fa-sharp fa-solid fa-house-chimney"></i> {{ fetchDetail.addr }}
                </p>
              </div>

              <div id="headerContent2">
                <div  class="heartBox">
                  <img @click="clkHeart('D')"  v-if="isGood" src="@/assets/heart-f.png" alt=""/>
                  <img @click="clkHeart('I')" v-else src="@/assets/heart-e.png" alt=""/>
                </div>
                <div>
                  <a id="kakaotalk-sharing-btn" href="#">
                    <img class="new-result-btn"
                      id="kakaotalk-sharing-btn"
                      @click=kakaoShare()
                      src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"/>
                  </a>
                </div>
              </div>
            </div>



            <small><b>근처 지하철</b></small>
            <div class="subway gray" v-if="subway.length > 0">
              <p v-for="(sub, index) in subway" :key="index">
                <i class="fa-solid fa-train-subway"></i>
                <small>
                  {{ sub.name }} <i class="fa-solid fa-arrow-right"></i> 약
                  {{ sub.dis }}m</small
                >
              </p>
            </div>
            <div v-else>
              <small class="gray">정보 없음</small>
            </div>

            <div style="margin-top: 20px">
              <small><b>매매 정보</b></small>
            </div>
            <small>최근: {{ fetchDetail.recentPrice | won(fetchDetail.recentPrice) }}<br/></small>
            <small>최고·최저: {{ fetchDetail.maxPrice | won(fetchDetail.maxPrice) }} /{{ fetchDetail.minPrice | won(fetchDetail.minPrice) }}</small>
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
        <div id="listBox" v-if="openListBox">
          <b-list-group v-if="fetchHomes.length > 0">
            <b-list-group-item
              v-for="(home, index) in fetchHomes"
              :key="home.aptCode"
              href="#"
              class="flex-column align-items-start"
              :id="`list${index}`"
              @click="itemElClick(home)"
              @mouseover="itemMouseover(home)"
              @mouseout="itemMouseout"
            >
              <div class="d-flex w-100 justify-content-between">
                <b><p class="mb-1">{{ home.apartmentName }}</p></b>
              </div>

              <small
                >최근거래금액: {{ home.recentPrice | won(home.recentPrice)
                }}<br
              /></small>
              <small class="text-muted"
                >최고·최저: {{ home.maxPrice | won(home.maxPrice) }} /
                {{ home.minPrice | won(home.minPrice) }}
              </small>
            </b-list-group-item>
          </b-list-group>


          <div id="noContent" v-else>
            <p>검색 결과가 없습니다.</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import http from "@/api/http";
export default {
  data() {
    return {
      nyear: new Date().getFullYear(),
      nmonth: new Date().getMonth() + 1,
      sido: "",
      gugun: "",
      dong: "",
      year: 0,
      month: 0,

      // 지도관련
      markers: [],
      map: {},
      infowindow: {},
      kakao: window.kakao,
      selDetail: false,
      detail: {
        addr:"",
        apartmentName:"",
        aptCode:0,
        lat:"",
        lng:"",
        maxPrice:"",
        minPrice:"",
        recentPrice:"",
      },
      isGood:false,
      openListBox:false,
      subway: [],
      // 유튜브
      videos: [],

      //카카오로 공유할 정보들
      kakaoinfo1: "",
      kakaoinfo2: "",
      
    };
  },
  created() {
    this.openListBox = false;
    this.sido = "";
    this.gugun = "";
    this.dong = "";
    this.$store.commit("SET_SIDO", []);
    this.$store.commit("SET_GUGUN", []);
    this.$store.commit("SET_DONG", []);
    this.$store.commit("SET_HOME_LIST", []);
    this.$store.commit("SET_DEAL_LIST", []);

    let payload = {
      regcode: "*00000000",
      selid: "sido",
    };
    this.$store.dispatch("getArea", payload);

    // this.youtube(encodeURI("서울 종로"));
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


    this.infowindow = new this.kakao.maps.InfoWindow({ zIndex: 1 });

    this.displayPlaces(this.fetchHomes);
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
    calMonth() {
      return this.year == new Date().getFullYear() ? this.nmonth : 13;
    },
    fetchSido() {
      return this.$store.state.sido;
    },
    fetchGugun() {
      return this.$store.state.gugun;
    },
    fetchDong() {
      return this.$store.state.dong;
    },
    fetchHomes() {
      return this.$store.state.homes;
    },
    fetchDetail(){
      return this.detail;
    },
    fetchDeals() {
      return this.$store.state.deals;
    },
  },
  methods: {
    getGugun() {
      let payload = {
        regcode: this.sido.substr(0, 2) + "*00000",
        selid: "gugun",
      };
      this.$store.dispatch("getArea", payload);
    },
    getDong() {
      let payload = {
        regcode: this.gugun.substr(0, 5) + "*",
        selid: "dong",
      };
      this.$store.dispatch("getArea", payload);
    },

    searchHome() {
      this.openListBox = true;
      this.selDetail = false;
      let $vm = this;
      const params = {
        code: this.dong,
      };

      var geocoder = new $vm.kakao.maps.services.Geocoder();

      var callback = function (result, status) {
        //동이름 -> 좌표
        if (status === $vm.kakao.maps.services.Status.OK) {
          var moveLatLon = new $vm.kakao.maps.LatLng(result[0].y, result[0].x);
          $vm.map.setCenter(moveLatLon);
        }
      };

      for (let i = 0; i < this.fetchDong.length; i++) {
        //동이름 -> 좌표
        if (this.fetchDong[i].code == this.dong) {
          geocoder.addressSearch(this.fetchDong[i].name, callback);
          // this.youtube(encodeURI(this.fetchDong[i].name.split(" ")[1]));
        }
      }

      http
        .get(`/homes`, { params })

        .then(({ data }) => {
          this.$store.commit("SET_HOME_LIST", data);
          this.removeMarker(); //마커제거하고
          this.displayPlaces(this.fetchHomes); //다시 가져온거 마킹
        });
    },
    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position) {
      var imageSrc = require(`@/assets/home.png`);

      var imageSize = new this.kakao.maps.Size(35, 35); // 마커 이미지 크기
      var markerImage = new this.kakao.maps.MarkerImage(imageSrc, imageSize); // 마커 이미지 생성
      var latlng = new this.kakao.maps.LatLng(position.lat, position.lng);

      let marker = new this.kakao.maps.Marker({
        position: latlng, // 마커를 표시할 위치
        image: markerImage, // 마커 이미지
      });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다
      return marker;
    },
    displayPlaces(positions) {
      let $vm = this;

      for (var i = 0; i < positions.length; i++) {
        var marker = this.addMarker(positions[i]);

        (function (marker, position) {
          $vm.kakao.maps.event.addListener(marker, "mouseover", function () {
            $vm.displayInfowindow(marker, position.apartmentName);
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
    //----------------------표시하고 지우고-----------------------
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },
    displayInfowindow(marker, title) {
      var content = '<div style="padding:5px;z-index:1;">' + title + "</div>";

      this.infowindow.setContent(content);
      this.infowindow.open(this.map, marker);
    },
    closeTab() {
      this.selDetail = false;
    },
    //---------------------이벤트 연결 함수들---------------------
    itemElClick(position) {
      let $vm = this;
      this.selDetail = true;
      this.detail = position;
      this.kakaoinfo1 = position.apartmentName;
      this.kakaoinfo2 = position.addr;
      //좋아요 여부 확인
      // 좋아요 한 매물인지
      const params = {
        id:this.$store.state.loginUser.id,
        lati:position.lat,
        longi:position.lng,
      }

      http.get(`/mymapdidgood`, { params })
      .then(({data}) => {
        if(data == 1){
          this.isGood = true;
        }else if(data == 0){
          this.isGood = false;
        }
      }).catch((error) => {
        console.log(error);
      })

      var geocoder = new this.kakao.maps.services.Geocoder();

      var coord = new this.kakao.maps.LatLng(position.lat, position.lng);
      var callback = function (result, status) {
        if (status === $vm.kakao.maps.services.Status.OK) {
          $vm.detail.addr = result[0].address.address_name;
        }
      };

      geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);

      // 로드뷰
      var roadviewContainer = document.getElementById("roadview"); //로드뷰를 표시할 div

      var roadview = new this.kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
      var roadviewClient = new this.kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

      // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
      roadviewClient.getNearestPanoId(coord, 50, function (panoId) {
        roadview.setPanoId(panoId, coord); //panoId와 중심좌표를 통해 로드뷰 실행
      });

      // 지도 중심을 이동 시킵니다
      var move = new this.kakao.maps.LatLng(
        coord.getLat(),
        coord.getLng() + 0.003
      );
      this.map.setCenter(move);
      this.map.setLevel(3);
      this.findSubway(position);
      this.$store.dispatch("getDeals", position.aptCode);
    },
    itemMouseover(position) {
      var marker = this.addMarker(position);
      this.displayInfowindow(marker, position.apartmentName);
    },
    itemMouseout() {
      this.infowindow.close();
    },
    //------------------지하철역--------------------------
    findSubway(position) {
      // 장소 검색 객체를 생성합니다
      let $vm = this;
      this.subway = [];
      var ps = new this.kakao.maps.services.Places(this.map);

      // 카테고리로 은행을 검색합니다
      ps.categorySearch("SW8", placesSearchCB, { useMapBounds: true });

      // 키워드 검색 완료 시 호출되는 콜백함수 입니다
      function placesSearchCB(data, status) {
        if (status === $vm.kakao.maps.services.Status.OK) {
          for (var i = 0; i < data.length; i++) {
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
      let lng1 = position.lng;
      let lng2 = sub.lng;
      let lat1 = position.lat;
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
    youtube(keyword) {
      //유튜브
      const url =
        `https://www.googleapis.com/youtube/v3/search?part=snippet&q=` +
        keyword +
        `&key=AIzaSyDAj5c37qKNPkaGj6Dua0OF7_AVkQ6dLI4&maxResults=3&type=video`;
      http.get(url).then(({ data }) => {
        this.videos = data;
        console.log("youtube", this.videos);
      });
    },
    clkHeart(cmd){
      let userId = this.$store.state.loginUser.id;
      if(userId == ""){
        this.$toast.info("로그인 후 이용 가능합니다. :->");
        return;
      }
      if(cmd == 'I'){
        const obj = {
          id:userId,
          dongcode:this.dong,
          category_group_code:'000',
          category_group_name:"아파트",
          lati: this.detail.lat,
          longi: this.detail.lng,

          addr: this.detail.addr,
          apartmentName: this.detail.apartmentName,
          aptCode: this.detail.aptCode,
          maxPrice: this.detail.maxPrice,
          minPrice: this.detail.minPrice,
          recentPrice: this.detail.recentPrice,
          
          address_name:"",
          phone:"",
          place_name:"",
          place_url:"",
          road_address_name:""
        }

        http.post(`/mymap`, obj)
        .then((response) => {
          if (response.status == 200) {
            this.$toast.success("등록되었습니다.");
            this.isGood = true;

          }
        }).catch((error) => {
          console.log(error);
        })


      }
      else if(cmd == 'D'){
        const params = {
          lati:this.detail.lat,
          longi:this.detail.lng,
        }

        http.delete(`/mymap`,{params})
        .then((response) => {
          console.log(response);
          if (response.status == 200) {
            this.$toast.success("삭제되었습니다.");
            this.isGood = false;
          }
        }).catch((error) => {
          console.log(error);
        })

      }
    },

    
    kakaoShare() {

      window.Kakao.Share.createDefaultButton({
        container: '#kakaotalk-sharing-btn',
        objectType: 'location',
        address:this.detail.addr,
        addressTitle:this.detail.apartmentName,
        content: {
          title: this.kakaoinfo2,
          description: this.kakaoinfo1,
          imageUrl:'https://s3.us-west-2.amazonaws.com/secure.notion-static.com/b536479e-85bf-4a1d-b7c0-e24ebaf3a784/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20221202%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20221202T134329Z&X-Amz-Expires=86400&X-Amz-Signature=edbc35568f2a5936485943023387a64b138ec2fa2c0d350907815312f6a80750&X-Amz-SignedHeaders=host&response-content-disposition=filename%3D%22Untitled.png%22&x-id=GetObject',
          link: {
            mobileWebUrl: 'https://developers.kakao.com',
            webUrl: 'https://developers.kakao.com',
          },
        },
        buttons: [
          {
            title: '카카오 맵으로',
            link: {
              mobileWebUrl: 'https://map.kakao.com/',
              webUrl: 'https://map.kakao.com/',
            },
          },
        ],
      });
    },

  },
};
</script>
<style scoped>
/* ===================== youtube 토글 버튼 ======================== */

.toggleBox {
  position: absolute;
  width: 16rem;
  top: 25px;
  left: 25px;
  z-index: 999;
  display: inline-block;
}

#toggle {
  -webkit-appearance: none;
}

.togglebtn {
  position: absolute;
  z-index: 999;
  width: 43px;
  height: 43px;
  background: #c10000;
  border-radius: 100%;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
  top: -12px;
}

.nav {
  opacity: 0;
  transition: all 0.4s ease-in-out;
  background: #fff;
  width: 100%;
  border-radius: 5px;
  transform: translateY(0%);
  box-shadow: 2px 3px 10px 0 rgba(81, 81, 81, 0.1);
  border: 1px solid #e4e4e4;
  padding: 10px;
}
.nav ul {
  margin: 0;
  padding: 0;
}

#toggle:checked ~ .nav {
  opacity: 1;
}

#toggle:checked ~ .button:before {
  transform: rotate(225deg);
}

#toggle:checked ~ .button:after {
  transform: rotate(135deg);
}

@media (max-width: 640px) {
  .toggleBox {
    width: 100%;
  }
}
/* ======================= youtube ====================== */
.video {
  padding: 5px 0px;
}
.video iframe {
  width: 100%;
}

/* ===================================================== */
.gray {
  color: gray;
  margin: 0;
  font-size: 14px;
  margin-top: 5px;
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
  margin-bottom: 15px;
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

#headerContent1{
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
#headerContent2{
  display: flex;
  flex-direction: column;
  align-items: center;  
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
  border: 1px #dfdfdf solid;
  border-radius: 8px;
  height:100%;
  width: 16rem;
  overflow: auto;
  text-align: left;
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
  margin: 13px 31%;
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
</style>
