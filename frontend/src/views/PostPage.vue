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
      <div id="posth" style="line-height:1.2em;font-size:4vw;text-align:center" slot="text">Items</div>
    </ImgBanner>

    <!-- 작성 버튼 -->
    <v-flex xs12 text-xs-center round my-5>
      <v-btn v-on:click="showPostWrite" class="mx-2" fab dark color="cyan" id="write">
        <v-icon dark>edit</v-icon>
      </v-btn>
    </v-flex>

    <template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span class="headline">Upload an item</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field v-model="title" label="Title" required></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field v-model="body" label="Content" required></v-text-field>
              </v-col>

              <v-col cols="12">
                <v-text-field v-model="created_at" label="Date" type="date" required></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="savePost">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>


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
      <!-- Post -->
      <v-layout>
        <v-flex xs12>
          <PostList :limits="6" :load-more="true"></PostList>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import PostList from "../components/PostList";
import FirebaseService from '@/services/FirebaseService'

export default {
  name: "PostPage",
  components: {
    ImgBanner,
    PostList,
  },
  data() {
    return {
      dialog: false,
      title : '',
      body : '',
      created_at : '',
      drawer: null,
      items: [
        { title: "Home", link: "/" },
        { title: "Pictures", link: "portfolio" },
        { title: "Posts", link: "post" }
      ]
    };
  },
  methods: {
    showPostWrite() {
      return this.dialog = true
    },
    savePost() {
      var title = this.title;
      var body = this.body;
      FirebaseService.postPost(title, body)
      this.dialog = false
    }
    }
  }

</script>
<style>
#write {
  margin-left: 85% !important;
}
</style>