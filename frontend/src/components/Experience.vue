<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th colspan="5" v-if="editing" style="font-size:20px; font-family:Jua">{{ exp_org_name }}</th>
          <th v-else style="font-size:20px; font-family:Jua" colspan="6"><input type="text" v-model="exp_org_name"></th>
          <th class="layout hold" style="padding:0">
            <v-btn @click="del" v-if="editing" small fab dark color="#F7CAC9" class="delkey">
              <v-icon>delete</v-icon>
            </v-btn>
            <v-btn @click="editor" v-if="editing" small fab dark color="#92A8D1" class="edu_write">
              <v-icon>edit</v-icon>
            </v-btn>
            <v-btn v-else @click="addExp" small fab class="edu_write" color="success">
              <v-icon>check</v-icon>
            </v-btn>

          </th>
        </tr>
      </thead>
      <tbody>
        <tr> 
          <td width="150px">기간</td>
          <td v-if="editing" colspan="2">{{ exp_org_period }}</td>
          <td v-else colspan="2"><input class="input" type="text" v-model="exp_org_period"></td>
        </tr>
        <tr>
          <td width="150px">경험/경력 타입</td>
          <td v-if="editing" colspan="2">{{ exp_org_type }}</td>
          <td v-else colspan="2"><input class="input" type="text" v-model="exp_org_type"></td>
        </tr>
        <tr>
          <td width="150px">역할</td>
          <td v-if="editing" colspan="2">{{ exp_org_role }}</td>
          <td v-else colspan="2"><input class="input" type="text" v-model="exp_org_role" placeholder="역할"></td>
        </tr>
        <tr>
          <td width="150px">세부내용</td>
          <td v-if="editing" colspan="2"><textarea v-model="exp_org_detail" readonly cols=100% rows="3"></textarea></td>
          <td v-else colspan="2"> <textarea class="input" v-model="exp_org_detail" cols=100% rows="3"></textarea> </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import API from "../services/Api"
export default {
  props:{
    id : {type:Number},
    exp_org_name : {type:String},
    exp_org_type : {type:String},
    exp_org_period : {type:String},
    exp_org_role : {type:String},
    exp_org_detail : {type:String},
  },
  data(){
    return{
      editing:true,
    }
  },
  methods:{
    del(){
      console.log("삭제예정")
      API.delete(`/exp/del/${this.id}`)
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
    addExp() {  // add and edit awards
      var exp = {
        'id': this.id,
        'exp_org_name' : this.exp_org_name,
        'exp_org_type' : this.exp_org_type,
        'exp_org_period' : this.exp_org_period,
        'exp_org_role' : this.exp_org_role,
        'exp_org_detail' : this.exp_org_detail,
      }
      API.post('/exp/update', exp)
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
.input{
  border-style:none;
  // border-bottom:solid 1px #cacaca;
  border-collapse:collapse;
  width:100%; height:100%;} 
textarea{
  width:100%;
  // resize: none;
}
</style> 