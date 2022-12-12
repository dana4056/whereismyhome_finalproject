import http from "@/api/http";
import router from '@/router/index';

import Vue from 'vue'
import 'vue-toast-notification/dist/theme-default.css';


const regionApiURL = `https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes`;

export default {
  //======================= 공지사항 관련 ========================
  // 공지사항 리스트 가져오기
  getNoticeList({ commit }, pnum) {
    const params = {
      page: pnum,
    };

    http.get(`/notices`, { params })
      .then(({ data }) => {
        commit("SET_NOTICE_LIST", data.list);
        commit("SET_PAGE", data.pageCount);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 공지사항 하나 가져오기
  getNotice({ commit }, no) {
    http.get(`/notice/${no}`)
      .then(({ data }) => {
        commit("SET_NOTICE", data);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 공지사항 등록
  createNotice({ commit }, notice) {
    http.post(`/notice`, notice)
      .then((response) => {
        if (response.status == 200) {
          commit("SET_NOTICE", response.data);
          Vue.$toast.success("등록되었습니다. :-)");
        } else {
          Vue.$toast.error("등록에 실패하였습니다. :-(")
        }
        router.replace(`/notice`);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 공지사항 수정
  updateNotice({ commit }, notice) {
    http.put(`/notice/${notice.id}`, notice)
      .then((response) => {
        if (response.status == 200) {
          commit("SET_NOTICE", response.data);
          Vue.$toast.success("수정되었습니다. :-)")
        } else {
          Vue.$toast.error("해당 게시물이 존재하지 않습니다.")
        }
        router.replace(`/notice/view/${notice.id}`);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 공지사항 삭제
  delNotice({ commit }, id) {
    http.delete(`/notice/${id}`)
      .then((response) => {
        if (response.status == 200) {
          Vue.$toast.success("삭제되었습니다. :-)");
          commit("SET_NOTICE", {});
        } else {
          Vue.$toast.error("해당 게시물이 존재하지 않습니다.")
        }
        router.replace(`/notice/list`);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // 공지사항 pin 작업
  setPin({dispatch, commit}, notice) {
    http.put(`/notice/${notice.id}/pin`)
      .then( (response) => {
        if (response.status == 200) {
          if (response.data == "FULL") {
            Vue.$toast.warning("전체 공지사항은 3개 까지만 가능합니다.")
          }
          else if (notice.pin === 0) {
            Vue.$toast.success("전체 공지사항에 등록되었습니다. :-)");
          }
          else if(notice.pin === 1) {
            Vue.$toast.success("전체 공지사항에 해제되었습니다. :-)");
          }         
          commit("SET_NOTICE", response.data);
          dispatch("getPinNoticeList");
        }
        else {
          Vue.$toast.error("전체 공지사항 등록에 실패하였습니다.");
        }
    })
    .catch((error) => {
      console.log(error);
    });
  },

  // 공지사항 pin 가져오기
  getPinNoticeList({ commit }) {
    http.get(`/notices/pin`)
      .then(({ data }) => {
        commit("SET_PIN_NOTICE_LIST", data);
      })
      .catch((error) => {
        console.log(error);
    })
  },

  // 키워드로 공지사항 리스트 검색
  searchNoticeList({ commit }, search) {
    const params = {
      keyword: search.keyword,
      page: search.page,
    };
    http.get(`/notices/search`, { params })
      .then(({ data }) => {
        commit("SET_NOTICE_LIST", data.list);
        commit("SET_PAGE", data.pageCount);
      })
      .catch((error) => {
        console.log(error);
      });
  },


  //======================= Q&A 관련 ========================
  
  // Q&A 리스트 가져오기
  getQnaList({ commit }, pnum) {
    const params = {
      page: pnum,
    };

    http.get(`/qnas`, { params })
      .then(({ data }) => {
        commit("SET_QNA_LIST", data.list);
        commit("SET_PAGE", data.pageCount);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // Q&A 좋아요순으로 가져오기 
  getQnaListByGood({ commit }, pnum) {
    const params = {
      page: pnum,
    };

    http.get(`/qnas/good`, { params })
      .then(({ data }) => {
      commit("SET_QNA_LIST", data.list);
        commit("SET_PAGE", data.pageCount);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // Q&A 조회수순으로 가져오기 
  getQnaListByView({ commit }, pnum) {
    const params = {
      page: pnum,
    };

    http.get(`/qnas/view`, { params })
      .then( ({ data }) => {
        commit("SET_QNA_LIST", data.list);
        commit("SET_PAGE", data.pageCount);
      })
      .catch((error) => {
        console.log(error);
      });
  },


  // Q&A 하나 가져오기
  getQna({ commit }, no) {
    http.get(`/qna/${no}`)
      .then(({ data }) => {
        commit("SET_QNA", data);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // Q&A 등록
  createQna({ commit }, qna) {
    http.post(`/qna`, qna)
      .then((response) => {
        if (response.status == 200) {
          commit("SET_QNA", response.data);
          Vue.$toast.success("등록되었습니다. :-)");
        } else {
          Vue.$toast.error("등록에 실패하였습니다. :-(")
        }
        router.replace(`/qna`);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // Q&A 수정
  updateQna({ commit }, qna) {
    http.put(`/qna/${qna.id}`, qna)
      .then((response) => {
        if (response.status == 200) {
          commit("SET_QNA", response.data);
          Vue.$toast.success("수정되었습니다. :-)");
        } else {
          Vue.$toast.error("해당 게시물이 존재하지 않습니다.")
        }
        router.replace(`/qna/view/${qna.id}`);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // Q&A 삭제
  delQna({ commit }, id) {
    http.delete(`/qna/${id}`)
      .then((response) => {
        if (response.status == 200) {
          Vue.$toast.success("삭제되었습니다. :-)");
          commit("SET_QNA", {});
        } else {
          Vue.$toast.error("해당 게시물이 존재하지 않습니다.")
        }
        router.replace(`/qna/list`);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  //댓글 리스트 가져오기
  getCommentList({ commit }, id) {
    http.get(`qna/${id}/comments`)
      .then(({ data }) => {
        commit("SET_COMMENT_LIST", data);
      });
  },

  //댓글 가져오기
  getComment({ commit }, comment_no) {
    const params = {
      no: comment_no,
    };
    http.get(`qna/comment`, { params })
      .then(({ data }) => {
        commit("SET_COMMENT", data);
      });
  },

  //댓글 등록
  createComment({ dispatch }, comment) {
    http.post("qna/comment", comment)
      .then((response) => {
        if (response.status == 200) {
          Vue.$toast.success("등록이 완료되었습니다. :-)")
          dispatch("getCommentList", comment.id);
        } else {
          Vue.$toast.error("등록 처리시 문제가 발생했습니다. :-(")
        }
      });
  },

  //댓글 수정
  updateComment({ dispatch }, payload) {
    http.put(`qna/comment`, payload.list)
      .then((response) => {
        if (response.status == 200) {
          dispatch("getCommentList", payload.id);
          Vue.$toast.success("수정되었습니다. :-)")
        } else {
          Vue.$toast.error("문제가 발생했습니다. :-(")
        }
      });
  },

  //댓글 삭제
  deleteComment({ dispatch }, payload) {
    http.delete(`qna/comment/${payload.comment_no}`)
      .then((response) => {
        if (response.status == 200) {
          Vue.$toast.success("삭제가 완료되었습니다. :-)")
          dispatch("getCommentList", payload.id);
        } else {
          Vue.$toast.error("문제가 발생했습니다. :-(")
        }
      });
  },

  
  //QnA 좋아요 토글
  qnaGood({  dispatch, commit }, qna) {
    http.put(`/qna/${qna.qnaid}/good/${qna.id}`)
      .then((response) => {
        if (response.status == 200) {
          if (qna.good == 0) {
            Vue.$toast.success("좋아요가 등록되었습니다. :-)");
          } else {
            Vue.$toast.success("좋아요가 취소되었습니다. :-)");
          }
          commit("SET_QNA", response.data);
          dispatch("qnaGoodCheck", qna);
        }
        else {
          Vue.$toast.error("좋아요 등록에 실패하였습니다.");
        }
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // QnA 좋아요 여부 확인
  qnaGoodCheck({ commit }, qna) {
    http.get(`/qna/${qna.qnaid}/good/${qna.id}`)
      .then((response) => {
        if (response.status == 200) {
          commit("SET_QNAGOOD", response.data);
        } else {
          Vue.$toast.error("좋아요 확인에 실패하였습니다.");
      }
      })
      .catch((error) => {
        console.log(error);
      });
  },



  //======================= Freeboard 관련 ========================
  // Freeboard 리스트 가져오기

  getFreeboardList({ commit }, temp) {
    const params = {
      page: temp.p,
      type: temp.type,
      area: temp.area,
    };
    console.log(temp);
    http.get(`/freeboards`, { params })
      .then(({ data }) => {
        commit("SET_FREEBOARD_LIST", data.list);
        commit("SET_PAGE", data.pageCount);
      })
      .catch((error) => {
        console.log(error);
      });
  },


    // Freeboard 조회수순으로 가져오기 
    getFreeboardListByView({ commit }, temp) {
      const params = {
        page: temp.p,
        type: temp.type,
        area: temp.area
      };
      console.log(temp);
      http.get(`/freeboards/view`, { params })
        .then( ({ data }) => {
          commit("SET_FREEBOARD_LIST", data.list);
          commit("SET_PAGE", data.pageCount);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  
      // Freeboard 좋아요순으로 가져오기 
      getFreeboardListByGood({ commit }, temp) {
        const params = {
          page: temp.p,
          type: temp.type,
          area: temp.area
        };
        console.log(temp);
        http.get(`/freeboards/good`, { params })
          .then(({ data }) => {
          commit("SET_FREEBOARD_LIST", data.list);
            commit("SET_PAGE", data.pageCount);
          })
          .catch((error) => {
            console.log(error);
          });
    },
      
      
      
    //Freeboard 좋아요 토글
    freeboardGood({  dispatch, commit }, freeboard) {
      http.put(`/freeboard/${freeboard.freeboardid}/good/${freeboard.id}`)
        .then((response) => {
          if (response.status == 200) {
            if (freeboard.good == 0) {
              Vue.$toast.success("좋아요가 등록되었습니다. :-)");
            } else {
              Vue.$toast.success("좋아요가 취소되었습니다. :-)");
            }
            commit("SET_FREEBOARD", response.data);
            dispatch("freeboardGoodCheck", freeboard);
          }
          else {
            Vue.$toast.error("좋아요 등록에 실패하였습니다.");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  
    // Freeboard 좋아요 여부 확인
    freeboardGoodCheck({ commit }, freeboard) {
      http.get(`/freeboard/${freeboard.freeboardid}/good/${freeboard.id}`)
        .then((response) => {
          if (response.status == 200) {
            commit("SET_FREEBOARDGOOD", response.data);
          } else {
            Vue.$toast.error("좋아요 확인에 실패하였습니다.");
        }
        })
        .catch((error) => {
          console.log(error);
        });
    },



  // Freeboard 하나 가져오기
  getFreeboard({ commit }, no) {
    http.get(`/freeboard/${no}`)
      .then(({ data }) => {
        commit("SET_FREEBOARD", data);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // Freeboard 등록
  createFreeboard({ commit }, freeboard) {
    http.post(`/freeboard`, freeboard)
      .then((response) => {
        if (response.status == 200) {
          commit("SET_FREEBOARD", response.data);
          Vue.$toast.success("등록되었습니다. :-)");
        } else {
          Vue.$toast.error("등록에 실패하였습니다. :-(")
        }
        router.replace(`/freeboard`);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // Freeboard 수정
  updateFreeboard({ commit }, freeboard) {
    http.put(`/freeboard/${freeboard.id}`, freeboard)
      .then((response) => {
        if (response.status == 200) {
          commit("SET_FREEBOARD", response.data);
          Vue.$toast.success("수정되었습니다. :-)")
        } else {
          Vue.$toast.error("해당 게시물이 존재하지 않습니다.")
        }
        router.replace(`/freeboard/view/${freeboard.id}`);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  // Freeboard 삭제
  delFreeboard({ commit }, id) {
    http.delete(`/freeboard/${id}`)
      .then((response) => {
        if (response.status == 200) {
          Vue.$toast.success("삭제되었습니다. :-)");
          commit("SET_FREEBOARD", {});
        } else {
          Vue.$toast.error("해당 게시물이 존재하지 않습니다.")
        }
        router.replace(`/freeboard/list`);
      })
      .catch((error) => {
        console.log(error);
      });
  },

  //댓글 리스트 가져오기
  getComment2List({ commit }, id) {
    http.get(`freeboard/${id}/comments`)
      .then(({ data }) => {
        commit("SET_COMMENT2_LIST", data);
      });
  },

  //댓글 가져오기
  getComment2({ commit }, comment_no) {

    const params = {
      no: comment_no,
    };

    http.get(`freeboard/comment`, { params })
      .then(({ data }) => {
        commit("SET_COMMENT2", data);
      });
  },

  //댓글 등록
  createComment2({ dispatch }, comment) {
    http.post("/comment2", comment)
      .then((response) => {

        if (response.status == 200) {
          Vue.$toast.success("등록이 완료되었습니다. :-)")
          dispatch("getComment2List", comment.id);
        } else {
          Vue.$toast.error("등록 처리시 문제가 발생했습니다. :-(")
        }
      });
  },

  //댓글 수정
  updateComment2({ dispatch }, payload) {
    http.put(`/comment2`, payload.list)
      .then((response) => {
        if (response.status == 200) {
          dispatch("getComment2List", payload.id);
          Vue.$toast.success("수정되었습니다. :-)")
        } else {
          Vue.$toast.error("문제가 발생했습니다. :-(")
        }
      });
  },

  //댓글 삭제
  deleteComment2({ dispatch }, payload) {
    http.delete(`/comment2/${payload.comment_no}`)
      .then((response) => {
        if (response.status == 200) {
          Vue.$toast.success("삭제가 완료되었습니다. :-)")
          dispatch("getComment2List", payload.id);
        } else {
          Vue.$toast.error("문제가 발생했습니다. :-(")
        }
      });
  },


  //=====================아파트 매매 관련=======================

  //행정구역 가져오기
  getArea({ commit }, payload) {

    const params = {
      regcode_pattern: payload.regcode,
      is_ignore_zero: true,
    };

    http.get(`${regionApiURL}`, { params })
      .then(({ data }) => {
        let list = data.regcodes;
        if (payload.selid == "sido") {
          commit("SET_SIDO", list);
        } else if (payload.selid == "gugun") {
          commit("SET_GUGUN", list);
        } else if (payload.selid == "dong") {
          commit("SET_DONG", list);
        }
      });
  },

  getDeals({ commit }, code) {
    const params = {
      apt: code,
    };

    http.get(`/deals`, { params })
      .then(({ data }) => {
        commit("SET_DEAL_LIST", data);
      }).catch((error) => {
        console.log(error)
      });
  },

  // getHomes({ dispatch }, dong) {
  getHomes({ commit }, dong) {
    
    const params = {
      code: dong,
    };

    http.get(`/homes`, { params })

      .then(({ data }) => {

        commit("SET_HOME_LIST", data);

      });
  },



  //======================관심지역 관련========================
  
  // 관심지역 등록 
  interestRegist({ dispatch, commit }, interest) {
    http.post(`/interest`, interest)
      .then((response) => {
        if (response.status == 200) {
          commit("SET_INTEREST_AREA", response.data);
          Vue.$toast.success("관심 지역 등록되었습니다. :-)");
          dispatch("getInterestAreaList", interest.id);
        }
      })
      .catch(() => {
        Vue.$toast.error("관심 지역 등록에 실패하였습니다.");
      })
  },

  // 관심지역 전부 가져오기
  getInterestAreaList({ commit }, id) {
    http.get(`interests/${id}`)
      .then(({ data }) => {
        console.log(data);
        commit("SET_INTEREST_AREA", data);
      })
      .catch((error) => {
        console.log(error);
    })
  },

  // 관심지역 삭제하기
  interestDelete({dispatch}, interest) {
    http.delete(`${interest.id}/interest/${interest.dongCode}`)
      .then((response) => {
        if (response.status == 200) {
          Vue.$toast.success("삭제가 완료되었습니다. :-)");
          dispatch("getInterestAreaList", interest.id);
        } else {
          Vue.$toast.error("삭제 중 문제가 발생했습니다.");
          router.replace("/");
        }
      })
  },

  //=======================마이맵 관련===========================
  // 장소 마이맵에 추가
  addMyMap(context, place) {
  http.post(`/mymap`, place)
    .then((response) => {
      if (response.status == 200) {
        Vue.$toast.success("등록되었습니다. :-)");
        return 1;
      }
    }).catch((error) => {
      console.log(error);
    })
  },

  // 장소 마이맵에서 삭제
  delMyMap(context, params) {
    http.delete(`/mymap`,{params})
      .then((response) => {
        console.log(response);
        if (response.status == 200) {
          Vue.$toast.success("삭제되었습니다. :-)");
        }
      }).catch((error) => {
        console.log(error);
      })
  },

  // 마이맵 전체 가져오기
  getMyMapList({ commit }, params) {
    http.get(`/mymaps`, {params})
    .then(({data}) => {
      commit("SET_MYMAP_LIST", data);
    }).catch((error) => {
      console.log(error);
    })
  },




  //=======================회원 관련===========================

  userSignUp({ commit }, user) {
    http.post(`/user`, user)
      .then((response) => {
        if (response.status == 200) {
          commit("SET_USER", response.data);
          Vue.$toast.success("회원 가입 되었습니다. :-)");
          router.replace(`/`);
        }
      })
      .catch(() => {
        Vue.$toast.error("회원 가입에 실패하였습니다.");
        router.replace(`/`);
      })
  },

  userUpdate({ commit }, user) {
    http.put(`/user`, user)
      .then((response) => {
        if (response.status == 200) {
          commit("SET_LOGINUSER", user);
          Vue.$toast.success("수정되었습니다. :-)");
        }
        router.replace("/user/mypage");
      })
      .catch(() => {
        Vue.$toast.error("회원 수정에 실패하였습니다.")
        router.replace("/user/mypage");
      })
  },

  userLogin({ commit }, user) {
    http.post(`/user/login`, user)
      .then((response) => {
        if (response.status == 200) {
          commit("SET_LOGINUSER", response.data);
          Vue.$toast.success("로그인에 성공하였습니다. :-)");
          router.replace("/");
        } else {
          Vue.$toast.error("로그인에 실패하였습니다.");
          console.log("로그인실패")
        }
      })
  },

  userDel({ commit }, payload) {
    http.delete(`/user/${payload.id}`)
      .then((response) => {
        if (response.status == 200) {
          Vue.$toast.success("삭제가 완료되었습니다. :-)");
          commit("SET_LOGOUT");
        } else {
          Vue.$toast.error("삭제 중 문제가 발생했습니다.");
        }
        router.replace("/");
      })
  },

  userLogout({ commit }) {
    commit("SET_LOGOUT");
    router.replace("/");
  },

  sendEMail({ commit }, sendmail) {
    http.post(`/user/find-pw`, sendmail)
    .then((response) => {
      if (response.status == 200) {
        Vue.$toast.success("이메일 발송되었습니다. :-)");
        commit("nothing");
      } else {
        Vue.$toast.error("이메일 발송에 실패하였습니다. :-(")
      }
      router.replace(`/`);
    })
    .catch((error) => {
      console.log(error);
    });
  }
}