<template>
  <div id="root">
    <div id="container" class="container">
      <!-- FORM SECTION -->
      <div class="row">
        <!-- ==============================회원가입============================ -->
        <div class="col align-items-center flex-col sign-up">
          <div class="form-wrapper align-items-center">
            <div class="form sign-up">
              <div class="input-group">
                <i class='bx bxs-user'></i>
                <input v-model="Sid" ref="Sid" type="text" placeholder="아이디">
              </div>
              <div class="input-group">
                <i class='bx bx-mail-send'></i>
                <input v-model="Spassword" ref="Spassword" type="password" placeholder="비밀번호">
              </div>
              <!-- <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input v-model="Spassword" type="password" placeholder="비밀번호 확인">
              </div> -->
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input v-model="Sname" ref="Sname" type="text" placeholder="이름">
              </div>
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input v-model="Saddress" ref="Saddress" type="text" placeholder="주소">
              </div>
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input v-model="Sphone" ref="Sphone" type="text" placeholder="핸드폰 번호">
              </div>
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input v-model="Semail" ref="Semail"  type="email" placeholder="이메일">
              </div>
              <button @click="submitSignup">Sign up</button>
              <p>
                <span>
                  이미 회원이십니까?
                </span>
                <b @click="toggle" class="pointer">
                  로그인
                </b>
              </p>
            </div>
          </div>
        
        </div>
        <!-- ==============================로그인============================ -->
        <div class="col align-items-center flex-col sign-in">
          <div class="form-wrapper align-items-center">
            <div class="form sign-in">
              <div class="input-group">
                <i class='bx bxs-user'></i>
                <input name="id" ref="id" v-model="Lid" type="text" placeholder="아이디">
              </div>
              <div class="input-group">
                <i class='bx bxs-lock-alt'></i>
                <input name="password" ref="password" v-model="Lpassword" type="password" placeholder="비밀번호">
              </div>
              <button @click="submitLogin">Log In</button>
              
              <p><b> <router-link to="/user/findPassword">비밀번호 찾기</router-link></b></p>
              <p>
                <span>아직 회원이 아니십니까?? </span>
                <b @click="toggle" class="pointer">회원가입</b>
              </p>
            </div>
          </div>
          <div class="form-wrapper">
          </div>
        </div>
      </div>
      <div class="row content-row">
        <div class="col align-items-center flex-col">
          <div class="text sign-in">
            <h2>
              Welcome
            </h2>
    
          </div>
          <div class="img sign-in">
      
          </div>
        </div>
        <div class="col align-items-center flex-col">
          <div class="img sign-up">
          
          </div>
          <div class="text sign-up">
            <h2>
              Join with us
            </h2>
    
          </div>
        </div>
      </div>
    </div>
<!-- ------------------------------------------------------------------ -->
<!-- Add User Modal Window -->
    <div id="modal" :class="{ hidden:failCount < 5, active:failCount >= 5 }">
      <h2><b><i class="fa-solid fa-robot"></i> 당신은 로봇입니까?</b></h2>

      <p>{{ this.robot_title[this.rand - 1] }}</p>

      <div style="display: none">{{ temp }}</div>

      <div style="margin:30px">
        <div>
          <img
            @click="clickImg(i)" v-for="i in 3" :key="i"
            :src="require(`@/assets/areYouRobot/${rand}/${i}.jpg`)"
            :class="{ selected: isSelected[i - 1] }"/>
        </div>

        <div>
          <img @click="clickImg(i)" v-for="i in [4, 5, 6]" :key="i"
            :src="require(`@/assets/areYouRobot/${rand}/${i}.jpg`)"
            :class="{ selected: isSelected[i - 1] }"/>
        </div>
        <div>
          <img @click="clickImg(i)" v-for="i in [7, 8, 9]" :key="i"
            :src="require(`@/assets/areYouRobot/${rand}/${i}.jpg`)"
            :class="{ selected: isSelected[i - 1] }"
          />
        </div>
      </div>

      <a @click="robotSubmit" id="confirm"><span class="entypo-thumbs-up"></span>저는 로봇인가요?</a>
    </div>

  </div>
</template>

