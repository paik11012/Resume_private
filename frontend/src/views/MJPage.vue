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
    <ImgBanner imgSrc="https://source.unsplash.com/5brvJbR1Pn8/1600x900">
      <div style="line-height:1.2em; text-align:center; font-size:4vw" slot="text" id="ban">
        Wherever you are,
        <br />be all there.
      </div>
    </ImgBanner>

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
            <a href="/post" style="margin-top:15px;">
              <v-list-item-title style="font-size:15px; color:black;">Posts</v-list-item-title>
            </a>
            <a href="/portfolio" style="margin-top:15px;">
              <v-list-item-title style="font-size:15px; color:black;">Portfolios</v-list-item-title>
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
          <h2 class="headline mb-3" style="text-align:center">About Me</h2>
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
        <v-flex xs8>
          <h2 class="headline mb-3">About MJ</h2>
          <p class="mr-4" id="lines">
            저는 람쥐와 토순이의 집사인 민주입니다!
            <br />요즘 코딩에 빠져서 firebase 백엔드와 vue.js 프론트엔드를 기반으로 귀여운 사진 자랑?사이트를 만들어 보았어요.
            코딩을 시작한지 얼마 안 되었지만 제가 원하는 사이트를 만들 수 있어서 좋아요. ♡
            제 깃허브도 놀러오세요!
          </p>
          <div class="my-2">
            <v-btn text href="https://github.com/paik11012" target="_blank">Github</v-btn>
          </div>
        </v-flex>
        <v-flex xs4>
          <v-img :src="getImgUrl('profile.png')" aspect-ratio="1.5" />
        </v-flex>
      </v-layout>
      <!-- 람쥐 -->
      <br>
      <br>
      <br>
      <br>
      <br>
      <v-layout my-5>
        <v-flex xs8>
          <h2 class="headline mb-3">About Ramzi</h2>
          <p class="mr-4" id="lines">
            다람쥐!
            <br />요즘 코딩에 빠져서 firebase 백엔드와 vue.js 프론트엔드를 기반으로 귀여운 사진 자랑?사이트를 만들어 보았어요.
            코딩을 시작한지 얼마 안 되었지만 제가 원하는 사이트를 만들 수 있어서 좋아요. ♡
            제 깃허브도 놀러오세요!
          </p>
        </v-flex>
        <v-flex xs4>
          <v-img :src="getImgUrl('sooni.jpg')" aspect-ratio="1.5" />
        </v-flex>
      </v-layout>

      
      <br>
      <br>

      <!-- 수니 -->
      <v-layout my-5>
        <v-flex xs8>
          <h2 class="headline mb-3">About Sooni</h2>
          <p class="mr-4" id="lines">
            토순이!
            <br />요즘 코딩에 빠져서 firebase 백엔드와 vue.js 프론트엔드를 기반으로 귀여운 사진 자랑?사이트를 만들어 보았어요.
            코딩을 시작한지 얼마 안 되었지만 제가 원하는 사이트를 만들 수 있어서 좋아요. ♡
            제 깃허브도 놀러오세요!
          </p>
        </v-flex>
        <v-flex xs4>
          <v-img :src="getImgUrl('sooni.jpg')" aspect-ratio="1.5" />
        </v-flex>
      </v-layout>

    </v-container>
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import PortfolioList from "../components/PortfolioList";
import PostList from "../components/PostList";
import RepositoryList from "../components/RepositoryList";
import Navbar from "../components/Navbar";

export default {
  name: "HomePage",
  components: {
    ImgBanner,
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
      ]
    };
  }
};
</script>

<style>
a {
  text-decoration: none;
}
</style>
