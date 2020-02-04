<template>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left" style="font-size:20px">Education</th>
          <th class="text-left" style="font-size:20px"></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="item in $store.state.edu" :key="item.name">
          <td width="200vm">{{ item.onetitle }}</td>
          <td width="700vm">{{ item.onecontent }}</td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
</template>