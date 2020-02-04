<template>
  <div>
    <v-container>
      <v-layout>
        <v-flex>
          <template>
            <v-layout justify-space-around="true" class="titlebox">
              <div class="title-item" @click="myinfo">My info</div>
              <div class="title-item" @click="myedu"> Education </div>
            </v-layout>
            <transition name="bounce">
              <myinfo id="rcorners2" v-if="showme"/>
            </transition>
            <transition name="bounce">
              <edu id="rcorners2" v-if="showeduh"/>
            </transition>
          </template>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>


<script>
import Navbar from "../components/Navbar"
import { mapGetters } from "vuex"
import myinfo from "@/components/MyInfo"
import edu from "@/components/EduHigh"
export default {
  name: "HomePage",
  components: {
    Navbar, myinfo, edu
  },
  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    },
    myinfo(){
      this.showeduh = false
      setTimeout(()=>{
        this.showme = !this.showme
      }, 300)
    },
    myedu(){
      this.showme = false
      setTimeout(() => {
        this.showeduh = !this.showeduh
      }, 300);
    }
  },
  data() {
    return {
      drawer: null,
      showme:false,
      showeduh:false,
    };
  },
};
</script>

<style lang="scss">
@import "@/assets/scss/mypage.scss";

</style>
