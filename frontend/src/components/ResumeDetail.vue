<template>
<div class="rsdetail">
  <div class="modalbox" @click="closing"></div>
  <div class="modal" >
    <!-- 수정할 때만 바꿀 수 있다 -->
    <div v-if="editing">
      <img src="@/assets/pass(2).png" class="PFP" v-if="pass">
      <img src="@/assets/no_pets.png" class="PF" v-else>  
    </div>
    <div v-else>
      <img src="@/assets/pass(2).png" class="PFP" v-if="pass" @click="editing ? pass = pass : pass = !pass">
      <img src="@/assets/no_pets.png" class="PF" v-else @click="editing ? pass = pass : pass = !pass"> 
    </div>

  <div class="buttons">
  <v-btn class="edit" v-on:click="editor" v-if="editing" small fab dark>
    <v-icon dark>edit</v-icon>
  </v-btn>
  <v-btn class="edit" v-on:click="editResume" v-else small fab dark>
    <v-icon dark>check</v-icon>
  </v-btn>
  <v-btn class="delete" v-on:click="destroy(resume_id)" small fab>
    <v-icon color="white">delete</v-icon>
  </v-btn>
  </div>


  <div>
    <div class="titleback"></div>
    <div v-if="editing" class="company">{{ com }}</div>
    <div v-else class="company"><input type="text" class="input" style="text-align:center;" v-model="com"></div>
    <hr class="hr1">
    <hr class="hr2">
    <hr class="hr3">
    <hr class="hr4">
  </div>
  <div>
    <div v-if="editing" class="task">{{ ta }}</div>
    <div v-else class="task"><input  class="input" type="text" v-model="ta"></div>
  </div>
  <div>
    <div v-if="editing" class="date">{{ da }}</div>
    <div v-else class="date"><v-select :items="resume_date_list" v-model="date"></v-select></div>
  <p class="p1">질문</p>
  <p class="p2">답변</p>
  </div>
  <div>
    <div v-if="editing"><textarea readonly v-model="question" class="question"></textarea></div>
    <div v-else><textarea v-model="que" class="question"></textarea></div>
  </div>
  <div>
    <div v-if="editing"><textarea readonly v-model="answer" class="answer" id="" cols="30" rows="10"></textarea></div>
    <div v-else><textarea v-model="ans" class="answer" id="" cols="30" rows="10"></textarea></div>
  </div>
  <div class="text_val">
    {{ getByte(ans) }} bytes / 
    {{ ans.length }} 자
  </div>
  <div v-if="editing" class="tags">
    <v-btn small aria-disabled="true" class="tag" outlined color="#92A8D1" v-for="i in tags.length" v-bind:key='i'>
    #{{ tags[i-1] }}
    </v-btn>
    <v-btn small class="ma-2" outlined color="success" @click="sendPdf">자소서 메일로 내보내기</v-btn>
  </div>
  <div v-else>
    <v-row class="bot_tags justify-space-around dig">
      <v-col cols="12" sm="2" md="2" style="padding-top:1px">
        <v-checkbox v-model="tag_name" class="mx-2" value="신뢰" label="신뢰" hide-details></v-checkbox> 
        <v-checkbox v-model="tag_name" class="mx-2" value="도덕성" label="도덕성" hide-details></v-checkbox> 
      </v-col>
      <v-col cols="12" sm="2" md="2" style="padding-top:1px">
        <v-checkbox v-model="tag_name" class="mx-2" value="책임감" label="책임감" hide-details></v-checkbox> 
        <v-checkbox v-model="tag_name" class="mx-2" value="전문성" label="전문성" hide-details></v-checkbox> 
      </v-col>
      <v-col cols="12" sm="2" md="2" style="padding-top:1px">
        <v-checkbox v-model="tag_name" class="mx-2" value="창의성" label="창의성" hide-details></v-checkbox> 
        <v-checkbox v-model="tag_name" class="mx-2" value="글로벌" label="글로벌" hide-details></v-checkbox> 
      </v-col>
      <v-col cols="12" sm="2" md="2" style="padding-top:1px">
        <v-checkbox v-model="tag_name" class="mx-2" value="도전정신" label="도전정신" hide-details></v-checkbox> 
        <v-checkbox v-model="tag_name" class="mx-2" value="협력" label="협력" hide-details></v-checkbox>
      </v-col>
      <v-col cols="12" sm="2" md="2" style="padding-top:1px">
        <v-checkbox v-model="tag_name" class="mx-2" value="혁신" label="혁신" hide-details></v-checkbox> 
        <v-checkbox v-model="tag_name" class="mx-2" value="지원동기" label="지원동기" hide-details></v-checkbox> 
      </v-col>
      <v-col cols="12" sm="2" md="2" style="padding-top:1px">
        <v-checkbox v-model="tag_name" class="mx-2" value="열정" label="열정" hide-details></v-checkbox>
        <v-checkbox v-model="tag_name" class="mx-2" value="포부" label="포부" hide-details></v-checkbox>
      </v-col>
    </v-row>      
  </div>

  </div>
