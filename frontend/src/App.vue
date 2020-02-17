<template>
  <v-app style="background:rgb(244, 249, 255);">
    <div class="backapp" v-if="setting"></div>
    <router-view v-if="setting" class="rout"/>
    <router-view v-else/>
    <transition name="slide">
    <Navbar class="Nav" v-if="setting"/>
    </transition>
    <transition name="slide">
    <DrwBtn class="DrwBtn" v-if="setting"/>
    </transition>
  </v-app>
</template>
<script>
import Navbar from '@/components/Navbar'
import DrwBtn from '@/components/Drawer'
export default {
  name: 'App',
  components:{
    Navbar, DrwBtn
  },
  data: () => ({
    showmenu:false,
    curpath:"",
    setting:false,
  }),
  mounted(){
    this.curpath = window.location.pathname
    if (this.curpath != '/' & this.curpath != '/home'){
      this.setting = true
    }
  }
};
</script>
<style>
  .Nav{
    opacity: 0.8;
    position: fixed;
    top:15px;
    width: 100%;
    z-index: 9999999999999999999999999999999999999995;
  }
  .DrwBtn{
    z-index: 9999;
    position: fixed;
    top: 20px;
    left: 5px;
  }
  .rout{
    margin-top:80px;
  }
  .backapp{
    font-size: 25px;
    text-align: center;
    left: 0;
    top: 0;
    position:fixed;
    width:100%;
    height:100%;
    z-index: -1000;
    background: linear-gradient(115deg, rgb(247,202,201), rgb(180, 180, 185), rgb(146,168,209), rgb(230,230,230));
    filter: blur(4px);
  }
a{
  text-decoration: none;
}
</style>
