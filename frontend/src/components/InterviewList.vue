<template>
  <v-layout mt-5 wrap justify-space-around>
    <v-flex
      v-for="i in interview.length > lim ? lim : interview.length"
      :key="i"
      xs12
      sm12
      md6
      lg4
      xl3
    >
      <transition-group name="list">
        <Interview
          v-bind:key="i"
          class="layout justify-center ma-3"
          v-if="sec >= i"
          :interview_company="interview[i - 1].interview_company"
          :interview_answer="interview[i - 1].interview_answer"
          :editans="interview[i-1].editans"
          :interview_question="interview[i - 1].interview_question"
          :interview_task="interview[i - 1].interview_task"
          :interview_date="interview[i - 1].interview_date"
          :interview_memo="interview[i - 1].interview_memo"
        ></Interview>
      </transition-group>
    </v-flex>
  </v-layout>
</template>
<script>
import Interview from "@/components/Interviews";
import axios from 'axios'

export default {
  name: "InterviewList",
  props: {
    limits: { type: Number, default: 6 },
  },
  data() {
    return {
      interview: [],
      lim: this.limits,
      sec: 0
    };
  },
  components: {
    Interview
  },
  mounted() {
    // console.log(this.interview);
    this.getInterView();
  },
  methods: {
    getInterView: function() {
      axios
        .get("http://70.12.247.99:8080/interview", {
          headers: {
            token: window.sessionStorage.getItem("jwt-auth-token"),
            user_id: window.sessionStorage.getItem("user_id")
          }
        })
        .then(resopnse => {
          console.log(resopnse.data);
          this.interview = resopnse.data;
          this.$emit("load");
          for (let i = 0; i < this.interview.length; i++) {
            setTimeout(() => {
              this.sec++;
              console.log(this.sec);
            }, 100 * i);
          }
        })
        .catch(error => {
          console.log(error)
        })
    }
    // async getInterView() {
    //   console.log("이거라고?");
    //   this.interview = await FirebaseService.getInterView();
    //   this.$emit('load')
    //   console.log("인터뷰 받았어?");
    //   for (let i = 0; i < this.interview.length; i++) {
    //     setTimeout(() => {
    //       this.sec ++
    //       console.log(this.sec);
    //     }, 100*i);
    //   }
    //   console.log(this.interview);

    // },
  }
};
</script>
<style lang="scss">
.mw-700 {
  max-width: 700px;
  margin: auto;
}
</style>
