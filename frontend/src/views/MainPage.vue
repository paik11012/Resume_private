<template >
  <div
    id="main"
    :style="{backgroundImage : `url(${backgroundUrl})`}"
    class="layout align-center justify-center row fill-height"
  >
    <v-container class="layout align-center justify-center row">
      <h2 id="title" class>Temp Title</h2>
      <br />
      <v-row class="justify-center">
        <div class="my-2" style="margin:10px">
          <v-btn tile outlined @click="showLoginModal=true">Login</v-btn>
        </div>
        <div class="my-2" style="margin:10px">
          <v-btn tile outlined><a href="/signup" style="color:black;">Sign Up</a></v-btn>
        </div>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import LoginModal from '../components/LoginModal.vue'
import backgroundUrl from "@/assets/first.png";
export default {
  components: {LoginModal : LoginModal},
  data() {
    return {
      backgroundUrl,
      showLoginModal: false
    };
  },
  methods: {
    showLoginModal() {
      this.showLoginModal = true;
    }
  }
};
</script>

<style>
#main {
  background-size: cover;
  width:100%;
}
#title {
  width: 100%;
  text-align: center;
  margin-bottom: 5%
}
</style>