<template>
  <div>
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
        alert(`${user_id}가 탈퇴되었습니다.`)
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
}
</style>