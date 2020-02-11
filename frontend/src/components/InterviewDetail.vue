<template>
<div class="idetail">
  <div class="modalbox" @click="closing"></div>
  <div class="modal">
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
  <div class="question">
   {{ que }}
  </div>
  <textarea v-model="answer" class="answer" id="" cols="30" rows="10"></textarea>
  <textarea v-model="memo" id="" cols="30" rows="10"></textarea>

  </div>
</div>  
</template>

<script>
export default {
  props:{
    company:{type:String},
    task:{type:String},
    date:{type:String},
    question:{type:String},
    answer:{type:String},
    memo:{type:String},
  },
  data(){
    return {
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
    }
  }
}
</script>

<style lang="scss">
.idetail{
  position: relative;
  z-index: 1e9;
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
    animation: bounce 0.3s;
    border-radius: 10px;  
    position: fixed;
    top: 15%;
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
      left:5%;
      text-align: center;
      
      }
    & .answer{
      position: absolute;
      font-size:14px;
      color:black;
      width: 90%;
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