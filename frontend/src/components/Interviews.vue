<template>
  <div>
  <div class="interviewCard layout justify-center" :class="{non_scroll:idt}" @click="opendetail">
  <transition name="slide">
  <div class="contain" oncontextmenu="return false" @click="show">
    <p class="company">{{interview_company.length>10 ? interview_company.slice(0,10)+'...': interview_company }}</p>
    <hr class="hr">
    <p class="task">{{interview_task.length >15 ? interview_task.slice(0,15)+'...' : interview_task}}</p>
    <p class="date">{{interview_date}}</p>
    <p class="label que">질문</p>
    <p id="question">{{interview_question}}</p>
    <p class="label anw">답변</p>
    <p class="myans">{{interview_answer}}</p>
    <p class="label mem">Memo</p>
    <p class="editans">{{interview_memo}}</p>
    </div>
    </transition>
  </div>
  <InterviewDetail v-if="idt"
    :company="interview_company"
    :task="interview_task"
    :date="interview_date"
    :question="interview_question"
    :answer="interview_answer"
    :memo="interview_memo"
    :interview_id="interview_id"
    @clsid="closedetail"
    @deleteinterview="reload"
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
    interview_id:{type:Number},
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
      scrc:0,
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
    },
    reload(){
      this.idt=false
      this.$emit('del')
    },
  }

};
</script>
<style lang="scss">
  @import "@/assets/scss/mystyle.scss";
  .contain{
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
    
    height: 400px;
    /* border: 1px solid ; */
    text-align: center;
    // border: 2px solid #92a8d1;
    border: 2px solid #f7cac9;
    border-radius: 10px; 
    font-size: 13px;
    // box-shadow: 3px 3px rgb(247, 202, 201);
    padding: 5%;
    background: white;
  }
  .hr{
    position: absolute;
    width:90%;
    top:11%
  }
  .que{
    position: absolute;
    width: 89%;
    top: 26%;
  }
  .anw{
    position: absolute;
    width: 89%;
    top:47%;
  }
  .company{
    position: absolute;
    font-size: 24px;
    top:3%;
    width: 89%;
    text-align: center;
    margin-bottom: 2px;
  }
  .task{
    position: absolute;
    width: 89%;
    font-size: 16px;
    top:13%;
  }
  .date{
    position: absolute;
    font-size: 13px;
    width: 89%;
    top:19%;
    color: rgb(145,145,145);
  }
  #question{
    position: absolute;
    width: 89%;
    font-size: 18px;
    top: 31%;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
  .myans{
    position: absolute;
    width: 89%;
    top: 52%;
    font-size: 16px;
    display: -webkit-box;
<<<<<<< HEAD
    -webkit-line-clamp: 2;
=======
    -webkit-line-clamp: 3;
>>>>>>> temp
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
  .editans{
    position: absolute;
    width: 89%;
    top:78%;
    font-size: 16px;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
  .mem{
    position: absolute;
    width: 89%;
    top:73%;
  }

</style>

