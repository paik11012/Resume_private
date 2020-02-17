<template>
  <v-simple-table v-if="$vuetify.breakpoint.mdAndUp">
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px;font-family:Jua" colspan="2">My Info
          </th>
          <th>
            <v-btn v-on:click="edit" v-if="editing" small fab dark color="cyan" id="write">
              <v-icon dark>edit</v-icon>
            </v-btn>
            <v-btn v-else v-on:click="editor" small fab id="write" color="success">
              <v-icon>check</v-icon>
            </v-btn>
          </th>
        </tr>
      </thead>
      <tbody>

        <tr>
          <td style="width:30%; position:relative;" rowspan="3"><img id="myPic" style="width:140px; height:170px; display: block; margin: 0px auto;" src="@/assets/person.jpg"/><div style="position:absolute; left: 23%; top:0px;"><v-icon dark>mdi-close</v-icon></div></td>
          <td style="width:20%">병역구분</td>
          <td v-if="editing">{{ military_sort }}</td>
          <td v-else><input type="text" v-model="military_sort" placeholder="병역구분"></td>
        </tr>

        <tr>
          <td style="width:20%">계급</td>
          <td v-if="editing">{{ military_class }}</td>
          <td v-else><input type="text" v-model="military_class" placeholder="계급"></td>
        </tr>
        <tr>
          <td style="width:20%">복무기간</td>
          <td v-if="editing">{{ military_st_date }}</td>
          <td v-else><input type="text" v-model="military_st_date" placeholder="복무기간"></td>
        </tr>
        <tr>
          <td class="layout justify-center"><div><v-file-input id="btn-upload" v-model="selectedFile" accept="image/*" prepend-icon="mdi-cloud-upload" height="1.8em" style="width:30px; overflow:hidden;"></v-file-input></div>
          <v-btn style="margin-top:6px;" color="success" outlined @click="downloadFile"><v-icon dark>mdi-cloud-download</v-icon></v-btn></td>
          <td style="width:20%">메모</td>
          <td v-if="editing">{{ memo }}</td>
          <td v-else><input type="text" v-model="memo" placeholder="메모"></td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
  
  <v-simple-table v-else>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px" colspan="1">My Info
          </th>
          <th>
            <v-btn v-on:click="edit" v-if="editing" small fab dark color="cyan" id="write">
              <v-icon dark>edit</v-icon>
            </v-btn>
            <v-btn v-else v-on:click="editor" small fab id="write" color="success">
              <v-icon>check</v-icon>
            </v-btn>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td style="width:40%; position:relative;" rowspan="3"><img id="myPic" style="width:140px; height:170px; display: block; margin: 0px auto;" src="@/assets/person.jpg"/><div style="position:absolute; left: 23%; top:0px;"><v-icon dark>mdi-close</v-icon></div></td>
          <td class="layout justify-center"><div><v-file-input id="btn-upload" v-model="selectedFile" accept="image/*" prepend-icon="mdi-cloud-upload" height="1.8em" style="width:30px; overflow:hidden;"></v-file-input></div></td>
          <td class="layout justify-center"><v-btn style="margin-top:6px;" color="success" outlined @click="downloadFile"><v-icon dark>mdi-cloud-download</v-icon></v-btn></td>
        </tr>
        <tr>
          <td style="text-align:center;">병역구분</td>
          <td v-if="editing" style="text-align:center;">{{ military_sort }}</td>
          <td v-else><input type="text" style="text-align:center; width:100%;" v-model="military_sort" placeholder="병역구분"></td>
        </tr>
        <tr>
          <td style="text-align:center;">계급</td>
          <td v-if="editing" style="text-align:center;">{{ military_class }}</td>
          <td v-else><input type="text" style="text-align:center; width:100%;" v-model="military_class" placeholder="계급"></td>
        </tr>
        <tr>
          <td style="text-align:center;">복무기간</td>
          <td v-if="editing" style="text-align:center;">{{ military_st_date }}</td>
          <td v-else><input style="text-align:center; width:100%;" type="text" v-model="military_st_date" placeholder="복무기간"></td>
        </tr>
        <tr>
          <td style="text-align:center;">메모</td>
          <td v-if="editing" style="text-align:center;">{{ memo }}</td>
          <td v-else><input style="text-align:center; width:100%;" type="text" v-model="memo" placeholder="메모"></td>
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
      career_myPic:'',
      military_class:'',
      military_st_date:'',
      military_sort:'',
      memo:'',
      selectedFile: '',
      downloadFileName: '',
    }
  },
  methods:{
    edit(){
      this.editing = !this.editing
    },
    editor(){
      var career_info = {
      'career_myPic':this.career_myPic,
      'military_class':this.military_class,
      'military_st_date':this.military_st_date,
      'military_sort':this.military_sort,
      'memo': this.memo
      }
      API.post('/careers/upload', career_info)
      .then(response => {
        
        this.career_myPic=response.data.career_myPic,
        this.military_class=response.data.military_class,
        this.military_st_date=response.data.military_st_date,
        this.military_sort=response.data.military_sort,
        this.memo=response.data.memo
      })
      .catch(error => {
        console.log(error)
      })
      this.editing = !this.editing
    },

    downloadFile() {
      var storageRef = firebase.storage().ref();

      // firebase storage의 업로드되어 있는 파일 다운로드 //
      API.get("/careers/downloadFile")
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

    setMyPicFromDB(){
      console.log(this.career_myPic)
      var storageRef = firebase.storage().ref();

        // firebase storage에 파일 업로드되면 사이트에도 반영 //
        storageRef
        .child(sessionStorage.getItem("user_id") + "/" + this.career_myPic)
        .getDownloadURL()
        .then(function(url) {
          var img_tag = document.getElementById("myPic");
          img_tag.src = url;
        });
    }
  },
    // 백에 넣은 데이터 가져오기
    mounted() {
      API.get("/careers/findOne")
        .then(response => {
          console.log(response);
          this.military_class = response.data.military_class
          this.military_st_date = response.data.military_st_date
          this.military_sort = response.data.military_sort
          this.career_myPic = response.data.career_myPic
          this.memo = response.data.memo

          setTimeout(() => {
            this.setMyPicFromDB()
          }, 100);
        })
        .catch(error => {
          console.log(error);
        });
  },
  watch: {
    selectedFile: function(selectedFile) {
      var storageRef = firebase.storage().ref();
      var user_id = sessionStorage.getItem("user_id");

      // firebase storage의 기존 파일 삭제 //
      if(this.career_myPic!=''){
        console.log(this.career_myPic)

        storageRef
        .child(user_id + "/" + this.career_myPic)
        .delete();
      }

      // firebase storage에 파일 업로드 //
      storageRef
      .child(user_id + '/' + selectedFile.name)
      .put(selectedFile);
      console.log("upload")

      // 업로드된 파일명 DB에 저장 //
      setTimeout(() => {
        const data = { career_myPic: selectedFile.name}
        API.post("/careers/uploadPic", data)
        .then(response=>{
          console.log("reload")
          this.career_myPic = response.data.career_myPic
          console.log(this.career_myPic)
          this.setMyPicFromDB();
        });
      }, 2000);
    },
  }
}
</script>

<style lang="scss" >
#write{
  position: relative;
  left: 10px;
}
.v-file-input__text{
  visibility: hidden;
}
.v-input__icon--clear{
  visibility:hidden;
}
</style>