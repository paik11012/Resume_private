<template>
<div class="container userinfo">
  <div class="rowss">
    <div class="col-xs-12 col-md-12 col-sm-12">
      <div class="img-profile pulse" id="profile_img">
      </div>
    </div>
    <div class="col-xs-12 col-md-12 col-sm-12">
      <h1 id="id" class="text-center">{{ user_id }}</h1>
    </div>
    <div class="col-xs-12 col-md-12 col-sm-12">
      <!-- 이름 전화번호 비밀번호 바꾸기 가능 -->
      <p id="name" class="text-center">Name: {{ user_name }}</p>
      <p id="name" class="text-center">Phone: {{ user_phone }}</p>
      <p id="name" class="text-center">Resumes  {{ user_resume_number }}  /  Interviews {{ user_interview_number }}</p>
      <v-btn class="ma-2" tile outlined small color="black" id="infochange" @click="editInfo">
       <v-icon left>mdi-pencil</v-icon> Edit Info
      </v-btn>
    </div>
  </div>
</div>
</template>

<script>
import API from "../services/Api"

import { app } from "../services/FirebaseService";
import firebase, { storage } from "firebase/app";
import "firebase/firestore";
import "firebase/storage";
import router from '../router'

export default {
  data() {
    return {
      user_id: "",
      user_resume_number: 0,
      user_interview_number: 0,
      user_name: "",
      user_phone:"",
      user_profile_img: "",
    };
  },
  mounted() {
      let id = sessionStorage.getItem("user_id")
      API.get(`/users/findOne/${id}`)
      .then(res => {
        const userInfo = {
          user_id : res.data.user_id,
          user_phone : res.data.user_phone,
          user_name : res.data.user_name,
          user_profile_img : res.data.user_profile_img
        }
        this.user_id = userInfo.user_id;
        this.user_phone = userInfo.user_phone;
        this.user_name = userInfo.user_name;
        this.user_profile_img = userInfo.user_profile_img;

        if(this.user_profile_img!=null & this.user_profile_img!=''){
          setTimeout(() => {
            this.setMyPicFromDB()
          }, 100);
        }
      });

      API.get("/interview/getLength")
      .then(res=>{
        this.user_resume_number = res.data.n_resume;
        this.user_interview_number = res.data.n_interview;
      })
  },
  methods:{
    editInfo(){
      router.push("editinfo")
    },
    uploadProfileImg(){
      var file_input = document.createElement("input");
      file_input.type = "file";
      file_input.setAttribute("accept", "image/*");

      file_input.onchange = event => { 
        this.selectedFile = event.target.files[0];
      }
      file_input.click();
    },
    setMyPicFromDB(){
      var storageRef = firebase.storage().ref();

      // firebase storage에 파일 업로드되면 사이트에도 반영 //
      storageRef
      .child(sessionStorage.getItem("user_id") + "/" + this.user_profile_img)
      .getDownloadURL()
      .then(function(img_url) {
        var profile_div = document.getElementById("profile_img");
        profile_div.style.backgroundImage = "url('" + img_url + "')";
        // profile_div.style.backgroundSize = "100% 100%";
      });
    }
  },
};
</script>

<style lang="scss">

@import url('https://fonts.googleapis.com/css?family=Clicker+Script|PT+Sans:700');
.userinfo{
  margin-top: 80px;
  p{
    font-size: 17px;
    font-family: 'Fredoka One', cursive;
  }
  #id{
    font-family: 'Clicker Script', cursive !important;
    font-size:72px;
  }
  #infochange{
  margin-right: auto !important;
  }
  #name{
   font-family: 'Jua';
   font-size: 25px;
  }
}

.img-profile{
  // background:url(https://thechive.com/wp-content/uploads/2018/04/cats-sleeping-in-perfect-circles-19.jpg?quality=85&strip=info&w=600);
  background-size: 100% 100%;
  height: 200px;
  width: 200px;
  border-radius: 50%;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  margin: 10px auto;
}
.rowss{
  padding:2px;
}

.pulse{
   box-shadow: 0 0 0 0 #92A8D1;
  -webkit-animation: pulse 1.5s infinite cubic-bezier(0.66, 0, 0, 1);
  -moz-animation: pulse 1.5s infinite cubic-bezier(0.66, 0, 0, 1);
  -ms-animation: pulse 1.5s infinite cubic-bezier(0.66, 0, 0, 1);
  animation: pulse 1.5s infinite cubic-bezier(0.66, 0, 0, 1);
}
.pulse:hover {
  -webkit-animation: none;-moz-animation: none;-ms-animation: none;animation: none;
}
@-webkit-keyframes pulse {to {box-shadow: 0 0 0 45px rgba(232, 76, 61, 0);}}
@-moz-keyframes pulse {to {box-shadow: 0 0 0 45px rgba(232, 76, 61, 0);}}
@-ms-keyframes pulse {to {box-shadow: 0 0 0 45px rgba(232, 76, 61, 0);}}
@keyframes pulse {to {box-shadow: 0 0 0 45px rgba(232, 76, 61, 0);}}


@media (max-width: 992px){ 
  h1{
  font-size:52px;
}
}
@media (max-width: 768px){ 
  h1{
  font-size:42px;
}
  .caption{
    font-size:20px;
  }
  .caption:before{
    border:none;
  }
  .caption:after{
    border:none;
  }
}

</style>