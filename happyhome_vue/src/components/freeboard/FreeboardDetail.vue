<template>
  <div>
    <div id="freeboardwrap">
        <div class="nbody col-lg-8 mx-auto">
          <h5 class="card-title" id="title">{{fetched.title}}</h5>
          <div id="subHeader">
            <small id="writer"><i class="fa-solid fa-user"></i> {{fetched.writer}}  </small>
            <small><i class="fa-regular fa-eye"></i> {{fetched.view}} </small>
          </div>
          <p class="ncontent" id="content">{{fetched.content}}</p>

          
          <div class="nfoot">
            <div class="nfootLeft">
              <i v-if="fetched.good == 1" @click="freeboardGood()" class="fa-solid fa-heart fa-xl"></i>
              <i v-if="fetched.good == 0" @click="freeboardGood()" class="fa-regular fa-heart fa-xl"></i>
            </div>
            <div class="nfootRight">
              <router-link v-if="loginUser == 'admin'" :to="`/freeboard/modify/${no}`" type="button" id="freeboardUpdateBtn" class="vbtn black">수정</router-link>
              <a v-if="loginUser == 'admin'" type="button" class="vbtn red" name="removeBnt" @click="freeboardDelete">삭제</a>
              <router-link to="/freeboard" type="button" class="vbtn gray" name="removeBnt">목록</router-link>
            </div>
          </div>
        </div>
    <!-- 댓글 기능 -->
    <comment-write/>
    </div>

  </div>
</template>

<script>
import CommentWrite from "@/components/freeboard/Comment2Write.vue";
// import Comment from "@/components/freeboard/Comment.vue";

export default {
  components:{
    CommentWrite,
    // Comment
  },
  data(){
    return{
      loginUser:"admin",
      no:this.$route.params.no
    }
  },
  created(){
    this.$store.dispatch('getFreeboard', this.no);
  },  
  computed:{
    fetched(){
      return this.$store.state.freeboard;
    },
    fetchUser() {
      return this.$store.getters.user;
    },
  },
  methods:{
    freeboardDelete(){
      if(confirm("게시물을 삭제하시겠습니까?")){
        this.$store.dispatch('delFreeboard', this.no);
      }
    },

    freeboardGood() {
      if(this.fetchUser.id === "") {
        this.$router.push("/user/login");
      }
      else {
        const freeboard = {
          id: this.fetchUser.id,
          freeboardid: this.fetched.id,
          good: this.fetched.good
        };
        this.$store.dispatch('freeboardGood', freeboard);
      }
    }

  }
};
</script>

<style scoped> 

.goodBtnOn{
  background-color: red;
}
.goodBtnOff{
  background-color: rgb(81, 81, 81);
}

#freeboardwrap {
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
  margin-bottom: 20px;
  padding: 35px;
  border-radius: 20px;
  position: relative;
}
.ncontent {
  border-top: solid 1px lightgrey;
  padding-top: 15px;
  height: 74%;
  margin: 15px 0;
}
#title{
  font-family: "Nanum Gothic";
  font-weight: 800;
  margin-bottom: 6px;
}
#subHeader{
  display: flex;
  flex-direction: column;
}
.nfoot {
  display: flex;
  justify-content: space-between;
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