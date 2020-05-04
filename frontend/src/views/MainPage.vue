<template >
  <div
    class="full layout col align-center main"
    :class="{'justify-end' : phone, 'justify-center' : !phone}"
  >
  <a href="/intro"><img src="../assets/p.png" class="cat"></a>
  <img src="../assets/title.png" class="title">
  <div class="light_box" v-if="backon"></div>
        <transition name="fadein">
          <div class="back" v-if="backon | loginModalOpen | signupModalOpen" @mouseover="back"></div>
        </transition>
          <p class="logbtn main_p" v-if="logc" @mouseover="logpic" @click="openLoginModal">Login</p>
          <p class="logbtnon main_p" v-else @mouseout="back" @click="openLoginModal">Login</p>
      <LoginModal v-model="loginModalOpen" @close="openLoginModal"></LoginModal>
      <div>
      <p class="signbtn main_p" v-if="signc" @mouseover="signpic" @click="openSignupModal">
            {{ phone ? signn : signbr}}
          </p>
      <p class="signbtnon main_p" v-else @mouseout="back" @click="openSignupModal">
        {{ phone ? signn : signbr}}
      </p>
      <SignupModal v-model="signupModalOpen"></SignupModal>
    </div>
  </div>
</template>

<script>
import LoginModal from "../components/LoginModal.vue";
import SignupModal from "../components/SignupModal.vue";

import router from '../router'

import API from "../services/Api"

export default {
  components: {
    LoginModal,
    SignupModal
  },
  data() {
    return {
      signn:'SignUp',
      signbr:'Sign\n\Up',
      loginModalOpen: false,
      signupModalOpen: false,
      backon: false,
      logc: false,
      signc: false,
      phone: false,

      file: File,
      uploadTask: ""
    };
  },
  created() {
    window.addEventListener("resize", this.resizing);
  },
  destroyed() {
    window.removeEventListener("resize", this.resizing);
  },
  mounted() {
    var this_url = window.location.href
    var code = this_url.split('code=');
    var state = this_url.split('state=');
    if (code.length > 1){  // naver (code: O, state: O)
      var n_data = {}
      const axio_url = "/users/loginSocial"
      if(state.length > 1) {
        var codes = code[1].split('&')
        n_data= { 
          ncode : codes[0], 
          nstate : state[1]
        }
      }
      else{ // kakako (code: O, state: X)
        n_data= { 
          ncode : code[1]
        }
      }

      const storage = window.sessionStorage
      window.sessionStorage.setItem("jwt-auth-token", "");

      API.post(axio_url, n_data)
      .then(res => {
          if(res.data.status) {
              swal('로그인이 성공적으로 이루어졌습니다')
              storage.setItem('jwt-auth-token',res.headers['jwt-auth-token'])
              storage.setItem('user_id',res.data.data.user_id);
              router.push('home')
          } else {
              swal('입력 정보를 확인해주세요')
          }
      })
      .catch(error => {
          console.log(error)
          swal('입력 정보를 확인해주세요')
      })
    }

    if (document.body.offsetWidth < 480) {
      this.phone = true;
    }
    setTimeout(() => {
      this.signc = true;
    }, 700);
    setTimeout(() => {
      this.logc = true;
    }, 200);
  },
  methods: {
    resizing(e) {
      // console.log(e.target.window.innerWidth);
      if (e.target.window.innerWidth < 480) {
        this.phone = true;
      } else {
        this.phone = false;
      }
    },
    openLoginModal() {
      this.loginModalOpen = !this.loginModalOpen;
    },
    openSignupModal() {
      this.signupModalOpen = !this.signupModalOpen;
    },
    logpic() {
      this.backon = true;
      setTimeout(() => {
        this.logc = false;
        this.signc = true;
      }, 300);
    },
    signpic() {
      this.backon = true;
      setTimeout(() => {
        this.signc = false;
        this.logc = true;
      }, 300);
    },
    back() {
      setTimeout(() => {
        this.background = "";
        this.color = "";
        this.signc = true;
        this.logc = true;
      }, 100);
      this.backon = false;
    },
  },
  watch: {
  }
};
</script>

<style lang="scss">
@import "@/assets/scss/mainpage.scss";
.main{
  // .light_box{
  //   position: fixed;
  //   z-index: 999;
  //   top:30%;
  //   left:62%;
  //   background-color: white;
  //   width: 29%;
  //   height: 45%;
  //   border-radius: 10%;
  //   filter: blur(5px);
  //   animation: fadein 1s;
  // }
  .title{
    z-index: 29;
    position: fixed;
    width: 25%;
    top: 9%;
    right: 5%;
  }
  .cat{
    z-index: 29;
    position: fixed;
    width: 25%;
    top:25%;
    right:15%;
  }
  position: relative;
  & .logbtn{
    position: fixed;
    top:20%;  // 원래30
    left: 22%;
    &on{
      position: fixed;
      top:20%;
    left: 22%;
    }
  }
  & .signbtn{
    position:fixed;
    top:45%;  // 원래55
    left: 22%;
    &on{
      position: fixed;
       top:45%;
    left: 22%;
    }
  }
}
@media screen and (max-width:600px) {
  .title {
    display: none
  }
  .cat{
    top: 70% !important;
  }
}

</style>
