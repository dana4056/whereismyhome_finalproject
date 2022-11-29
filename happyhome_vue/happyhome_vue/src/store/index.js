import Vue from "vue";
import Vuex from "vuex";
import actions from './actions'

import createPersistedState from "vuex-persistedstate";


Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    //공지사항
    notices: [],
    notice: {},
    pinNotices: [],
    //Q&A
    qnas: [],
    qna: {},
    comments: [],
    comment: {},
    page: 0,
    qnaGood: 0,

    
    //Freeboard
    freeboards: [],
    freeboard: {},
    freeboardGood: 0,
    comments2: [],
    comment2: {},
    page2: 0,
    //지역
    sido: [],
    gugun: [],
    dong: [],
    //아파트 매매
    deals: [],
    homes: [],

    //회원
    loginUser: {
      id: "",
      name: "",
      address: "",
      phone: "",
      email: "",
    },

    //관심지역
    interestArea: [],
    //마이맵
    mymaps:[],

  },
  getters: {
    //공지사항
    allNotice(state) {
      return state.notices;
    },
    notice(state) {
      return state.notice;
    },
    allPinNotice(state) {
      return state.pinNotices;
    },
    //Q&A
    allQna(state) {
      return state.qnas;
    },
    qna(state) {
      return state.qna;
    },
    allComment(state) {
      return state.comments;
    },
    qnaGood(state) {
      return state.qnaGood;
    },

    
    //커뮤니티
    allFreeboard(state) {
      return state.freeboards;
    },
    freeboard(state) {
      return state.freeboard;
    },
    allComment2(state) {
      return state.comments2;
    },

    freeboardGood(state) {
      return state.freeboardGood;
    },
    //회원
    user(state) {
      return state.loginUser;
    },
    allInterestArea(state) {
      return state.interestArea;
    }
  },
  mutations: {
    //공지사항
    SET_NOTICE_LIST(state, list) {
      state.notices = list;
    },
    SET_NOTICE(state, notice) {
      state.notice = notice;
    },
    SET_PAGE(state, page) {
      state.page = page;
    },
    SET_PIN_NOTICE_LIST(state, data) {
      state.pinNotices = data.list;
    },
    //Q&A
    SET_QNA_LIST(state, list) {
      state.qnas = list;
    },
    SET_QNA(state, qna) {
      state.qna = qna;
    },
    SET_COMMENT_LIST(state, list) {
      state.comments = list;
    },
    SET_COMMENT(state, comment) {
      state.comment = comment;
    },
    SET_QNAGOOD(state, qnaGood) {
      state.qnaGood = qnaGood;
    },

    
    //freeboard
    SET_FREEBOARD_LIST(state, list) {
      state.freeboards = list;
    },
    SET_FREEBOARD(state, freeboard) {
      state.freeboard = freeboard;
    },
    SET_COMMENT2_LIST(state, list) {
      state.comments2 = list;
    },
    SET_COMMENT2(state, comment2) {
      state.comment2 = comment2;
    },
    SET_FREEBOARDGOOD(state, freeboardGood) {
      state.freeboardGood = freeboardGood;
    },
    //지역
    SET_SIDO(state, list) {
      state.sido = list;
    },
    SET_GUGUN(state, list) {
      state.gugun = list;
    },
    SET_DONG(state, list) {
      state.dong = list;
    },
    //아파트 매매
    SET_DEAL_LIST(state, list) {
      state.deals = list;
    },
    SET_HOME_LIST(state, list) {
      state.homes = list;
    },
    //회원
    SET_ID(state, id) {
      state.loginUser.id = id;
    },
    SET_NAME(state, name) {
      state.loginUser.name = name;
    },
    SET_LOGINUSER(state, user) {
      state.loginUser.id = user.id;
      state.loginUser.name = user.name;
      state.loginUser.address = user.address;
      state.loginUser.phone = user.phone;
      state.loginUser.email = user.email;
    },
    SET_LOGOUT(state) {
      state.loginUser.id = "";
      state.loginUser.name = "";
      state.loginUser.address = "";
      state.loginUser.phone = "";
      state.loginUser.email = "";
    },

    // 관심지역 데이터 넣기 
    SET_INTEREST_AREA(state, data) {
      state.interestArea = data;
    },
    // 마이맵
    SET_MYMAP_LIST(state, list) {
      state.mymaps = list;
    }


    // dongcode:"1153010100"
    // id:"daeun"
    // lati:"37.5090772145136"
    // longi:"126.882441341584"
    // mymapnum:46
    //        name:"대림e-편한세상7"
    // type:1
    // addr:"주소입니다"




  },
  actions,
  modules: {},

  // 새로고침해도 vuex의 값이 유지되는 설정
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