</div>  
</template>

<script>

import swal from 'sweetalert';
import API from "../services/Api"
export default {
  props:{
    resume_id:{type:Number},
    company:{type:String},
    task:{type:String},
    date:{type:String},
    question:{type:String},
    answer:{type:String},
    tags:{type:Array},
    text_val:{type:Number},
    res_pass:{type:Boolean},
  },

  data(){
    return {
      bytes: 0,
      pass:this.res_pass,
      editing:true,
      modalop: false,
      id: this.resume_id,
      com: this.company,
      ta : this.task,
      da : this.date,
      que : this.question,
      ans : this.answer,
      tag : this.tags,
      tv : this.text_val,
      tag_name: this.tags, // 수정시 태그 들어가게
      filter_one_tag: null,
      resume_date_list:['2017 상반기', '2017 하반기', '2018 상반기', '2018 하반기', '2019 상반기', '2019 하반기', '2020 상반기', '2020 하반기', '2021 상반기', '2021 하반기'],
    }
  },
  methods:{
    closing(){
      this.$emit('clsrsd',this.tag_name)
    },
    editor(){
      this.editing = !this.editing
    },
    destroy(){ 
      swal('삭제하시겠습니까?',
      {buttons: ['Cancel', 'Delete']})
      .then((Delete) => {
        if (Delete) {
          API.delete(`/resume/del/${this.resume_id}`)
          .then(response => {
            this.resumes = response.data
            this.$emit("load")
            for (let i = 0; i < this.resumes.length; i++) {
              setTimeout(() => {
                this.sec ++
              }, 100*i);
            }
          })
          .catch(error => {
          console.log(error)
          })
        this.$emit('deleteresume')
        }
      })
    }, 
    editResume() {
      var resume_info = {
        "id": String(this.resume_id),  // 현재 id가 없다
        "resume_company" : this.com,
        "resume_task" : this.ta,
        "resume_date" : this.date,
        "resume_question" : this.que,
        "resume_answer" : this.ans,
        "resume_pass" : this.pass,
      };
      var r_data = {
          resume_info : resume_info,
          tag_name : this.tag_name
      }
      API.post('resume/update', r_data)
      .then(response => {
        this.$emit('upload',r_data.tag_name)
        console.log('hihi')
        this.editing = !this.editing
      })
      .catch(error => {
        console.log(error)
      })
    },
    sendPdf() {
      API.get(`attach/${this.resume_id}`)
      .then(response => {
        alert("자소서 pdf를 메일로 전송 했다옹~")
      })
      .catch(error => {
        console.log(error)
      })
    },
    getByte(str) {
    return str
    .split('') 
    .map(s => s.charCodeAt(0))
    .reduce((prev, c) => (prev + ((c === 10) ? 2 : ((c >> 7) ? 2 : 1))), 0); // 계산식에 관한 설명은 위 블로그에 있습니다.
}
  }
}
</script>

