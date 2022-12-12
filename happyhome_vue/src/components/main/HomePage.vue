<template>
  <div id="wrap">
    <section id="sec1">
      <div id="content1">
        <h1 class="">Where Is MyHome</h1>
        <h2 class="">SSAFY WEB PJT</h2>
        <b-button pill class="my-5"><router-link to="/house/list">매물 검색하러 가기</router-link></b-button>
      </div>
    </section>

    <section id="sec2">
    <b-table
    
      :style="'display: inline-table; width:30%'"
      class="mx-3"
      :bordered=true
      :small=true
      :hover=true
      :fixed=false
      :table-variant="'light'"
      :head-variant="'dark'"
      :items="fetchNotice"
      :fields="fields"
      caption-top
    >
      <template #table-caption>공지사항</template>
    </b-table>

    <b-table
      :style="'display: inline-table; width:30%'"
      class="mx-3"
      :bordered=true
      :small=true
      :hover=true
      :table-variant="'light'"
      :head-variant="'dark'"
      :items="fetchFreeboard"
      
      caption-top
    >
      <template #table-caption>커뮤니티</template>
    </b-table>
      
    </section>
    <section id="sec3">
      
    </section>

  </div>
</template>

<script>
export default {
  data() {
      return {
        // fields: ['Id', 'Title', 'Content', 'View', 'Pin'],
        notices: [
          { age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
          { age: 21, first_name: 'Larsen', last_name: 'Shaw' },
          { age: 89, first_name: 'Geneva', last_name: 'Wilson' }
        ],
        community:[

        ],
        bordered: true,
        small: true,
        hover: true,
        fixed: true,
        headVariant: "dark",
        tableVariant: "light",
      }
  },
  computed:{
    fetchNotice() {
      let notices = this.$store.getters.allNotice;
      let list = [];
      let idx = 0;
      for(let notice of notices){
        if(idx == 5) break;
        let obj = {
          title:notice.title.length >10?notice.title.substring(0,10)+"...":notice.title,
          content: notice.content.length >25?notice.content.substring(0,25)+"...":notice.content
        }
        list.push(obj);
        idx++;
      }
      return list;
    },
    fetchFreeboard() {
      let community = this.$store.getters.allFreeboard;
      let list = [];
      let idx = 0;
      for(let commu of community){
        if(idx == 5) break;
        let obj = {
          title:commu.title.length >10?commu.title.substring(0,10)+"...":commu.title,
          content: commu.content.length >25?commu.content.substring(0,25)+"...":commu.content
        }
        list.push(obj);
        idx++;
      }
      return list;
    },
  },
  created() {
    const temp = {
        p: 1,
        type: 0,    
        area:""    
    };
    this.$store.dispatch('getNoticeList', 1);
    this.$store.dispatch('getFreeboardList', temp);
  },
  methods: {
    click1(){
      alert("hi1")
    }
  },
}
</script>
<style scoped>
  button a{
    text-decoration: none;
    color:white;
  }
  #wrap{
    width: 100%;
    height: auto;
    background:  url("@/assets/background.webp");
    background-position: center;
    background-repeat: no-repeat;
    background-attachment: scroll;
    background-size: cover;
  }
  #sec1{
    height: 75vh;
    width: 100%;
  }
  #sec1 h1{
    padding-top: 8rem;
    font-size: 6.5rem;
    text-transform: uppercase;
    font-family: 'Modak', cursive;
    background: linear-gradient(to bottom, #fff 60%, #ffffff3d 81%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
  }
  #sec1 h2{
    font-family: 'Modak', cursive;
    color: #ffffff8c;
  }
  #sec1 button{
    background-color: #60938d;
    border: 3px solid #60938d;
    padding: 0.5rem;
  }
  #content1{
    padding: 6rem 0 0 0;
  }
  #sec2{
    background: linear-gradient(to bottom, #ffffff00 2%, #1a273ed9 33%);
    height: 70vh;
    width: 100%;
    padding-top: 10rem;
  }
  #sec2 caption{
    color:white;
  }
  #sec3{
    background-color:#17202c;
    height: 30vh;
    width: 100%;
  }
</style>