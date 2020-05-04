<template>
  <v-simple-table class="awardcard">
    <template v-slot:default>
      <thead>
        <tr>
          <th colspan="5" v-if="editing" style="font-size:20px; font-family:Jua">{{awti}}</th>
          <th v-else style="font-size:20px; font-family:Jua" colspan="6"><input type="text" v-model="awti" placeholder="Award/Licence"></th>
          <th class="layout hold" style="padding:0">
            <v-btn @click="editor" v-if="editing" small fab dark color="#92A8D1" class="edu_write">
              <v-icon>edit</v-icon>
            </v-btn>
            <v-btn v-else @click="addAward" small fab class="edu_write" color="success">
              <v-icon>check</v-icon>
            </v-btn>
            <v-btn @click="del" v-if="editing" small fab dark color="#F7CAC9" class="delkey">
              <v-icon>delete</v-icon>
            </v-btn>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td width="150px">발급기관</td>
          <td v-if="editing" colspan="2">{{ awor }}</td>
          <td v-else colspan="2"><input class="input" type="text" v-model="awor" placeholder="발급기관명"></td>
        </tr>
        <tr> 
          <td width="150px">취득일자</td>
          <td v-if="editing" colspan="2">{{ awdt }}</td>
          <td v-else colspan="2"><input class="input" type="text" v-model="awdt" placeholder="ex) 2020-02-20"></td>
        </tr>
        <tr>
          <td width="150px">등급</td>
          <td v-if="editing" colspan="2">{{ awpr }}</td>
          <td v-else colspan="2"><input class="input" type="text" v-model="awpr" placeholder="급/점수"></td>
        </tr>
        <tr>
          <td width="150px">세부내용</td>
          <td v-if="editing" colspan="2">{{ awdet }}</td>
          <td v-else colspan="2"><input class="input" type="text" v-model="awdet" placeholder="세부내용"></td>
        </tr>
        <tr>
          <td width="150px">파일</td>
          <td v-if="editing" colspan="2"><span id="award_file" @click="openWindow">{{ awfi }}</span> <v-btn style="margin-top:6px;" color="success" outlined @click="downloadFile"><v-icon dark medium>mdi-cloud-download</v-icon></v-btn></td>
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
    id : {type:String},
    award_org : {type:String},
    award_title : {type:String},
    award_date : {type:String},
    award_prize : {type:String},
    award_detail : {type:String},
    award_file : {type:String},
  },
  mounted(){
  },
  data(){
    return{
      editing:true,
      selectedFile: '',
      awti:this.award_title,
      awor:this.award_org,
      awdt:this.award_date,
      awpr:this.award_prize,
      awdet:this.award_detail,
      awfi:this.award_file,
    }
  },
  methods:{
    del(){
      API.delete(`/awards/del/${this.id}`)
      .then(response => {
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
      else filename = this.awfi

      var award = {
        'id': this.id,
        'award_org' : this.awor,
        'award_title' : this.awti,
        'award_date' : this.awdt,
        'award_detail' : this.awdet,
        'award_prize' : this.awpr,
        'award_file' : filename,
      }
      API.post('/awards/update', award)
      .then(response => {
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
  font-family:'Nanum Square';
  width:100%; height:100%;}
  
.awardcard{
  .v-file-input__text{
    visibility: visible;
    font-family:'Nanum Square';
  }
}

#award_file:hover{
  text-decoration: underline;
  cursor: pointer;
}
</style>