<template>
  <v-simple-table class="educard">
    <template v-slot:default>
      <thead>
        <tr>
          <th style="position:relative;">
            <div>
            <ul class="menubar" @click="opendb"> 
                 <!-- eslint-disable -->
              <li
                v-for="i in 4" 
                v-bind:key="i"
                v-if="select==i | opendrop"
                @click="selectone(i)"
                class="layout row"
                style="font-family:Jua"
              >
                <!-- eslint-disable -->
              {{ edu_school_sort[i-1] }} <div v-if="!opendrop" style="position:absolute; right:0;font-family:Jua"><i class="material-icons">arrow_drop_down</i></div>
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
          <td width="150px">학교명</td>
          <td><input type="text" class="input" v-model="edu_school_name" placeholder="학교명"></td>
        </tr>
        <tr>
          <td width="150px">재학기간</td>
          <td><input type="text" v-model="edu_school_st_date"> ~ <input type="text" v-model="edu_school_ed_date"></td>
        </tr>
        <tr v-if="select != 1">
          <td width="150px">전공구분</td>
          <td><input type="text" class="input" v-model="edu_detail_major_sort" placeholder="전공/부전공/복수전공"></td>
        </tr>
        <tr v-if="select != 1">
          <td width="150px">전공명</td>
          <td><input type="text" class="input" v-model="edu_detail_major" placeholder="전공명"></td>
        </tr>
        <tr v-if="select != 1">
          <td width="150px">이수학점</td>
          <td><input type="text" class="input" v-model="edu_detail_credit" placeholder="이수학점"></td>
        </tr>
        <tr v-if="select != 1">
          <td width="150px">총 평점</td>
          <td><input type="text" class="input" v-model="edu_detail_grade" placeholder="총 평점"></td>
        </tr>
        <tr v-if="select != 1">
          <td width="150px">성적표</td>
          <td><v-file-input v-model="selectedFile" accept="*/*" height="1.8em"></v-file-input></td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import { app } from "../services/FirebaseService";
import firebase, { storage } from "firebase/app";
import "firebase/firestore";
import "firebase/storage";

import API from "../services/Api"

export default {
  data(){
    return{
      editing:true,
      edu_school_name:'',
      edu_school_st_date:'',
      edu_school_ed_date:'',
      edu_detail_major_sort:'',
      edu_detail_major:'',
      edu_detail_credit:'',
      edu_detail_grade:'',
      edu_detail_grade_img: '',
      select:1,
      opendrop:false,
      edu_school_sort:[
        'HighSchool', 'University', 'Transfer', 'GradSchool'
      ],

      selectedFile: '',
    }
  },
  methods:{
    editor(){
      this.editing = !this.editing
    },
    opendb(){
      this.opendrop = !this.opendrop
    },
    selectone(i){
      this.select = i
    },
    create(){
      if (this.select == 1){
        var set = {
          "education": {
            "edu_school_sort": String(this.select),
            "edu_school_name": String(this.edu_school_name),
            "edu_school_st_date": String(this.edu_school_st_date),
            "edu_school_ed_date": String(this.edu_school_ed_date),
          }
        }
      } else {
          var set = {
            "education": {
              "edu_school_sort": String(this.select),
              "edu_school_name": String(this.edu_school_name),
              "edu_school_st_date": String(this.edu_school_st_date),
              "edu_school_ed_date": String(this.edu_school_ed_date),
          },
          "education_detail" : {
            "edu_detail_major_sort" : String(this.edu_detail_major_sort),
            "edu_detail_major" : String(this.edu_detail_major),
            "edu_detail_credit" : String(this.edu_detail_credit),
            "edu_detail_grade" : String(this.edu_detail_grade),
            "edu_detail_grade_img" : String(this.edu_detail_grade_img),
          }
        }
      }
      API.post('/edu/upload', set)
      .then(response => {
        console.log(response)
      })
      .catch(error => {
        console.log(error)
      })
      setTimeout(() => {
        this.$emit('create')
      }, 500);

      // firebase storage에 파일 업로드 //
      if(this.edu_detail_grade_img!=''){
        console.log(this.edu_detail_grade_img)
        var storageRef = firebase.storage().ref();
        var user_id = sessionStorage.getItem("user_id");
  
        storageRef
        .child(user_id + '/' + this.edu_detail_grade_img)
        .put(this.selectedFile);
      }
      // END: firebase storage에 파일 업로드 //
    }
  },

  watch:{
    selectedFile: function(selectedFile) {
      this.edu_detail_grade_img = this.selectedFile.name
    },
  }
}
</script>

<style lang="scss">
@import "@/assets/scss/mystyle.scss"; 
.menubar {
    background: none;
    height:100%;
    font-size: 20px;
    top: 5px;
    left: 0;
    padding:0px;
    margin:0px;
    border:0px;
    text-align: left;
    width:100%;
    z-index:28;
    color:white;
    position: absolute;
    & li {
      background: white;;
      border-radius: 2px;
      color: black;
      list-style: none;
    }
    & ul{
      color:black;
      list-style: none;
    }
}


#write{
  position: relative;
  left: 10px;
}

.educard{
  .v-file-input__text{
    visibility: visible;
  }
}

</style>