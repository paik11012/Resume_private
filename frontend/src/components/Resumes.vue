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
    <div class="text-center" style="float:center">
      <ul v-if="tag_name.length > 3">
        <li v-for="item in tag_name" v-bind:key="item.id">
          {{ ' #' + item }}
        </li>
      </ul>
      <ul v-else-if="tag_name.length == 2">
        <div> #{{tag_name[0]}}  #{{ tag_name[1] }} </div>
      </ul>
      <ul v-else>
        <div> #{{tag_name[0]}}</div>
      </ul>
    </div>
    <div id="texts" class="text-right">{{ resume_answer.length }}자</div>
    <!-- 개인적으로 글자를 오른쪽, 맨 아래 배치하고 싶음 -->
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
ul{
   list-style:none;
   padding-left:0px !important;
   }
   
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
    margin-bottom: 10px;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
  .slide{
    animation: slide_l 0.6s
  }
#texts{
  margin-right:0;
  float: right;
}
</style>

