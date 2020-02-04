<template>
  <div>
    <v-btn
      v-if="$vuetify.breakpoint.xs"
      @click.stop="drawer = !drawer"
      class="mx-2;"
      fab
      dark
      color="indigo"
      style="position:fixed;z-index:1;margin-top:50px"
    >
      <v-icon dark>menu</v-icon>
    </v-btn>

    <v-navigation-drawer v-model="drawer" absolute temporary>
      <v-list dense>
        <!-- <v-list-item v-for="item in items" :key="item.title" link="item.link">
          <v-list-item-content>
            <a href="${item.link}"><v-list-item-title>{{ item.title }}</v-list-item-title></a>
          </v-list-item-content>
        </v-list-item>-->

        <v-list-item>
          <v-list-item-content>
            <a href="/" style="margin-top:15px;">
              <v-list-item-title style="font-size:15px; color:black;">Home</v-list-item-title>
            </a>
            <a href="/portfolio" style="margin-top:15px;">
              <v-list-item-title style="font-size:15px; color:black;">Pictures</v-list-item-title>
            </a>
            <a href="/post" style="margin-top:15px;">
              <v-list-item-title style="font-size:15px; color:black;">Items</v-list-item-title>
            </a>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-container>
      <!-- About Me -->
      <!-- 모바일 사이즈인 경우 -->
      <v-layout my-5 v-if="$vuetify.breakpoint.xs" justify-center>
        <v-flex xs8>
          <a href="/info">
            <h2 class="headline my-5 text-xs-center" style="color:black">About Me</h2>
          </a>

          <p class="mr-4" style="text-align:center">
            안녕하세요, 숲속의람쥐 구독자 여러분!
            <br />람쥐와 토순이 그리고 다른 고양이들의 사진을 모아 놓은 홈페이지입니다 :) 제가 찍은 사진 중 기억하고 싶은 사진을 저장해 놓았습니다.
            앞으로도 귀여운 냥이들 많이 사랑해주세용!!!
          </p>
          <div class="my-2">
            <v-btn
              text
              href="https://m.youtube.com/channel/UCmNjgKuiiZr3MjBriGqVc_Q/featured"
              target="_blank"
            >Youtube</v-btn>
          </div>
        </v-flex>
      </v-layout>
      <!-- 웹사이즈인 경우 -->
      
      <v-layout my-5 v-else>
        <h3>{{ this.date }}</h3>
        <!-- <h5>https://codepen.io/shadeed/pen/JbjeKx</h5> -->
        <v-flex column>
          <a href="/info">
            <h2 style="color:black; margin-top:7%" 
            @mouseover="isHovering = true"
            @mouseout="isHovering = false">Profile</h2>
          </a>
          <a href="/info">
            <h2 style="color:black; ">Resumes</h2>
          </a>
          <a href="/info">
            <h2 style="color:black; ">Interviews</h2>
          </a>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import PortfolioList from "../components/PortfolioList";
import PostList from "../components/PostList";
import RepositoryList from "../components/RepositoryList";
import Navbar from "../components/Navbar";

export default {
  name: "HomePage",
  components: {
    PortfolioList,
    PostList,
    RepositoryList
  },
  methods: {
    getImgUrl(img) {
      return require("../assets/" + img);
    }
  },
  data() {
    return {
      drawer: null,
      items: [
        { title: "Home", link: "/" },
        { title: "Pictures", link: "portfolio" },
        { title: "Posts", link: "post" }
      ],
      isHovering: false,
      date: this.date
    }
  },
  mounted(){
    var date = new Date();
    console.log(date)
    // 나중에 날짜 받아오는 것 만들기
  }
  
};
</script>

<style lang="scss" scoped>
a {
  text-decoration: none;
}
h2{
  margin-top:5px;
  font-family: 'Arial';
  font-size: 6vw;
  margin-left: 50%;
  display: inline-block;
  text-transform: uppercase;
}
#one_image {
  width: 70%;
  margin-top: 10%;
  margin-left:10%;
}
h2:hover {
  position: relative;
  display: inline-block;
  
  &:before {
    position: absolute;
    left: 0;
    top: -10%;
    width: 100%;
    height: 120%;
    background: #fff;
    filter: blur(10px);
    content: "";
    opacity: 0;
    animation: flash .5s ease-out alternate infinite;
  }
}

@keyframes flash{
  to {
    opacity: 1;
  }
}

@keyframes flashText {
  to {
    opacity: 0.15;
  }
}

</style>
