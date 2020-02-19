<template>
  <v-app style="background:rgb(244, 249, 255);">
    <div class="backapp" v-if="setting"></div>
    <router-view @modified="editinfo" v-if="setting" ref="editdone"/>
    <!-- edifinfo 페이지에서 modified로 data보낸 것 받으면 edifinfo실행하기 -->
    <router-view @modified="editinfo" v-else ref="editdone"/>
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
    
  },
  methods: {
    editinfo(value) { // 위에서 editinfo  실행되면 data를 value로 받는다
    // refs 이용해  참조 컴포넌트의 value를 editdone.user_phone이라는 자식 컴포넌트에 할당);
      console.log(value.user_name);
      setTimeout(() => {
      this.$refs.editdone.user_phone = value.user_phone
      this.$refs.editdone.user_name = value.user_name
      this.$refs.editdone.profile_img = value.profile_img
      }, 100);
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
    z-index: 28;
  }
  .DrwBtn{
    z-index: 28;
    position: fixed;
    top: 20px;
    left: 5px;
  }
  .backapp{
    font-size: 25px;
    text-align: center;
    left: 0;
    top: 0;
    position:fixed;
    width:100%;
    height:100%;
    z-index: -1;
    background: linear-gradient(115deg, rgb(247,202,201), rgb(180, 180, 185), rgb(146,168,209), rgb(230,230,230));
    filter: blur(4px);
  }
a{
  text-decoration: none;
}

</style>
