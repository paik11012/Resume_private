<template>
  <v-layout>
    <div class="back"></div>
      <div class="profile hovright" v-if="show1">
        <a href="/info" class="gradient_text_black">
          PROFILE
        </a>
      </div>
      <div class="resume hovright" v-if="show2">
        <a href="/resume" class="gradient_text_black">
          RESUMES
        </a>
      </div>
      <div class="interview hovright" v-if="show3">
        <a href="/interview" class="gradient_text_black">
          INTERVIEW
        </a>
      </div>
  </v-layout>
</template>

<style lang="scss" scoped>
@import "@/assets/scss/homepage.scss";
</style>
<script>
export default {
  data(){
    return{
      show1:false,
      show2:false,
      show3:false,
    }
  },
  mounted(){
    this.show1 = true
    setTimeout(()=>{
      this.show2 = true
    }, 300)
    setTimeout(()=>{
      this.show3 = true
    }, 600)
  }
}
</script>