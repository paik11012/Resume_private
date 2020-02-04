<template>
<v-dialog v-model="show" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span class="headline">Modify a picture</span>
          <h2>{{this.title}}</h2>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field v-model="title" label="Title*" required placeholder=""></v-text-field>
                <h2>{{this.title}}</h2>
              </v-col>
              <v-col cols="12">
                <v-text-field v-model="body" label="Content*" required></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="red darken-1" text @click="show = false">Delete</v-btn>
          <v-btn color="black darken-1" text @click="show = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="savePort()">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
</template>

<script>
export default {
  props: {
    value: Boolean,
    date: { type: String },
    title: { type: String },
    body: { type: String },
    imgSrc: { type: String }
  },
  computed: {
    show: {
      get () {
        return this.value
      },
      set (value) {
         this.$emit('input', value)
      }
    }
  },
  data() {
    return {
      dialog: false
    }
  }
}
</script>