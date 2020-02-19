<template>
<div class="container userinfo">
  <div class="rowss">
    <div class="col-xs-12 col-md-12 col-sm-12">
      <div class="img-profile pulse" id="profile_img" @click="uploadProfileImg">
      </div>
    </div>
    <div class="col-xs-12 col-md-12 col-sm-12">
      <span style="font-size: small;text-align:center">빈 원을 클릭하면 프로필 사진을 업로드 할 수 있습니다.<br></span>
      <h1 id="id" class="text-center">{{ user_id }}</h1>
    </div>
    <div class="col-xs-12 col-md-12 col-sm-12">
      <p id="edit" class="text-center">Name: <input type="text" class="line" required v-model="user_name" placeholder="Name"></p>
      <p id="edit" class="text-center">Phone: <input type="text" class="line" required v-model="user_phone" placeholder="Phone"></p>
      <p id="edit" class="text-center"><input type="password" class="line" required v-model="user_password" placeholder="Password"></p>
      <p id="edit" class="text-center"><input type="password" class="line" required v-model="user_password_re" placeholder="Password Check"></p>
      <span v-if="verify_password" style="color:green; font-size: small;">비밀번호가 일치합니다.<br></span>
      <span v-else style="color:red; font-size: small;">비밀번호가 일치하지 않습니다.<br></span>
      <v-btn class="ma-2" tile outlined small color="black" id="infochange" @click="editDone">Edit Done</v-btn>
      <v-btn class="ma-2" tile outlined small color="black" id="infochange" @click="cancel">Cancel</v-btn>
      <v-btn class="ma-2" tile outlined small color="black" id="infochange" @click="withdraw">회원탈퇴</v-btn>
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
import swal from 'sweetalert';
export default {
  data() {
    return {
      user_id: "",
      user_password: "",
      user_password_re: "",
      user_name: "",
      user_phone:"",
      user_profile_img: "",
      selectedFile: "",
      verify_password: false,
      confirm:false,
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
  },
  methods:{
    editDone(){
        if(this.user_password==this.user_password_re){
          var filename = '';
          if(this.selectedFile.name=='' | this.selectedFile.name==null) filename = this.user_profile_img
          else filename = this.selectedFile.name

          var data = {
              user_id : this.user_id,
              user_password : this.user_password,
              user_name : this.user_name,
              user_phone : this.user_phone,
              user_profile_img : filename
          }
          API.put("/users/update", data)
          .then(res=>{
            console.log(res)
          })
          // firebase storage에 파일 업로드 //
          var storageRef = firebase.storage().ref();
          var user_id = sessionStorage.getItem("user_id");

          // firebase storage의 기존 파일 삭제 //
          if(this.selectedFile.name!='' & this.selectedFile.name!=null){
            storageRef
            .child(user_id + "/" + this.selectedFile.name)
            .delete();

            storageRef
            .child(user_id + '/' + this.selectedFile.name)
            .put(this.selectedFile);
          }
          this.$emit('modified', data)
          // data를 app.vue로 보내기
          router.replace("userinfo")
        }
        else{
            swal("비밀번호를 확인하세요.");
        }
    },
    cancel(){
        router.push("userinfo")
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
    },
    withdraw() {
      const user_id = this.user_id
      swal(`${this.user_name}님 탈퇴할거냐옹? ( =ノωヽ=)`,{
        buttons: ['Cancel', 'Yes']
      }).then((Yes) => {
        if (Yes) {
          API.delete(`users/deleteByAdmin/${user_id}`)
          .then(response => {
            swal(`$탈퇴가 완료되었습니다.`)
          })
          .catch(error => {
            console.log(error)
          })
        location.replace('/')
        }
      })
    }
  },
  watch:{
    selectedFile: function(selectedFile) {
      // this.new_user_profile_img = selectedFile.name
      var tmp = document.querySelector("#profile_img")
      var img_url = URL.createObjectURL(this.selectedFile)

      tmp.style.backgroundImage =  "url('" + img_url + "')";
    },
    user_password_re: function(user_password_re){
        if(this.user_password==user_password_re){
            this.verify_password = true
        }
        else{
            this.verify_password = false
        }
    }
  }
};
</script>

<style lang="scss">

@import url('https://fonts.googleapis.com/css?family=Clicker+Script|PT+Sans:700');
.userinfo{
  p{
    font-size: 17px;
    font-family: 'Fredoka One', cursive;
  }
  #id{
    font-family: 'Clicker Script', cursive !important;
    font-size:57px;
  }
  #infochange{
  margin-right: auto !important;
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
#edit{
  font-family: 'Jua';
  font-size: 18px;
}

.line{
  border-style:none;
  border-bottom:solid 1px #cacaca;
  border-collapse:collapse;
}

.swal-button--confirm{
    background: rgb(146, 168, 209);
}
// .swal-button--confirm:not([disabled]):hover {
//     background: rgb(252, 186, 185);
// }
</style>