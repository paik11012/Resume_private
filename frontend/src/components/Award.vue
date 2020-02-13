<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px">HighSchool{{asd}}</th>
          <th class="layout hold">
            <v-btn @click="editor" v-if="editing" small fab dark color="cyan" class="edu_write">
              <v-icon>edit</v-icon>
            </v-btn>
            <v-btn v-else @click="addEduHigh" small fab class="edu_write" color="success">
              <v-icon>check</v-icon>
            </v-btn>
            <v-btn @click="del" v-if="editing" small fab dark color="red" class="delkey">
              <v-icon>delete</v-icon>
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
          <td v-if="editing">{{ period }}</td>
          <td v-else><input type="text" v-model="period" placeholder="education period"></td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import API from "../services/Api"
export default {
  computed:{
    period:{
      get() {
        return `${this.edu_school_st_date} ${this.edu_school_ed_date}`;
      },
      set(newValue) {
        const m = newValue.match(/(\S*)\s+(.*)/);
        this.edu_school_st_date = m[1];
        this.edu_school_ed_date = m[2];
      }
    }
  },
  props:{
    id : {type:Number},
    award_org : {type:String},
    award_title : {type:String},
    award_date : {type:String},
    award_prize : {type:String},
    award_detail : {type:String},
  },
  data(){
    return{
      editing:true
    }
  },
  methods:{
    del(){
      console.log(this.education_id,"삭제예정")
      API.delete(`/edu/deleteOne/${this.education_id}`,
      {headers : {
      'token' : window.sessionStorage.getItem("jwt-auth-token"),
      'user_id': window.sessionStorage.getItem("user_id")}}
      )
      .then(response => {
        console.log(response)
        this.$emit('delete')
      })
      .catch(error => {
        console.log(error)
      })
    },
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
      API.post('/edu/upload', e_data,
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