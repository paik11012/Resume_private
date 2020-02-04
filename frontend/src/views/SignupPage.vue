<template>
  <div>
    <v-container>
      <!--  v-model="valid" lazy-validation -->
      <v-form ref="form">
        <br />
        <br />
        <h1 style="text-align:center; font-size:3vw; font-family: system-ui;">SIGN UP</h1>
        <v-text-field v-model="userId" :counter="10" :rules="nameRules" label="User Id" required></v-text-field>
        <v-text-field v-model="password" type="password" :rules="passwordRules" label="Password" required></v-text-field>
        <v-text-field v-model="phonenumber" :rules="emailRules" label="Phone Number" required></v-text-field>
        <v-text-field v-model="name" :rules="emailRules" label="Name" required></v-text-field>
        <v-text-field v-model="email" :rules="emailRules" label="E-mail" required></v-text-field>

        <v-btn id="signupbutton" color="primary" class="mr-4 justify-center" @click="validate">Sign Up</v-btn>
      </v-form>
    </v-container>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      userData: {
        userId: '',
        password: '',
        name: '',
        email: '',
        phonenumber:'',
      },
      storeData: 'Yes',
    }
  },
  methods: {
    submitted() {
      this.isSubmitted = true;
    }
  }
}
</script>

<style>

</style>