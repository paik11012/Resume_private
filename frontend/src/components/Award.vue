<template>
  <v-simple-table class="awardcard">
    <template v-slot:default>
      <thead>
        <tr>
          <th colspan="5" v-if="editing" style="font-size:20px; font-family:Jua">{{award_title}}</th>
          <th v-else style="font-size:20px; font-family:Jua" colspan="6"><input type="text" v-model="award_title"></th>
          <th class="layout hold" style="padding:0">
            <v-btn @click="editor" v-if="editing" small fab dark color="cyan" class="edu_write">
              <v-icon>edit</v-icon>
            </v-btn>
            <v-btn v-else @click="addAward" small fab class="edu_write" color="success">
              <v-icon>check</v-icon>
            </v-btn>
            <v-btn @click="del" v-if="editing" small fab dark color="red" class="delkey">
              <v-icon>delete</v-icon>
            </v-btn>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td width="150px">발급기관</td>
          <td v-if="editing" colspan="2">{{ award_org }}</td>
          <td v-else colspan="2"><input class="input" type="text" v-model="award_org" ></td>
        </tr>
        <tr> 
          <td width="150px">취득일자</td>
          <td v-if="editing" colspan="2">{{ award_date }}</td>
          <td v-else colspan="2"><input class="input" type="text" v-model="award_date"></td>
        </tr>
        <tr>
          <td width="150px">등급</td>
          <td v-if="editing" colspan="2">{{ award_prize }}</td>
          <td v-else colspan="2"><input class="input" type="text" v-model="award_prize"></td>
        </tr>
        <tr>
          <td width="150px">세부내용</td>
          <td v-if="editing" colspan="2">{{ award_detail }}</td>
          <td v-else colspan="2"><input class="input" type="text" v-model="award_detail" placeholder="education period"></td>
        </tr>
        <tr>
          <td width="150px">파일</td>
          <td v-if="editing" colspan="2"><span id="award_file" @click="openWindow">{{ new_award_file }}</span> <v-btn style="margin-top:6px;" color="success" outlined @click="downloadFile"><v-icon dark medium>mdi-cloud-download</v-icon></v-btn></td>
          <td v-else colspan="2"><v-file-input v-model="selectedFile" accept="*/*" height="1.8em"/></td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import API from "../services/Api"

import { app } from "../services/FirebaseService";
import firebase, { storage } from "firebase/app";
import "firebase/firestore";
import "firebase/storage";

export default {
  props:{
    id : {type:Number},
    award_org : {type:String},
    award_title : {type:String},
    award_date : {type:String},
    award_prize : {type:String},
    award_detail : {type:String},
    award_file : {type:String},
  },
  mounted(){
    this.new_award_file = this.award_file
  },
  data(){
    return{
      editing:true,
      new_award_file : '',
      selectedFile: '',
    }
  },
  methods:{
    del(){
      console.log("삭제예정")
      API.delete(`/awards/del/${this.id}`)
      .then(response => {
        console.log(response)
        this.$emit('delete')
      })
      .catch(error => {
        console.log(error)
      })
    },
    editor(){
      this.editing = !this.editing
    },
    addAward() {  // add and edit awards
      var filename = '';
      if(this.selectedFile.name!=null & this.selectedFile.name!='') filename = this.selectedFile.name
      else filename = this.award_file

      var award = {
        'id': this.id,
        'award_org' : this.award_org,
        'award_title' : this.award_title,
        'award_date' : this.award_date,
        'award_detail' : this.award_detail,
        'award_prize' : this.award_prize,
        'award_file' : filename,
      }
      API.post('/awards/update', award)
      .then(response => {
        console.log(response)
      })
      .catch(error => {
        console.log(error)
      })
      this.editing = !this.editing
    },

    downloadFile() {
      var storageRef = firebase.storage().ref();
      var data = {id: this.id};

      // firebase storage의 업로드되어 있는 파일 다운로드 //
      API.post("/awards/downloadFile", data)
      .then(response=>{
        var file_path = sessionStorage.getItem("user_id");
        file_path += "/";
        file_path += response.data;

        storageRef
        .child(file_path)
        .getDownloadURL()
        .then(function(url) {
          var xhr = new XMLHttpRequest();
          xhr.open("GET", url);

          xhr.responseType = "blob";
          xhr.onload = function(event) {
            var blob = xhr.response;
            var link = document.createElement("a");
            link.href = window.URL.createObjectURL(blob);
            link.download = response.data; // 다운로드되는 파일명 설정
            link.click();
          };
          xhr.send();
        });
      })
    },
    openWindow(){
      var storageRef = firebase.storage().ref();
      var data = {id: this.id};

      // firebase storage의 업로드되어 있는 파일 다운로드 //
      API.post("/awards/downloadFile", data)
      .then(response=>{
        var file_path = sessionStorage.getItem("user_id");
        file_path += "/";
        file_path += response.data;

        storageRef
        .child(file_path)
        .getDownloadURL()
        .then(function(url) {
          var xhr = new XMLHttpRequest();
          xhr.open("GET", url);

          xhr.responseType = "blob";
          xhr.onload = function(event) {
            var blob = xhr.response;
            var link = document.createElement("a");
            link.setAttribute("target", "_new");
            link.href = window.URL.createObjectURL(blob);
            link.open = response.data; // 새 탭으로 열기
            link.click();
          };
          xhr.send();
        });
      })
    }
  },
  watch:{
    selectedFile:function(selectedFile){
      this.new_award_file = selectedFile.name
    }
  }
}
</script>

<style lang="scss">
.input{
  border-style:none;
  // border-bottom:solid 1px #cacaca;
  border-collapse:collapse;
  width:100%; height:100%;}
  
.awardcard{
  .v-file-input__text{
    visibility: visible;
  }
}

#award_file:hover{
  text-decoration: underline;
  cursor: pointer;
}
</style>