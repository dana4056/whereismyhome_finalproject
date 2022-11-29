<template> 
  <div>
    <div id="qnawrap">
        <div class="nbody col-lg-8 mx-auto">
          <h5 class="card-title" id="title">{{fetched.title}}</h5>
          <h6 id="writer"> 작성자 : {{fetched.writer}}</h6>
          <small>조회수:{{fetched.view}} </small>
          <small>좋아요:{{fetched.good}} </small>
          <p class="ncontent" id="content">{{fetched.content}}</p>


          <!-- 좋아요 버튼 -->
          <b-button @click="qnaGood()" :class="{ goodBtnOn : fetchQnAGood == 1, goodBtnOff : fetchQnAGood == 0}" > 좋아요 </b-button>


          <div class="nfoot">
            <router-link v-if="fetchUser.id == fetched.writer" :to="`/qna/modify/${no}`" type="button" id="qnaUpdateBtn" class="vbtn black">수정</router-link>
            <a v-if="fetchUser.id == fetched.writer" type="button" class="vbtn red" name="removeBnt" @click="qnaDelete">삭제</a>
            <router-link to="/qna" type="button" class="vbtn gray" name="removeBnt">목록</router-link>
          </div>
        </div>
    </div>

    <!-- 댓글 기능 -->
    <comment-write/>
  </div>
</template>

<script>
import CommentWrite from "@/components/qna/CommentWrite.vue";
// import Comment from "@/components/qna/Comment.vue";

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
    this.$store.dispatch('getQna', this.no);
  },  
  computed:{
    fetched(){
      return this.$store.state.qna;
    },
    fetchUser() {
      return this.$store.getters.user;
    },
    fetchQnAGood() {
      return this.$store.getters.qnaGood;
    },
  },
  methods:{
    qnaDelete(){
      if(confirm("게시물을 삭제하시겠습니까?")){
        this.$store.dispatch('delQna', this.no);
      }
    },
    qnaGood() {
      if(this.fetchUser.id === "") {
        this.$router.push("/user/login");
      }
      else {
        const qna = {
          id: this.fetchUser.id,
          qnaid: this.fetched.id,
        };
        this.$store.dispatch('qnaGood', qna);
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

#qnawrap {
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