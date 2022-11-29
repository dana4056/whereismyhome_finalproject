<template>
  <div>
    <h1 class="mx-auto my-1 text-uppercase mb-5">WhereIsMyHome</h1>
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

      <!-- <div class="form-group col-md-2">
              <select v-model="year" class="form-select" id="year">
                <option value="">매매년도선택</option>
                <option v-for="i in 20" :key="i" :value="nyear - (i - 1)">
                  {{ nyear - (i - 1) }}
                </option>
              </select>
            </div>

            <div class="form-group col-md-2">
              <select v-model="month" class="form-select" id="month">
                <option value="">매매월선택</option>
                <option v-for="i in calMonth - 1" :key="i" :value="i < 10 ? '0' + i : i">
                  {{ i }}월
                </option>
              </select>
            </div> -->
      <a id="list-btn" @click="searchHome" class="btn btn-primary">
        <i class="fa-sharp fa-solid fa-magnifying-glass"></i>
      </a>
    </div>

    <div id="contentBox">
      <!-- <div id="map" style="width:80%;height:70vh;"></div> -->

      <div class="map_wrap">
        <div
          id="map"
          style="
            width: 100%;
            height: 70vh;
            position: relative;
            overflow: hidden;
          "
        ></div>

        <div id="menu_wrap" class="bg_white">
          <div class="option">
            <div>
              <form
                @submit.prevent="
                  searchPlaces();
                  return false;
                "
              >
                키워드 :
                <input type="text" value="" id="keyword" size="15" />
                <button type="submit">검색하기</button>
              </form>
            </div>
          </div>
          <hr />
          <ul id="placesList"></ul>
          <div id="pagination"></div>
        </div>
      </div>

      <div id="listBox" v-if="fetchHomes.length > 0">
        <b-list-group>
          <b-list-group-item
            v-for="home in fetchHomes"
            :key="home.aptCode"
            href="#"
            class="flex-column align-items-start"
          >
            <div class="d-flex w-100 justify-content-between">
              <b
                ><p class="mb-1">{{ home.apartmentName }}</p></b
              >
              <!-- <small class="text-muted">3 days ago</small> -->
            </div>

            <p class="mb-1">
              <i class="fa-sharp fa-solid fa-house-chimney"></i>
              {{ home.addr }}
            </p>

            <small
              >최근거래금액: {{ home.recentPrice | won(home.recentPrice) }}<br
            /></small>
            <small class="text-muted"
              >최고·최저: {{ home.maxPrice | won(home.maxPrice) }} /
              {{ home.minPrice | won(home.minPrice) }}
            </small>
          </b-list-group-item>
        </b-list-group>
      </div>
    </div>
  </div>
