<template>
  <div>
  <div class="contain" oncontextmenu="return false" @click="show">
    <div class="company">{{company}}</div>
    <hr>
    <div class="task">{{task}}</div>
    <div class="date">{{date}}</div>
    <div class="question">{{question}}</div>
    <div class="answer">{{answer}}</div>
    <div class="row">
      <div class="tag">{{tag[0]}} {{tag[1]}}</div>
      <v-spacer></v-spacer>
      <div class="text_val">{{text_val}}자</div>
    </div>
  </div>
  <PortfolioModal
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
import PortfolioModal from '../components/PortfolioModal.vue'

export default {
  name: "Portfolio",
  components: {
    PortfolioModal : PortfolioModal
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
      console.log(PortfolioModal.props.value);
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
  .contain{
    width: 95%;
    /* border: 1px solid ; */
    text-align: center;
    border: 2px solid #92a8d1;
    border-radius: 10px; 
    font-size: 13px;
    box-shadow: 3px 3px rgb(247, 202, 201);
    padding: 15px;
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
    font-size: 20px;
    margin-bottom: 6px;
  }
  .answer{
    font-size: 16px;
    margin-bottom: 20px;
  }
</style>

