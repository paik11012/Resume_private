<template>
<div class="container">
  <div class="rowss">
    <div class="col-xs-12 col-md-12 col-sm-12">
      <div class="img-profile pulse">
      </div>
    </div>
    <div class="col-xs-12 col-md-12 col-sm-12">
      <h1 id="id" class="text-center">{{ user_id }}</h1>
    </div>

    <div id="testt">
      <input type="file" multiple accept="image/jpeg" @change="detectFiles($event.target.files)">
    </div>

    <div class="col-xs-12 col-md-12 col-sm-12">
      <!-- 이름 전화번호 비밀번호 바꾸기 가능 -->
      <p class="text-center">{{ user_name }}</p>
      <p class="text-center">전화번호</p>
      <p class="text-center">RESUME  {{ user_resume_number }}</p>
      <p class="text-center">INTERVIEW {{ user_interview_number }} </p>
    </div>
  </div>
</div>
</template>

<script>

export default {
  data() {
    return {
      user_id: "",
      user_resume_number: '',
      user_interview_number: "5",
      user_name: "",
      user_phone_number:""
    };
  },
  mounted() {
    const storage = window.sessionStorage;
      this.user_id = storage.user_id;
      console.log(this.$store.state.user_info)
      // this.user_name = .user_name;
  }
};
</script>

<style>
@import url('https://fonts.googleapis.com/css?family=Clicker+Script|PT+Sans:700');

#id{
  font-family: 'Clicker Script', cursive;
  font-size:82px;
}

.img-profile{
  background:url(https://thechive.com/wp-content/uploads/2018/04/cats-sleeping-in-perfect-circles-19.jpg?quality=85&strip=info&w=600);
  background-size: 100% 100%;
  height: 200px;
  width: 200px;
  border-radius: 50%;
  -moz-border-radius: 50%;
  -webkit-border-radius: 50%;
  margin: 10px auto;

}
.rowss{
  padding:50px;
}
.caption{
    font-family: "PT Sans", sans-serif;
    font-size: 25px;
    text-transform: uppercase;
    letter-spacing: 6px;
}
.caption:before, .caption:after {
    content: " ";
    position: absolute;
    width: 50px;
    height: 10px;
}


.pulse{
   box-shadow: 0 0 0 0 rgb(231, 231, 231);
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
  font-size:62px;
}
}
@media (max-width: 768px){ 
  h1{
  font-size:42px;
}
  .caption{
    font-size:14px;
  }
  .caption:before{
    border:none;
  }
  .caption:after{
    border:none;
  }
}

</style>