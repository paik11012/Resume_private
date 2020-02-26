<template>
<div class="idetail">
  <div class="modalbox" @click="closing"></div>
  <div class="modal">
  <div class="buttons">
  <v-btn class="edit" v-on:click="editor" v-if="editing" small fab dark color="#92A8D1" >
    <v-icon dark>edit</v-icon>
  </v-btn>
  <v-btn class="edit" v-on:click="editInterview" v-else small fab dark color="#F7CAC9" >
    <v-icon dark>check</v-icon>
  </v-btn>
  <v-btn class="delete" v-on:click="destroy(interview_id)" small fab color="#F7CAC9" >
    <v-icon color="white">delete</v-icon>
  </v-btn>
  </div>


  <div class="titleback"></div>
  <hr class="hr1">
  <hr class="hr2">
  <hr class="hr3">
  <hr class="hr4">
  <p class="p1">질문</p>
  <p class="p2">답변</p>
  <p class="p3">메모</p>


  <div class="company" v-if="editing">
    {{ com }}
  </div>
  <input type="text" class="company" v-else v-model="com">  
  <div class="task" v-if="editing">
    {{ ta }}
  </div>
  <input type="text" class="task" v-else v-model="ta">
  <div class="date" v-if="editing">
    {{ da }}
  </div>
  <input type="text" class="date" v-else v-model="da">
  <br>
  <textarea readonly v-model="que" class="question" v-if="editing"></textarea>
  <textarea v-model="que" class="question" v-else></textarea>
  <textarea readonly v-model="ans" class="answer" v-if="editing" ></textarea>
  <textarea v-model="ans" class="answer" v-else></textarea>
  <textarea readonly v-model="mem" class="memo"  cols="30" rows="10" v-if="editing"></textarea>
  <textarea  v-model="mem" class="memo"  cols="30" rows="10" v-else></textarea>

  </div>
</div>  
</template>

<script>
import API from "../services/Api"
export default {
  props:{
    interview_id:{type:Number},
    company:{type:String},
    task:{type:String},
    date:{type:String},
    question:{type:String},
    answer:{type:String},
    memo:{type:String},
  },
  data(){
    return {
      editing:true,
      modalop: false,
      com: this.company,
      ta : this.task,
      da : this.date,
      que : this.question,
      ans : this.answer,
      mem: this.memo,
    }
  },
  methods:{
    closing(){
      this.$emit('clsid')
    },
    editor(){
      this.editing = !this.editing
    },
    destroy(i){
      API.delete(`/interview/del/${i}`)
      .then(response => {
        this.interviews = response.data
        for (let i = 0; i < this.interviews.length; i++) {
        setTimeout(() => {
          this.sec ++
          console.log(this.sec);
        }, 100*i);
      }
        this.$emit('deleteinterview')
      })
      .catch(error => {
      console.log(error)
      })
    },
    
    editInterview(){
      var interview_info = {
        "id":this.interview_id,
        "interview_company": this.com,
        "interview_task":this.ta,
        "interview_date": this.da,
        "interview_question":this.que,
        "interview_answer":this.ans,
        "interview_memo":this.mem,
      };
      API.post('interview/update', interview_info)
      .then(res => {
        console.log(res);
      })
      .catch(error => {
        console.log(error);
      })
      this.editing = !this.editing
      this.$emit('upload')
    }
  }
}
</script>

<style lang="scss">
.idetail{
  position: relative;
  .hr{
    &1{
      position: absolute;
      width:100%;
      top:10%;
      border-bottom-width: 0;
    }
    &2{
      position: absolute;
      width:100%;
      top:19%;
      border-bottom-width: 0;
      }
    &3{      
      position: absolute;
      width:100%;
      top:32%;
      border-bottom-width: 0;
    }
    &4{      
      position: absolute;
      width:100%;
      top:62%;
      border-bottom-width: 0;
    }
  }
  .p{
    &1{
      position: absolute;
      font-size: 11px; 
      width:100%;
      text-align: left;
      top:21%;
      left: 2%;
    }
    &2{
      position: absolute;
      font-size: 11px; 
      width:100%;
      text-align: left;
      top:34%;
      left: 2%;
    }
    &3{
      position: absolute;
      font-size: 11px; 
      width:100%;
      text-align: left;
      top:64%;
      left: 2%;
    }
  }
  .titleback{
    width: 100%;
    height: 59px;
    background-color: rgb(245,246,251);
  }

  textarea{
    resize: none;
    font-family: 'NanumSquare', sans-serif;
  }
  position: relative;
  z-index: 29;
  & .modalbox{
    width: 100%;
    height: 100%;
    top:0;
    left: 0;
    position: fixed;
    background: gray;
    opacity: 0.4;
  }
  & .modal{
    z-index: 32;
    & .edit{
      position: absolute;
      z-index: 32;
      right: 65px;
      top : 1.5%;
    }
    & .delete{
      position: absolute;
      z-index: 30;
      right: 15px;
      top : 1.5%;
    }
    animation: bounce 0.3s;
    border-radius: 10px;  
    position: fixed;
    top: 15%;
    left: 15%;
    background: white;
    width: 70%;
    height:80%;
    overflow: hidden;
    & .company{
      overflow: hidden;
      height: 9%;
      position: absolute;
      font-size:32px;
      width: 80%;
      top:1%;
      left:10%;
      text-align: center;
    }
    & .task{
      overflow: hidden;
      height: 5%;
      position: absolute;
      font-size:20px;
      width: 30%;
      color: black;
      top:12%;
      left:3%;
      text-align: left;
    }
    & .date{
      overflow: hidden;
      height: 5%;
      position: absolute;
      font-size:20px;
      color:black;
      width: 30%;
      top:12%;
      left:50%;
      text-align: left;
      padding-left:3%;
      border-left: 1.5px solid;
      border-color: #EEEEEE;
    }
    & .question{
      position: absolute;
      font-size:14px;
      color:black;
      width: 90%;
      top:24%;
      outline-style: none;
      left:3.5%;
      text-align: left;
      }
    & .answer{
      position: absolute;
      font-size:14px;
      color:black;
      width: 95%;
      top:35.5%;
      left:2.5%;
      overflow: auto;
      height: 26%;
      outline-style: none;
      border-radius: 5px;
      padding: 1%;
      // text-align: center;
      }
    & .q{
      font-size: 4vw !important;
      position: absolute;
      font-size:22px;
      color:black;
      width: 5%;
      top:22%;
      left:3%;
      text-align: center;
    }
    & .memo{
      position: absolute;
      font-size:14px;
      color:black;
      width: 95%;
      top: 65.5%;
      outline-style: none;
      left:2.5%;
      overflow: auto;
      height: 26%;
      padding: 1%;
      // text-align: center;
      }
    & .a{
      font-size: 4vw !important;
      
      color:black;
      position: absolute;
      font-size:22px;
      color:black;
      width: 5%;
      top:37%;
      left:3%;
      text-align: center;
    }
    & .tags{
      position: absolute;
      right: 5%;
      bottom: 5%;
    }
    & .tag{
      color: white;
      text-align: center;
      width: 80px;
    }
    & .text_val{
      font-size: 13px;
      font-weight: 500;
      position: absolute;
      right:4%;
      top: 82%;
    }
  }
}
@media screen and (max-width:800px) {
  .buttons {
    display: none
  }
}
</style>