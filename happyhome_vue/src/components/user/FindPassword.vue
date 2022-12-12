<template>
    <div>
        <section class="container align-middle p-5"> 
            <div class="wrap">
                <div class="form sign-up">
                    

                    <img v-bind="mainProps" rounded="circle" alt="Circle image" src="@/assets/user/email.png" width="75" height="75"/>
                    <br>
                    <br>


                    <h1 class="style-1"> 비밀번호 찾기 </h1>

                    
                    <div class="input-group">
                    <i class='bx bxs-lock-alt'></i>
                    <input type="text" id="id" name="id" v-model="id" ref="id" placeholder="Id를 입력하세요."/>
                    </div>

                    <div class="input-group">
                    <i class='bx bxs-lock-alt'></i>
                    <input type="text" id="userEmail" name="userEmail" v-model="userEmail" ref="userEmail" placeholder="E-mail을 입력하세요."/>
                    </div>

                    
                    <br>

                    <div style="display:flex">
                      <button @click="sendEMail">메일발송</button>
                    </div>
                    <p>
                    <span> Find your password. </span>
                    </p>
                </div>
            </div>
                <!-- </div> -->
            <!-- </div> -->
        <!-- </div> -->
        </section> 
    </div>
</template>


<script>

export default {
    data() {
      return {
        id: "",
        userEmail: "",
      };
    },

    methods: {
    sendEMail() {

        let err = true;
        let msg = "";
        !this.id && ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.id.focus());
        err && !this.userEmail && ((msg = "이메일을 입력해주세요"), (err = false), this.$refs.userEmail.focus());
        if(!err) this.$toast.error(msg);
        else {
            const sendmail = {
                id: this.id,
                userEmail: this.userEmail,
            }
            this.$store.dispatch('sendEMail', sendmail);
            }
        }
    }
}
</script>


<style>
.wrap{
    width: 837px;
    margin: 0 auto;
}


.flex-container {
  display: flex;
}

@import url(https://fonts.googleapis.com/css?family=Pacifico);
.style-1 {
  font-family: "Pacifico", sans-serif;
  text-transform: none!important;
}

@import url(https://fonts.googleapis.com/css?family=Oswald);
.style-3 {
  font-family: "Oswald", sans-serif;
}

.regist {
  padding: 10px;
}
.regist_form {
  text-align: left;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
input,
textarea,
.view {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: medium;
}

/* 외부 CSS */
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600&display=swap');

* {
    font-family: 'Poppins', sans-serif;
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

html,
body {
    height: 100vh;
    overflow: hidden;
}

/* .container {
    position: relative;
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
} */

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

.input-group {
    position: relative;
    width: 100%;
    margin: 1rem 0;
}

.input-group i {
    position: absolute;
    top: 50%;
    left: 1rem;

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





/* RESPONSIVE */

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


.form button {
  width:13%;
  margin:auto;
}


</style>