<template>
  <v-layout mt-5 wrap>
    <transition-group name="list">
    <v-flex
      v-for="i in resumes.length > lim ? lim : resumes.length"
      :key="i"
      xs12
      sm6
      md6
      lg4
      xl4
    >
      <Resume
        class="ma-3"
        :resume_company="resumes[i - 1].resume_company"
        :resume_answer="resumes[i - 1].resume_answer"
        :resume_question="resumes[i - 1].resume_question"
        :resume_task="resumes[i - 1].resume_task"
        :text_val="resumes[i - 1].text_val"
        :resume_date="resumes[i - 1].resume_date"        
      ></Resume>
    </v-flex>
    </transition-group>

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
// import FirebaseService from "@/services/FirebaseService";
import axios from 'axios'

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
    };
  },
  components: {
    Resume
  },
  mounted() {
    this.getResume()
  },
  methods: {
    // async getResume() {
      // this.resumes = await FirebaseService.getResume();    
    //   console.log(this.resumes);
    // },
    getResume: function() {
      axios.get('http://70.12.247.99:8080/resume',
      {headers : {'token' : window.sessionStorage.getItem("jwt-auth-token")}})
      .then(response => {
        console.log(response.data)
        this.resumes = response.data
      })
      .catch(error => {
      console.log(error)
      })
    }
  }
};
</script>
<style lang="scss">
@import "@/assets/scss/style.scss";
.mw-700 {
  max-width: 700px;
  margin: auto;
}

</style>
