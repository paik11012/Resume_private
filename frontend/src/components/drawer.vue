<template>
  <div>
    <v-app-bar-nav-icon class="drw_btn" dark @click="show=!show" v-if="!show"></v-app-bar-nav-icon>
    <transition name='slide-fade'>
    <div v-if="show" class="all" @click="close">
      <div id="drawer" v-if="show">
        <v-list id="drwlist">
          <a href="/">
            <v-list-item style="margin-top:5px;">
              <v-list-item-action>
                <v-icon>home</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Home</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </a>
          <a href="/portfolio">
            <v-list-item>
              <v-list-item-action>
                <v-icon>contact_mail</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Portfolio</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </a>
          <a href="/post">
            <v-list-item>
              <v-list-item-action>
                <v-icon>contact_mail</v-icon>
              </v-list-item-action>
              <v-list-item-content>
                <v-list-item-title>Post</v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </a>
        </v-list>
      </div>
    </div>
    </transition>
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

<style>
  .all{
    position: fixed;
    height: 100%;
    width: 100%;
    top:0;
    left: 0;
  }
  .drw_btn{
    position: fixed;
  }
  #drwlist{
    position: fixed;
    height: 100%;
    top: 0;
    left: 0;
    width:200px;
    box-shadow: x-position;
  }
  .v-list-item{
    margin-top: 20px;
  }
  .slide-fade-enter-active {
    transition: all .3s ease-in;
  }
  .slide-fade-leave-active {
    transition: all .6s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }
  .slide-fade-enter, .slide-fade-leave-to
  /* .slide-fade-leave-active below version 2.1.8 */ {
    transform: translateX(-10px);
    opacity: 0;
  }

</style>