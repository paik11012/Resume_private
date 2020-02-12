<template>
  <div>
  <div class="resumeCard layout justify-center" @click="opendetail" >
  <transition name="slide">
  <div class="full" oncontextmenu="return false">
    <div class="company">{{rs_cpn}}</div>
    <hr>
    <div class="task">{{rs_tsk}}</div>
    <div class="date">{{resume_date}}</div>
    <div class="label Q">질문</div>
    <div id="r_question">{{resume_question}}</div>
    <div class="label A">답변</div>
    <div class="answer">{{resume_answer}}</div>
    <div class="text_val">{{ resume_answer.length }}자</div>
    <div class="tags">
      <ul v-if="tag_name.length > 2">
        <div> #{{tag_name[0]}}  #{{ tag_name[1] }} </div>
      </ul>
      <ul v-else-if="tag_name.length == 2">
        <div> #{{tag_name[0]}}  #{{ tag_name[1] }} </div>
      </ul>
      <ul v-else>
        <div> #{{tag_name[0]}}</div>
      </ul>
    </div>
    <!-- 개인적으로 글자를 오른쪽, 맨 아래 배치하고 싶음 -->
  </div>
  </transition>
  </div>
  <rsd v-if="rsdt"
    :resume_id="resume_id"
    :company="resume_company"
    :task="resume_task"
    :date="resume_date"
    :question="resume_question"
    :answer="resume_answer"
    :tags="tag_name"
    :text_val="resume_answer.length"
    @clsrsd="closedetail"
    @deleteresume="reload"
  />
  </div>
</template>

<script>
import rsd from "@/components/ResumeDetail"

export default {
  name: "Resume",
  components:{
    rsd,
  },
  props:{
    resume_id:{type:Number},
    resume_company : {type: String},
    resume_answer : {type: String},
    resume_question : {type: String},
    resume_task : {type: String},
    tag_name : {type: Array},
    text_val : {type: Number},
    resume_date : {type: String},
    created_at : {type: String},
  },
  data() {
    return {
      showmenu: false,
      rs_cpn: this.resume_company,
      rs_ans: this.resume_answer,
      rs_qst: this.resume_question,
      rs_tsk: this.resume_task,
      rs_dat: this.resume_date,
      rsdt:false,
      scr_cur:0,
    };
  },
  mounted(){
    if (this.rs_cpn.length > 10){
      this.rs_cpn = this.rs_cpn.slice(0, 10) + "..."
    }
    if (this.rs_tsk.length > 15){
      this.rs_tsk = this.rs_tsk.slice(0,15) + '...'
    }
  },
  methods: {
    input(value){
      this.showmenu = value
      console.log(this.showmenu);
    },
    opendetail(){
      this.rsdt = true
      this.scr_cur = window.scrollY
      this.$emit('opdt')
      var a = document.querySelector('html')
      a.style.overflowY="hidden"
      console.log(this.resume_id);
    },
    closedetail(){
      var a = document.querySelector('html')
      a.style.overflowY="scroll"
      this.rsdt = false
      this.$emit('cldt')
    },
    reload(){
      this.rsdt=false
      this.$emit('del')
    },
  }
};
</script>
<style lang="scss">
@import "@/assets/scss/mystyle.scss";

.resumeCard{
  position: relative;
  @include breakpoint(sm){
    width: 60% !important;
  }
  @include breakpoint(xs,down){
    width: 90%;
  }
  @include breakpoint(md,up){
    width: 90%;
  }
  height: 325px;
  position: relative;
  /* border: 1px solid ; */
  text-align: center;
  border: 2px solid #92a8d1;
  border-radius: 10px;
  font-size: 13px;
  box-shadow: 3px 3px rgb(247, 202, 201);
  padding: 5%;
  background: white;
  &.full{
    width: 100%
  }
  & ul{
    width: 90%;
    padding: 0;
    position: absolute;
    bottom: 5%;
  }
  & .company{
    width: 90%;
    position: absolute;
    top:4%;
    font-size: 20px;
    margin-bottom: 2px;
  }
  & hr{
    position: absolute;
    width:90%;
    top: 14%;
  }
  & .task{
    width: 90%;
    position: absolute;
    top: 14%;
    font-size: 16px;
  }
  & .date{
    width: 90%;
    top: 23%;
    position: absolute;
    font-size: 13px;
    color: rgb(145,145,145);
    margin-bottom: 10px;
  }
  & .Q{
    width: 90%;
    top: 32%;
    position: absolute;
    }

  & #r_question{
    width: 90%;
    top: 39%;
    position: absolute;
    font-size: 15px;
    margin-bottom: 6px;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }

  & .A{
    width: 90%;
    top: 56%;
    position: absolute;
  }
  & .answer{
    width: 90%;
    top: 63%;
    position: absolute;
    font-size: 15px;
    margin-bottom: 10px;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
  & .text_val{
    top:5%;
    right: 2%;
    position: absolute;
    font-size: 15px;
  }
  
  
  .slide{
    animation: slide_l 0.6s
  }
}
</style>


