<template>
    <div id="MyInterest">
      <section class="noticeWrap container align-middle p-5">
        <h2> 관심 지역 설정 </h2>
        <div class="form-box">
            <select v-model="sido" @change="getGugun" class="form-select" id="sido">
                <option value="">시도선택</option>
                <option :key="sido.code" :value="sido.code" v-for="sido in fetchSido">
                {{ sido.name }}
                </option>
            </select>

            <select v-model="gugun" @change="getDong" class="form-select" id="gugun">
                <option value="">구군선택</option>
                <option
                :key="gugun.code"
                :value="gugun.code"
                v-for="gugun in fetchGugun"
                >
                {{ gugun.name.split(" ")[1] }}
                </option>
            </select>

            <select v-model="dong" class="form-select" id="dong">
                <option value="">동선택</option>
                <option :key="dong.code" :value="dong.code" v-for="dong in fetchDong">
                {{ dong.name.split(" ")[2] }}
                </option>
            </select>
            <a id="list-btn" @click="interestRegist" class="btn btn-primary">
                등록
            </a>
        </div>

        <interest-list :key="interestList"/>
      </section>
    </div>
</template>

<script>

import InterestList from "@/components/interest/InterestList.vue";

export default {


    data() {
        return {
            sido: "",
            gugun: "",
            dong: "",

            interestList: 0,
        }
    },

    components: {
      InterestList,
    },

    created() {
        let payload = {
        regcode: "*00000000",
        selid: "sido",
        };
        this.$store.dispatch("getArea", payload);
        // this.$store.dispatch("getInterestAreaList", this.fetchUser.id);
    },

    computed: {
    calMonth() {
      return this.year == new Date().getFullYear() ? this.nmonth : 13;
    },
    fetchSido() {
      return this.$store.state.sido;
    },
    fetchGugun() {
      return this.$store.state.gugun;
    },
    fetchDong() {
      return this.$store.state.dong;
    },
    fetchHomes() {
      return this.$store.state.homes;
    },
    fetchDeals() {
      return this.$store.state.deals;
    },
    },

  methods: {
      // 구, 동의 정보들을 가져오기
      getGugun() {
      let payload = {
          regcode: this.sido.substr(0, 2) + "*00000",
          selid: "gugun",
      };
      this.$store.dispatch("getArea", payload);
      },
      getDong() {
      let payload = {
          regcode: this.gugun.substr(0, 5) + "*",
          selid: "dong",
      };
      this.$store.dispatch("getArea", payload);
      },

      // 관심지역 등록
      interestRegist() {

        let temp = "";

        for(let item of this.fetchDong){
          if(this.dong == item.code){
            temp = "" + item.name;
          }
        }

        let interest = {
            id : this.$store.getters.user.id,
            dongCode : this.dong,
            area : temp,
        }

        console.log(interest);
        this.interestList += 1;
        this.$store.dispatch('interestRegist', interest);
        // this.$store.dispatch("getInterestAreaList", this.fetchUser.id);
        // hihi
      }


  }

}
</script>

<style>
#listBox {
  border: 1px #dfdfdf solid;
  border-radius: 8px;
  height: 70vh;
  width: 18rem;
  overflow: auto;
  text-align: left;
}
#listBox::-webkit-scrollbar {
  width: 10px;
}
#listBox::-webkit-scrollbar-thumb {
  background-color: #96969648;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}

#contentBox {
  width: 80%;
  margin: 0 auto;
  display: flex;
  flex-direction: row;
}
.form-box {
  display: flex;
  flex-direction: row;
  justify-content: center;
}
.form-select,
.btn-primary {
  margin: 13px 6px;
  width: 9.3rem;
  border-radius: 20px;
  padding-left: 20px;
  color: black;
}
table {
  background-color: rgb(52 52 52 / 59%);
  color: white;
}
.btn {
  padding: 11px;
  border-radius: 24px;
  box-shadow: 0 0.1875rem 0.1875rem 0 rgb(0 0 0 / 10%) !important;
  font-family: "Varela Round", -apple-system, BlinkMacSystemFont, "Segoe UI",
    Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
  font-size: 80%;
  text-transform: uppercase;
  letter-spacing: 0.15rem;
  border: 0;
  color: #fff;
  background-color: #64a19d;
  border-color: #64a19d;
}
.submitBtn {
  font-family: "Varela Round", -apple-system, BlinkMacSystemFont, "Segoe UI",
    Roboto, "Helvetica Neue", Arial, sans-serif, "Apple Color Emoji",
    "Segoe UI Emoji", "Segoe UI Symbol", "Noto Color Emoji";
  font-size: 80%;
  text-transform: uppercase;
  letter-spacing: 0.15rem;
  border: 0;
  color: #fff;
  background-color: #64a19d;
  border-color: #64a19d;
}

</style>