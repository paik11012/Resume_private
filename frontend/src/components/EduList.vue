<template>
<div>
    <eduh v-for="hi in high.length" :key="hi" class="corners2"  
      :edu_school_name="high[hi-1].edu_school_name"
      :edu_school_sort="high[hi-1].edu_school_sort"
      :edu_school_st_date="high[hi-1].edu_school_st_date"
      :edu_school_ed_date="high[hi-1].edu_school_ed_date"
    />
  <eduu v-for="ui in univ.length" :key="ui" class="corners2"
    :edu_school_name="univ[ui-1].edu_school_name"
    :edu_school_sort="univ[ui-1].edu_school_sort"
    :edu_school_st_date="univ[ui-1].edu_school_st_date"
    :edu_school_ed_date="univ[ui-1].edu_school_ed_date"
    :edu_detail_major_sort="univ[ui-1].edu_detail_major_sort"
    :edu_detail_grade="univ[ui-1].edu_detail_grade"
    :edu_detail_credit="univ[ui-1].edu_detail_credit"
    :edu_detail_id="univ[ui-1].edu_detail_id"
  />
</div>




</template>
<script>
import axios from 'axios'
import eduh from './EduHigh'
import eduu from './EduUniv'
export default {
  components:{
    eduh,eduu,
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
        console.log(response)
        for (var [key,value] of Object.entries(response.data)){
          console.log(value);
          
          for (var [key1, value1] of Object.entries(value)){
            // console.log(value1)
            this.edu_school_name = value1[0]["education"]["edu_school_name"]
            this.edu_school_st_date = value1[0]["education"]["edu_school_st_date"]
            this.edu_school_ed_date = value1[0]["education"]["edu_school_ed_date"],
            this.edu_school_sort = value1[0]["education"]["edu_school_sort"], // 1이 고등학교 2가 대학교 3이 대학원 4가 편입
            this.edu_detail_major_sort = value1[0]["edu_detail_major_sort"],
            this.edu_detail_grade = value1[0]["edu_detail_grade"],
            this.edu_detail_credit = value1[0]["edu_detail_credit"],
            this.edu_detail_id = value1[0]["edu_detail_id"]
            if(this.edu_school_sort == 1){
              this.high.push({edu_school_name:this.edu_school_name, edu_school_st_date:this.edu_school_st_date, edu_school_sort:this.edu_school_sort, edu_school_ed_date:this.edu_school_ed_date})
            } else {
              this.univ.push({edu_school_name:this.edu_school_name, edu_school_st_date:this.edu_school_st_date, edu_school_sort:this.edu_school_sort, edu_school_ed_date:this.edu_school_ed_date, edu_detail_major_sort:this.edu_detail_major_sort, edu_detail_grade:this.edu_detail_grade, edu_detail_credit:this.edu_detail_credit, edu_detail_id:this.edu_detail_id})
            }
          }
        }
      })
      .catch(error => {
        console.log(error)
      })
    },  
  data(){
    return{
      editing:true,
      edu_school_sort: '', // 1이 고등학교 2가 대학교 3이 대학원 4가 편입
      edu_school_name:'',
      edu_school_st_date:'',
      edu_school_ed_date:'',
      edu_detail_major_sort:'',
      edu_detail_grade:'',
      edu_detail_credit:'',
      edu_detail_id:'',
      high:[],
      univ:[],
    }
  },
}
</script>

<style lang="scss">
.corners2{
  border-radius: 15px;
  border: 1px solid #92a8d1;
  padding: 15px;
  width: 100%;
  margin-bottom: 25px;
}

</style>