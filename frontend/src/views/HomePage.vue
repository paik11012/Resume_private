<template>
  <v-layout>
    <div class="back"></div>
    <div v-if="phone">
      <div class="layout align-center hometitle profile hovright" @click="topro" v-if="show1" >
        <p class=" gradient_text_black">
          P<br>R<br>O<br>F<br>I<br>L<br>E
        </p>
      </div>
      <div class="layout align-center hometitle resume hovright" @click="tores" v-if="show2">
        <p class="gradient_text_black">
          R<br>E<br>S<br>U<br>M<br>E<br>S
        </p>
      </div>
      <div class="layout align-center hometitle interview hovright" @click="toint" v-if="show3">
        <p class="gradient_text_black">
          I<br>N<br>T<br>E<br>R<br>V<br>I<br>E<br>W
        </p>
      </div>
    </div>
    <div v-else>
      <div class="layout align-center hometitle profile hovright" v-if="show1">
        <p class=" gradient_text_black" @click="topro">
          PROFILE
        </p>
      </div>
      <div class="layout align-center hometitle resume hovright" v-if="show2">
        <p class="gradient_text_black" @click="tores">
          RESUMES
        </p>
      </div>
      <div class="layout align-center hometitle interview hovright" v-if="show3">
        <p class="gradient_text_black" @click="toint">
          INTERVIEW
        </p>
      </div>
    </div>
  </v-layout>
</template>

<style lang="scss">
@import "@/assets/scss/homepage.scss";
</style>
<script>
export default {
  created(){
    window.addEventListener("resize",this.resizing);
  },
  destroyed(){
    window.removeEventListener("resize",this.resizing);
  },
  methods:{
    resizing(e){
      if (e.target.window.innerWidth < 480){
        this.phone = true
      } else {
        this.phone = false
      }
    },
    topro(){
      location.replace('/info')
    },
    toint(){
      location.replace('/interview')
    },
    tores(){
      location.replace('/resume')
    }
  },
  data(){
    return{
      show1:false,
      show2:false,
      show3:false,
      phone:false,
    }
  },
  computed:{

  },
  mounted(){
    
    if(document.body.offsetWidth < 480){
      this.phone = true
      this.show3 = true
      setTimeout(()=>{
        this.show2 = true
      }, 200)
      setTimeout(()=>{
        this.show1 = true
      }, 400)
    } else {
      this.show1 = true
      setTimeout(()=>{
        this.show2 = true
      }, 300)
      setTimeout(()=>{
        this.show3 = true
      }, 600)
    }
  }
}
</script>