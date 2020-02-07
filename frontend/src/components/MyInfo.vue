<template>
  <v-simple-table v-if="$vuetify.breakpoint.mdAndUp">
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px" colspan="2">My Info
          </th>
          <th>
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
          <td style="width:30%;" rowspan="3"><img style="width:140px; height:170px; display: block; margin: 0px auto;" src="@/assets/권응.jpg"/></td>
          <td>병역구분</td>
          <td v-if="editing">공군 만기전역</td>
          <td v-else><input type="text" v-model="mil_sort" placeholder="병역구분"></td>
        </tr>
        <tr>
          <td>계급</td>
          <td v-if="editing">병장</td>
          <td v-else><input type="text" v-model="mil_grade" placeholder="계급"></td>
        </tr>
        <tr>
          <td>복무기간</td>
          <td v-if="editing">하하하</td>
          <td v-else><input type="text" v-model="mil_service" placeholder="복무기간"></td>
        </tr>
        <tr>
          <td class="layout justify-center"><v-btn style="margin-top:6px;" color="success" outlined><v-icon dark>mdi-cloud-download</v-icon></v-btn></td>
          <td>메모</td>
          <td v-if="editing">하하하</td>
          <td v-else><input type="text" v-model="memo" placeholder="메모"></td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
  <v-simple-table v-else>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px" colspan="1">My Info
          </th>
          <th>
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
          <td style="width:40%; text-align:center;">사진 URL</td>
          <td class="layout justify-center"><v-btn style="margin-top:6px;" color="success" outlined><v-icon dark>mdi-cloud-download</v-icon></v-btn></td>
        </tr>
        <tr>
          <td style="text-align:center;">병역구분</td>
          <td v-if="editing" style="text-align:center;">공군 만기전역</td>
          <td v-else><input type="text" style="text-align:center; width:100%;" v-model="mil_sort" placeholder="병역구분"></td>
        </tr>
        <tr>
          <td style="text-align:center;">계급</td>
          <td v-if="editing" style="text-align:center;">병장</td>
          <td v-else><input type="text" style="text-align:center; width:100%;" v-model="mil_grade" placeholder="계급"></td>
        </tr>
        <tr>
          <td style="text-align:center;">복무기간</td>
          <td v-if="editing" style="text-align:center;">하하하</td>
          <td v-else><input style="text-align:center; width:100%;" type="text" v-model="mil_service" placeholder="복무기간"></td>
        </tr>
        <tr>
          <td style="text-align:center;">메모</td>
          <td v-if="editing" style="text-align:center;">하하하</td>
          <td v-else><input style="text-align:center; width:100%;" type="text" v-model="memo" placeholder="메모"></td>
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
      memo:'',
      mil_service:'',
      mil_grade:'',
      mil_sort:'',
    }
  },
  methods:{
    editor(){
      this.editing = !this.editing
    },
  }
}
</script>

<style lang="scss" >
#write{
  position: relative;
  left: 10px;
}

</style>