<template>
<div>
  <eduh class="corners2"/>
  <eduu class="corners2"/>
</div>




</template>
<script>
import axios from 'axios' 
import eduh from './EduHigh'
import eduu from './EduUniv'
export default {
  components:{
    eduh,eduu,
  },
mounted() {
    // const id = window.sessionStorage.getItem('user_id')
    const SERVER_IP = 'http://70.12.247.99:8080'
    axios.get(SERVER_IP + `/edu/findAll`, {
    headers: {
          token: window.sessionStorage.getItem("jwt-auth-token"),
          user_id: window.sessionStorage.getItem("user_id")
    }})
    .then(response => {
      console.log(response);
      for (var [key,value] of Object.entries(response.data)){
        // console.log(value)
        for (var [key1, value1] of Object.entries(value)){
          if (value1[0]["education"]["edu_school_sort"] == "1")
          this.edu_school_name = value1[0]["education"]["edu_school_name"]
          this.edu_school_st_date = value1[0]["education"]["edu_school_st_date"]}
        }
      })
    .catch(error => {
      console.log(error)
    })
  }
}
</script>

<style lang="scss">
.corners2{
  border-radius: 15px;
  border: 1px solid #92a8d1;
  padding: 15px;
  width: 100%;
  margin-bottom: 25px;
}

</style>