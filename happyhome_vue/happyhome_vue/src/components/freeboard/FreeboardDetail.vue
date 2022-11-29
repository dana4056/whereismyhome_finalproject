<template>
  <div>
    <div id="freeboardwrap">
        <div class="nbody col-lg-8 mx-auto">
          <h5 class="card-title" id="title">{{fetched.title}}</h5>
          <h6 id="writer"> 작성자 : {{fetched.writer}}</h6>
          <small>조회수:{{fetched.view}} </small>
          <p class="ncontent" id="content">{{fetched.content}}</p>

          <!-- 좋아요 버튼 -->
          <b-button @click="freeboardGood()" :class="{ goodBtnOn : fetchfreeboardGood == 1, goodBtnOff : fetchfreeboardGood == 0}" > 좋아요 </b-button>


          <div class="nfoot">
            <router-link v-if="loginUser == 'admin'" :to="`/freeboard/modify/${no}`" type="button" id="freeboardUpdateBtn" class="vbtn black">수정</router-link>
            <a v-if="loginUser == 'admin'" type="button" class="vbtn red" name="removeBnt" @click="freeboardDelete">삭제</a>
            <router-link to="/freeboard" type="button" class="vbtn gray" name="removeBnt">목록</router-link>
          </div>
        </div>
    </div>

    <!-- 댓글 기능 -->
    <comment-write/>
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
    fetchfreeboardGood() {
      return this.$store.getters.freeboardGood;
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