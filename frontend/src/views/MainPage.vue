<template >
  <div
    id="main"
    class=" align-center justify-center row fill-height"
  >
  <div class="back" v-if="backon | loginModalOpen | signupModalOpen" @mouseover="back"></div>
  <p class="logbtn" v-if="logc" @mouseover="logpic" @click="openLoginModal">Login</p>
  <p class="logbtnon" v-else @mouseout="back" @click="openLoginModal">Login</p>
  <LoginModal v-model="loginModalOpen"></LoginModal>
        
  <p class="signbtn" v-if="signc" @mouseover="signpic" @click="openSignupModal">Sign Up</p>
  <p class="signbtnon" v-else @mouseout="back" @click="openSignupModal">Sign Up</p>
  <SignupModal v-model="signupModalOpen"></SignupModal>

  </div>
</template>

<script>
import LoginModal from '../components/LoginModal.vue'
import SignupModal from '../components/SignupModal.vue'

export default {
  components: {
    LoginModal,
    SignupModal
    },
  data() {
    return {
      loginModalOpen: false,
      signupModalOpen: false,
      backon:false,
      logc: false,
      signc: false,
    };
  },
  mounted(){setTimeout(() => {
    this.signc = true
  }, 700);
    setTimeout(() => {
      this.logc = true
    }, 200);
  },
  methods: {
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

<style lang="scss" scoped>
@import "@/assets/scss/mainpage.scss"
</style>
