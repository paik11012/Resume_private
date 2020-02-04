<template>
  <div>
    <v-container>
      <!--  v-model="valid" lazy-validation -->
      <v-form ref="form">
        <br />
        <br />
        <h1 style="text-align:center; font-size:3vw; font-family: system-ui;">SIGN UP</h1>
        <v-text-field v-model="user_id" label="User Id" required></v-text-field>
        <v-text-field v-model="user_password" type="password" label="Password" required></v-text-field>
        <v-text-field v-model="user_phone" label="Phone Number"></v-text-field>
        <v-text-field v-model="user_name" label="Name" required></v-text-field>
        <v-text-field v-model="user_email" label="E-mail" required></v-text-field>
        <v-btn id="signupbutton" color="primary" class="mr-4 justify-center" @click="signup({user_id, user_email, user_password, user_phone, user_name})" value="회원가입">Sign Up</v-btn>
      </v-form>
    </v-container>
  </div>
</template>

<script>
import { mapActions } from 'vuex'


export default {
  data: function() {
    return {
      user_id: '',
      user_password: '',
      user_name: '',
      user_email: '',
      user_phone:'',
      storeData: 'Yes',
    }
  },
  methods: {
    submitted() {
      this.isSubmitted = true;
    },
    ...mapActions(["signup"])
    }
  }

</script>

<style>

</style>