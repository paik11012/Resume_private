<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th style="position:relative;">
            <div>
            <ul class="menubar"> 
                 <!-- eslint-disable -->
              <li
                class="layout row" style="font-size:20px;"
              ><input type="text" v-model="award_title" placeholder="Title">
                <!-- eslint-disable -->
              </li>
            </ul>
            </div>
          </th>
          <th class="layout justify-end">
            <v-btn @click="create" small fab id="write" color="success">
              <v-icon>check</v-icon>
            </v-btn>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td width="150px">발급기관</td>
          <td><input type="text" v-model="award_org" placeholder="기관명"></td>
        </tr>
        <tr>
          <td width="150px">취득일자</td>
          <td><input type="text" v-model="award_date" placeholder="취득일자"></td>
        </tr>
        <tr>
          <td width="150px">등급/점수</td>
          <td><input type="text" v-model="award_prize" placeholder="등급/점수"></td>
        </tr>
        <tr>
          <td width="150px">세부내용</td>
          <td><input type="text" v-model="award_detail" placeholder="세부내용"></td>
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
      award_org:'',
      award_title:'',
      award_detail:'',
      award_prize:'',
      award_date:'',
    }
  },
  methods:{
    create(){
      var set = {
      'award_org': this.award_org,
      'award_title': this.award_title,
      'award_detail':this.award_detail,
      'award_prize':this.award_prize,
      'award_date': this.award_date,
      }
      const SERVER_IP = 'http://70.12.247.99:8080'
      axios.post(SERVER_IP + '/awards/save', set,
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
      setTimeout(() => {
        this.$emit('createa')
      }, 500);
    }
  }
}
</script>

<style lang="scss">
@import "@/assets/scss/mystyle.scss"; 

#write{
  position: relative;
  left: 10px;
}

</style>