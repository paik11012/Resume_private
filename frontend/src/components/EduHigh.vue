<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px">HighSchool</th>
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
#write{
  position: relative;
  left: 10px;
}

</style>