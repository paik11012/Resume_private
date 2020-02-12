<template>
<div>
    <v-flex v-for="i in school.length" :key="i">
    <transition name="bounce">
    <eduh class="corners2" 
      :edu_school_name="high[i-1].edu_school_name"
      :edu_school_sort="high[i-1].edu_school_sort"
      :edu_school_st_date="high[i-1].edu_school_st_date"
      :edu_school_ed_date="high[i-1].edu_school_ed_date"
      v-if="sec >= i & i == 1"
    />
    </transition>
    <transition name="bounce">
    <eduu  class="corners2"
    :edu_school_name="univ[i-1].edu_school_name"
    :edu_school_sort="univ[i-1].edu_school_sort"
    :edu_school_st_date="univ[i-1].edu_school_st_date"
    :edu_school_ed_date="univ[i-1].edu_school_ed_date"
    :edu_detail_major_sort="univ[i-1].edu_detail_major_sort"
    :edu_detail_grade="univ[i-1].edu_detail_grade"
    :edu_detail_credit="univ[i-1].edu_detail_credit"
    :edu_detail_id="univ[i-1].edu_detail_id"
    v-if="sec >= i+1 & i > 0"
  />
  </transition>
  </v-flex>
</div>

</template>
<script>
import API from "../services/Api"
import eduh from './EduHigh'
import eduu from './EduUniv'
export default {
  components:{
    eduh,eduu,
  },
  mounted() {
      // const id = window.sessionStorage.getItem('user_id')
      API.get(SERVER_IP + `/edu/findAll`)
      .then(response => {
        for (var [key,value] of Object.entries(response.data)){
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
        this.school = this.high.concat(this.univ)
        for(let i = 0; i < this.school.length; i++){
          setTimeout(() => {
          this.sec ++
        }, 100*i);
        }
        console.log(this.school)
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
      school:[],
      sec:0,
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
.bounce-enter-active, .bounce-leave-active {
  animation: bounce 1s;
}

</style>