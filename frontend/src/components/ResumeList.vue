<template>
  <v-layout mt-5 wrap justify-space-around>
    <v-flex
      v-for="i in resumes.length"
      :key="i"
      xs12
      sm12
      md6
      lg4
      xl4
    >
    <transition-group name="list" >
      <Resume
      v-bind:key="i"
      v-if="sec >= i"
        class="ma-3 layout justify-center"
        :resume_company="resumes[i - 1].resume_company"
        :resume_answer="resumes[i - 1].resume_answer"
        :resume_question="resumes[i - 1].resume_question"
        :resume_task="resumes[i - 1].resume_task"
        :text_val="resumes[i - 1].text_val"
        :resume_date="resumes[i - 1].resume_date"        
      ></Resume>
    </transition-group>
    </v-flex>
  </v-layout>
</template>
<script>
import Resume from "@/components/Resumes";
import axios from 'axios'

export default {
  name: "ResumeList",
  props: {
    limits: { type: Number, default: 6 },
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
    this.getResume()
  },
  methods: {
    // async getResume() {
      // this.resumes = await FirebaseService.getResume();    
    //   console.log(this.resumes);
    // },
    getResume: function() {
      axios.get('http://70.12.247.99:8080/resume',
      {headers : {'token' : window.sessionStorage.getItem("jwt-auth-token"),
      'user_id' : window.sessionStorage.getItem("user_id")}
      })
      .then(response => {
        console.log(response.data)
        this.resumes = response.data
        this.$emit("load")
        for (let i = 0; i < this.resumes.length; i++) {
        setTimeout(() => {
          this.sec ++
          console.log(this.sec);
        }, 100*i);
      }
      })
      .catch(error => {
      console.log(error)
      })
    }
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
