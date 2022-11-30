<template>
  <div>
    <section class="container align-middle py-5">
      <caption>
        <h1><img src="@/assets/comunity.svg" alt=""> 커뮤니티 게시판    </h1>
        <router-link to="/freeboard/create" type="button" class="addbtn"> <i class="fa-solid fa-circle-plus fa-2x"></i></router-link>
      </caption>

      <div class="form-box">
        <select v-model="interIdx" class="form-select" id="interestSel">
          <option value="">관심 지역 선택</option>
          <option :key="index" :value="index" v-for="(interest, index) in fetchInterestList">
            {{ interest.area }}
          </option>
        </select>

        <a id="list-btn"  class="usallyBnt btn-primary">
          <i class="fa-sharp fa-solid fa-magnifying-glass"></i>
        </a>
      </div>

      <b-navbar>
        <b-navbar-nav>
          <!-- b-nav-item 부트스트랩이라 글자색이 변경이 안된다. 어떻게 하는건지 물어보기 -->
          <nav-item @click="freeboardSort(1)" :class="{sortLetter : sortType == 1, sortNot : sortType != 1}">최신순</nav-item>
          &nbsp;
          <nav-item @click="freeboardSort(2)" :class="{sortLetter : sortType == 2, sortNot : sortType != 2}">조회수순</nav-item>
          &nbsp;
          <nav-item @click="freeboardSort(3)" :class="{sortLetter : sortType == 3, sortNot : sortType != 3}">좋아요순</nav-item>
        </b-navbar-nav>
      </b-navbar>

      <b-navbar>
        <b-navbar-nav>
          <!-- b-nav-item 부트스트랩이라 글자색이 변경이 안된다. 어떻게 하는건지 물어보기 -->
          <nav-item @click="freeboardmaltype(0)" class="categoryBtn" :class="{sortLetter : malType == 0, sortNot : malType != 0}">전체</nav-item>
          &nbsp;
          <nav-item @click="freeboardmaltype(1)" class="categoryBtn" :class="{cate1 : malType == 1, sortNot : malType != 1}">맛집추천</nav-item>
          &nbsp;
          <nav-item @click="freeboardmaltype(2)" class="categoryBtn" :class="{cate2 : malType == 2, sortNot : malType != 2}">취미공유</nav-item>
          &nbsp;
          <nav-item @click="freeboardmaltype(3)" class="categoryBtn" :class="{cate3 : malType == 3, sortNot : malType != 3}">부탁해요</nav-item>
          &nbsp;
          <nav-item @click="freeboardmaltype(4)" class="categoryBtn" :class="{cate4 : malType == 4, sortNot : malType != 4}">분실신고</nav-item>
        </b-navbar-nav>
      </b-navbar>


      <div class="list-group">
        <div v-for="(freeboard) in fetchFreeboard" :key="freeboard.id" @click="movePage(freeboard.id, $event)" class="El list-group-item list-group-item-action py-3">
          <div class="ElLeft">
            <small class="tag" :class="`cate${freeboard.type}`">{{types[freeboard.type]}}</small>
          </div>
          <div class="ElMiddle">
            <p class="ElTitle">{{freeboard.title}}</p>
            <p>{{freeboard.content}}</p>
            <small><i class="fa-solid fa-user"></i> {{freeboard.writer}}</small>
          </div>
          <div class="ElRight">
            <small><i class="fa-regular fa-eye"></i> {{freeboard.view}}</small>
            <small><i class="fa-regular fa-heart"></i> {{freeboard.good}}</small>
          </div> 
        </div>
      </div>

      <div class="mt-3">
        <b-button-group size="sm">
          <b-button @click.prevent="toStart">처음으로</b-button>
          <b-button variant="outline-secondary" v-for="i in fetchPage" @click.prevent="toPage(i)" :key=i>{{i}}</b-button>
          <b-button @click.prevent="toEnd">끝으로</b-button>
        </b-button-group>
      </div>

    </section>
  </div>
</template>







<script>