</template>
<script>
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

      markers: [],
      // 지도를 생성합니다
      map: {},

      // 장소 검색 객체를 생성합니다
      ps: {},

      // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
      infowindow: {},
      kakao: window.kakao,
    };
  },
  created() {
    let payload = {
      regcode: "*00000000",
      selid: "sido",
    };
    this.$store.dispatch("getArea", payload);
  },
  mounted() {
    ////////////////////////////////////////////////////
    // let kakao = window.kakao;
    let $vm = this;

    var mapContainer = document.getElementById("map"), // 지도를 표시할 div
      mapOption = {
        center: new this.kakao.maps.LatLng(
          // this.fetchHomes[0].lat,
          // this.fetchHomes[0].lng
          37.5642135,
          127.0016985
        ), // 지도의 중심좌표
        level: 5, // 지도의 확대 레벨
      };

    // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
    this.map = new this.kakao.maps.Map(mapContainer, mapOption);

    // 마커를 표시할 위치와 title 객체 배열입니다
    var positions = [];

    // // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
    // var infowindow = new kakao.maps.InfoWindow({zIndex:1});

    for (let home of this.fetchHomes) {
      // console.log("home", home);
      let obj = {
        title: home.apartmentName,
        latlng: new this.kakao.maps.LatLng(home.lat, home.lng),
      };
      positions.push(obj);
    }

    // 마커 이미지의 이미지 주소입니다
    var imageSrc = require(`@/assets/home.png`);

    for (var i = 0; i < positions.length; i++) {
      // 마커 이미지의 이미지 크기 입니다
      var imageSize = new this.kakao.maps.Size(35, 35);

      // 마커 이미지를 생성합니다
      var markerImage = new this.kakao.maps.MarkerImage(imageSrc, imageSize);

      // 마커를 생성합니다
      var marker = new this.kakao.maps.Marker({
        map: this.map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
      });

      (function (marker, title) {
        $vm.kakao.maps.event.addListener(marker, "mouseover", function () {
          $vm.displayInfowindow(marker, title);
        });

        $vm.kakao.maps.event.addListener(marker, "mouseout", function () {
          $vm.infowindow.close();
        });

        // itemEl.onmouseover =  function () {
        //     displayInfowindow(marker, title);
        // };

        // itemEl.onmouseout =  function () {
        //     infowindow.close();
        // };
      })(marker, positions[i].title);
    }
    // console.log(marker);

    console.log("KAKAO", this.kakao);
    console.log("???", new this.kakao.maps.services.Places());
    this.ps = new this.kakao.maps.services.Places();

    // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
    this.infowindow = new this.kakao.maps.InfoWindow({ zIndex: 1 });
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
    searchDeal() {
      let payload = {
        code: this.dong,
        year: this.year,
        month: this.month,
      };

      this.$store.dispatch("getDeals", payload);
    },
    searchHome() {
      this.$store.dispatch("getHomes", this.dong);
    },
    ////////////////////////////////////////////////////////////////////////////
    // 키워드 검색을 요청하는 함수입니다
    searchPlaces() {
      
      var keyword = document.getElementById("keyword").value;

      if (!keyword.replace(/^\s+|\s+$/g, "")) {
        this.$toast.warning("키워드를 입력해주세요!");
        return false;
      }

      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
      console.log("ps는 무엇인가", this.ps);
      this.ps.keywordSearch(keyword, this.placesSearchCB);
    },

    // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
    placesSearchCB(data, status, pagination) {
      if (status === this.kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        this.displayPlaces(data);

        // 페이지 번호를 표출합니다
        this.displayPagination(pagination);
      } else if (status === this.kakao.maps.services.Status.ZERO_RESULT) {
        this.$toast.info("검색 결과가 존재하지 않습니다.");
        return;
      } else if (status === this.kakao.maps.services.Status.ERROR) {
        this.$toast.error("검색 결과 중 오류가 발생했습니다.");
        return;
      }
    },

    // 검색 결과 목록과 마커를 표출하는 함수입니다
    displayPlaces(places) {
      let $vm = this;

      var listEl = document.getElementById("placesList"),
        menuEl = document.getElementById("menu_wrap"),
        fragment = document.createDocumentFragment(),
        bounds = new this.kakao.maps.LatLngBounds();

      // 검색 결과 목록에 추가된 항목들을 제거합니다
      this.removeAllChildNods(listEl);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new this.kakao.maps.LatLng(
            places[i].y,
            places[i].x
          ),
          marker = this.addMarker(placePosition, i),
          itemEl = this.getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        (function (marker, title) {
          $vm.kakao.maps.event.addListener(marker, "mouseover", function () {
            $vm.displayInfowindow(marker, title);
          });

          $vm.kakao.maps.event.addListener(marker, "mouseout", function () {
            $vm.infowindow.close();
          });

          itemEl.onmouseover = function () {
            $vm.displayInfowindow(marker, title);
          };

          itemEl.onmouseout = function () {
            $vm.infowindow.close();
          };
        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
      }

      // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
      listEl.appendChild(fragment);
      menuEl.scrollTop = 0;

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      this.map.setBounds(bounds);
    },

    // 검색결과 항목을 Element로 반환하는 함수입니다
    getListItem(index, places) {
      var el = document.createElement("li"),
        itemStr =
          '<span class="markerbg marker_' +
          (index + 1) +
          '"></span>' +
          '<div class="info">' +
          "   <h5>" +
          places.place_name +
          "</h5>";

      if (places.road_address_name) {
        itemStr +=
          "    <span>" +
          places.road_address_name +
          "</span>" +
          '   <span class="jibun gray">' +
          places.address_name +
          "</span>";
      } else {
        itemStr += "    <span>" + places.address_name + "</span>";
      }

      itemStr += '  <span class="tel">' + places.phone + "</span>" + "</div>";

      el.innerHTML = itemStr;
      el.className = "item";

      return el;
    },

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, idx) {
      var imageSrc =
          "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new this.kakao.maps.Size(36, 37), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new this.kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          spriteOrigin: new this.kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new this.kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new this.kakao.maps.MarkerImage(
          imageSrc,
          imageSize,
          imgOptions
        ),
        marker = new this.kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },

    // 지도 위에 표시되고 있는 마커를 모두 제거합니다
    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },

    // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
    displayPagination(pagination) {
      var paginationEl = document.getElementById("pagination"),
        fragment = document.createDocumentFragment(),
        i;

      // 기존에 추가된 페이지번호를 삭제합니다
      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
      }

      for (i = 1; i <= pagination.last; i++) {
        var el = document.createElement("a");
        el.href = "#";
        el.innerHTML = i;

        if (i === pagination.current) {
          el.className = "on";
        } else {
          el.onclick = (function (i) {
            return function () {
              pagination.gotoPage(i);
            };
          })(i);
        }

        fragment.appendChild(el);
      }
      paginationEl.appendChild(fragment);
    },

    // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
    // 인포윈도우에 장소명을 표시합니다
    displayInfowindow(marker, title) {
      var content = '<div style="padding:5px;z-index:1;">' + title + "</div>";

      this.infowindow.setContent(content);
      this.infowindow.open(this.map, marker);
    },

    // 검색결과 목록의 자식 Element를 제거하는 함수입니다
    removeAllChildNods(el) {
      while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
      }
    },
  },
};
</script>


