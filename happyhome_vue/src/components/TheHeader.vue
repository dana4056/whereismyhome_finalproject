<template>
  <!-- <div> -->
  <div>
  <b-navbar class="pl-3" style="padding-left: 10rem; padding-right: 10rem" toggleable="lg" type="light" variant="light">
    <b-navbar-brand href="/">WhereIsMyHome</b-navbar-brand>

    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" style="justify-content: space-between" is-nav>
      <b-navbar-nav>
        <b-nav-item href="/notice">공지사항</b-nav-item>
        <b-nav-item href="/qna">Q&A</b-nav-item>
        <!-- <b-nav-item href="/youtube">Youtube</b-nav-item> -->
        <b-nav-item href="/house/list"> 매물검색</b-nav-item>
        <b-nav-item href="/freeboard"> 커뮤니티</b-nav-item>
      </b-navbar-nav>

      <!-- user  -->
      <b-navbar-nav class="ml-auto" v-if="!fetchUser" id="before-login" is-nav>
        <notice-pin/>
        <b-nav-item href="/user/login">로그인</b-nav-item>
      </b-navbar-nav>

      <b-navbar-nav class="ml-auto" v-else id="after-login" is-nav>
        <notice-pin/>
        <b-nav-item href="/house/interest">나만의 지도</b-nav-item>
        <b-nav-item href="/user/mypage">마이페이지</b-nav-item>
        <b-nav-item @click="logout">로그아웃</b-nav-item>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
  </div>
</template>

<script>

import NoticePin from "@/components/notice/NoticePin.vue"

export default {
  data() {
    return {
      loginUser: {
        id: "",
        password: "",
        name: "",
        address: "",
        phone: "",
      },
    };
  },

  components: {
    NoticePin
  },

  computed: {
    fetchUser() {
      return this.$store.getters.user.id;
    },
  },

  methods: {
    logout() {
      this.$store.dispatch("userLogout");
    },
  },
};
</script>

<style scoped>
.navbar-brand{
  font-family: 'Modak', cursive;
  background: linear-gradient(to bottom, #4f5862 51%, #0404045e 52%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;

}

</style>
