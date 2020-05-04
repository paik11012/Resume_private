<template>
  <v-layout mt-5 wrap justify-start class="nav">
    <v-flex
      v-for="i in sresumes.length"
      :key="i"
      xs12
      sm12
      md6
      lg4
      xl4
    >
    <transition-group name="list" >
      <Resume 
        ref="tag"
        @del="del_detail"
        @cldt="rere"
        @reload="getResume()"
        v-bind:key="i"
        v-if="sec >= i"
        class="ma-3 layout justify-center"
        :cnt="i"
        :resume_id="sresumes[i-1].resume.id"
        :resume_company="sresumes[i-1].resume.resume_company"
        :resume_answer="sresumes[i-1].resume.resume_answer"
        :resume_question="sresumes[i-1].resume.resume_question"
        :resume_task="sresumes[i-1].resume.resume_task"
        :text_val="sresumes[i-1].text_val"
        :resume_date="sresumes[i-1].resume.resume_date"
        :tag_name="sresumes[i-1].tag_name"
        :pass="sresumes[i-1].resume.resume_pass"
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
    search:{type:String},
    keypick:{type:Number},
  },
  data() {
    return {
      searkey : ["resume_company","resume_task","resume_question","resume_answer", "resume_date"],
      tag_names:["신뢰","책임감","창의성","도전정신","혁신","열정","도덕성","전문성","글로벌","협력","지원동기","포부"],
      sresumes:[],
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
  },
  methods: {
    rere(){
    },
    del_detail(){
      var a = document.querySelector('html')
      a.style.overflowY="scroll"
      setTimeout(() => {
        this.getResume()
      }, 100);
    },

    filter(){
      const reducer = (accumulator, currentValue) => accumulator + currentValue;
      if(this.filter_tag.reduce(reducer)==0) this.sresumes = this.resumes;
      var filtering = []
      for (let i=0; i < this.filter_tag.length; i++){
        if (this.filter_tag[i]) {filtering.push(this.tag_names[i])  
        }
      }
      var rs = []
      for (let i=0; i < this.resumes.length; i++){
        var cnt = 0
        for (let j=0; j < this.resumes[i].tag_name.length; j++){
          for(let k=0; k < filtering.length; k ++){
            if(filtering[k] == this.resumes[i].tag_name[j]) cnt += 1;
          }
        }
        if (cnt == filtering.length) rs.push(this.resumes[i])
      }
      this.sresumes = rs
    },
    getResume: function() {
      API.get('/resume')
      .then(response => {
        this.resumes = response.data 
        this.sresumes = this.resumes
        this.$emit("load")
        for (let i = 0; i < this.resumes.length; i++) {
        setTimeout(() => {
          this.sec ++
        }, 100*i);
      }
      })
      .catch(error => {
      })
    }
  },
  watch:{
    search:function(val){
      var keyword = val.split(' ')
      if (keyword[0]){
        var pick = []
        for (let i=0; i < this.resumes.length; i++){
          var key = this.search.split(' ')
          var picked = 0
          for(let k=0; k < key.length; k++){
            if (picked == 0){
              for(let j=0; j < this.resumes[i]["resume"][this.searkey[this.keypick]].length; j++){
                if (this.resumes[i]["resume"][this.searkey[this.keypick]].slice(j,j+key[k].length) == key[k] & key[k] != ''){
                  picked = 1
                  pick.push(i)
                  break
                }
              }
            }
          }
        } 
        if (pick.length > 0){
          var mod_resume = []
          for(let i=0; i < pick.length; i++){
            mod_resume.push(this.resumes[pick[i]])
          }
          this.sresumes = Array.from(mod_resume)
        } else{
          this.sresumes = []
        }
      } else {
        this.sresumes = this.resumes
      }
    }
  },  
};
</script>
<style lang="scss">
@import "@/assets/scss/mystyle.scss";
</style>
