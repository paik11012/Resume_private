<template>
  <v-app>
    <Menu v-if="show"/>
    <Navbar class="Nav"/>
    <DrwBtn class="DrwBtn"/>
    <v-content>
      <router-view class="rout"/>
    </v-content>
  </v-app>
<!-- 힘들어 -->
</template>


<script>
import Navbar from '@/components/Navbar'
import DrwBtn from '@/components/drawer'
import Menu from '@/components/Menu'
export default {
  name: 'App',
  components:{
    Navbar, DrwBtn, Menu
  },
  data: () => ({
    showmenu:false
  }),
};
</script>

<style>
  .Nav{
    opacity: 0.8;
    position: fixed;
    top:15px;
    width: 100%;
    z-index: 9995;
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
</style>
