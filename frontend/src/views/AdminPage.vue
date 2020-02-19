<template>
  <div v-if="user_authority == 'admin'" class="admin">
    <v-container>
      <h1 style="font-family:Jua">Users</h1>
      <br>
      <ul>
        <li id="users">index / user_id / user_name / user_authority / signup date</li>
        <li v-for="(user, index) in users" v-bind:key="user.user_id" id="users">
          {{ index+1 }}  / {{ user.user_id }} / {{ user.user_name }} / {{user.user_authority}} / {{ user.signUpDate }} 
          <v-btn small outlined color="error" @click="withdraw([user.user_id])">탈퇴</v-btn>
        </li>
      </ul>
    </v-container>
  </div>
</template>

<script>
import API from "../services/Api"
import swal from 'sweetalert';

export default {
  data() {
    return{
      users: [],
      signUpDate: null,
      user_id: null,
      user_name: null,
      user_authority: null
    }
  },
  created(){
    var person = window.sessionStorage.getItem("user_authority")
    if (person != "admin"){
      alert("관리자만 접근할 수 있습니다.")
      location.replace('/')
    } else {
      this.user_authority = person
    }
  },
  mounted() {
    API.get('/users/findAll')
    .then(res => {
      this.users = res.data
    })
  },
  methods: {
    withdraw(user_id) {
      API.delete(`users/deleteByAdmin/${user_id}`)
      .then(response => {
        swal(`${user_id}가 탈퇴되었습니다.`)
      })
      .catch(error => {
        console.log(error)
      })
    }
  }
}
</script>

<style>
#users{
  font-size:18px !important;
  font-family:Jua;
  margin-top: 5px;
}

.swal-button--confirm{
    background: rgb(146, 168, 209);
}
/* .swal-button--confirm:not([disabled]):hover {
    background: rgb(252, 186, 185);
} */
.admin{
  margin-top: 80px; 
}
</style>