<script>
export default {
  name: "UserLogin",

  data() {
    return {
      //로그인
      Lid: "",
      Lpassword: "",
      //회원가입
      Sid: "",
      Spassword: "",
      Sname: "",
      Saddress: "",
      Sphone: "",
      Semail: "",
        // 로봇관련
      areYouRobot: false, //false여야지 로그인 가능
      failCount: 0,
      // rand: Math.floor(Math.random() * 6 + 1), //랜덤하게
      rand: 1,
      isSelected: [
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
        false,
      ],
      temp: 0,
      isSignIn:true,
      robot_title: ["소화기를 선택해주세요.","눈,코,입을 선택해주세요.", "횡단보도를 선택해주세요.","횡단보도를 선택해주세요.", "자전거를 선택해주세요.","신호등을 선택해주세요."],
      robot_answer: [new Set([4, 5, 7, 8]), new Set([4, 5, 8]), new Set([4, 5, 6]),new Set([5, 6, 7, 8, 9]),new Set([1, 2, 4, 5, 6]),new Set([2, 5]) ],
    };
  },
  mounted(){
    console.log(this.rand);
    let container = document.getElementById('container')

    container.classList.toggle('sign-in')

    setTimeout(() => {
        container.classList.add('sign-in')
      }, 200)
  },

  methods: {

    submitSignup(){
      let err = true;
      let msg = "";
      !this.Sid && ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.Sid.focus());
      err && !this.Spassword && ((msg = "비밀번호를 입력해주세요"), (err = false), this.$refs.Spassword.focus());
      err && !this.Sname && ((msg = "이름을 입력해주세요"), (err = false), this.$refs.Sname.focus());
      err && !this.Saddress && ((msg = "주소를 입력해주세요"), (err = false), this.$refs.Saddress.focus());
      err && !this.Sphone && ((msg = "전화번호를 입력해주세요"), (err = false), this.$refs.Sphone.focus());
      err && !this.Semail && ((msg = "전화번호를 입력해주세요"), (err = false), this.$refs.Semail.focus());

      if(!err) this.$toast.error(msg);
      else {
        const user = {
          id: this.Sid,
          password: this.Spassword,
          name: this.Sname,
          address: this.Saddress,
          phone: this.Sphone,
          email: this.Semail,
        }
        this.$store.dispatch('userSignUp', user); 
      }
    },
    submitLogin() {
      let err = true;
      let msg = "";

      !this.Lid &&
        ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.id.focus());
      err &&
        !this.Lpassword &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.password.focus());

      if (!err) {
        this.$toast.error(msg);
        this.failCount += 1; // 로그인 정보 제대로 입력 안했을때
      } else {
        const user = {
          id: this.Lid,
          password: this.Lpassword,
        };

        this.$store.dispatch("userLogin", user);
        this.failCount += 1; //로그인 실패시
      }
    },
    movePassword() {
      this.$router.push("/user/findPassword");
    },
    clickImg(num) {
      this.isSelected[num - 1] = !this.isSelected[num - 1];

      this.temp += 1;
    },
    robotSubmit() {
      if (this.checkAnswer()) {
        this.areYouRobot = false;
        this.$toast.success("정답입니다.");
        this.isSelected = [false,false,false,false,false,false,false,false,false];
        this.failCount = 0;
        this.rand += 1;
      } else {
        this.$toast.error("오답입니다.");
        this.isSelected = [false,false,false,false,false,false,false,false,false];
      }
    },
    checkAnswer() {
      let cnt = 0;
      for (let i = 0 ; i < 9 ; i++) {
        if(this.isSelected[i]){
          if (!this.robot_answer.at(this.rand - 1).has(i+1)){
            return false;
          }
          cnt++;
        }
      }
      console.log("cnt:", cnt);
      if(cnt != this.robot_answer.at(this.rand - 1).size){
        return false;
      }
      return true;
    },

    toggle(){
      this.isSignIn = !this.isSignIn;


      let container = document.getElementById('container')

      container.classList.toggle('sign-in')
      container.classList.toggle('sign-up')



      if(!this.isSignIn){
        setTimeout(() => {
          container.classList.add('sign-up')
        }, 200)
      }else{
        setTimeout(() => {
          container.classList.add('sign-in')
        }, 200)
      }
    }    
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600&display=swap');

/* entypo */
[class*="entypo-"]:before {
  font-family: 'entypo', sans-serif;
}
.active { display: block; }
.hidden { display: none; }

