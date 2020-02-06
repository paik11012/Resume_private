<template>
  <div>
  <transition name="slide">
  <div class="cont" oncontextmenu="return false">
    <div class="company">{{resume_company}}</div>
    <hr>
    <div class="task">{{resume_task}}</div>
    <div class="date">{{resume_date}}</div>
    <div class="label">질문</div>
    <div class="question">{{resume_question}}</div>
    <div class="label">답변</div>
    <div class="answer">{{resume_answer}}</div>
    <!-- <div class="row">
      <div class="tag">{{tag[0]}} {{tag[1]}}</div>
      <v-spacer></v-spacer>
      <div class="text_val">{{text_val}}자</div>
    </div> -->
  </div>
  </transition>
  </div>
</template>

<script>

export default {
  name: "Resume",
  props:{
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
    };
  },
  methods: {
    input(value){
      this.showmenu = value
      console.log(this.showmenu);
    }
  }

};
</script>
<style lang="scss">
  @import "@/assets/scss/mystyle.scss";
  .cont{
    @include breakpoint(sm){
      width: 60%;
    }
    @include breakpoint(xs,down){
      width: 90%;
    }
    @include breakpoint(md,up){
      width: 90%;
    }
    height: 325px;
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
    margin-bottom: 6px;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
  .answer{
    font-size: 16px;
    margin-bottom: 20px;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
  .slide{
    animation: slide_l 0.6s
  }

</style>

