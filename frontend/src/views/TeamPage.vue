<template>
  <div class="team back" @click="reset">
    <div v-for="i in team.length" :key="i">
      <transition name="teams" >
        <img :src="imgsrc[i-1]" :class="team[i-1]" class="circle" v-if="(sel == team[i-1] | all) & sec[sel][i-1] < time" @click="select(team[i-1])" style="z-index:20;">
        <div v-else-if="sec[sel][i-1] < time" :class="team[i-1]+'on'" class="circle mess light_gradient" @click="select(team[i-1])">
          <p class="fonts">
            {{ message[sel][i-1] }} {{ sec[team[i-1]][i-1] }} {{ sel }}
          </p>
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
        "park":[0,1,3,2],
        "paik":[1,0,2,3],
        "lee":[1,2,0,3],
        "yun":[2,3,1,0],
      },
      message:{
        "park":['','박','권','응'],
        "paik":['백','','민','주'],
        "lee":['이','다','','슬'],
        "yun":['윤','규','희',''],
      },
      all : true,
      sel : "park",
      triger : 0,
      team:["park", "paik", "lee", "yun"],
      imgsrc:["https://www.bigstockphoto.com/images/homepage/module-6.jpg", "https://helpx.adobe.com/content/dam/help/en/stock/how-to/visual-reverse-image-search/jcr_content/main-pars/image/visual-reverse-image-search-v2_intro.jpg", "https://image.shutterstock.com/image-photo/beautiful-water-drop-on-dandelion-260nw-789676552.jpg", "data:image/jpeg;base64,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"]
    }
  },
  methods:{
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
      top: 45%;
    }
  }
  & .circle{
    width: 40vh;
    height: 40vh;
    border-radius: 100%;
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
  }
  & .lee{
    top :57%;
    left : 20%;
    &on{
      top :57%;
      left : 20%;

    }
  }
  & .paik{
    top :12%;
    left : 55%;
    &on{
      top :12%;
      left : 55%;
    }
  }
  & .yun{
    top :57%;
    left : 55%;
    &on{
      top :57%;
      left : 55%;
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
// .teams-leave-active{
//   animation: spin .6s;
// }
</style>