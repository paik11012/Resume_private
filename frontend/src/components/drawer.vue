<template>
  <div>
    <v-app-bar-nav-icon class="drw_btn" dark @click="show=!show" v-if="!show"></v-app-bar-nav-icon>
    <div v-if="show" class="drwback" @click="close"></div>
      <div id="drawer">
      <transition name="slide_f_l">
        <v-list id="drwlist" v-if="show">
          <a href="/home">
            <v-list-item>
              <v-list-item-action>
                <v-icon>home</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Home</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </a>
          <a href="/info">
            <v-list-item>
              <v-list-item-action>
                <v-icon>thumb_up</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Profile</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </a>
          <a href="/resume">
            <v-list-item>
              <v-list-item-action>
                <v-icon>edit</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Resumes</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </a>
          <a href="/interview">
            <v-list-item>
              <v-list-item-action>
                <v-icon>email</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Interviews</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </a>
          <a href="/team">
            <v-list-item>
              <v-list-item-action>
                <v-icon>message</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>About Us</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </a>
        </v-list>
      </transition>
      </div>
    </div>
</template>

<script>
export default {
  data(){
    return{
      show: false,
    }
  },
  methods:{
    close(){
      if(this.show){
        this.show = !this.show
      }
    }
  }
}
</script>

<style lang="scss">
  @import "@/assets/scss/drawer.scss";
</style>