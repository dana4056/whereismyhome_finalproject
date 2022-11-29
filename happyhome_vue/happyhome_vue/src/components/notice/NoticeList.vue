<template>
  <div>
    <section class="noticeWrap container align-middle p-5">

      <caption>
        <h1><img src="@/assets/notice.png" alt=""> 공지사항</h1>
        <router-link v-if="fetchUser.id === 'admin'" to="/notice/create" type="button" class="addbtn px-5"> <i class="fa-solid fa-circle-plus fa-2x"></i></router-link>
      </caption>

      <b-navbar>
        <b-navbar-brand>검색</b-navbar-brand>
        <input v-model="keyword"/>
        &nbsp;
        <b-button @click="search"> <i class="fa-sharp fa-solid fa-magnifying-glass"></i></b-button>
      </b-navbar>
      
      <table class="table table-hover">
        <thead>
          <tr class="table-secondary">
              <th class="col-md-2" scope="col">번호</th>
              <th class="col-md-7" scope="col">제목</th>
              <th class="col-md-3" scope="col">조회수</th>
          </tr>
        </thead>

        <tbody id="noticeBody">
            <tr v-for="(notice) in fetchNotice" :key="notice.id" @click="movePage(notice.id, $event)">
                <td :class="{pin : notice.pin == 1}">{{notice.id}}</td>
                <td :class="{pin : notice.pin == 1}">{{notice.title}}</td>
                <td :class="{pin : notice.pin == 1}">{{notice.view}}</td>
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
      keyword: "",
    } 
  },
  created(){
    this.$store.dispatch('getNoticeList', 1);
  },
  computed:{
    fetchPage(){
      console.log("페이지",this.$store.state.page);
      return this.$store.state.page;
    },
    fetchUser() {
      return this.$store.getters.user;
    },
    fetchNotice() {
      return this.$store.getters.allNotice;
    }
  },
  methods:{
    movePage(id){
      this.$router.push(`/notice/view/${id}`)
    }, 
    toStart(){
      this.$store.dispatch('getNoticeList', 1);
    },
    toEnd(){
      console.log("클릭 마지막");
      this.$store.dispatch('getNoticeList', this.$store.state.page);
    },
    toPage(page){
      this.$store.dispatch('getNoticeList', page);
    },
    search() {
      const search = {
        keyword: this.keyword,
        page: 1,
      }
      this.$store.dispatch('searchNoticeList', search);
    },
  }
}
</script>

<style scoped>
.pin{
  color:red;
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
  width: 4vw;
}
caption h1{
  font-weight: bold;
  color: #505050;
}


tbody tr{
  cursor:pointer;
}


</style>