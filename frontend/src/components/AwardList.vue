<template>
<div>
    <v-flex v-for="i in awd.length" :key="i">
    <transition name="bounce">
    <awd class="corners2"
      :id="awd[i]"
      :award_org="awd.award_org"
      :award_title="awd.award_title"
      :award_date="awd.award_date"
      :award_prize="awd.award_prize"
      :award_detail="awd.award_detail"

    />
    </transition>
  </v-flex>
</div>

</template>
<script>
import awd from './Award'
import API from "../services/Api"
export default {
  components:{
    awd
  },
  methods:{
    pop(i){
      this.school.splice(i-1,1)
    },
    loadDt(){
      API.get(`/awards`)
      .then(response => {
        var array = []
        for(let i=0; i < response.data.length; i++){
          console.log(response.data[i]);
          var data = response.data[i]
          var table = {
            id : data.id,
            award_org : data.award_org,
            award_title : data.award_title,
            award_date : data.award_date,
            award_prize : data.award_prize,
            award_detail : data.award_detail,
          }
          array.push(table)
        }
        this.awd = array
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
      awd:[],
      sec:0,
    }
  },
}
</script>

<style lang="scss">
</style>