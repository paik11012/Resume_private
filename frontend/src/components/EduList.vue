<template>
<div>
    <v-flex v-for="i in school.length" :key="i">
    <transition name="bounce">
    <eduh class="corners2"
      v-if="sec >= i & i-1 < high.length "
      :education_id="school[i-1].education_id"
      :edu_school_name="school[i-1].edu_school_name"
      :edu_school_sort="school[i-1].edu_school_sort"
      :edu_school_st_date="school[i-1].edu_school_st_date"
      :edu_school_ed_date="school[i-1].edu_school_ed_date"
      :num="i"
      @delete="pop"
    />
    </transition>
    <transition name="bounce">
    <eduu class="corners2"
    v-if="sec >= i & i-1 > high.length-1"
    :education_id="school[i-1].education_id"
    :edu_school_name="school[i-1].edu_school_name"
    :edu_school_sort="school[i-1].edu_school_sort"
    :edu_school_st_date="school[i-1].edu_school_st_date"
    :edu_school_ed_date="school[i-1].edu_school_ed_date"
    :edu_detail_major_sort="school[i-1].edu_detail_major_sort"
    :edu_detail_major="school[i-1].edu_detail_major"
    :edu_detail_grade="school[i-1].edu_detail_grade"
    :edu_detail_grade_img="school[i-1].edu_detail_grade_img"
    :edu_detail_credit="school[i-1].edu_detail_credit"
    :edu_detail_id="school[i-1].edu_detail_id"
    :num="i"
    @delete="pop"
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
  methods:{
    pop(val,sort){
      console.log(val)
      console.log(this.school[val-1])
      this.school.splice(val-1,1)
      if(sort == 1){ this.high.splice(val-1,1) }
    },
    loadDt(){
      this.sec = 0
      this.school=[]
      this.high=[]
      this.univ=[]
      API.get('edu/findAll')
      .then(response => {
        for (var [key,value] of Object.entries(response.data)){
          for (var [key1, value1] of Object.entries(value)){
            this.education_id = value1[0]["education"]["education_id"]
            this.edu_school_name = value1[0]["education"]["edu_school_name"]
            this.edu_school_st_date = value1[0]["education"]["edu_school_st_date"]
            this.edu_school_ed_date = value1[0]["education"]["edu_school_ed_date"],
            this.edu_school_sort = value1[0]["education"]["edu_school_sort"], // 1이 고등학교 2가 대학교 3이 대학원 4가 편입
            this.edu_detail_major_sort = value1[0]["edu_detail_major_sort"],
            this.edu_detail_major = value1[0]["edu_detail_major"],
            this.edu_detail_grade = value1[0]["edu_detail_grade"],
            this.edu_detail_grade_img = value1[0]["edu_detail_grade_img"],
            this.edu_detail_credit = value1[0]["edu_detail_credit"],
            this.edu_detail_id = value1[0]["edu_detail_id"]
            if(this.edu_school_sort == 1){
              this.high.push({education_id:this.education_id, edu_school_name:this.edu_school_name, edu_school_st_date:this.edu_school_st_date, edu_school_sort:this.edu_school_sort, edu_school_ed_date:this.edu_school_ed_date})
            } else {
              this.univ.push({education_id:this.education_id, edu_school_name:this.edu_school_name, edu_school_st_date:this.edu_school_st_date, edu_school_sort:this.edu_school_sort, edu_school_ed_date:this.edu_school_ed_date, edu_detail_major_sort:this.edu_detail_major_sort, edu_detail_major:this.edu_detail_major, edu_detail_grade:this.edu_detail_grade, edu_detail_grade_img:this.edu_detail_grade_img, edu_detail_credit:this.edu_detail_credit, edu_detail_id:this.edu_detail_id})
            }
          }
        }
        this.school = this.high.concat(this.univ)

          for(let i = 0; i < this.school.length; i++){
            setTimeout(() => {
            this.sec ++
          }, 100*i);
        }
      })
      .catch(error => {
        console.log(error)
      })
    }
  },
  mounted() {
      this.loadDt()
    },  
  data(){
    return{
      editing:true,
      edu_school_name:'',
      edu_school_sort: '', // 1이 고등학교 2가 대학교 3이 대학원 4가 편입
      edu_school_st_date:'',
      edu_school_ed_date:'',
      edu_detail_major_sort:'',
      edu_detail_major:'',
      edu_detail_grade:'',
      edu_detail_grade_img:'',
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
.bounce-enter-active{
  animation: bounce 1s;
}
.bounce-leave-active {
  animation: bounce .5s reverse;
}
</style>