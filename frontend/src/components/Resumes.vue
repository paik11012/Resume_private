<template>
  <div>
  <div class="cont" oncontextmenu="return false" @click="show">
    <div class="company">{{company}}</div>
    <hr>
    <div class="task">{{task}}</div>
    <div class="date">{{date}}</div>
    <div class="label">질문</div>
    <div class="question">{{question}}</div>
    <div class="label">답변</div>
    <div class="answer">{{answer}}</div>
    <div class="row">
      <div class="tag">{{tag[0]}} {{tag[1]}}</div>
      <v-spacer></v-spacer>
      <div class="text_val">{{text_val}}자</div>
    </div>
  </div>
  <ResumeModal
  :value="showmenu"
  :company="company"
  :answer="answer"
  :question="question"
  :task="task"
  :tag="tag"
  :text_val="text_val"
  :date="date"
  :created_at="created_at" 
  @input="input"
  />
  </div>
</template>

<script>
import ResumeModal from '../components/ResumeModal.vue'

export default {
  name: "Portfolio",
  components: {
    ResumeModal : ResumeModal
  },
  props:{
    company : {type: String},
    answer : {type: String},
    question : {type: String},
    task : {type: String},
    tag : {type: Array},
    text_val : {type: Number},
    date : {type: String},
    created_at : {type: String},
  },
  data() {
    return {
      showmenu: false,
      title:"회사명 넘겨줄게",
      body:"뭔가 넘겨줄게"
    };
  },
  methods: {
    show(){
      console.log(ResumeModal.props.value);
      this.showmenu = true
    },
    input(value){
      this.showmenu = value
      console.log(this.showmenu);
      
    }
  }

};
</script>
<style>
  .cont{
    width: 95%;
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

