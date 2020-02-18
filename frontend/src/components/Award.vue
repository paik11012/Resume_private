<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th colspan="5" v-if="editing" style="font-size:20px; font-family:Jua">{{award_title}}</th>
          <th v-else style="font-size:20px; font-family:Jua" colspan="6"><input type="text" v-model="award_title"></th>
          <th class="layout hold" style="padding:0">
            <v-btn @click="editor" v-if="editing" small fab dark color="cyan" class="edu_write">
              <v-icon>edit</v-icon>
            </v-btn>
            <v-btn v-else @click="addAward" small fab class="edu_write" color="success">
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
          <td v-if="editing" colspan="2">{{ award_org }}</td>
          <td v-else colspan="2"><input type="text" v-model="award_org" ></td>
        </tr>
        <tr> 
          <td width="150px">취득일자</td>
          <td v-if="editing" colspan="2">{{ award_date }}</td>
          <td v-else colspan="2"><input type="text" v-model="award_date"></td>
        </tr>
        <tr>
          <td width="150px">등급</td>
          <td v-if="editing" colspan="2">{{ award_prize }}</td>
          <td v-else colspan="2"><input type="text" v-model="award_prize"></td>
        </tr>
        <tr>
          <td width="150px">세부내용</td>
          <td v-if="editing" colspan="2">{{ award_detail }}</td>
          <td v-else colspan="2"><input type="text" v-model="award_detail" placeholder="education period"></td>
        </tr>
        <tr>
          <td width="150px">파일</td>
          <td v-if="editing" colspan="2">{{ award_file }}</td>
          <td v-else colspan="2"><input type="text" v-model="award_file" placeholder="education period"></td>
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
    award_org : {type:String},
    award_title : {type:String},
    award_date : {type:String},
    award_prize : {type:String},
    award_detail : {type:String},
    award_file : {type:String},
  },
  mounted(){
    console.log(this.id);
  },
  data(){
    return{
      editing:true,
      new_award_file : '',
    }
  },
  methods:{
    del(){
      console.log("삭제예정")
      API.delete(`/awards/del/${this.id}`)
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
    addAward() {  // add and edit awards
      var award = {
        'id': this.id,
        'award_org' : this.award_org,
        'award_title' : this.award_title,
        'award_date' : this.award_date,
        'award_detail' : this.award_detail,
        'award_prize' : this.award_prize,
        'award_file' : this.award_file,
      }
      console.log(award)
      API.put('/awards/update', award)
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