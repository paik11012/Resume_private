<template>
  <v-layout mt-5 wrap>
    <v-flex
      v-for="i in interview.length > lim ? lim : interview.length"
      :key="i"
      xs12
      sm6
      md6
      lg4
      xl3
    >
    <transition-group name="list">
      <Interview v-bind:key="i"
        class="ma-3"
        v-if="sec >= i"
        :company="interview[i - 1].company"
        :myans="interview[i - 1].myans"
        :editans="interview[i-1].editans"
        :question="interview[i - 1].question"
        :task="interview[i - 1].task"
        :date="interview[i - 1].date"
        :created_at="interview[i - 1].created_at.toString()"
      ></Interview>
    </transition-group>
    </v-flex>

    <v-flex xs12 text-xs-center round my-5 v-if="loadMore">
      <v-btn color="info" v-if="interview.length > lim" dark v-on:click="loadMorePortfolios()">
        <!-- lim은 4 8 12 이런 식으로 커지고 length는 6으로 일정하다 -->
        <v-icon size="25" class="mr-2">fa-plus</v-icon>more
      </v-btn>
    </v-flex>
  </v-layout>
</template>
<script>
import Interview from "@/components/Interviews";
import FirebaseService from "@/services/FirebaseService";

export default {
  name: "InterviewList",
  props: {
    limits: { type: Number, default: 6 },
    loadMore: { type: Boolean, default: false }
  },
  data() {
    return {
      interview: [],
      lim : this.limits,
      company:"",
      myans:"",
      editans:"",
      question:"",
      task:"",
      date:"",
      created_at:"",
      sec: 0,
    };
  },
  components: {
    Interview,
  },
  mounted() {
    // console.log(this.interview);
    this.getInterView()
  },
  methods: {
    async getInterView() {
      console.log("이거라고?");
      this.interview = await FirebaseService.getInterView();
      console.log("인터뷰 받았어?");
      for (let i = 0; i < this.interview.length; i++) {
        setTimeout(() => {
          this.sec ++
          console.log(this.sec);
        }, 100*i);
      }
      console.log(this.interview);
      
      
    },
    loadMorePortfolios() {
      this.lim = this.lim + 4
    }
  }
};
</script>
<style>
.mw-700 {
  max-width: 700px;
  margin: auto;
}

</style>
