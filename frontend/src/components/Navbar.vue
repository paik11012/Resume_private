<template>
  <div>
    <div id="nav">
      <v-layout class="navbar" row align-center>
          <div class="my-2">
            <a href="/home" class="navtitle">9's resume</a>
          </div>
          <v-layout row align-center justify-end>
            <div class="my-2" v-if="$vuetify.breakpoint.mdAndUp">
              <a href="/home" class="navitem">Home</a>
            </div>
            <div class="my-2" v-if="$vuetify.breakpoint.mdAndUp">
              <a href="/info" class="navitem" >Profile</a>
            </div>
            <div class="my-2" v-if="$vuetify.breakpoint.mdAndUp">  
              <a href="/resume" class="navitem">Resumes</a>
            </div>
            <div class="my-2" v-if="$vuetify.breakpoint.mdAndUp">
              <a href="/interview" class="navitem">Interviews</a>
            </div>
            <div class="my-2" v-if="$vuetify.breakpoint.mdAndUp">
              <a href="/team" class="navitem">About Us</a>
            </div>
            <div class="navitem">
            <v-icon @click="my_menu" class="nav_icon">mdi-account-circle</v-icon>
            </div>
          </v-layout>
      </v-layout>
        <div class="mymenu">
          <transition name="slide">
          <p v-if="show1"><a href="/userinfo" style="text-align:center; color:black !important; font-family: 'Fredoka One', cursive;">My Page</a></p>
          </transition>
          <transition name="slide">
          <p v-if="show2"><a href="/" @click="logout()" style="text-align:center; color:black !important; font-family: 'Fredoka One', cursive;">Logout</a></p>
          </transition>
        </div>
    </div>
  </div>
</template>

<script>
import {mapActions} from 'vuex';
export default {
  data(){
    return{
      show:false,
      show1:false,
      show2:false,
    }
  },
  methods:{
    my_menu(){
      if(this.show1 == false){
        setTimeout(()=>{
          this.show1 = !this.show1
        },100)
        setTimeout(()=>{
          this.show2 = !this.show2
        },300)
      } else {
        setTimeout(()=>{
          this.show1 = !this.show1
        },300)
        setTimeout(()=>{
          this.show2 = !this.show2
        },100)
      }
    },
    ...mapActions(["logout"])
  },
};
</script>

<style lang="scss">
  @import "@/assets/scss/navbar.scss";
</style>