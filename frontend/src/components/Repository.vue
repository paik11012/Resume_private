<template>
  <div class="py-3">
    <v-layout>
      <v-flex xs8>
        <h2 class="font-weight-regular" id="repotitle">{{repos.path_with_namespace}}</h2>
        <p
          id="repop"
          class="subheading mb-1 grey--text text--darken-1 font-weight-light"
          style="display: -webkit-box; white-space: nowrap;"
        >{{repos.namespace.name}}</p>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import GitlabService from "@/services/GitlabService";

export default {
  name: "Repository",
  props: {
    repos: { type: null }
  },
  data() {
    return {
      stats: {}
    };
  },
  mounted() {
    this.drawStatGraph();
  },
  methods: {
    async drawStatGraph() {
      this.commits = await GitlabService.getCommits(this.repos.id);
    }
  }
};
</script>
<style>
@media screen and (max-width: 450px) {
  #bann {
    font-size: 20px;
  }
}
#repotitle {
  display: -webkit-box;
  white-space: nowrap;
}
</style>
