<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px" colspan="3">My Info
            <v-btn v-on:click="editor" v-if="editing" class="mx-2" small fab dark color="cyan" id="write">
              <v-icon dark>edit</v-icon>
            </v-btn>
            <v-btn v-else v-on:click="editor" class="mx-2" small fab id="write" color="success">
              <v-icon>check</v-icon>
            </v-btn>
          </th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td rowspan="3"><v-layout justify-center><img style="width:140px; height:170px;" src="@/assets/권응.jpg"/></v-layout></td>
          <td class="rights">병역구분</td>
          <td v-if="editing">육군 만기전역</td>
          <td v-else><input type="text" v-model="mil_sort" placeholder="병역구분"></td>
        </tr>
        <tr>
          <td class="rights">계급</td>
          <td v-if="editing">병장</td>
          <td v-else><input type="text" v-model="mil_grade" placeholder="계급"></td>
        </tr>
        <tr>
          <td class="rights">복무기간</td>
          <td v-if="editing">하하하</td>
          <td v-else><input type="text" v-model="mil_service" placeholder="복무기간"></td>
        </tr>
        <tr>
          <td style="width:50px; text-align:center;">사진 URL</td>
          <td class="rights">메모</td>
          <td v-if="editing">하하하</td>
          <td v-else><input type="text" v-model="memo" placeholder="메모"></td>
        </tr>
        <!-- <tr v-for="item in $store.state.career" :key="item.onetitle">
          <td width="200vm">{{ item.onetitle }}</td>
          <td width="700vm">{{ item.onecontent }}</td>
        </tr> -->
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

<style lang="scss" scoped>

</style>