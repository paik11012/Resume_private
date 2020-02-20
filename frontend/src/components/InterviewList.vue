<template>
  <v-layout mt-5 wrap justify-start>
    <v-flex
      v-for="i in interview.length"
      :key="i"
      xs12
      sm12
      md6
      lg6
      xl6
    >
      <transition-group name="list">
        <Interview  @del="del_detail"
        @reload="reload"
          v-bind:key="i"
          class="layout justify-center ma-3"
          v-if="sec >= i"
          :interview_id="interview[i - 1].id"
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
import API from "../services/Api"

export default {
  name: "InterviewList",
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
    del_detail(){
      console.log("삭제");
      var a = document.querySelector('html')
      a.style.overflowY="scroll"
      this.getInterView()
    },
    
    reload(){
      setTimeout(() => {
        this.getInterView()
      }, 100);
    },
    getInterView: function() {
      API.get("/interview")
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
    },
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
  },
};
</script>
<style lang="scss">
.mw-700 {
  max-width: 700px;
  margin: auto;
}

</style>
