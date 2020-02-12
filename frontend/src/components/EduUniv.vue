<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px">{{sch_name}}</th>
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
          <td v-if="editing">{{ period }}</td>
          <td v-else><input type="text" v-model="period" placeholder="education period"></td>
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
  mounted(){
    console.log(typeof(this.edu_school_sort));
    if(this.edu_school_sort == 2){
      this.sch_name = 'University'
    } else if(this.edu_school_sort == 3){
      this.sch_name = 'Transfer'
    } else if(this.edu_school_sort==4){
      this.sch_name = 'GradSchool'
    }
    console.log(this.edu_school_sort, 
      this.edu_school_name,
      this.edu_school_st_date,
      this.edu_school_ed_date,
      this.edu_detail_major_sort,
      this.edu_detail_grade,
      this.edu_detail_credit,
      this.edu_detail_id);
    
  },
  props:{
    edu_school_sort:{type:String}, // 1이 고등학교 2가 대학교 3이 대학원 4가 편입
    edu_school_name:{type:String},
    edu_school_st_date:{type:String},
    edu_school_ed_date:{type:String},
    edu_detail_major_sort:{type:String},
    edu_detail_grade:{type:Number},
    edu_detail_credit:{type:Number},
    edu_detail_id:{type:Number}
  },
      
  data(){
    return{
      editing:true,
      sch_name:''
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
      API.post('/edu/upload', u_data)
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