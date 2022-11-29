<template>
  <div id="noticewrap">
    <!-- <div class="col-lg-8 mx-auto p-4"> -->
        <div class="nbody col-lg-8 mx-auto">
          <h5 class="card-title" id="title">{{fetched.title}}</h5>
          <small>조회수:{{fetched.view}} </small>
          <p class="ncontent" id="content">{{fetched.content}}</p>
          


          <b-button v-if="fetchUser.id === 'admin'" @click="setPin()" :class="{ pinBtnOn : fetched.pin == 1, pinBtnOff : fetched.pin == 0}" >전체 공지 등록</b-button>

          <div class="nfoot">
            <router-link v-if="loginUser == 'admin'" :to="`/notice/modify/${no}`" type="button" id="noticeUpdateBtn" class="vbtn black">수정</router-link>
            <a v-if="loginUser == 'admin'" type="button" class="vbtn red" name="removeBnt" @click="noticeDelete">삭제</a>
            <router-link to="/notice" type="button" class="vbtn gray" name="removeBnt">목록</router-link>
          </div>
        </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      loginUser:"admin",
      no:this.$route.params.no
    }
  },
  created(){
    this.$store.dispatch('getNotice', this.no);
    // this.notice = this.$store.state.notice;
  },  
  computed:{
    fetched(){
      return this.$store.state.notice;
    },
    fetchUser() {
      return this.$store.getters.user;
    },
  },
  methods:{
    noticeDelete(){
      if(confirm("게시물을 삭제하시겠습니까?")){
        this.$store.dispatch('delNotice', this.no);
      }
    },
    setPin() {
      this.$store.dispatch('setPin', this.fetched);
    }
  }
};
</script>

<style scoped> 
.pinBtnOn{
  background-color: red;
}
.pinBtnOff{
  background-color: rgb(81, 81, 81);
}
#noticewrap {
  text-align: left;
  background-color: #f5f5f5;
  height: 100ch;
  overflow: auto;
}
.nbody {
  background-color: white;
  height: 580px;
  width: 550px;
  margin: 120px auto;
  padding: 35px;
  border-radius: 20px;
  position: relative;
}
.ncontent {
  border-top: solid 1px lightgrey;
  padding-top: 15px;
  height: 50%;
  margin: 15px 0;
}
.nfoot {
  position: absolute;
  bottom: 29px;
  right: 29px;
}
.vbtn {
  color: #fff;
  box-shadow: 0 0.1875rem 0.1875rem 0 rgb(0 0 0 / 10%) !important;
  padding: 3px;
  border-radius: 6px;
  text-decoration: none;
  margin-left: 8px;
}
.black {
  background-color: #646464;
}
.red {
  background-color: #bb5b5b;
}
.gray {
  color: #181818;
  border: 1px solid #181818;
}
</style>