<template>

  <v-layout mt-5 wrap justify-start class="nav">
    <div>{{keyword}}</div>
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
      <Resume @del="del_detail"
      v-bind:key="i"
      v-if="sec >= i"
        class="ma-3 layout justify-center"
        :resume_id="resumes[i-1].resume.id"
        :resume_company="resumes[i - 1].resume.resume_company"
        :resume_answer="resumes[i - 1].resume.resume_answer"
        :resume_question="resumes[i - 1].resume.resume_question"
        :resume_task="resumes[i - 1].resume.resume_task"
        :text_val="resumes[i - 1].text_val"
        :resume_date="resumes[i - 1].resume.resume_date"
        :tag_name="resumes[i-1].tag_name"
      ></Resume>
    </transition-group>
    </v-flex>
  </v-layout>
</template>
<script>
import Resume from "@/components/Resumes";
import API from "../services/Api"
export default {
  name: "ResumeList",
  props: {
    filter_tag:{type:Array},
    tag_name:{type:Array},
    search:{type:String},
  },
  data() {
    return {
      resumes: [],
      sec : 0,
    };
  },
  components: {
    Resume
  },
  beforeMount(){
    this.getResume()
  },
  mounted() {
    console.log(this.filter_tag)
  },
  methods: {
    del_detail(){
      console.log("화면 꺼");
      var a = document.querySelector('html')
      a.style.overflowY="scroll"
      setTimeout(() => {
        this.getResume()
      }, 100);
    },

    // async getResume() {
      // this.resumes = await FirebaseService.getResume();    
    //   console.log(this.resumes);
    // },
    filter(){
      const reducer = (accumulator, currentValue) => accumulator + currentValue;
      console.log(this.filter_tag.reduce(reducer));
      if(this.filter_tag.reduce(reducer)==0) this.getResume()
      
      // this.getResume()
      var filtering = []
      for (let i=0; i < this.filter_tag.length; i++){
        if (this.filter_tag[i]) {filtering.push(this.tag_name[i])}
      }
      var rs = []
      for (let i=0; i < this.resumes.length; i++){
        // console.log(this.resumes[i].tag_name);
        var cnt = 0
        for (let j=0; j < this.resumes[i].tag_name.length; j++){
          // console.log(this.resumes[i].tag_name[j]);
          for(let k=0; k < filtering.length; k ++){
            // console.log(filtering[k]);
            if(filtering[k] == this.resumes[i].tag_name[j]) cnt += 1;
          }
          // console.log(this.resumes[i].tag_name[j]);
        }
        if (cnt == filtering.length) rs.push(this.resumes[i])
      }
      this.resumes = rs
      // for (let i=0; i < filtering.length; i++){

      // }
    },
    getResume: function() {
      API.get('/resume')
      .then(response => {
        this.resumes = response.data 
        console.log(response.data)
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
  },
  computed:{
    keyword:function(){
      for (let i=0; i < this.resumes.length; i++){
        console.log(this.resumes[i])
        for (let j=0; j <this.resumes[i].length; j++){
          console.log(this.resumes[i][j]);
          
        }
      }
      return this.search.split(' ')
    }
  }
  
};
</script>
<style lang="scss">
@import "@/assets/scss/mystyle.scss";
</style>