#modal {
  position: fixed;
  z-index: 100;
  top: 32%;
  left: 50%;
  margin-top: -125px;
  margin-left: -200px;
  width: 470px;
  background-color: #F3F5F4;
  -webkit-border-radius: 8px;
     -moz-border-radius: 8px;
          border-radius: 8px;
  -webkit-box-shadow: 0 0 0 999px rgba(30,65,71,0.9);
     -moz-box-shadow: 0 0 0 999px rgba(30,65,71,0.9);
          box-shadow: 0 0 0 999px rgba(30,65,71,0.9);
}
  
#modal:after {
  position: fixed;
  width: 100%;
  height: 100%;
  background-color: rgba(30,65,71,0.9);
}
  
#modal h2 {
  padding: 10px 15px;
  font-size: 20px;
  line-height: 30px;
  color: #F3F5F4;
  font-weight: 300;
  width: 100%;
  background-color: #1E4147;
  text-transform: uppercase;
  -webkit-border-radius: 6px 6px 0 0;
      -moz-border-radius: 6px 6px 0 0;
          border-radius: 6px 6px 0 0;
  
}
  
#modal .entypo-cancel {
  position: absolute;
  top: 10px;
  right: 7px;
  z-index: 200;
  padding: 5px 10px;
  font-size: 18px;
  color: #FEFEFE;
  background-color:#437356;
  -webkit-border-radius: 50%;
  -moz-border-radius: 50%;
  border-radius: 50%;
  cursor: pointer;
}
  
#modal p {
  padding: 20px;
  line-height: 20px;
  font-size: 14px;
}
  
#modal #confirm {
  display: block;
  margin: 0 auto;
  margin-bottom: 30px;
  width: 200px;
  padding: 10px 5px;
  background-color: #437356d5;
  text-align: center;
  text-decoration: none;
  color: #F3F5F4;
  -webkit-border-radius: 6px;
  -moz-border-radius: 6px;
  border-radius: 6px;
}
    
#modal #confirm:hover {
  background-color: #437356;
}
    
.entypo-thumbs-up {
    color: #F3F5F4;
    padding-right: 5px;
}


/* ==================== 로봇관련 ======================== */
#robotBox {
  width: 395px;
  margin: 0 auto;
  padding: 20px;
}
#modal img {
  width: 120px;
  cursor: pointer;
  border: 1px solid rgb(255, 255, 255);
}
#modal button {
  margin: 5px;
  padding: 6px 16px;
  font-size: 12px;
  text-decoration: none;
  color: white;
  display: inline-block;
  position: relative;
  border: 1px solid rgba(0, 0, 0, 0.21);
  border-bottom: 4px solid rgba(0, 0, 0, 0.21);
  border-radius: 10px;
  text-shadow: 0 1px 0 rgba(0, 0, 0, 0.15);
  background: rgba(102, 152, 203, 1);
  background: -moz-linear-gradient(
    top,
    rgba(102, 152, 203, 1) 0%,
    rgba(92, 138, 184, 1) 100%
  );
  background: -webkit-gradient(
    left top,
    left bottom,
    color-stop(0%, rgba(102, 152, 203, 1)),
    color-stop(100%, rgba(92, 138, 184, 1))
  );
  background: -webkit-linear-gradient(
    top,
    rgba(102, 152, 203, 1) 0%,
    rgba(92, 138, 184, 1) 100%
  );
  background: -o-linear-gradient(
    top,
    rgba(102, 152, 203, 1) 0%,
    rgba(92, 138, 184, 1) 100%
  );
  background: -ms-linear-gradient(
    top,
    rgba(102, 152, 203, 1) 0%,
    rgba(92, 138, 184, 1) 100%
  );
  background: linear-gradient(
    to bottom,
    rgba(102, 152, 203, 1) 0%,
    rgba(92, 138, 184, 1) 100%
  );
  filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#6698cb', endColorstr='#5c8ab8', GradientType=0 );
}
#modal button:active {
  background: #608fbf;
}

.selected {
  opacity: 0.5;
}


/* ================================템플릿================================ */



* {
    font-family: 'Poppins', sans-serif;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}


#root {
    height: 100vh;
    /* overflow: hidden; */
}

.container {
  margin-left: 225px;
  min-height: 100vh;
  overflow: hidden;
}

.row {
    display: flex;
    flex-wrap: wrap;
    height: 100vh;
} 

.col {
    width: 50%;
} 
.align-items-center {
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
}

.form-wrapper {
    width: 100%;
    max-width: 28rem;
}

