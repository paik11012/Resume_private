<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px">{{award_title}}</th>
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
          <td width="150px">발급기관</td>
          <td v-if="editing">{{ award_org }}</td>
          <td v-else><input type="text" v-model="award_org" placeholder="highschool name"></td>
        </tr>
        <tr>
          <td width="150px">취득일자</td>
          <td v-if="editing">{{ award_date }}</td>
          <td v-else><input type="text" v-model="award_date" placeholder="education period"></td>
        </tr>
        <tr>
          <td width="150px">등급</td>
          <td v-if="editing">{{ award_prize }}</td>
          <td v-else><input type="text" v-model="award_prize" placeholder="education period"></td>
        </tr>
        <tr>
          <td width="150px">세부내용</td>
          <td v-if="editing">{{ award_detail }}</td>
          <td v-else><input type="text" v-model="award_detail" placeholder="education period"></td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import axios from 'axios'
export default {
  props:{
    id : {type:Number},
    award_org : {type:String},
    award_title : {type:String},
    award_date : {type:String},
    award_prize : {type:String},
    award_detail : {type:String},
  },
  mounted(){
    console.log(this.id);
    console.log(this.award_org)
  },
  data(){
    return{
      editing:true
    }
  },
  methods:{
    del(){
      console.log(this.award_title,"삭제예정")
      const SERVER_IP = 'http://70.12.247.99:8080'
      axios.delete(SERVER_IP + `/awards/del/${this.id}`,
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
  
}
</script>

<style lang="scss">
</style>