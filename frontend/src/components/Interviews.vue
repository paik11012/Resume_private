<template>
  <div>
  <div class="interviewCard layout justify-center" :class="{non_scroll:idt}" @click="opendetail">
  <transition name="slide">
  <div class="contain" oncontextmenu="return false" @click="show">
    <div class="company">{{interview_company}}</div>
    <hr>
    <div class="task">{{interview_task}}</div>
    <div class="date">{{interview_date}}</div>
    <div class="label">질문</div>
    <div class="question">{{interview_question}}</div>
    <div class="label">답변</div>
    <div class="myans">{{interview_answer}}</div>
    <!-- <div class="label">Memo</div>
    <div class="editans">{{interview_memo}}</div> -->
    </div>
    </transition>
  </div>
  <InterviewDetail v-if="idt"
    :company="interview_company"
    :task="interview_task"
    :date="interview_date"
    :question="interview_question"
    :memo="interview_momo"
    @clsid="closedetail"
   />
  </div>
</template>

<script>
import InterviewDetail from "@/components/InterviewDetail"
export default {
  name: "Interview",
  components: {
    InterviewDetail
  },
  props:{
    interview_company : {type: String},
    interview_answer : {type: String},
    editans:{type:String},
    interview_question : {type: String},
    interview_task : {type: String},
    interview_date : {type: String},
    created_at : {type: String},
    interview_memo : {type: String},
  },
  data() {
    return {
      showmenu: false,
      i_cpn: this.interview_company,
      i_ans: this.interview_answer,
      i_qst: this.interview_question,
      i_tsk: this.interview_task,
      i_dat: this.interview_date,
      i_mem: this.interview_memo,
      idt:false,
    };
  },
    mounted(){
    if (this.i_cpn.length > 10){
      this.i_cpn = this.i_cpn.slice(0, 10) + "..."
    }
    if (this.i_tsk.length > 15){
      this.i_tsk = this.i_tsk.slice(0,15) + '...'
    }
  },
  methods: {
    show(){
      console.log(InterviewModal.props.value);
      this.showmenu = true
    },
    input(value){
      this.showmenu = value
      console.log(this.showmenu);
    },
    move(){
      window.scroll(0,100)
    },
    opendetail(){
      this.scrc = window.scrollY
      this.idt = true
      this.$emit('opdt')
    },
    closedetail(){
      this.idt = false
      this.$emit('cldt')
    }
  }

};
</script>
<style lang="scss">
  @import "@/assets/scss/mystyle.scss";
  .contain{
    @include breakpoint(sm){
      width: 60%;
    }
    @include breakpoint(xs,down){
      width: 90%;
    }
    @include breakpoint(md,up){
      width: 90%;
    }
    
    height: 400px;
    /* border: 1px solid ; */
    text-align: center;
    border: 2px solid #92a8d1;
    border-radius: 10px; 
    font-size: 13px;
    box-shadow: 3px 3px rgb(247, 202, 201);
    padding: 15px;
    background: white;
  }
  .tag{
    margin-left: 20px;
    font-size: 15px;
    color: rgb(123,123,255)
  }
  .text_val{
    margin-right: 20px;
    font-size: 15px;
  }
  .company{
    font-size: 24px;
    margin-bottom: 2px;
  }
  .task{
    margin-top: 7px;  
    font-size: 16px;
    margin-bottom: 3px;
  }
  .date{
    font-size: 13px;
    color: rgb(145,145,145);
    margin-bottom: 10px;
  }
  .question{
    font-size: 18px;
    margin-bottom: 10px;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
  .myans{
    font-size: 16px;
    margin-bottom: 6px;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
  .editans{
    font-size: 16px;
    margin-bottom: 20px;
    display: -webkit-box;
    -webkit-line-clamp: 4;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
</style>

