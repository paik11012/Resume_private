<template>
<div class="idetail">
  <div class="modalbox" @click="closing"></div>
  <div class="modal">
  <v-btn class="edit" v-on:click="editor" v-if="editing" small fab dark color="primary" >
    <v-icon dark>edit</v-icon>
  </v-btn>
  <v-btn class="edit" v-on:click="editor" v-else small fab dark color="success" >
    <v-icon dark>check</v-icon>
  </v-btn>
  <v-btn class="delete" v-on:click="destroy(interview_id)" small fab color="red" >
    <v-icon color="white">delete</v-icon>
  </v-btn>
  <div class="company">
    {{ com }}
  </div>
  <div class="task">
    {{ ta }}
  </div>
  <div class="date">
    {{ da }}
  </div>
  <br>
  <textarea readonly v-model="question" class="question" id=""></textarea>
  <textarea readonly v-model="answer" class="answer" ></textarea>
  <textarea readonly v-model="memo" class="memo"  cols="30" rows="10"></textarea>

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
    destroy(){
      console.log(this.interview_id)
      API.delete(`/interview/del/${this.interview_id}`)
      .then(response => {
        this.interviews = response.data
        this.$emit("load")
        for (let i = 0; i < this.interviews.length; i++) {
        setTimeout(() => {
          this.sec ++
          console.log(this.sec);
        }, 100*i);
      }
      })
      .catch(error => {
      console.log(error)
      })
    this.$emit('deleteinterview')
    },
  }
}
</script>

<style lang="scss">
.idetail{
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
      right: 8%;
      top : 3%;
    }
    & .delete{
      position: absolute;
      z-index: 30;
      right: 3%;
      top : 3%;
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
      top:35%;
      left:5%;
      overflow: auto;
      height: 30%;
      border: 1px solid rgb(247, 202, 201);
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
      width: 90%;
      top: 70%;
      outline-style: none;
      left:5%;
      overflow: auto;
      height: 25%;
      border: 1px solid rgb(247, 202, 201);
      border-radius: 5px;
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

</style>