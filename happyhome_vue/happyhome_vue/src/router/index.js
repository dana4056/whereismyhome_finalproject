import Vue from 'vue'
import VueRouter from 'vue-router'

import AppMain from '@/views/AppMain'
import AppHouse from '@/views/AppHouse'
import AppNotice from '@/views/AppNotice'
import AppQna from '@/views/AppQna'
import AppFreeboard from '@/views/AppFreeboard'
import AppInterest from '@/views/AppInterest'
import HomePage from '@/components/main/HomePage'
import AppUser from '@/views/AppUser'
// import HomePage from '../views/HomePage.vue'
// import HouseDealDetail from '../views/HouseDealDetail.vue'
// import NoticeList from '../views/NoticeList.vue'
// import NoticeDetail from '../views/NoticeDetail.vue'
// import NoticeModify from '../views/NoticeModify.vue'
// import InterestRegist from '../views/InterestRegist.vue'
// import InterestList from '../views/InterestList.vue'
// import HouseDealByInterest from '../views/HouseDealByInterest.vue'


import YoutubePage from '@/components/youtube/YoutubePage'

Vue.use(VueRouter)

const routes = [
  {  // 메인페이지
    path: '/',
    name: 'home',
    component: AppMain,
    children: [
      {
        path: '',
        name: 'homepage',
        component: HomePage,
      },
    ]
  },
  { // 아파트 관련 페이지
    path: '/house',
    name: 'house',
    component: AppHouse,
    redirect: '/house/list',
    children: [
      {
        path: 'list',
        name: 'house-list',
        component: () => import("@/components/house/HouseDealList")
      },
      {
        path: 'view/:no',
        name: 'house-view',
        component: () => import("@/components/house/HouseDealDetail")
      },
      {
        path: 'interest',
        name: 'house-interest',
        component: () => import("@/components/house/HouseDealByInterest")
      }
    ]
  },
  { // 공지사항 관련 페이지
    path: '/notice',
    name: 'notice',
    component: AppNotice,
    redirect: '/notice/list',
    children: [
      {
        path: 'list',
        name: 'notice-list',
        component: () => import("@/components/notice/NoticeList")
      },
      {
        path: 'create',
        name: 'notice-create',
        component: () => import("@/components/notice/NoticeCreate")
      },
      {
        path: 'view/:no',
        name: 'notice-view',
        component: () => import("@/components/notice/NoticeDetail")
      },
      {
        path: 'modify/:no',
        name: 'notice-modify',
        component: () => import("@/components/notice/NoticeModify")
      }
    ]
  },
  { // QnA 관련 페이지
    path: '/qna',
    name: 'qna',
    component: AppQna,
    redirect: '/qna/list',
    children: [
      {
        path: 'list',
        name: 'qna-list',
        component: () => import("@/components/qna/QnaList")
      },
      {
        path: 'create',
        name: 'qna-create',
        component: () => import("@/components/qna/QnaCreate")
      },
      {
        path: 'view/:no',
        name: 'qna-view',
        component: () => import("@/components/qna/QnaDetail")
      },
      {
        path: 'modify/:no',
        name: 'qna-modify',
        component: () => import("@/components/qna/QnaModify")
      }
    ]
  },


  { // 커뮤니티 관련 페이지
    path: '/freeboard',
    name: 'freeboard',
    component: AppFreeboard,
    redirect: '/freeboard/list',
    children: [
      {
        path: 'list',
        name: 'freeboard-list',
        component: () => import("@/components/freeboard/FreeboardList")
      },
      {
        path: 'create',
        name: 'freeboard-create',
        component: () => import("@/components/freeboard/FreeboardCreate")
      },
      {
        path: 'view/:no',
        name: 'freeboard-view',
        component: () => import("@/components/freeboard/FreeboardDetail")
      },
      {
        path: 'modify/:no',
        name: 'freeboard-modify',
        component: () => import("@/components/freeboard/FreeboardModify")
      }
    ]
  },

  { // 관심지역 관련 페이지
    path: '/interest',
    name: 'interest',
    component: AppInterest,
    redirect: '/interest/list',
    children: [
      {
        path: 'list',
        name: 'interest-list',
        component: () => import("@/components/interest/InterestList")
      },
      {
        path: 'create',
        name: 'interest-create',
        component: () => import("@/components/interest/InterestRegist")
      },
    ]
  },
  // { // 유저관련
  //   path: '/user',
  //   name: 'user',
  //   component: AppUser,
  //   redirect: '/user/login',
  //   children: [
  //     {
  //       path: 'login',
  //       name: 'login',
  //       component: () => import("@/components/user/LoginView")
  //     },
  //   ]
  // },

  // 로그인 관련 페이지
  {
    path: '/user',
    name: 'user',
    component: AppUser,
    children: [
      {
        // 작성폼 : 등록, 수정 
        path: 'write-form',
        name: 'user-write-form',
        component: () => import("@/components/user/UserWriteForm")
      },
      {
        // 로그인 페이지 
        path: 'login',
        name: 'user-login',
        component: () => import("@/components/user/UserLogin")
      },
      {
        // 마이페이지
        path: 'mypage',
        name: 'user-mypage',
        component: () => import("@/components/user/MyPage")
      },
      {
        // 관심지역설정
        path: 'myinterest',
        name: 'user-myinterest',
        component: () => import("@/components/user/MyInterest")
      },
      {
        // 비밀번호 찾기
        path: 'findPassword',
        name: 'find-password',
        component: () => import("@/components/user/FindPassword")
      },
    ]
  },

  // youtube
  {
    path: '/youtube',
    name: 'youtue-page',
    component: YoutubePage,
  },





  // {    path: '/about', name: 'about',   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  // }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
