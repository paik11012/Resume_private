<template>
<div class="rsdetail">
  <div class="modalbox" @click="closing"></div>
  <div class="modal" >
  <img src="@/assets/pets.png" class="PFP" v-if="pass" @click="pass=!pass">
  <img src="@/assets/no_pets.png" class="PF" v-else @click="pass=!pass">
  <!-- <v-btn class="PF" v-if="editing" small fab dark color="success" >
    <v-icon dark>check</v-icon>
  </v-btn> -->
  <!-- <v-checkbox v-else v-model="pass" class="mx-2 PFC" value="합격" label="서류합격" hide-details></v-checkbox> -->

  <v-btn class="edit" v-on:click="editor" v-if="editing" small fab dark>
    <v-icon dark>edit</v-icon>
  </v-btn>
  <v-btn class="edit" v-on:click="editResume" v-else small fab dark>
    <v-icon dark>check</v-icon>
  </v-btn>
  <v-btn class="delete" v-on:click="destroy(resume_id)" small fab>
    <v-icon color="white">delete</v-icon>
  </v-btn>
  <div>
    <div v-if="editing" class="company">{{ com }}</div>
    <div v-else class="company"><input type="text" v-model="com"></div>
  </div>
  <div>
    <div v-if="editing" class="task">{{ ta }}</div>
    <div v-else class="task"><input type="text" v-model="ta"></div>
  </div>
  <div>
    <div v-if="editing" class="date">{{ da }}</div>
    <div v-else class="date"><input type="text" v-model="da"></div>
  </div>
  <br>
  <div>
    <div v-if="editing"><textarea readonly v-model="question" class="question" id=""></textarea></div>
    <div v-else><textarea v-model="question" class="question"></textarea></div>
  </div>
  <div>
    <div v-if="editing"><textarea readonly v-model="answer" class="answer" id="" cols="30" rows="10"></textarea></div>
    <div v-else><textarea v-model="answer" class="answer" id="" cols="30" rows="10"></textarea></div>
  </div>
  <div class="text_val">
    {{ answer.length }} 자
  </div>
  <div v-if="editing" class="tags">
    <v-btn small aria-disabled="true" class="tag" outlined color="#92A8D1" v-for="i in tags.length" v-bind:key='i'>
    #{{ tags[i-1] }}
    </v-btn>
    <v-btn small class="ma-2" outlined color="success">자소서 내보내기</v-btn>
  </div>
  <div v-else>
    <v-row class="bot_tags justify-space-around dig">
      <v-col cols="12" sm="2" md="2" style="padding-top:1px">
        <v-checkbox v-model="tag_name" class="mx-2" value="신뢰" label="신뢰" hide-details></v-checkbox> 
        <v-checkbox v-model="tag_name" class="mx-2" value="도덕성" label="도덕성" hide-details></v-checkbox> 
      </v-col>
      <v-col cols="12" sm="2" md="2" style="padding-top:1px">
        <v-checkbox v-model="tag_name" class="mx-2" value="책임감" label="책임감" hide-details></v-checkbox> 
        <v-checkbox v-model="tag_name" class="mx-2" value="가치창출" label="가치창출" hide-details></v-checkbox> 
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
        <v-checkbox v-model="tag_name" class="mx-2" value="전문성" label="전문성" hide-details></v-checkbox> 
      </v-col>
      <v-col cols="12" sm="2" md="2" style="padding-top:1px">
        <v-checkbox v-model="tag_name" class="mx-2" value="열정" label="열정" hide-details></v-checkbox>
        <v-checkbox v-model="tag_name" class="mx-2" value="배려" label="배려" hide-details></v-checkbox>
      </v-col>
    </v-row>      
  </div>

  </div>
</div>  
</template>

<script>

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
  },
  data(){
    return {
      pass:false,
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
      tag_name: [],
      filter_one_tag: null,
    }
  },
  methods:{
    closing(){
      this.$emit('clsrsd')
    },
    editor(){
      this.editing = !this.editing
    },
    destroy(){
      API.delete(`/resume/del/${this.resume_id}`)
      .then(response => {
        this.resumes = response.data
        console.log(response.data)
        this.$emit("load")
        for (let i = 0; i < this.resumes.length; i++) {
        setTimeout(() => {
          this.sec ++
          console.log(this.sec);
        }, 100*i);
      }
      })
      .catch(error => {
      console.log(error)
      })
    this.$emit('deleteresume')
    },
    editResume() {
      var resume_info = {
        "id": String(this.resume_id),  // 현재 id가 없다
        "resume_company" : this.com,
        "resume_task" : this.ta,
        "resume_date" : this.da,
        "resume_question" : this.question,
        "resume_answer" : this.answer,
        // 이부분 확인
        "resume_pass" : this.pass,
        //
      };
      var r_data = {
          resume_info : resume_info,
          tag_name : this.tag_name
      }
      console.log(r_data)
      API.post('resume/update', r_data)
      .then(response => {
        console.log(response.data)
      })
      .catch(error => {
        console.log(error)
      })
      this.editing = !this.editing
      console.log('ssss');
      this.$emit('upload',r_data)
    }
  }
}
</script>

<style lang="scss">
.rsdetail{
  .theme--dark.v-btn:not(.v-btn--flat):not(.v-btn--text):not(.v-btn--outlined){
    background-color: #92A8D1;
  }
  .theme--light.v-btn:not(.v-btn--flat):not(.v-btn--text):not(.v-btn--outlined){
    background-color: #F7CAC9;
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
  }
  & input {
    text-align: center;
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
      top : 3%;
    }
    & .delete{
      position: absolute;
      z-index: 32;
      right: 15px;
      top : 3%;
    }
    animation: bounce 0.3s;
    border-radius: 10px;  
    position: fixed;
    top: 10%;
    left: 15%;
    background: white;
    width: 70%;
    height: 80%;
    overflow: hidden;
    & .company{
      overflow: hidden;
      height: 9%;
      position: absolute;
      font-size:32px;
      width: 80%;
      top:3%;
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
      top:14%;
      left:10%;
      text-align: center;
    }
    & .date{
      overflow: hidden;
      height: 5%;
      position: absolute;
      font-size:20px;
      color:black;
      width: 30%;
      top:14%;
      left:60%;
      text-align: center;
    }
    & .question{
      position: absolute;
      font-size:14px;
      color:black;
      width: 90%;
      top:26%;
      outline-style: none;
      left:5%;
      text-align: center;
      
      }
    & .answer{
      position: absolute;
      font-size:14px;
      color:black;
      width: 90%;
      outline-style: none;
      top:38%;
      left:5%;
      overflow: auto;
      height: 43%;
      border: 1px solid #92A8D1;
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
      bottom: 5%;
      margin: 5px;
    }
    & .tag{
      color: white;
      text-align: center;
      width: 80px;
      margin-right:10px;
    }
    & .text_val{
      font-size: 16px;
      font-weight: 500;
      position: absolute;
      right:4%;
      top: 82%;
    }
    .PF{
      cursor: pointer;
      position: absolute;
      width: 45px;
      height: 45px;
      top: 15px;
      left: 15px;
      &P{
        cursor: pointer;
        position: absolute;
        width: 74px;
        height: 74px;
        top: 1px;
        left: 13px;
      }
    }
  }
} 

</style>