<style scoped>
#listBox {
  border: 1px #dfdfdf solid;
  border-radius: 8px;
  height: 70vh;
  width: 18rem;
  overflow: auto;
  text-align: left;
}
#listBox::-webkit-scrollbar {
  width: 10px;
}
#listBox::-webkit-scrollbar-thumb {
  background-color: #96969648;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}

#contentBox {
  width: 80%;
  margin: 0 auto;
  display: flex;
  flex-direction: row;
}
.form-box {
  display: flex;
  flex-direction: row;
  justify-content: center;
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
.btn {
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
  background-color: #64a19d;
  border-color: #64a19d;
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

/* =========================================== */
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 500px;
}
#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 250px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}
.bg_white {
  background: #fff;
}
#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}
#menu_wrap .option {
  text-align: center;
}
#menu_wrap .option p {
  margin: 10px 0;
}
#menu_wrap .option button {
  margin-left: 5px;
}
#placesList li {
  list-style: none;
}
#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}
#placesList .item span {
  display: block;
  margin-top: 4px;
}
#placesList .item h5,
#placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
#placesList .item .info {
  padding: 10px 0 10px 55px;
}
#placesList .info .gray {
  color: #8a8a8a;
}
#placesList .info .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png)
    no-repeat;
}
#placesList .info .tel {
  color: #009900;
}
#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png)
    no-repeat;
}
#placesList .item .marker_1 {
  background-position: 0 -10px;
}
#placesList .item .marker_2 {
  background-position: 0 -56px;
}
#placesList .item .marker_3 {
  background-position: 0 -102px;
}
#placesList .item .marker_4 {
  background-position: 0 -148px;
}
#placesList .item .marker_5 {
  background-position: 0 -194px;
}
#placesList .item .marker_6 {
  background-position: 0 -240px;
}
#placesList .item .marker_7 {
  background-position: 0 -286px;
}
#placesList .item .marker_8 {
  background-position: 0 -332px;
}
#placesList .item .marker_9 {
  background-position: 0 -378px;
}
#placesList .item .marker_10 {
  background-position: 0 -423px;
}
#placesList .item .marker_11 {
  background-position: 0 -470px;
}
#placesList .item .marker_12 {
  background-position: 0 -516px;
}
#placesList .item .marker_13 {
  background-position: 0 -562px;
}
#placesList .item .marker_14 {
  background-position: 0 -608px;
}
#placesList .item .marker_15 {
  background-position: 0 -654px;
}
#pagination {
  margin: 10px auto;
  text-align: center;
}
#pagination a {
  display: inline-block;
  margin-right: 10px;
}
#pagination .on {
  font-weight: bold;
  cursor: default;
  color: #777;
}
</style>
