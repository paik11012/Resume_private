<template>
  <div>
    <v-container>
      <v-layout>
        <v-flex>
          <template>
            <v-layout justify-space-around="true" class="titlebox">
              <div class="title-item" @click="myinfo">My info</div>
              <div class="title-item" @click="myedu">Education</div>
              <div class="title-item" @click="myaward">Award</div>

            </v-layout>
            <transition name="bounce">
              <myinfo id="rcorners2" v-if="showme" />
            </transition>
            <transition name="bounce">
              <crecarde v-if="showcre" id="rcorners2" @create="upload"/>
            </transition>
            <transition name="bounce">
              <crecarda v-if="showcra" id="rcorners2" @createa="uploada"/>
            </transition>
            <transition name="bounce_l">
              <edu v-if="showedu" ref="reload" />
            </transition>
            <transition name="bounce">
              <awd v-if="showawd" ref="reawd"/>
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
import awd from "../components/AwardList";
<<<<<<< HEAD
=======
<<<<<<< HEAD
import crecarda from '../components/CreateCarda'
=======
>>>>>>> 4ca32ce36eb0b46ba3d330617c3c36977509d545
>>>>>>> b5d960900d66d159de5d9dc0361dcce321e9016f
export default {
  name: "HomePage",
  components: {
    Navbar,
    myinfo,
    edu,
    crecarde,
    awd,
    crecarda,
  },
  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    myinfo() {
      this.showedu = false;
      this.showcre = false;
      this.showawd = false;
      setTimeout(() => {
        this.showme = !this.showme;
      }, 300);
    },
    myedu() {
      this.showcre = false;
      this.showawd = false;
      this.showme = false;
      setTimeout(() => {
        this.showedu = !this.showedu;
      }, 300);
    },
    myaward(){
      this.showedu = false;
      this.showcre = false;
      this.showme = false;
      setTimeout(() => {
        this.showawd = !this.showawd
      }, 300);
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
      this.showcra = !this.showcra;
    },
    uploada(){
      this.$refs.reawd.loadDt()
      this.showcra = !this.showcra;
    }
  },
  data() {
    return {
      drawer: null,
      showme: false,
      showedu: false,
      showcre: false,
      showcra:false,
      showawd: false,
    };
  },
};
</script>

<style lang="scss">
@import "@/assets/scss/mypage.scss";
.title-item{
  font-size:23px;
}
</style>
