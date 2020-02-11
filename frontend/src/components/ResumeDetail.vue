<template>
<div class="rsdetail">
  <div class="modalbox" @click="closing"></div>
  <div class="modal" >
  
  <v-btn class="edit" v-on:click="editor" v-if="editing" small fab dark color="primary" >
    <v-icon dark>edit</v-icon>
  </v-btn>
  <v-btn class="edit" v-on:click="editor" v-else small fab dark color="success" >
    <v-icon dark>check</v-icon>
  </v-btn>
  <v-btn class="delete" v-on:click="destroy(resume_id)" small fab color="red" >
    <v-icon color="white">delete</v-icon>
  </v-btn>
  <div class="company">
    {{ com }} 회사명
  </div>
  <div class="task">
    {{ ta }} 직무

  </div>
  <div class="date">
    {{ da }} 지원시기

  </div>

  <div class="question">
    Q {{ que }}

  </div>
  <div class="answer">
    A {{ ans }}

  </div>
  <div class="text_val">
    {{ tv }} 자수

  </div>
  <div class="tags">
    <v-btn sm class="tag" color="primary" v-for="i in tags.length" v-bind:key='i'>
    {{ tags[i-1] }}
    </v-btn>

  </div>

  </div>
</div>  
</template>

<script>
import axios from 'axios'
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
      editing:true,
      modalop: false,
      com: this.company,
      ta : this.task,
      da : this.date,
      que : this.question,
      ans : this.answer,
      tag : this.tags,
      tv : this.text_val,

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
      axios.delete(`http://70.12.247.99:8080/resume/del/${this.resume_id}`,
      {headers : {'token' : window.sessionStorage.getItem("jwt-auth-token"),
      'user_id' : window.sessionStorage.getItem("user_id")}
      })
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
    }
  }
}
</script>

<style lang="scss">
.rsdetail{
  position: relative;
  z-index: 1e9;
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
    & .edit{
      position: absolute;
      z-index: 1e9+2;
      right: 8%;
      top : 3%;
    }
    & .delete{
      position: absolute;
      z-index: 1e9+2;
      right: 3%;
      top : 3%;
    }
    animation: bounce 0.3s;
    border-radius: 10px;  
    position: fixed;
    top: 15%;
    left: 10%;
    background: white;
    width: 80%;
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
      left:5%;
      // text-align: center;
      
      }
    & .answer{
      position: absolute;
      font-size:14px;
      color:black;
      width: 90%;
      top:41%;
      left:5%;
      overflow: auto;
      height: 40%;
      border: 1px solid;
      padding: 1%;
      // text-align: center;
      
      }
    & .q{
      font-size: 4vw !important;
      position: absolute;
      font-size:20px;
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
      font-size:20px;
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

</style>