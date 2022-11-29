<template>
  <div id="wrap">
    <section id="sec1">
      <div id="content1">
        <h1 class="">Where Is MyHome</h1>
        <h2 class="">SSAFY WEB PJT</h2>
        <b-button pill class="my-5"><router-link to="/house/list">아파트 매매 검색하기</router-link></b-button>
      </div>
    </section>

    <section id="sec2">
    <b-table
      :style="'display: inline-table; width:30%'"
      class="mx-3"
      :bordered=true
      :small=true
      :hover=true
      :fixed=true
      :table-variant="'light'"
      :head-variant="'dark'"
      :items="items"
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
      :fixed=true
      :table-variant="'light'"
      :head-variant="'dark'"
      :items="items"
      :fields="fields"
      caption-top
    >
      <template #table-caption>무슨 테이블일까나</template>
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
        fields: ['first_name', 'last_name', 'age'],
        items: [
          { age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
          { age: 21, first_name: 'Larsen', last_name: 'Shaw' },
          { age: 89, first_name: 'Geneva', last_name: 'Wilson' }
        ],
        bordered: true,
        small: true,
        hover: true,
        fixed: true,
        headVariant: "dark",
        tableVariant: "light",
      }
    }
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