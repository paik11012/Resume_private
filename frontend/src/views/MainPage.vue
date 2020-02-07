<template >
  <div>
  <transition name="fadein">
  <div class="back" v-if="backon | loginModalOpen | signupModalOpen" @mouseover="back"></div>
  </transition>
  <p class="logbtn main_p" v-if="logc" @mouseover="logpic" @click="openLoginModal">Login</p>
  <p class="logbtnon main_p" v-else @mouseout="back" @click="openLoginModal">Login</p>
  <LoginModal v-model="loginModalOpen" @close="openLoginModal"></LoginModal>
        
  <p class="signbtn main_p" v-if="signc" @mouseover="signpic" @click="openSignupModal">Sign Up</p>
  <p class="signbtnon main_p" v-else @mouseout="back" @click="openSignupModal">Sign Up</p>
  <SignupModal v-model="signupModalOpen"></SignupModal>

  <div id="testt">
    <input type="file" multiple accept="image/jpeg" @change="detectFiles($event.target.files)">
    <button @click="downloadImg">download</button>
    <img src="" id="imgtag">
  </div>

  </div>
</template>

<script>
import LoginModal from '../components/LoginModal.vue'
import SignupModal from '../components/SignupModal.vue'

import { app } from '../services/FirebaseService'
import firebase, { storage } from 'firebase/app'
import 'firebase/firestore'
import 'firebase/storage'

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

      file: File,
      uploadTask: '',

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
    },


    detectFiles (fileList) {
      Array.from(Array(fileList.length).keys()).map( x => {
        // console.log(fileList[x])
        this.upload(fileList[x])
      })
    },
    upload (file) {
      this.uploadTask = firebase.storage(app).ref(file.name).put(file);
    },
    
    downloadImg(){
      var storage = firebase.storage();
      var storageRef = firebase.storage().ref();

      var gsReference = storage.refFromURL("gs://web-9to6.appspot.com/벼리.jpg")
      storageRef.child('벼리.jpg').getDownloadURL().then(function(url) {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url);

        xhr.responseType = 'blob';
        xhr.onload = function(event) {
          var blob = xhr.response;
          console.log(xhr);
          var link=document.createElement('a');
          link.href=window.URL.createObjectURL(blob);
          link.download=blob;
          link.click();
        };
        xhr.send();
      });
    }
  },
  watch: {
    uploadTask: function() {
      this.uploadTask.on('state_changed', sp => {
        this.progressUpload = Math.floor(sp.bytesTransferred / sp.totalBytes * 100)
      }, 
      null, 
      () => {
        this.uploadTask.snapshot.ref.getDownloadURL().then(downloadURL => {
          this.$emit('url', downloadURL)
        })
      })
    }
  }
};
</script>

<style lang="scss">
@import "@/assets/scss/mainpage.scss"
</style>
