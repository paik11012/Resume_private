<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th>
          <div class="menubar">
            <ul>
              <li><a href="#">Sliders</a></li>
              <li><a href="#">Galleries</a></li>
              <li><a href="#">Apps</a></li>
              <li><a href="#">Extensions</a></li>
            </ul>
          </div>
          </th>
          <th class="layout justify-end">
            <v-btn v-on:click="editor" v-if="editing" small fab dark color="cyan" id="write">
              <v-icon dark>edit</v-icon>
            </v-btn>
            <v-btn v-else v-on:click="editor" small fab id="write" color="success">
              <v-icon>check</v-icon>
            </v-btn>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td width="150px">고등학교</td>
          <td v-if="editing">ABC 고등학교</td>
          <td v-else><input type="text" v-model="schoolname" placeholder="schoolname"></td>
        </tr>
        <tr>
          <td width="150px">재학기간</td>
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
    }
  },
  methods:{
    editor(){
      this.editing = !this.editing
    },
  }
}
</script>

<style lang="scss">
.menubar ul{
    background: rgb(109,109,109);
    display:none;  /* 평상시에는 서브메뉴가 안보이게 하기 */
    height:auto;
    padding:0px;
    margin:0px;
    border:0px;
    position:absolute;
    width:200px;
    z-index:200;
    color:black;
}

.menubar li:hover ul{
  display: block;
}

#write{
  position: relative;
  left: 10px;
}

</style>