<template>
  <v-simple-table v-if="$vuetify.breakpoint.mdAndUp">
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px" colspan="2">My Info
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

        <!-- 사진 업로드하는 부분 -->
        <tr>
          <!-- <td v-if="career_myPic == ''" style="width:30%;" rowspan="3">
            <div id="testt">
              <input
                type="file"
                multiple
                accept="image/jpeg"
                @change="detectFiles($event.target.files)"
              />
              <button @click="downloadImg">download</button>
              <img src id="imgtag" />
            </div>
          </td> -->

          <td style="width:30%;" rowspan="3"><img style="width:140px; height:170px; display: block; margin: 0px auto;" src="@/assets/권응.jpg"/></td>
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
          <!-- <td class="layout justify-center"><v-btn style="margin-top:6px;" color="success" outlined><v-icon dark>mdi-cloud-download</v-icon></v-btn></td> -->
          <td class="layout justify-center"><v-file-input v-model="selectedFile" accept="image/*" prepend-icon="mdi-cloud-download" height="1.8em"></v-file-input></td>
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
          <td style="width:40%; text-align:center;">사진 URL</td>
          <!-- <td class="layout justify-center"><v-btn style="margin-top:6px;" color="success" outlined><v-icon dark>mdi-cloud-download</v-icon></v-btn></td> -->
          <td class="layout justify-center"><v-file-input v-model="selectedFile" accept="image/*" prepend-icon="mdi-cloud-download" height="1.8em"></v-file-input></td>
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
      uploadTask: "",
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

    downloadImg() {
      var storage = firebase.storage();
      var storageRef = firebase.storage().ref();

      var gsReference = storage.refFromURL(
        "gs://web-9to6.appspot.com/벼리.jpg"
      );
      storageRef
        .child("벼리.jpg")
        .getDownloadURL()
        .then(function(url) {
          var xhr = new XMLHttpRequest();
          xhr.open("GET", url);

          xhr.responseType = "blob";
          xhr.onload = function(event) {
            var blob = xhr.response;
            console.log(xhr);
            var link = document.createElement("a");
            link.href = window.URL.createObjectURL(blob);
            link.download = blob;
            link.click();
          };
          xhr.send();
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
        console.log(response)
      })
      .catch(error => {
        console.log(error);
      });
  },
  watch: {
    selectedFile: function(selectedFile) {
      firebase
      .storage(app)
      .ref()
      .child(sessionStorage.getItem('user_id') + '/' + selectedFile.name)
      .put(selectedFile);

      const data = { career_myPic: selectedFile.name}
      API.post("/careers/uploadPic", data)
      .then(response=>{
        this.career_myPic = response.data.career_myPic
      });
    }
  }
}
</script>

<style lang="scss" >
#write{
  position: relative;
  left: 10px;
}

</style>