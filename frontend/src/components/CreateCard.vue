<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th style="position:relative;">
            <div>
            <ul class="menubar" @click="opendb"> 
                 <!-- eslint-disable -->
              <li
                v-for="i in 4" 
                v-bind:key="i"
                v-if="select==i | opendrop"
                @click="selectone(i)"
                class="layout row"
                style="padding-left: 20px;"
              >
                <!-- eslint-disable -->
              {{ schoolsort[i-1] }} <div v-if="!opendrop" style="position:absolute; right:0;"><i class="material-icons">arrow_drop_down</i></div>
              </li>
            </ul>
            </div>
          </th>
          <th class="layout justify-end">
            <v-btn v-on:click="editor" v-if="editing" small fab dark color="success" id="write">
              <v-icon dark>check</v-icon>
            </v-btn>
            <v-btn v-else v-on:click="editor" small fab id="write" color="success">
              <v-icon>check</v-icon>
            </v-btn>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td width="150px">학교명</td>
          <td v-if="editing">ABC 고등학교</td>
          <td v-else><input type="text" v-model="schoolname" placeholder="schoolname"></td>
        </tr>
        <tr>
          <td width="150px">재학기간</td>
          <td v-if="editing">시작 ~ 끝</td>
          <td v-else><input type="text" v-model="edu_period" placeholder="edu_period"></td>
        </tr>
        <tr v-if="select != 1">
          <td width="150px">전공여부</td>
          <td v-if="editing">시작 ~ 끝</td>
          <td v-else><input type="text" v-model="edu_period" placeholder="edu_period"></td>
        </tr>
        <tr v-if="select != 1">
          <td width="150px">이수학점</td>
          <td v-if="editing">시작 ~ 끝</td>
          <td v-else><input type="text" v-model="edu_period" placeholder="edu_period"></td>
        </tr>
        <tr v-if="select != 1">
          <td width="150px">총 평점</td>
          <td v-if="editing">시작 ~ 끝</td>
          <td v-else><input type="text" v-model="edu_period" placeholder="edu_period"></td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>

<script>
export default {
  data(){
    return{
      editing:true,
      schoolname:'',
      edu_period:'',
      select:2,
      opendrop:false,
      schoolsort:[
        'HighSchool', 'University', 'Transfer', 'GradSchool'
      ]
    }
  },
  methods:{
    editor(){
      this.editing = !this.editing
    },
    opendb(){
      this.opendrop = !this.opendrop
      console.log(this.opendrop);
    },
    selectone(i){
      this.select = i
      console.log(this.select);
      
    },
  }
}
</script>

<style lang="scss">
@import "@/assets/scss/mystyle.scss"; 
.menubar {
    background: none;
    height:100%;
    font-size: 20px;
    top: 5px;
    left: 0;
    padding:0px;
    margin:0px;
    border:0px;
    text-align: left;
    width:100%;
    z-index:200;
    color:white;
    position: absolute;
    & li {
      background: white;;
      border-radius: 2px;
      color: black;
      list-style: none;
    }
    & ul{
      color:black;
      list-style: none;
    }
}


#write{
  position: relative;
  left: 10px;
}

</style>