<template>
  <div>
    <v-container>
      <v-layout>
        <v-flex>
          <template>
            <v-layout justify-space-around="true" class="titlebox">
              <div class="title-item" @click="myinfo">My info</div>
              <div class="title-item" @click="myedu">Education</div>
            </v-layout>
            <transition name="bounce">
              <myinfo id="rcorners2" v-if="showme" />
            </transition>
            <transition name="bounce">
              <edu v-if="showedu" />
            </transition>
            <transition name="bounce">
              <crecard v-if="showcre" id="rcorners2" />
            </transition>
            <v-btn
              class="insert"
              v-if="showedu"
              @click="create"
              small
              fab
              dark
              color="#F7CAC9"
              id="write"
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
import crecard from "@/components/CreateCard";
import axios from "axios";
export default {
  name: "HomePage",
  components: {
    Navbar,
    myinfo,
    edu,
    crecard
  },
  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    myinfo() {
      this.showedu = false;
      this.showcre = false;
      setTimeout(() => {
        this.showme = !this.showme;
      }, 300);
    },
    myedu() {
      this.showme = false;
      setTimeout(() => {
        this.showedu = !this.showedu;
      }, 300);
    },
    create() {
      this.showcre = !this.showcre;
    }
  },
  data() {
    return {
      drawer: null,
      showme: false,
      showedu: false,
      showcre: false
    };
  },
};
</script>

<style lang="scss">
@import "@/assets/scss/mypage.scss";
</style>
