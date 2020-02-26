<template>
  <v-simple-table class="educard">
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px; font-family:Jua">{{sch_name}}</th>
          <th class="layout hold">
            <v-btn v-on:click="editor" v-if="editing" small fab dark color="#92A8D1" class="edu_write">
              <v-icon dark>edit</v-icon>
            </v-btn>
            <v-btn v-else v-on:click="addEduUniv" small fab class="edu_write" color="success">
              <v-icon>check</v-icon>
            </v-btn>
            <v-btn @click="del" v-if="editing" small fab dark color="#F7CAC9" class="delkey">
              <v-icon dark>delete</v-icon>
            </v-btn>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td width="150px">학교명</td>
          <td v-if="editing">{{ edu_school_name }}</td>
          <td v-else><input type="text" class="input" v-model="edu_school_name" placeholder="univ name"></td>
        </tr>
        <tr>
          <td width="150px">재학기간</td>
          <td v-if="editing">{{ period }}</td>
          <td v-else><input type="text" class="input" v-model="period" placeholder="education period"></td>
        </tr>
        <tr>
          <td width="150px">전공구분</td>
          <td v-if="editing">{{ edu_detail_major_sort }}</td>
          <td v-else><input type="text" class="input" v-model="edu_detail_major_sort" placeholder="major_sort"></td>
        </tr>
        <tr>
          <td width="150px">전공명</td>
          <td v-if="editing">{{ edu_detail_major }}</td>
          <td v-else><input type="text" class="input" v-model="edu_detail_major" placeholder="major"></td>
        </tr>
        <tr>
          <td width="150px">이수학점</td>
          <td v-if="editing">{{ edu_detail_credit }}</td>
          <td v-else><input type="number" class="input" v-model="edu_detail_credit" placeholder="credit"></td>
        </tr>
        <tr>
          <td width="150px">총 평점</td>
          <td v-if="editing">{{ edu_detail_grade }}</td>
          <td v-else><input type="text" class="input" v-model="edu_detail_grade" placeholder="grade"></td>
        </tr>
        <tr>
          <td width="150px">성적표</td>
          <td v-if="editing"><span id="grade_img" @click="openWindow">{{ new_edu_detail_grade_img }}</span> <v-btn style="margin-top:6px;" color="success" outlined @click="downloadFile"><v-icon dark medium>mdi-cloud-download</v-icon></v-btn></td>
          <td v-else><v-file-input v-model="selectedFile" accept="*/*" height="1.8em"/></td>
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
  computed:{
    period:{
      get() {
        return `${this.edu_school_st_date} ${this.edu_school_ed_date}`;
      },
      set(newValue) {
        const m = newValue.match(/(\S*)\s+(.*)/);
        this.edu_school_st_date = m[1];
        this.edu_school_ed_date = m[2];
      }
    }
  },
  mounted(){
    this.new_edu_detail_grade_img = this.edu_detail_grade_img;

    if(this.edu_school_sort == 2){
      this.sch_name = 'University'
    } else if(this.edu_school_sort == 3){
      this.sch_name = 'Transfer'
    } else if(this.edu_school_sort==4){
      this.sch_name = 'GradSchool'
    }
  },
  props:{
    education_id:{type:Number},
    edu_school_sort:{type:String}, // 1이 고등학교 2가 대학교 3이 대학원 4가 편입
    edu_school_name:{type:String},
    edu_school_st_date:{type:String},
    edu_school_ed_date:{type:String},
    edu_detail_major_sort:{type:String},
    edu_detail_major:{type:String},
    edu_detail_grade:{type:Number},
    edu_detail_grade_img:{type:String},
    edu_detail_credit:{type:Number},
    edu_detail_id:{type:Number},
  },
      
  data(){
    return{
      editing:true,
      sch_name:'',

      new_edu_detail_grade_img: '',
      selectedFile: '',
    }
  },
  methods:{
    del(){
      API.delete(`/edu/deleteOne/${this.education_id}`)
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
    addEduUniv() {
      var u_education = {
        'edu_id': String(this.education_id),
        'edu_school_sort': this.edu_school_sort,
        'edu_school_name': this.edu_school_name,
        'edu_school_st_date': this.edu_school_st_date,
        'edu_school_ed_date': this.edu_school_ed_date,
      }
      var u_detail = {
        'edu_detail_id': String(this.edu_detail_id),
        'edu_detail_grade': String(this.edu_detail_grade),
        'edu_detail_grade_img': this.new_edu_detail_grade_img,
        'edu_detail_major_sort': this.edu_detail_major_sort,
        'edu_detail_major': this.edu_detail_major,
        'edu_detail_credit': String(this.edu_detail_credit)
      }
      var u_data = { education: u_education, education_detail: u_detail }
      API.post('/edu/upload', u_data)
      .then(response => {
      })
      .catch(error => {
        console.log(error)
      })
      this.editing = !this.editing

      // firebase storage에 파일 업로드 //
      var storageRef = firebase.storage().ref();
      var user_id = sessionStorage.getItem("user_id");

      // firebase storage의 기존 파일 삭제 //
      if(this.edu_detail_grade_img!=''){
        storageRef
        .child(user_id + "/" + this.edu_detail_grade_img)
        .delete();
      }

      // 새로운 파일 업로드 //
      storageRef
      .child(user_id + '/' + this.selectedFile.name)
      .put(this.selectedFile);
      // END: firebase storage에 파일 업로드 //
    },
    downloadFile() {
      var storageRef = firebase.storage().ref();
      var data = {edu_detail_id: this.edu_detail_id};

      // firebase storage의 업로드되어 있는 파일 다운로드 //
      API.post("/edu/downloadFile", data)
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
      var data = {edu_detail_id: this.edu_detail_id};

      // firebase storage의 업로드되어 있는 파일 다운로드 //
      API.post("/edu/downloadFile", data)
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
    selectedFile: function(selectedFile) {
      this.new_edu_detail_grade_img = this.selectedFile.name
    },
  }
}
</script>

<style lang="scss">
#grade_img:hover{
  text-decoration: underline;
  cursor: pointer;
}

.educard{
  .v-file-input__text{
    visibility: visible;
  }
}
.input{
  border-style:none;
  // border-bottom:solid 1px #cacaca;
  border-collapse:collapse;
  width:100%; height:100%;}
</style>