.form {
    padding: 1rem;
    background-color: #ffffff;
    border-radius: 1.5rem;
    width: 100%;
    box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;

}
.form a{
  text-decoration: none;
  color: rgb(42, 42, 150);
}

.input-group {
    position: relative;
    width: 100%;
    margin: 1rem 0;
}

.input-group i {
    position: absolute;
    top: 50%;
    left: 1rem;
    transform: translateY(-50%);
    font-size: 1.4rem;
    color: #757575;
}

.input-group input {
    width: 100%;
    padding: 1rem 3rem;
    font-size: 1rem;
    background-color: #efefef;
    border-radius: .5rem;
    border: 0.125rem solid #ffffff;
    outline: none;
}

.input-group input:focus {
    border: 0.125rem solid #4EA685;
}

.form button {
    cursor: pointer;
    width: 100%;
    padding: .6rem 0;
    border-radius: .5rem;
    border: none;
    background-color: #4EA685;
    color: #ffffff;
    font-size: 1.2rem;
    outline: none;
}

.form p {
    margin: 1rem 0;
    font-size: .7rem;
}

.flex-col {
    flex-direction: column;
}

.pointer {
    cursor: pointer;
}
.container.sign-in .form.sign-in,
.container.sign-in .social-list.sign-in,
.container.sign-in .social-list.sign-in>div,
.container.sign-up .form.sign-up,
.container.sign-up .social-list.sign-up,
.container.sign-up .social-list.sign-up>div {
    transform: scale(1);
}

.content-row {
    position: absolute;
    top: 0;
    left: 0;
    pointer-events: none;
    z-index: 6;
    width: 100%;
}
.text {
    margin: 4rem;
    color: #ffffff;
}

.text h2 {
    font-size: 3.5rem;
    font-weight: 800;
    margin: 2rem 0;
    transition: 1s ease-in-out;
}

.text p {
    font-weight: 600;
    transition: 1s ease-in-out;
    transition-delay: .2s;
}

.img img {
    width: 30vw;
    transition: 1s ease-in-out;
    transition-delay: .4s;
}

.text.sign-in h2,
.text.sign-in p,
.img.sign-in img {
    transform: translateX(-250%);
}

.text.sign-up h2,
.text.sign-up p,
.img.sign-up img {
    transform: translateX(250%);
}

.container.sign-in .text.sign-in h2,
.container.sign-in .text.sign-in p,
.container.sign-in .img.sign-in img,
.container.sign-up .text.sign-up h2,
.container.sign-up .text.sign-up p,
.container.sign-up .img.sign-up img {
    transform: translateX(0);
}

.container::before {
    content: "";
    position: absolute;
    right: 0;
    height: 100vh;
    width: 300vw;
    transform: translate(-5%, 0);
    background-image: linear-gradient(-45deg, #4EA685 0%, #4EA685 100%);
    transition: 1s ease-in-out;
    z-index: 6;
    box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
    border-bottom-right-radius: 50vh;
    border-top-left-radius:50vh;
}

.container.sign-in::before {
    transform: translate(0, 0);
    right: 50%;
}

.container.sign-up::before {
    transform: translate(100%, 0);
    right: 55%;
}

@media only screen and (max-width: 425px) {

.container::before,
.container.sign-in::before,
.container.sign-up::before {
    height: 100vh;
    border-bottom-right-radius: 0;
    border-top-left-radius: 0;
    z-index: 0;
    transform: none;
    right: 0;
}

/* .container.sign-in .col.sign-up {
    transform: translateY(100%);
} */

.container.sign-in .col.sign-in,
.container.sign-up .col.sign-up {
    transform: translateY(0);
}

.content-row {
    align-items: flex-start !important;
}

.content-row .col {
    transform: translateY(0);
    background-color: unset;
}

.col {
    width: 100%;
    position: absolute;
    padding: 2rem;
    background-color: #ffffff;
    border-top-left-radius: 2rem;
    border-top-right-radius: 2rem;
    transform: translateY(100%);
    transition: 1s ease-in-out;
}

.row {
    align-items: flex-end;
    justify-content: flex-end;
}

.form,
.social-list {
    box-shadow: none;
    margin: 0;
    padding: 0;
}

.text {
    margin: 0;
}

.text p {
    display: none;
}

.text h2 {
    margin: .5rem;
    font-size: 2rem;
}
}
</style>