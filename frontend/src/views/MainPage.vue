<template >
  <div class="full layout col align-center" :class="{'justify-end' : phone, 'justify-center' : !phone}">
    <div style="width:75%">
      <div class="{layout justify-start:!phone}">
  <transition name="fadein">
  <div class="back" v-if="backon | loginModalOpen | signupModalOpen" @mouseover="back"></div>
  </transition>
  <div class="locset_log" v-if="logc">
  <p class="logbtn main_p"  @mouseover="logpic" @click="openLoginModal">Login</p>
  </div>
  <div class="locset_log" v-else>
  <p class="logbtnon main_p"  @mouseout="back" @click="openLoginModal">Login</p>
  </div>
      </div>
  <LoginModal v-model="loginModalOpen" @close="openLoginModal"></LoginModal>
  <div class="layout justify-end">
  <div class="locset_sign" v-if="signc & phone">
  <p class="signbtn main_p"  @mouseover="signpic" @click="openSignupModal">Sign<br>Up</p>
  </div>
  <div class="locset_sign" v-else-if="phone">
  <p class="signbtnon main_p"  @mouseout="back" @click="openSignupModal">Sign<br>Up</p>
  </div>

  <div class="locset_sign" v-if="signc & !phone">
  <p class="signbtn main_p"  @mouseover="signpic" @click="openSignupModal">Sign Up</p>
  </div>
  <div class="locset_sign" v-else-if="!phone">
  <p class="signbtnon main_p"  @mouseout="back" @click="openSignupModal">Sign Up</p>
  </div>
  </div>
  <SignupModal v-model="signupModalOpen"></SignupModal>
  </div>
    </div>
</template>

<script>
import LoginModal from '../components/LoginModal.vue'
import SignupModal from '../components/SignupModal.vue'

export default {
  components: {
    LoginModal,
    SignupModal,
    },
  data() {
    return {
      loginModalOpen: false,
      signupModalOpen: false,
      backon:false,
      logc: false,
      signc: false,
      phone:false,
    };
  },
  created(){
    window.addEventListener("resize",this.resizing);
  },
  destroyed(){
    window.removeEventListener("resize",this.resizing);
  },
  mounted(){
    console.log(this.phone);
    
    if(document.body.offsetWidth < 480){
      this.phone = true}
    setTimeout(() => {
    this.signc = true
  }, 700);
    setTimeout(() => {
      this.logc = true
    }, 200);
  },
  methods: {
    resizing(e){
      // console.log(e.target.window.innerWidth);
      if (e.target.window.innerWidth < 480){
        this.phone = true
      } else {
        this.phone = false
      }
    }
    ,
    openLoginModal() {
      this.loginModalOpen = !this.loginModalOpen;
    },
    openSignupModal() {
      this.signupModalOpen = !this.signupModalOpen;
    },
    logpic(){
      this.backon = true
      setTimeout(() => {
        this.logc = false
      }, 300);

      
    },
    signpic(){
      this.backon = true
      setTimeout(() => {
        this.signc = false
      }, 300);
    },
    back(){
      setTimeout(() => {
        this.background = ''
        this.color = ''
        this.signc = true
        this.logc = true
      }, 100);
      this.backon = false;
    }
  }
};
</script>

<style lang="scss">
@import "@/assets/scss/mainpage.scss"
</style>
