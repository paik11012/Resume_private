<template>
  <v-simple-table class="awardcard">
    <template v-slot:default>
      <thead>
        <tr>
          <th style="position:relative;">
            <div>
            <ul class="menubar"> 
                 <!-- eslint-disable -->
              <li
                class="layout row" style="font-size:20px;font-family:Jua"
              ><input type="text" v-model="award_title" style="font-family:Jua" placeholder="License / Award Title">
                <!-- eslint-disable -->
              </li>
            </ul>
            </div>
          </th>
          <th class="layout justify-end">
            <v-btn @click="create" small fab id="write" color="success">
              <v-icon>check</v-icon>
            </v-btn>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td width="150px">발급기관</td>
          <td><input type="text" class="input" v-model="award_org" placeholder="기관명"></td>
        </tr>
        <tr>
          <td width="150px">취득일자</td>
          <td><input type="text" class="input" v-model="award_date" placeholder="취득일자"></td>
        </tr>
        <tr>
          <td width="150px">등급/점수</td>
          <td><input type="text" class="input" v-model="award_prize" placeholder="등급/점수"></td>
        </tr>
        <tr>
          <td width="150px">세부내용</td>
          <td><input type="text" class="input" v-model="award_detail" placeholder="세부내용"></td>
        </tr>
         <tr>
          <td width="150px">파일</td>
          <td><v-file-input v-model="selectedFile" accept="*/*" height="1.8em"></v-file-input></td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import API from '../services/Api'

import { app } from "../services/FirebaseService";
import firebase, { storage } from "firebase/app";
import "firebase/firestore";
import "firebase/storage";

export default {
  data(){
    return{
      editing:true,
      award_org:'',
      award_title:'',
      award_detail:'',
      award_prize:'',
      award_date:'',

      selectedFile: '',
    }
  },
  methods:{
    create(){
      var set = {
      'award_org': this.award_org,
      'award_title': this.award_title,
      'award_detail':this.award_detail,
      'award_prize':this.award_prize,
      'award_date': this.award_date,
      'award_file' : this.selectedFile.name
      }
      console.log(set)

      API.post('/awards/save', set)
      .then(response => {
        console.log(response)
      })
      .catch(error => {
        console.log(error)
      })

      // firebase storage에 파일 업로드 //
      if(this.selectedFile.name!=null & this.selectedFile.name!=''){
        var storageRef = firebase.storage().ref();
        var user_id = sessionStorage.getItem("user_id");
  
        storageRef
        .child(user_id + '/' + this.selectedFile.name)
        .put(this.selectedFile);
      }
      // END: firebase storage에 파일 업로드 //

      setTimeout(() => {
        this.$emit('createa')
      }, 500);
    },
  },
}
</script>

<style lang="scss">
@import "@/assets/scss/mystyle.scss"; 

#write{
  position: relative;
  left: 10px;
}

.awardcard{
  .v-file-input__text{
    visibility: visible;
  }
}
</style>