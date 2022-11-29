<template>
  <div>
    <section class="container align-middle p-5">

      <caption>
        <h1><img src="@/assets/qna.png" alt=""> 질의응답 게시판</h1>

        
        <b-button  @click="moveCreate" type="button" class="addbtn px-5"> 
        <i class="fa-solid fa-circle-plus fa-2x" @click="moveCreate"></i>
        </b-button>
      </caption>

      <b-navbar>
        <b-navbar-brand>정렬</b-navbar-brand>
        <b-navbar-nav>
          <!-- b-nav-item 부트스트랩이라 글자색이 변경이 안된다. 어떻게 하는건지 물어보기 -->
          <nav-item @click="qnaSort(1)" :class="{sortLetter : sortType == 1, sortNot : sortType != 1}">최신순</nav-item>
          &nbsp;
          <nav-item @click="qnaSort(2)" :class="{sortLetter : sortType == 2}">조회수순</nav-item>
          &nbsp;
          <nav-item @click="qnaSort(3)" :class="{sortLetter : sortType == 3}">좋아요순</nav-item>
        </b-navbar-nav>
      </b-navbar>

      <table class="table table-hover">
        <thead>
          <tr class="table-secondary">
              <th class="col-md-2" scope="col">번호</th>
              <th class="col-md-3" scope="col">제목</th>
              <!-- <th class="col-md-3" scope="col">내용</th> -->
              <th class="col-md-1" scope="col">작성자</th>
              <th class="col-md-1" scope="col">조회수</th>
              <th class="col-md-2" scope="col">좋아요</th>
          </tr>
        </thead>

        <tbody >
            <tr v-for="(qna) in fetchList" :key="qna.id" @click="movePage(qna.id, $event)">
                <td>{{qna.id}}</td>
                <td>{{qna.title}}</td>
                <!-- <td><small>{{qna.content | strFormat(qna.content)}}</small></td> -->
                <td>{{qna.writer}}</td>
                <td>{{qna.view}}</td>
                <td>{{qna.good}}</td>
            </tr>
        </tbody>
      </table>

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
      toastCount: 0,
    } 
  },

  created(){
    this.$store.dispatch('getQnaList', 1);
  },

  computed:{
    fetchPage(){
      return this.$store.state.page;
    },
    fetchList(){
      return this.$store.getters.allQna;
    },
    fetchUser() {
      return this.$store.getters.user;
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
      this.$router.push(`/qna/view/${id}`)
    }, 
    toStart(){
      // 1 : 최신 순으로 정렬
      if(this.sortType == 1) { 
        this.$store.dispatch('getQnaList', 1);
      }
      // 2 : 조회수 순으로 정렬
      else if(this.sortType == 2) {
        this.$store.dispatch('getQnaListByView', 1);
      }
      // 3 : 좋아요 순으로 정렬 
      else if(this.sortType == 3) {
        this.$store.dispatch('getQnaListByGood', 1);
      }
    },
    toEnd(){
      console.log("클릭 마지막");
      // 1 : 최신 순으로 정렬
      if(this.sortType == 1) { 
        this.$store.dispatch('getQnaList', this.$store.state.page);
      }
      // 2 : 조회수 순으로 정렬
      else if(this.sortType == 2) {
        this.$store.dispatch('getQnaListByView', this.$store.state.page);
      }
      // 3 : 좋아요 순으로 정렬 
      else if(this.sortType == 3) {
        this.$store.dispatch('getQnaListByGood', this.$store.state.page);
      }


    },
    
    //정렬 선택 함수
    qnaSort(sortType) {
      this.sortType = sortType;
      // 1 : 최신 순으로 정렬
      if(this.sortType == 1) { 
        this.$store.dispatch('getQnaList', 1);
      }
      // 2 : 조회수 순으로 정렬
      else if(this.sortType == 2) {
        this.$store.dispatch('getQnaListByView', 1);
      }
      // 3 : 좋아요 순으로 정렬 
      else if(this.sortType == 3) {
        this.$store.dispatch('getQnaListByGood', 1);
      }
    },

    // 이동하는 페이지 
    toPage(page){
      // 1 : 최신 순으로 정렬한 상태에서 움직임
      if(this.sortType == 1) { 
        this.$store.dispatch('getQnaList', page);
      }
      // 2 : 조회수 순으로 정렬
      else if(this.sortType == 2) {
        this.$store.dispatch('getQnaListByView', page);
      }
      // 3 : 좋아요 순으로 정렬 
      else if(this.sortType == 3) {
        this.$store.dispatch('getQnaListByGood', page);
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

.sortLetter{
  color: blue;
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




</style>