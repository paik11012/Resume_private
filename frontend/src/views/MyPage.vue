<template>
  <div class="mypage">
    <v-container>
      <v-layout>
        <v-flex>
          <template>
            <v-layout justify-space-around="true" class="titlebox">
              <div class="title-item" @click="myinfo">My info</div>
              <div class="title-item" @click="myedu">Education</div>
              <div class="title-item" @click="myaward">License</div>
              <div class="title-item" @click="myexp">Experience</div>
            </v-layout>
            <transition name="bounce">
              <myinfo id="rcorners2" v-if="showme" @myinfo="mypage = true" />
            </transition>
            <transition name="bounce">
              <crecarde v-if="showcre" id="rcorners2" @create="upload"/>
            </transition>
            <transition name="bounce">
              <crecarda v-if="showcra" id="rcorners2" @createa="uploada"/>
            </transition>
            <transition name="bounce">
              <creexp v-if="showcree" id="rcorners2" @createe="uploade"/>
            </transition>
            <transition name="bounce_l">
              <edu v-if="showedu" ref="reload" />
            </transition>
            <transition name="bounce">
              <awd v-if="showawd" ref="reawd"/>
            </transition>
            <transition name="bounce">
              <exp v-if="showexp" ref="reexp"/>
            </transition>

            <v-btn
              class="insert"
              v-if="showedu"
              @click="create"
              small
              fab
              dark
              color="#F7CAC9"
            >
              <v-icon color="black">mdi-plus</v-icon>
            </v-btn>
            <v-btn
              class="insert"
              v-if="showawd"
              @click="createa"
              small
              fab
              dark
              color="#F7CAC9"
            >
              <v-icon color="black">mdi-plus</v-icon>
            </v-btn><v-btn
              class="insert"
              v-if="showexp"
              @click="createe"
              small
              fab
              dark
              color="#F7CAC9"
            >
              <v-icon color="black">mdi-plus</v-icon>
            </v-btn>
          </template>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>


<script>
import Navbar from "../components/Navbar";
import { mapGetters } from "vuex";
import myinfo from "@/components/MyInfo";
import edu from "@/components/EduList";
import crecarde from "@/components/CreateCard";
import axios from "axios";
import awd from "../components/AwardList";
import crecarda from '../components/CreateCarda'
import exp from '../components/ExperienceList'
import creexp from '../components/CreateExperience'
export default {
  name: "HomePage",
  components: {
    Navbar,
    myinfo,
    edu,
    crecarde,
    awd,
    crecarda,
    creexp,
    exp
  },
  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    myinfo() {
      this.showedu = false;
      this.showcre = false;
      this.showawd = false;
      this.showcra = false;
      this.showexp = false;
      this.showcree = false; // experience create
      setTimeout(() => {
        this.showme = !this.showme;
      }, 200);
    },
    myedu() {
      this.showcre = false;
      this.showawd = false;
      this.showme = false;
      this.showcra = false;
      this.showexp = false;
      this.showcree = false;
      setTimeout(() => {
        this.showedu = !this.showedu;
      }, 200);
      setTimeout(() => {
        if (this.$refs.reload.school < 1){
          this.showcre = true
        }
        console.log(this.$refs.reload);
      }, 250);
      
    },
    myaward(){
      this.showedu = false;
      this.showcree = false;
      this.showcre = false;
      this.showme = false;
      this.showcra = false;
      this.showexp = false;
      setTimeout(() => {
        this.showawd = !this.showawd
      }, 200);
      setTimeout(() => {
        if (this.$refs.reawd.awd < 1){
          this.showcra = true
        }
        console.log(this.$refs.reawd.awd);
        
      }, 250);
    },
    myexp(){
      this.showedu = false;
      this.showcre = false;
      this.showme = false;
      this.showcra = false;
      this.showawd = false;
      this.showcree = false;
      setTimeout(() => {
        this.showexp = !this.showexp
      }, 200);
      setTimeout(() => {
        console.log(this.$refs.reexp.exp);
        if (this.$refs.reexp.exp < 1){
          this.showcree = true
        }
      }, 250);
    },
    create() {
      this.showcre = !this.showcre;
      window.scroll(0,0)
      
    },
    createa() {
      this.showcra = !this.showcra;
      window.scroll(0,0)
    },
    upload(){
      this.$refs.reload.loadDt()
      this.showcre = !this.showcre;
    },
    uploada(){
      this.$refs.reawd.loadDt()
      this.showcra = !this.showcra;
    },
    createe() {
      this.showcree = !this.showcree
      window.scroll(0,0)
    },
    uploade(){
      this.$refs.reexp.loadDt()
      this.showcree = !this.showcree;
    },
  },
  data() {
    return {
      mypage:false,
      showcree:false,
      drawer: null,
      showme: false,
      showedu: false,
      showcre: false,
      showcra:false,
      showawd: false,
      showexp: false,
    };
  },
};
</script>

<style lang="scss">
@import "@/assets/scss/mypage.scss";
.mypage{
  margin-top: 80px;
}
</style>