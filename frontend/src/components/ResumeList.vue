<template>
  <v-layout mt-5 wrap justify-space-around>
    <v-flex
      v-for="i in resumes.length > lim ? lim : resumes.length"
      :key="i"
      xs12
      sm12
      md6
      lg4
      xl3
    >
    <transition-group name="list">
      <Resume
        v-bind:key="i"
        v-if="sec>=i"
        class="layout justify-center ma-3"
        :company="resumes[i - 1].company"
        :answer="resumes[i - 1].answer"
        :question="resumes[i - 1].question"
        :tag="resumes[i - 1].tag.split(',')"
        :task="resumes[i - 1].task"
        :text_val="resumes[i - 1].text_val"
        :date="resumes[i - 1].date"
        :created_at="resumes[i - 1].created_at.toString()"
        
      ></Resume>
    </transition-group>
    </v-flex>

    <v-flex xs12 text-xs-center round my-5 v-if="loadMore">
      <v-btn color="info" v-if="resumes.length > lim" dark v-on:click="loadMorePortfolios()">
        <!-- lim은 4 8 12 이런 식으로 커지고 length는 6으로 일정하다 -->
        <v-icon size="25" class="mr-2">fa-plus</v-icon>more
      </v-btn>
    </v-flex>
  </v-layout>
</template>
<script>
import Resume from "@/components/Resumes";
import FirebaseService from "@/services/FirebaseService";

export default {
  name: "ResumeList",
  props: {
    limits: { type: Number, default: 6 },
    loadMore: { type: Boolean, default: false }
  },
  data() {
    return {
      resumes: [],
      lim : this.limits,
      sec : 0,
    };
  },
  components: {
    Resume
  },
  mounted() {
    // console.log(this.resumes);
    this.getResume()
  },
  methods: {
    async getResume() {
      this.resumes = await FirebaseService.getResume();
      this.$emit('load')
      for (let i = 0; i < this.resumes.length; i++) {
              setTimeout(() => {
                this.sec ++
                console.log(this.sec);
              }, 100*i);
            }
      console.log(this.resumes);
    },
  }
};
</script>
<style lang="scss">
@import "@/assets/scss/mystyle.scss";
.mw-700 {
  max-width: 700px;
  margin: auto;
}

</style>
