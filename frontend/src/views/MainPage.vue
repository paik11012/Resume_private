<template >
  <div
    class="full layout col align-center"
    :class="{'justify-end' : phone, 'justify-center' : !phone}"
  >
    <div style="width:75%">
      <div class="{layout justify-start:!phone}">
        <transition name="fadein">
          <div class="back" v-if="backon | loginModalOpen | signupModalOpen" @mouseover="back"></div>
        </transition>
        <div class="locset_log" v-if="logc">
          <p class="logbtn main_p" @mouseover="logpic" @click="openLoginModal">Login</p>
        </div>
        <div class="locset_log" v-else>
          <p class="logbtnon main_p" @mouseout="back" @click="openLoginModal">Login</p>
        </div>
      </div>
      <LoginModal v-model="loginModalOpen" @close="openLoginModal"></LoginModal>
      <div class="layout justify-end">
        <div class="locset_sign" v-if="signc & phone">
          <p class="signbtn main_p" @mouseover="signpic" @click="openSignupModal">
            Sign
            <br />Up
          </p>
        </div>
        <div class="locset_sign" v-else-if="phone">
          <p class="signbtnon main_p" @mouseout="back" @click="openSignupModal">
            Sign
            <br />Up
          </p>
        </div>

        <div class="locset_sign" v-if="signc & !phone">
          <p class="signbtn main_p" @mouseover="signpic" @click="openSignupModal">Sign Up</p>
        </div>
        <div class="locset_sign" v-else-if="!phone">
          <p class="signbtnon main_p" @mouseout="back" @click="openSignupModal">Sign Up</p>
        </div>

        <!-- 이미지 넣기 예시 -->

        <!-- <div id="testt">
          <input
            type="file"
            multiple
            accept="image/jpeg"
            @change="detectFiles($event.target.files)"
          />
          <button @click="downloadImg">download</button>
          <img src id="imgtag" />
        </div> -->

      </div>
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
              alert('로그인이 성공적으로 이루어졌습니다')
              console.log(res.data)
              storage.setItem('jwt-auth-token',res.headers['jwt-auth-token'])
              storage.setItem('user_id',res.data.data.user_id);
              router.push('home')
          } else {
              // alert('입력 정보를 확인해주세요')
          }
      })
      .catch(error => {
          console.log(error)
          alert('입력 정보를 확인해주세요')
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
      }, 300);
    },
    signpic() {
      this.backon = true;
      setTimeout(() => {
        this.signc = false;
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
</style>
