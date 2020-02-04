<template>
  <v-layout row wrap mw-700>


	<!-- 포스트 가져오기 -->
    <v-flex
      v-for="i in posts.length > lim ? lim : posts.length"
      :class="'xs' + 12 / column"
      px-3
      :key="i"
    >
      <Post :date="posts[i - 1].created_at" :title="posts[i - 1].title" :body="posts[i - 1].body"></Post>
      <v-divider></v-divider>
    </v-flex>

	<!-- 더보기 버튼 -->
    <v-flex xs12 text-xs-center round my-5 v-if="loadMore">
      <v-btn color="info" v-if="lim < posts.length" dark v-on:click="loadMorePosts">
        <v-icon size="25" class="mr-2">fa-plus</v-icon>더 보기
      </v-btn>
    </v-flex>
  </v-layout>
</template>

<script>
import Post from "@/components/Post";
import FirebaseService from "@/services/FirebaseService";

export default {
  name: "PostList",
  props: {
    column: { type: Number, default: 1 },
    limits: { type: Number, default: 4 },
	loadMore: { type: Boolean, default: false },
	writeMore: { type: Boolean, default: false }
  },
  data() {
    return {
      posts: [],
      lim: this.limits
    };
  },
  components: {
	Post,
  },
  mounted() {
    this.getPosts();
  },
  methods: {
    async getPosts() {
      this.posts = await FirebaseService.getPosts();
    },
    loadMorePosts() {
      this.lim = this.lim + 4;
	},
  }
};
</script>
<style>
.mw-700 {
  max-width: 700px;
  margin: auto;
}
</style>
