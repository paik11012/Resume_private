<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px; font-family:Jua">HighSchool</th>
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
          <td v-if="editing">{{ sch_name }}</td>
          <td v-else><input type="text" v-model="sch_name" placeholder="highschool name"></td>
        </tr>
        <tr>
          <td width="150px">재학기간</td>
          <td v-if="editing">{{ sch_std }} ~ {{ sch_edd }}</td>
          <td v-else><input type="text" v-model="sch_std"> ~ <input type="text" v-model="sch_edd"> </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
import API from "../services/Api"
export default {
  // computed:{
  //   period:{
  //     get() {
  //       return `${this.sch_std} ${this.sch_edd}`;
  //     },
  //     set(newValue) {
  //       const m = newValue.match(/(\S*)\s+(.*)/);
  //       this.sch_std = m[1];
  //       this.sch_edd = m[2];
  //     }
  //   }
  // },
  props:{
    education_id:{type:Number},
    edu_school_name:{type:String},
    edu_school_sort:{type:String}, // 1이 고등학교 2가 대학교 3이 대학원 4가 편입,
    edu_school_st_date:{type:String},
    edu_school_ed_date:{type:String},
  },
  data(){
    return{
      editing:true,
      sch_name:this.edu_school_name,
      sch_sort:this.edu_school_sort,
      sch_std:this.edu_school_st_date,
      sch_edd:this.edu_school_ed_date,
    }
  },
  methods:{
    del(){
      console.log(this.education_id,"삭제예정")
      API.delete(`/edu/deleteOne/${this.education_id}`)
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
      console.log(this.sch_std, this.sch_edd);
      var education = {
        'edu_id': String(this.education_id),
        'edu_school_sort': this.edu_school_sort,
        'edu_school_name': this.edu_school_name,
        'edu_school_st_date': this.sch_std,
        'edu_school_ed_date': this.sch_edd,
      }
      console.log(education);
      
      var e_data = { education: education }
      API.post('/edu/upload', e_data)
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
.hold{
  position: relative;
}
.edu_write{
  position: absolute;
  right: 5px;
}
.delkey{
  position: absolute;
  right: 55px;
}

</style>