export default {
  data(){
    return { 
      sortType: 1,
      malType: 0,
      types: ["전체", "맛집추천", "취미공유", "부탁해요", "분실신고"],
    } 
  },
  created() {
    const temp = {
        p: 1,
        type: 0,        
        }
    this.$store.dispatch('getFreeboardList', temp);
  },
  computed:{
    fetchPage(){
      return this.$store.state.page;
    },

    fetchUser() {
      return this.$store.getters.user;
    },
    fetchFreeboard() {
      return this.$store.getters.allFreeboard;
    },
    fetchInterestList() {
      return this.$store.state.interestArea;
    },
  },

  watched:{
    malType:function(val){
      console.log(val);
      console.log(val);
      console.log(val);
      console.log(val);
    },


  },

  filters: {
    strFormat(val) {
      if(val.length > 15){
        return val.substr(0, 15)+"...";  
      }else{
        return val;
      }
    },
  },
  methods:{
    movePage(id){
      // location.href = `/freeboard/view/${id}`;
      this.$router.push(`/freeboard/view/${id}`);
    }, 

    toStart() {

      const temp = {
        p: 1,
        type: this.malType,        
        }

      
      // 1 : 최신 순으로 정렬
      if(this.sortType == 1) { 
        this.$store.dispatch('getFreeboardList', temp);
      }
      // 2 : 조회수 순으로 정렬
      else if(this.sortType == 2) {
        this.$store.dispatch('getFreeboardListByView', temp);
      }
      // 3 : 좋아요 순으로 정렬 
      else if(this.sortType == 3) {
        this.$store.dispatch('getFreeboardListByGood', temp);
      }
    },
    toEnd(){
      console.log("클릭 마지막");

      const temp = {
        p: this.$store.state.page,
        type: this.malType,        
        }


      // 1 : 최신 순으로 정렬
      if(this.sortType == 1) { 
        this.$store.dispatch('getFreeboardList', temp);
      }
      // 2 : 조회수 순으로 정렬
      else if(this.sortType == 2) {
        this.$store.dispatch('getFreeboardListByView', temp);
      }
      // 3 : 좋아요 순으로 정렬 
      else if(this.sortType == 3) {
        this.$store.dispatch('getFreeboardListByGood', temp);
      }
    },

    
    //정렬 선택 함수
    freeboardSort(sortType) {
      this.sortType = sortType;

      const temp = {
        p: 1,
        type: this.malType,        
        }

      // 1 : 최신 순으로 정렬
      if (this.sortType == 1) {
        this.$store.dispatch('getFreeboardList', temp);
      }
      // 2 : 조회수 순으로 정렬
      else if (this.sortType == 2) {
        this.$store.dispatch('getFreeboardListByView', temp);
      }
      // 3 : 좋아요 순으로 정렬 
      else if (this.sortType == 3) {
        this.$store.dispatch('getFreeboardListByGood', temp);
      }
    },
      //말머리 선택 함수
    freeboardmaltype(malType) {
      this.malType = malType;   
      console.log(this.malType);

      const temp = {
        p: 1,
        type: this.malType,        
        }
      
      // 1 : 최신 순으로 정렬
      if (this.sortType == 1) {
        console.log(temp);
      console.log(this.sortType);
        this.$store.dispatch('getFreeboardList', temp);
      }
      // 2 : 조회수 순으로 정렬
      else if (this.sortType == 2) {
        this.$store.dispatch('getFreeboardListByView', temp);
      }
      // 3 : 좋아요 순으로 정렬 
      else if (this.sortType == 3) {
        this.$store.dispatch('getFreeboardListByGood', temp);
      }
      
      
    },


    toPage(page) {

      const temp = {
        p: page,
        type: this.malType,        
        }
      
      // 1 : 최신 순으로 정렬한 상태에서 움직임
      if(this.sortType == 1) { 
        this.$store.dispatch('getFreeboardList', temp);
      }
      // 2 : 조회수 순으로 정렬
      else if(this.sortType == 2) {
        this.$store.dispatch('getFreeboardListByView', temp);
      }
      // 3 : 좋아요 순으로 정렬 
      else if(this.sortType == 3) {
        this.$store.dispatch('getFreeboardListByGood', temp);
      }
    },

    
    // QnA 작성으로 이동
    // 로그인이 되어있지 않다면 로그인 페이지로 이동
    moveCreate() {
      if(this.fetchUser.id === "") {
        this.$router.push("/user/login");
      }
      else {
        this.$router.push("/qna/create");
      }
    },




  }
}
</script>

<style scoped>
.addbtn{
  color: #505050;
}

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
  z-index: 9999;
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
/* th {
  text-align: center;
  border-bottom: 1px solid gray;
}
th,
td {
  padding: 5px;
} */

#listBox {
  font-family: 'Noto Sans KR', sans-serif;
  background-color: #ffffffc9;
  top: 0;
  position: absolute;
  right: 0px;
  z-index: 9999;

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
  margin: 11px 0px 11px 420px;
  z-index: 999;
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
/* table {
  background-color: rgb(52 52 52 / 59%);
  color: white;
} */
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
  z-index: 9999;
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




/*  */

.new-result-btn {
  height: 42px;
  border-radius: 10px;
  border: 2px solid #636366;
}
.sortLetter{
  color: white;
  background-color: #226699;
  padding: 2px 5px;
  border: 2px solid #4e799b;
  border-radius: 3px;
}
.categoryBtn{
  color: white;
  padding: 2px 5px;
  border-radius: 3px;
}
.cate1{
  background-color: #2f656a;
  border: 2px solid #2f656a;
}
.cate2{
  background-color: #5b9183;
  border: 2px solid #5b9183;
}
.cate3{
  background-color: #de5e66;
  border: 2px solid #de5e66;
}
.cate4{
  background-color: #f5a55d;
  border: 2px solid #f5a55d;
}

.sortNot{
  color: #6a6a6a;
  background-color: #f7f7f7;
  padding: 2px 5px;
  border: 2px solid #d9d9d9;
  border-radius: 3px;
}

.tag{
  color: white;
  padding: 2px 4px;
  border-radius: 4px;
}

table{
  text-align: left;
}
caption{
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  color: #6c757d;
  text-align: left;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
caption img{
  width: 5vw;
}
caption h1{
  font-weight: bold;
  color: #505050;
}

tbody tr{
  cursor:pointer;
}

.El{
  display: flex;
  justify-content: space-between;
}
.ElLeft{

}
.ElId{
  font-family: 'Nanum Gothic';
  font-weight: 800;
}
.ElMiddle{
  text-align: left;
  width: 500px;
}
.ElMiddle small{
  color:rgb(143, 142, 142);
}
.ElTitle{
  font-family: 'Nanum Gothic';
  font-weight: 800;
  margin-bottom: 3px;
}
.ElRight{
  display: flex;
  flex-direction: row;
  align-items: flex-end;
}
.ElRight small{
  padding:0 6px;
}
.container{
  padding: 0px 14rem;
}

</style>