<style lang="scss">
.rsdetail{
  position: relative;
  .p{
    &1{
      width: 100%;
      font-size: 12px;
      padding-left: 1%;
      text-align: left;
      position: absolute;
      top:22%;
    }
    &2{
      width: 100%;
      font-size: 12px;
      left: 1%;
      text-align: left;
      position: absolute;
      top:35%;

    }
  }
  .theme--dark.v-btn:not(.v-btn--flat):not(.v-btn--text):not(.v-btn--outlined){
    background-color: #92A8D1;
  }
  .theme--light.v-btn:not(.v-btn--flat):not(.v-btn--text):not(.v-btn--outlined){
    background-color: #F7CAC9;
  }
  .hr{
    &1{
      position: absolute;
      width: 100%;
      top:11.1%;
      border-bottom-width: 0;
    }
    &2{
      position:absolute;
      width: 100%;
      top:21%;
      border-bottom-width: 0;
    }
    &3{
      position:absolute;
      width: 100%;
      top:34%;
      border-bottom-width: 0;
    }
    &4{
      position:absolute;
      width: 100%;
      top:82%;
      border-bottom-width: 0;
    }
  }
  .titleback{
    width: 100%;
    height: 59px;

  }

  & .dig{
    & .v-icon{
      z-index: 0;
    }
    & .v-input--selection-controls__ripple{
      z-index: 3;
    }
  }
  & textarea{
    resize: none;
    font-family:'Nanum Square';
  }
  & input {
    text-align: left;
    font-family:'Nanum Square';
  }
  position: relative;
  z-index: 29;
  & .bot_tags{
    position: absolute;
    bottom:15px;
    width: 90%;
    left: 8%;
  }
  & .modalbox{
    width: 100%;
    height: 100%;
    top:0;
    left: 0;
    position: fixed;
    background: rgb(33, 33, 33);
    opacity: 0.46;
  }
  & .modal{
    z-index: 32;
    & .edit{
      position: absolute;
      z-index: 32;
      right: 65px;
      top : 2%;
    }
    & .delete{
      position: absolute;
      z-index: 32;
      right: 15px;
      top : 2%;
    }
    animation: bounce 0.3s;
    border-radius: 10px;  
    position: fixed;
    top: 12.5%;
    left: 15%;
    background: white;
    width: 70%;
    height: 75%;
    overflow: hidden;
    & .company{
      overflow: hidden;
      height: 9%;
      position: absolute;
      font-size:25px;
      font-family:'Nanum Square';
      width: 80%;
      top:2%;
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
      top:13.5%;
      left:3%;
      text-align: left;
    }
    & .date{
      overflow: hidden;
      height: 5%;
      position: absolute;
      font-family:'Nanum Square';
      font-size:20px;
      color:black;
      width: 30%;
      top:13.5%;
      left:52%;
      text-align: left;
      padding-left:3%;
      border-left: 1.5px solid;
      border-color: #EEEEEE;
    }
    & .question{
      position: absolute;
      font-size:14px;
      color:black;
      width: 95%;
      top:26%;
      outline-style: none;
      left:2%;
      text-align: left;
      
      }
    & .answer{
      position: absolute;
      font-size:14px;
      color:black;
      width: 97%;
      outline-style: none;
      top:37%;
      left:1%;
      overflow: auto;
      height: 43%;
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
      font-family: Jua;
      position: absolute;
      right: 5%;
      bottom: 2%;
      margin: 5px;
    }
    & .tag{
      color: white;
      text-align: center;
      width: 80px;
      margin-right:10px;
    }
    & .text_val{
      font-size: 15px;
      font-weight: 500;
      position: absolute;
      right:2%;
      top: 83%;
    }
    .PF{
      cursor: pointer;
      position: absolute;
      width: 42px;
      height: 42px;
      top: 1.7%;
      left: 16px;
      &P{
        cursor: pointer;
        position: absolute;
        width: 70px;
        height: 70px;
        top: -1%;
        left: 11px;
      }
    }
  }
} 
.input{
  border-style:solid;
  border-collapse:collapse;
  width:100%; height:100%;}
  
.v-input{
  padding-top:0px;
}
@media screen and (max-width:800px) {
  .buttons {
    display: none
  }
  .tags{
    bottom: 0 !important;
  }
}
</style>