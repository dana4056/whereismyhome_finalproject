<template>
  <div class="nbody">
    <h2>커뮤니티 글 작성</h2>
    
    <form class="formwarp" method="post" action="/freeboard">
      <div class="form-box">
      <label>말머리 선택</label>
      <select v-model="type" class="form-select" id="type" >
        <option :value="index+1" v-for="type, index in types" :key = "index">
          {{ type }}
        </option>
      </select>
    </div>
    <div class="form-box">
      <label>시도선택</label>
      <select v-model="sido" @change="getGugun" class="form-select" id="sido">
        <option value="">시도선택</option>
        <option :key="sido.code" :value="sido" v-for="sido in fetchSido">
          {{ sido.name }}
        </option>
      </select>

      <label>구군선택</label>
      <select v-model="gugun" @change="getDong" class="form-select" id="gugun">
        <option value="">구군선택</option>
        <option
          :key="gugun.code"
          :value="gugun"
          v-for="gugun in fetchGugun"
        >
          {{ gugun.name.split(" ")[1] }}
        </option>
      </select>

      <label>동선택</label>
      <select v-model="dong" class="form-select" id="dong">
        <option value="">동선택</option>
        <option :key="dong.code" :value="dong" v-for="dong in fetchDong">
          {{ dong.name.split(" ")[2] }}
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
      sido: "",
      gugun: "",
      dong: "",
      title: "",
      type: "",
      area: "",
      content: "",
      view: "",
    }
  },
  created(){
    let payload = {
      regcode: "*00000000",
      selid: "sido",
    };
    this.$store.dispatch("getArea", payload);
  },
  computed: {
    fetchSido() {
      return this.$store.state.sido;
    },
    fetchGugun() {
      return this.$store.state.gugun;
    },
    fetchDong() {
      return this.$store.state.dong;
    },
  },


  methods:{
    getGugun() {
      let payload = {
        regcode: this.sido.code.substr(0, 2) + "*00000",
        selid: "gugun",
      };
      this.$store.dispatch("getArea", payload);
    },
    getDong() {
      let payload = {
        regcode: this.gugun.code.substr(0, 5) + "*",
        selid: "dong",
      };
      this.$store.dispatch("getArea", payload);
    },
    submit(){
      console.log(this.type)
      if(this.title != "" && this.content != "" && this.type != ""){
        const freeboard = {
        title: this.title,
        type: this.type,
        area: this.dong.name,
        content: this.content,
        view: this.view,
        writer : this.$store.state.loginUser.name
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