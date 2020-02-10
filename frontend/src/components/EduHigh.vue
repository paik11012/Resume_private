<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px">HighSchool</th>
          <th class="layout justify-end">
            <v-btn v-on:click="editor" v-if="editing" small fab dark color="cyan" id="write">
              <v-icon dark>edit</v-icon>
            </v-btn>
            <v-btn v-else v-on:click="addEduHigh" small fab id="write" color="success">
              <v-icon>check</v-icon>
            </v-btn>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td width="150px">고등학교</td>
          <td v-if="editing">{{ edu_school_name }}</td>
          <td v-else><input type="text" v-model="edu_school_name" placeholder="highschool name"></td>
        </tr>
        <tr>
          <td width="150px">재학기간</td>
          <td v-if="editing">{{ edu_school_st_date }}</td>
          <td v-else><input type="text" v-model="edu_school_st_date" placeholder="education period"></td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import axios from 'axios'
export default {
  data(){
    return{
      editing:true,
      edu_school_sort: null, // 1이 고등학교 2가 대학교 3이 대학원 4가 편입
      edu_school_name:'',
      edu_school_st_date:'',
    }
  },
  methods:{
    editor(){
      this.editing = !this.editing
    },
    addEduHigh() {
      var education = {
        'edu_school_sort': '고등학교',
        'edu_school_name': this.edu_school_name,
        'edu_school_st_date': this.edu_school_st_date,
        'edu_school_ed_date': '',
      }
      var e_data = { education: education }
      const SERVER_IP = 'http://70.12.247.99:8080'
      axios.post(SERVER_IP + '/edu/upload', e_data,
      {headers : {
      'token' : window.sessionStorage.getItem("jwt-auth-token"),
      'user_id': window.sessionStorage.getItem("user_id")}}
      )
      .then(response => {
        console.log(response)
      })
      .catch(error => {
        console.log(error)
      })
      this.editing = !this.editing
    }
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
      // console.log(response.data[0]);
      for (var [key,value] of Object.entries(response.data)){
        // console.log(value)
        for (var [key1, value1] of Object.entries(value)){
          if (value1[0]["education"]["edu_school_sort"] == "고등학교")
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
#write{
  position: relative;
  left: 10px;
}

</style>