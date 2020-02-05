<template>
  <div class="full">
    <v-layout justify-center align-center style="height:100%;" v-if="loading">
      <Load />
    </v-layout>
    <v-flex xs12 text-xs-center round my-5>
      <v-btn v-on:click="showWrite" class="mx-2 corner" fab dark color="cyan" id="write">
        <v-icon dark>edit</v-icon>
      </v-btn>
    </v-flex>

    <template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="800px" max-height="1000px" style="z-index:999999;">
      <!-- v-dialog의 persistent속성 - 주위 클릭해도 안사라짐 -->
      <v-card>
        <v-card-title class="justify-center">
          <span class="headline" style="margin-top:20px;">면접후기 작성하기</span>
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
          <InterviewList :limits="3" :load-more="true" @load="complete">
          </InterviewList>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import ImgBanner from "../components/ImgBanner";
import InterviewList from "../components/InterviewList";
import Navbar from "../components/Navbar";
import FirebaseService from '@/services/FirebaseService';
import Load from '@/components/Loading';
import axios from 'axios';

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
      return this.loading = !this.loading
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
      axios.post(
        'http://70.12.247.99:8080/interview/save',
        interview_info)
        .then(response=>{
        console.log(response)
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
</style>
