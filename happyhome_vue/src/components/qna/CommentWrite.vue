<template>
  <div>
    <div class="regist">

      <div class="regist_form">
        <div>
          <textarea id="comment" name="comment" v-model="comment" cols="35" rows="2"></textarea>
        </div>
        <div id="btnBar">
          <b-button @click="registComment">등록</b-button>
        </div>
      </div>
    </div>


    <div v-for="item in fecthList" :key="item.comment_no" class="comment">
      <div v-if="item.comment_no != fetchComment.comment_no">
        <div class="head">{{ item.user_name }} ({{ item.comment_time }})</div>
        <div class="content" v-html="enterToBr(item.comment)"></div>
        <!-- 로그인 기능 구현 후 로그인한 자신의 글에만 보이게 한다. -->
        <div class="cbtn"><label @click="modifyCommentView(item.comment_no)">수정</label> | <label @click="deleteComment(item.comment_no)">삭제</label></div>
      </div>
      <div v-else class="regist_form">
        <textarea id="comment" name="comment" v-model="fetchComment.comment" cols="35" rows="2"></textarea>
        <button class="small" @click="updateCommentCancel">취소</button>
        <button class="small" @click="updateComment()">수정</button>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      loginUser:"admin",
      no:this.$route.params.no,
      comment:"",
    };
  },
  created(){
    this.$store.dispatch("getCommentList", this.no);
  },
  computed:{
    fetchComment(){
      return this.$store.state.comment;
    },
    fecthList(){
      return this.$store.state.comments
    }
  },
  methods: {
    registComment() {

      const comment = {
        comment:this.comment,
        id:this.no,
        user_name:"test name"
      };

      this.$store.dispatch("createComment", comment);
    },
    updateComment() {

      let payload = {
          list:{
            comment_no:this.fetchComment.comment_no,
            comment:this.fetchComment.comment,
          },  
          id:this.no
        };
      

      this.$store.dispatch("updateComment", payload);
      this.$store.commit("SET_COMMENT", {});

    },
    updateCommentCancel() {
      this.$store.commit("SET_COMMENT", {});
    },

    //이하 댓글창 관련
    modifyCommentView(comment_no) {

      this.$store.dispatch("getComment", comment_no);

    },
    deleteComment(cnum) {
      console.log("댓 삭제");
      if (confirm("정말로 삭제?")) {

        let payload = {
          comment_no:cnum,
          id:this.no
        };
        this.$store.dispatch("deleteComment", payload);
      }
    },
    enterToBr(str) {
      if (str) return str.replace(/(?:\r\n|\r|\n)/g, "<br />");
    }
  }
};
</script>

<style scoped>
.regist {
  padding: 10px;
  width: 730px;
  margin: 0px auto;
}
.regist_form {
  text-align: left;
  padding: 20px;
  background: #e0e2e5;
  border: 1px solid #e3e7eb;
}
#btnBar{
  text-align: right;
  height: 30px;
}
#btnBar button{
  padding: 3px 24px;
  border-radius: 2px;
}
textarea {
  width: 100%;
  padding: 10px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: large;
}

button {
  float: right;
}

button.small {
  width: 60px;
  font-size: small;
  font-weight: bold;
}

.comment {
  width: 726px;
  margin: 0 auto;
  margin-bottom: 5px;
  text-align: left;
  border-bottom: 1px solid #d1d1d1;
  padding: 10px 20px;
}
.head {
  font-weight: bold;
  margin-bottom: 5px;
}
.content {
  padding: 5px;
}
.cbtn {
  text-align: right;
  color: steelblue;
  margin: 5px 0px;
}
</style>
