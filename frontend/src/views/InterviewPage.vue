<template>
  <div class="full interpage">
    <v-layout justify-center align-center style="height:100%;" v-if="loading">
      <Load />
    </v-layout>
    <v-flex xs12 text-xs-center round my-5>
      <v-btn v-on:click="showWrite" class="mx-2 corner" small fab dark color="cyan">
        <v-icon dark>edit</v-icon>
      </v-btn>
    </v-flex>

    <template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="800px" max-height="1000px" style="z-index:30;">
      <!-- v-dialog의 persistent속성 - 주위 클릭해도 안사라짐 -->
      <v-card>
        <v-card-title class="justify-center">
          <span style="margin-top:20px;" id="headline">Write an Interview Note</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row dense justify="center">
              <v-col cols="12" sm="2" md="2">
              <v-text-field v-model="interview_company" label="회사명" required
              :dense="true"
              ></v-text-field>
              </v-col>
              <v-col cols="12" sm="2" md="2">
              <v-text-field v-model="interview_task" label="직무" required
              :dense="true"
              ></v-text-field>
              </v-col>
            <v-col cols="12" sm="3" md="3">
            <v-text-field v-model="interview_date" label="지원시기" required
            :dense="true"></v-text-field>
            </v-col>
            </v-row>
            <v-text-field label="질문"
            required
            v-model="interview_question"
            :dense="true"></v-text-field>
      <v-textarea label="답변"
      v-model="interview_answer"
      :auto-grow="true"
      :counter="true"
      :dense="true"
      ></v-textarea>
      <v-textarea label="메모"
      v-model="interview_memo"
      :auto-grow="true"
      :counter="true"
      :dense="true"
      ></v-textarea>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text @click="writeInterview">Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>
    <v-container>
      <v-layout>
        <v-flex xs12>
          <InterviewList ref="updating" :load-more="true" @load="complete">
          </InterviewList>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import InterviewList from "../components/InterviewList";
import Navbar from "../components/Navbar";
import FirebaseService from '@/services/FirebaseService';
import Load from '@/components/Loading';
import API from "../services/Api"

export default {
  name: "PostPage",
  components: {
    Load,
    InterviewList,
    Navbar,
  },
  data() {
    return {
      loading:true,
      dialog: false,
      interview_company: null,
      interview_task : null,
      interview_date : null,
      interview_question: null,
      interview_answer: null,
      interview_memo: null,
    };
  },
  methods: {
    showWrite() {
      return this.dialog = true
    },
    complete(){
      return this.loading = false
    },
    writeInterview() {
      var interview_info = {
        "interview_company" : this.interview_company,
        "interview_task" : this.interview_task,
        "interview_date": this.interview_date,
        "interview_question": this.interview_question,
        "interview_answer": this.interview_answer,
        "interview_memo": this.interview_memo,
      }
      API.post('/interview/save', 
        interview_info)
        .then(response=>{
        console.log(response)
        this.$refs.updating.getInterView()
        this.interview_company = null;
        this.interview_task = null;
        this.interview_question = null;
        this.interview_memo = null;
        this.interview_answer = null;
        this.interview_date = null;
        return this.dialog = false
      })
      .catch(error=>{
        console.log(error)
      })
    }
  }
}
</script>
<style>
#write{
  margin-left:85% !important
}
.corner{
  position: fixed;
  right:30px;
  bottom:30px;
}
.interpage{
  margin-top: 80px;
}
</style>
