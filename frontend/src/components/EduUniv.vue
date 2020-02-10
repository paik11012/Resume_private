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
            <v-btn v-else v-on:click="addEduUniv" small fab id="write" color="success">
              <v-icon>check</v-icon>
            </v-btn>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td width="150px">학교명</td>
          <td v-if="editing">{{ edu_school_name }}</td>
          <td v-else><input type="text" v-model="edu_school_name" placeholder="univ name"></td>
        </tr>
        <tr>
          <td width="150px">재학기간</td>
          <td v-if="editing">{{ edu_school_st_date }}</td>
          <td v-else><input type="text" v-model="edu_school_st_date" placeholder="education period"></td>
        </tr>
        <tr>
          <td width="150px">전공</td>
          <td v-if="editing">{{ edu_detail_major_sort }}</td>
          <td v-else><input type="text" v-model="edu_detail_major_sort" placeholder="major"></td>
        </tr>
        <tr>
          <td width="150px">이수학점</td>
          <td v-if="editing">{{ edu_detail_credit }}</td>
          <td v-else><input type="number" v-model="edu_detail_credit" placeholder="credit"></td>
        </tr>
        <tr>
          <td width="150px">총 평점</td>
          <td v-if="editing">{{ edu_detail_grade }}</td>
          <td v-else><input type="text" v-model="edu_detail_grade" placeholder="grade"></td>
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
      edu_school_name: '',
      edu_school_st_date: '',
      edu_school_sort: '2',
      edu_detail_credit: null,
      edu_detail_grade: null,
      edu_detail_major_sort: ''
    }
  },
  methods:{
    editor(){
      this.editing = !this.editing
    },
    addEduUniv() {
      var u_education = {
        'edu_school_sort': this.edu_school_sort,
        'edu_school_name': this.edu_school_name,
        'edu_school_st_date': this.edu_school_st_date,
        'edu_school_ed_date': '',
      }
      var u_detail = {
        'edu_detail_grade': this.edu_detail_grade,
        'edu_detail_major_sort': this.edu_detail_major_sort,
        'edu_detail_credit': this.edu_detail_credit
      }
      var u_data = { education: u_education, education_detail: u_detail }
      const SERVER_IP = 'http://70.12.247.99:8080'
      axios.post(SERVER_IP + '/edu/upload', u_data,
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
}
</script>

<style lang="scss">

</style>