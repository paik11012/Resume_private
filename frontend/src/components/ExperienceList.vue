<template>
<div>
    <v-flex v-for="i in exp.length" :key="i">
    <transition name="bounce">
      <exp class="corners2"
        v-if="sec >= i & i-1 < exp.length "
        :id="exp[i-1].id"
        :exp_org_name="exp[i-1].exp_org_name"
        :exp_org_type="exp[i-1].exp_org_type"
        :exp_org_period="exp[i-1].exp_org_period"
        :exp_org_role="exp[i-1].exp_org_role"
        :exp_org_detail="exp[i-1].exp_org_detail"
        @delete="pop(i)"
      />
    </transition>
  </v-flex>
</div>

</template>
<script>
import exp from './Experience'
import API from "../services/Api"
export default {
  components:{
    exp
  },
  methods:{
    pop(i){
      this.exp.splice(i-1,1)
    },
    loadDt(){
      API.get(`/exp`)
      .then(response => {
        var array = []
        for(let i=0; i < response.data.length; i++){
          console.log(response.data[i]);
          var data = response.data[i]
          var table = {
            id : data.id,
            exp_org_name : data.exp_org_name,
            exp_org_type : data.exp_org_type,
            exp_org_period : data.exp_org_period,
            exp_org_role : data.exp_org_role,
            exp_org_detail : data.exp_org_detail,
          }
          array.push(table)
        }
        this.exp = array
        for(let i = 0; i < this.exp.length; i++){
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
      exp:[],
      sec:0,
    }
  },
}
</script>

<style lang="scss">
</style>