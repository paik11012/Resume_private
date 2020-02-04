<template>
  <div>
    <div id="nav">
      <v-app-bar color="#92a8d1" dense dark style="padding:0px;">
        <v-toolbar-title>
          <div class="my-2 spacer">
            <v-btn text href="/">MJ & Cats</v-btn>
          </div>
        </v-toolbar-title>

        <v-spacer></v-spacer>
          <div class="my-2" v-if="$vuetify.breakpoint.mdAndUp">
            <v-btn text href="/" >Home</v-btn>
          </div>
          <div class="my-2" v-if="$vuetify.breakpoint.mdAndUp">  
            <v-btn text href="/portfolio">Pictures</v-btn>
          </div>
          <div class="my-2" v-if="$vuetify.breakpoint.mdAndUp">
            <v-btn text href="/post">Items</v-btn>
          </div>
      </v-app-bar>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
  .my-2{
    margin-right: 50px;
  }
  .spacer{
    margin-left: 15px;
  }
</style>