<template>
  <div id="noticewrap">
        <div class="nbody col-lg-8 mx-auto">
          <div id="titleBox">
            <i class="fa-regular fa-flag"></i>
            <h5 class="card-title" id="title">{{fetched.title}}</h5>
          </div>
          <small>조회수:{{fetched.view}} </small>
          <p class="ncontent" id="content">{{fetched.content}}</p>

          <div class="nfoot">
            <div id="nfootLeft">
              <b-button v-if="fetchUser.id === 'admin' && fetched.pin == 1" @click="setPin()" class="pinBtnOn" >전체 공지 해제</b-button>
              <b-button v-if="fetchUser.id === 'admin' && fetched.pin == 0" @click="setPin()" class="pinBtnOff">전체 공지 등록</b-button>
            </div>
            <div id="nfootRight">
              <router-link v-if="loginUser == 'admin'" :to="`/notice/modify/${no}`" type="button" id="noticeUpdateBtn" class="vbtn black">수정</router-link>
              <a v-if="loginUser == 'admin'" type="button" class="vbtn red" name="removeBnt" @click="noticeDelete">삭제</a>
              <router-link to="/notice" type="button" class="vbtn gray" name="removeBnt">목록</router-link>
            </div>
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
*{
  font-family: 'Nanum Gothic';
}
#title{
  display: inline;
  font-family: 'Nanum Gothic';
  font-weight: 800;
  margin-left: 5px;
}
#titleBox{
  margin-bottom: 10px;
}
.pinBtnOn{
  background-color: rgb(81, 81, 81);
}
.pinBtnOff{
  background-color: white;
  color:  rgb(81, 81, 81);
  border-color:  rgb(81, 81, 81);
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
  display: flex;
  position: absolute;
  width: 100%;
  bottom: 29px;
  right: 0px;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}
#nfootRight{
  margin-right: 25px;
}
#nfootLeft{
  margin-left: 31px;
}
.vbtn {
  color: #fff;
  box-shadow: 0 0.1875rem 0.1875rem 0 rgb(0 0 0 / 10%) !important;
  padding: 6px;
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