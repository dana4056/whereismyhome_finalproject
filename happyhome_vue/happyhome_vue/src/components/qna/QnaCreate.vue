<template>
  <div class="nbody">
    <h2>질문글 작성</h2>
    
    <form class="formwarp" method="post" action="/qna">
      <div class="form-group">
          <label>제목</label>
          <input type="text" id="title" name="title" class="form-control" placeholder="제목" v-model="title"/>
      </div>
      <div class="form-group">
          <label>내용</label>
          <textarea id="content" name="content" class="form-control" v-model="content"></textarea>
      </div>
      <div class="form-foot form-group text-right">
          <input class="vbtn black" type="submit" @click.prevent="submit"  value="등록">
          <router-link to="/qna" type="button" class="vbtn red">닫기</router-link>
      </div>
    </form>
  </div>
</template>

<script scoped>
export default {
  data(){
    return{
      title: "",
      content: "",
      view: 0,
    }
  },

  computed:{
    fetchUser() {
      return this.$store.getters.user;
    },
  },

  methods:{
    submit(){

      if(this.title != "" && this.content != ""){
        const qna = {
        title: this.title,
        writer: this.fetchUser.id,
        content: this.content,

        view: this.view,
        }

        this.$store.dispatch('createQna', qna);
      }else{
        this.$store.error();("기입한 내용을 확인해주세요.");
      }
    }
  }
}
</script>

<style scoped>
	.nbody{
    background-color: white;
    height: 580px;
    width: 35%;
    margin: 100px auto;
    border-radius: 20px;
    text-align: left;
	}
	form{
		position:relative;
	}
  .form-group{
    padding-top: 15px;
  }
	.vbtn{
    color: #fff;
    box-shadow: 0 0.1875rem 0.1875rem 0 rgb(0 0 0 / 10%) !important;
    padding: 3px;
    border-radius: 6px;
    text-decoration: none;
    border: 0;
    margin-left: 8px;
	}
  .black{
    background-color: #646464;
  }
  .red{
    background-color: #bb5b5b;
  }
  .form-foot{
		position : absolute;
    right: 0px;
    margin-top: 20px
	}
</style>