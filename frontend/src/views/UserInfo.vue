<template>
  <div>
    <v-container>
    <h2 style="text-align:center">User Info</h2>
    <img
      src="https://cdn.pixabay.com/photo/2017/02/20/18/03/cat-2083492_1280.jpg"
      alt="cat"
      id="cat"
    />
    <h3 style="text-align:center">{{ user_id }}</h3>
    <h3 style="text-align:center">{{ user_email }}</h3>
    <h3 style="text-align:center">resumes: {{ user_resume_number }}</h3>
    <h3 style="text-align:center">interviews: {{ user_interview_number }}</h3>
    <v-btn class="ma-2" outlined color="black">탈퇴하기</v-btn>
    </v-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user_id: "temp",
      user_email: "e@com",
      user_resume_number: "3",
      user_interview_number: "5"
    };
  }
};
</script>

<style scoped>
#cat {
  border-radius: 40px;
  width: 30vw;
  text-align: center;
}
</style>