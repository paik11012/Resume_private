<template>
  <div>
  <div class="resumeCard layout justify-center" @click="opendetail" >
  <transition name="slide">
  <div class="full" oncontextmenu="return false">
    <img src="../assets/pass.png" class="passcheck" v-if="pass">
    <div class="company">{{(resume_company.length>10) ? resume_company.slice(0,10)+'...' : resume_company}}</div>
    <hr>
    <div class="task">{{(resume_task.length>15) ? resume_task.slice(0,15)+'...' : resume_task}}</div>
    <div class="date">{{resume_date}}</div>
    <div class="label Q">질문</div>
    <div id="r_question">{{resume_question}}</div>
    <div class="label A">답변</div>
    <div class="answer">{{resume_answer}}</div>
    <div class="text_val">{{ resume_answer.length }}자</div>
    <div class="tags">
      <ul v-if="tag_name.length > 2">
        <div> #{{tag_name[0]}}  #{{ tag_name[1] }} ... </div>
      </ul>
      <ul v-else-if="tag_name.length == 2">
        <div> #{{tag_name[0]}}  #{{ tag_name[1] }} </div>
      </ul>
      <ul v-else>
        <div> {{tag_name.length>0 ? '#'+tag_name[0] : '' }}</div>
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
    :res_pass="pass"
    @upload="hihi"
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
    cnt:{type:Number},
    resume_id:{type:Number},
    resume_company : {type: String},
    resume_answer : {type: String},
    resume_question : {type: String},
    resume_task : {type: String},
    tag_name : {type: Array},
    text_val : {type: Number},
    resume_date : {type: String},
    created_at : {type: String},
    pass : {type:Boolean},
  },
  data() {
    return {
      showmenu: false,
      rsdt:false,
      scr_cur:0,
    };
  },
  mounted(){
  },
  methods: {
    hihi(val){
      this.tag_name = val
      this.rsdt = false
      this.$emit('reload')
    },
    input(value){
      this.showmenu = value
    },
    opendetail(){
      this.rsdt = true
      this.scr_cur = window.scrollY
      this.$emit('opdt')
      var a = document.querySelector('html')
      a.style.overflowY="hidden"
    },
    closedetail(){
      this.rsdt = false
      var a = document.querySelector('html')
      a.style.overflowY="scroll"
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
  // box-shadow: 3px 3px rgb(247, 202, 201);
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
  & .stamp{
    position: absolute;
  }
  & .company{
    width: 90%;
    position: absolute;
    top:4%;
    font-family:'Nanum Square';
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
    font-family:'Nanum Square';
    top: 15%;
    font-size: 16px;
  }
  & .date{
    width: 90%;
    top: 23%;
    position: absolute;
    font-size: 13px;
    font-family:'Nanum Square';
    color: rgb(145,145,145);
    margin-bottom: 10px;
  }
  & .Q{
    width: 90%;
    top: 32%;
    position: absolute;
    font-family:'Nanum Square';
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
    font-family:'Nanum Square';
  }
  & .A{
    width: 90%;
    top: 56%;
    position: absolute;
    font-family:'Nanum Square';
    -webkit-line-clamp: 4;
    -webkit-box-orient: vertical;
  }
  & .answer{
    width: 90%;
    top: 63%;
    position: absolute;
    font-size: 15px;
    margin-bottom: 10px;
    font-family:'Nanum Square';
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
    font-family:'Nanum Square';
  }
  .passcheck{
    width: 30px;
    height: 30px;
    position: absolute;
    top:2%;
    left:2%;
    margin: 0;

  }
  .slide{
    animation: slide_l 0.6s
  }
}
</style>


