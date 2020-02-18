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
                class="layout row" style="font-size:20px;font-family:Jua"
              ><input type="text" class="input" v-model="exp_org_name" style="font-family:Jua" placeholder="Experience Title">
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
          <td width="150px">기간</td>
          <td><input type="text" class="input" v-model="exp_org_period" placeholder="기간"></td>
        </tr>
        <tr>
          <td width="150px">경험/경력 타입</td>
          <td><input type="text" class="input" v-model="exp_org_type" placeholder="동아리/봉사/경력"></td>
        </tr>
        <tr>
          <td width="150px">역할</td>
          <td><input type="text" class="input" v-model="exp_org_role" placeholder="역할"></td>
        </tr>
        <tr>
          <td width="150px">세부내용</td>
          <td><textarea name="" id="" v-model="exp_org_detail" cols="20" rows="3"></textarea>
            </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import API from "../services/Api"
export default {
  data(){
    return{
      editing:true,
      exp_org_name:'',
      exp_org_type: '',
      exp_org_period:'',
      exp_org_role:'',
      exp_org_detail:'',
    }
  },
  methods:{
    create(){
      var exp_data = {
      'exp_org_name': this.exp_org_name,
      'exp_org_type': this.exp_org_type,
      'exp_org_period':this.exp_org_period,
      'exp_org_role':this.exp_org_role,
      'exp_org_detail': this.exp_org_detail,
      }
      API.post('/exp/save', exp_data)
      .then(response => {
        console.log(response)
      })
      .catch(error => {
        console.log(error)
      })
      setTimeout(() => {
        this.$emit('createe')
      }, 500);
    },
    
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