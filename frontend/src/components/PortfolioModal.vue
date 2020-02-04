<template>
<v-dialog v-model="show" persistent max-width="600px">
      <v-card>
        <v-card-title>
          <span class="headline">{{company}} {{task}}</span>
        </v-card-title>
        <p style="margin-left:30px; font-size:12px;">{{date}}</p>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field v-model="question" label="질문" required placeholder=""></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-textarea v-model="answer" 
                label="답변" required
                :auto-grow="true"
                :counter="true"
                :row-height="0"
                ></v-textarea>
              </v-col>
              <div style="width:100%">
              </div>
              <div style="width:100%">tag :</div>
              <div v-for="i in tag.length"
              :key="i"
              >
              <v-btn color="primary" style="margin-right:20px;">{{tag[i-1]}}</v-btn>
              </div>
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
    company : {type: String},
    answer : {type: String},
    question : {type: String},
    task : {type: String},
    tag : {type: Array},
    text_val : {type: Number},
    date : {type: String},
    created_at : {type: String},
  },
  methods:{
    
  },
  computed: {
    show: {
      get () {
        return this.value
      },
      set (value) {
         this.$emit('input', value)
      }
    },
  },
  data() {
    return {
      autogrow:true,
      dialog: false,
    }
  }
}
</script>