<template>
  <div class="nbody">
    <h2>커뮤니티 글 작성</h2>
    
    <form class="formwarp" method="post" action="/freeboard">
      <div class="form-box">
      <label>말머리 선택</label>
      <select v-model="type" class="form-select" id="type" >
        
        <!-- <option :value= "null">말머리 선택</option> -->
        <option :value="type" v-for="type, index in types" :key = "index">
          {{ type }}
        </option>
      </select>
    </div>
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
          <router-link to="/freeboard" type="button" class="vbtn red">닫기</router-link>
      </div>
    </form>
  </div>
</template>

<script scoped>
export default {
  data(){
    return{
      types: ["맛집추천", "취미공유", "부탁해요", "분실신고"],
    
    }
  },

  computed: {
    
  },


  methods:{
    submit(){

      if(this.title != "" && this.content != "" && this.type != ""){
        const freeboard = {
        title: this.title,
        type: this.type,
        content: this.content,
        view: this.view,
        writer : "sdf"
        }

        this.$store.dispatch('createFreeboard', freeboard);
      }else{
        this.$toast.error("기입한 내용을 확인해주세요.");
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