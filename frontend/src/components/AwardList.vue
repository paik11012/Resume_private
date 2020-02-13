<template>
<div>
    <v-flex v-for="i in awd.length" :key="i">
    <transition name="bounce">
      <awd class="corners2"
        v-if="sec >= i & i-1 < awd.length "
        :id="awd[i-1].id"
        :award_org="awd[i-1].award_org"
        :award_title="awd[i-1].award_title"
        :award_date="awd[i-1].award_date"
        :award_prize="awd[i-1].award_prize"
        :award_detail="awd[i-1].award_detail"
        @delete="pop(i)"
      />
    </transition>
  </v-flex>
</div>

</template>
<script>
import axios from 'axios'
import awd from './Award'
export default {
  components:{
    awd
  },
  methods:{
    pop(i){
      this.awd.splice(i-1,1)
    },
    loadDt(){
      const SERVER_IP = 'http://70.12.247.99:8080'
      axios.get(SERVER_IP + `/awards`, {
      headers: {
            token: window.sessionStorage.getItem("jwt-auth-token"),
            user_id: window.sessionStorage.getItem("user_id")
      }})
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
        for(let i = 0; i < this.awd.length; i++){
            setTimeout(() => {
            this.sec ++
            console.log(this.sec);
          }, 100*i)
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
      awd:[],
      sec:0,
    }
  },
}
</script>

<style lang="scss">
</style>