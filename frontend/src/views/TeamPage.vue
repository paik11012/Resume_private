<template>
  <div class="team back" @click="reset">
    <div v-for="i in team.length" :key="i">
      <transition name="teams" >
        <img :src="imgsrc[i-1]" :class="team[i-1]" class="circle" v-if="(sel == team[i-1] | all) & sec[sel][i-1] < time" @click="select(team[i-1])" style="z-index:20;">
        <div v-else-if="sec[sel][i-1] < time" :class="team[i-1]+'on'" class="circle mess light_gradient" @click="select(team[i-1])">
          <img :class="team[i-1]+'1'" :src="message[sel][i-1]" @click="link(sel, i-1)">
          </div>
      </transition>
    </div>

  </div>
</template>

<script>
export default {
  data(){
    return{
      time:5,
      sec:{
        "park":[0,1,2,3],
        "paik":[1,0,2,3],
        "lee":[1,2,0,3],
        "yun":[2,1,3,0],
      },
      message:{
        "park":['', require("../assets/team_park_1.png"), require("../assets/team_park_2.png"), require("../assets/GitHub.png")],
        "paik":[require("../assets/team_paik_1.png"),'', require("../assets/team_paik_2.png"), require("../assets/GitHub.png")],
        "lee":[require("../assets/team_lee_1.png"), require("../assets/team_lee_2.png"),'', require("../assets/GitHub.png")],
        "yun":[require("../assets/team_yun_1.png"), require("../assets/team_yun_2.png"), require("../assets/GitHub.png"),''],
      },
      github_link:{
        "park": "https://github.com/kwoneyng",
        "paik": "https://github.com/paik11012",
        "lee": "https://github.com/daseul-lee99",
        "yun": "https://github.com/yunkyuhee",
      },
      all : true,
      sel : "park",
      triger : 0,
      team:["park", "paik", "lee", "yun"],
      imgsrc:[require("../assets/team_park.jpg"), require("../assets/team_minjoo.jpg"), require("../assets/team_daseul.jpg"), require("../assets/team_kkyu.jpg")]
    }
  },
  methods:{
    link(name, i){
      if(this.sec[this.sel][i] == 3){
        window.open(this.github_link[name], '_blank')
      }
    },
    select(i){
      this.time = 0
      for (let i=0; i < 5; i++){
        setTimeout(() => {
          this.time += 1
        }, 200*i);
      }
      this.sel = i
      this.all = false
      this.triger = 1
    },
    reset(){
      if (!this.triger) {
        this.all = true
        this.time = 5
      } else {
        setTimeout(() => {
          this.triger = 0
        }, 200);
      }
    }

  },
  mounted(){
    this.time = 0
    for (let i=0; i < 5; i++){
      setTimeout(() => {
        this.time += 1
      }, 200*i);
    }
  }
}
</script>

<style lang="scss">
@import "@/assets/scss/mystyle.scss";
.team{
  animation: fadein 2s;
  position: relative;
  & .mess{
    position: relative;
    color: rgb(54, 60, 57);
    & .fonts{
      position: absolute;
      font-size: 25px;
      left: 20%;
      // top: 45%;
      font-size: 1.2em;
    }
  }
  & .circle{
    width: 40vh;
    height: 40vh;
    border-radius: 10%;
    background-position: center center;
    position: absolute;
  }
  & .park{
    top :12%;
    left : 20%;
    &on{
      top :12%;
      left : 20%;
    }
    &1{
      width: 100%;
      height: 100%;
    }
  }
  & .lee{
    top :57%;
    left : 20%;
    &on{
      top :57%;
      left : 20%;
    }
    &1{
      width: 100%;
      height: 100%;
    }
  }
  & .paik{
    top :12%;
    left : 55%;
    &on{
      top :12%;
      left : 55%;
    }
    &1{
      width: 100%;
      height: 100%;
    }
  }
  & .yun{
    top :57%;
    left : 55%;
    &on{
      top :57%;
      left : 55%;
    }
    &1{
      width: 100%;
      height: 100%;
    }
  }
}

.spinimage:hover{
  animation: spin 1s infinite linear;
}
.turned{    
    -webkit-animation-play-state: running;
    -moz-animation-play-state: running;
    -ms-animation-play-state: running;
    -o-animation-play-state: running;
    animation-play-state: running;
}

@keyframes spin {
    0%  {transform: scale3d(1,1,1);}
    100%  {transform: scale3d(0,1,1);}
}

.teams-enter-active{
  animation: spin .8s reverse;
}

.teams-leave-active{
  animation: spin .8s;
}
</style>