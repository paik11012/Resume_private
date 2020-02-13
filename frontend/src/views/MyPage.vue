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
            <transition name="bounce_l">
              <edu v-if="showedu" ref="reload" />
            </transition>
            <transition name="bounce">
              <awd v-if="showawd" id="rcorners2"/>
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
export default {
  name: "HomePage",
  components: {
    Navbar,
    myinfo,
    edu,
    crecarde,
    awd,
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
    upload(){
      this.$refs.reload.loadDt()
      this.showcre = !this.showcre;
    }
  },
  data() {
    return {
      drawer: null,
      showme: false,
      showedu: false,
      showcre: false,
      showawd: false,
    };
  },
};
</script>

<style lang="scss">
@import "@/assets/scss/mypage.scss";
</style>
