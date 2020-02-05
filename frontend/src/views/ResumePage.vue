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
  <!-- resume 작성하기 -->
  <template>

  <v-row justify="center">
    <v-dialog v-model="dialog" :persistent="true" max-width="800px" max-height="1000px" style="z-index:999999;">
      <!-- v-dialog의 persistent속성 - 주위 클릭해도 안사라짐 -->
      <v-card>
        <v-card-title class="justify-center">
          <span class="headline" style="margin-top:20px;">Write a Resume</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row justify="center">
              <v-col cols="12" sm="2" md="2">
              <v-text-field v-model="resume_company" label="회사명" required
              ></v-text-field>
              </v-col>
              <v-col cols="12" sm="2" md="2">
              <v-text-field v-model="resume_task" label="직무" required
              ></v-text-field>
              </v-col>
            <v-col cols="12" sm="3" md="3">
            <v-text-field v-model="resume_date" label="지원시기" required
            ></v-text-field>
            </v-col>
            </v-row>


            <div style="margin:0px;">
            <label>tag</label>
            </div>
            <div style=" margin:0;">
        <v-row style="height: 60px;"
        justify="space-around">
          <v-checkbox
          v-model="tag_name" class="mx-2" value="신뢰" label="신뢰"></v-checkbox>
          <v-checkbox
          v-model="tag_name" class="mx-2" value="혁신" label="혁신"></v-checkbox>
          <v-checkbox
          v-model="tag_name" class="mx-2" value="글로벌역량" label="글로벌역량"></v-checkbox>
          <v-checkbox
          v-model="tag_name" class="mx-2" value="책임감" label="책임감"></v-checkbox>
          <v-checkbox
          v-model="tag_name" class="mx-2" value="열정" label="열정"></v-checkbox>
          <v-checkbox
          v-model="tag_name" class="mx-2" value="협력" label="협력"></v-checkbox>
        </v-row>
        <v-row style="height: 60px;"
        justify="space-around" cols="12" sm="4" md="4">
          <v-checkbox
          v-model="tag_name" class="mx-2" value="창의성" label="창의성 "></v-checkbox>
          <v-checkbox
          v-model="tag_name" class="mx-2" value="도덕성" label="도덕성"></v-checkbox>
          <v-checkbox
          v-model="tag_name" class="mx-2" value="전문성" label="전문성"></v-checkbox>
          <v-checkbox
          v-model="tag_name" class="mx-2" value="도전정신" label="도전정신"></v-checkbox>
          <v-checkbox
          v-model="tag_name" class="mx-2" value="가치창출" label="가치창출"></v-checkbox>
          <v-checkbox
          v-model="tag_name" class="mx-2" value="배려" label="배려"></v-checkbox>
        </v-row>
      </div>
            <v-text-field label="질문"
            v-model="resume_question"
            ></v-text-field>
      <v-textarea label="대답"
      v-model="resume_answer"
      :auto-grow="true"
      :counter="true"
      ></v-textarea>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
          <v-btn color="blue darken-1" text 
          @click="writeResume"
          >Save</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

    <v-container>
      <!-- Portfolio -->
      <v-layout>
        <v-flex xs12>
          <ResumeList :limits="4" :load-more="true" @load="complete">
          </ResumeList>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import axios from 'axios'
import ImgBanner from "../components/ImgBanner";
import ResumeList from "../components/ResumeList";
import Navbar from "../components/Navbar";
import { mapActions } from 'vuex';
import Load from "@/components/Loading";

export default {
  name: "ResumePage",
  components: {
    Load,
    ImgBanner,
    ResumeList,
    Navbar,
    
  },
  data() {
    return {
      loading:true,
      resume_company:null,
      resume_task:null,
      resume_date:null,
      resume_question:null,
      resume_answer:null,
      dialog: false,
      drawer: null,
      tag_name: [],
      resumes: []
    };
  },
  methods: {
    showWrite() {
      return this.dialog = true
    },
    complete(){
      return this.loading = !this.loading
    },
    writeResume() {
      var resume_info = {
        "resume_company" : this.resume_company,
        "resume_task" : this.resume_task,
        "resume_date" : this.resume_date,
        "resume_question" : this.resume_question,
        "resume_answer" : this.resume_answer,
      };
      var r_data = {
          resume_info : resume_info,
          tag_name : this.tag_name
      }
      // axios.request({
      //   url: 'resume/save',
      //   method: 'post',
      //   baseURL: 'http://70.12.247.99:8080/',
      //   headers: {
      //     'Authorization' : window.sessionStorage.getItem("jwt-auth-token")}
      //   })
        axios.post(
        'http://70.12.247.99:8080/resume/save',
        r_data,
        {headers : {'Authorization' : 'Bearer ' + window.sessionStorage.getItem("jwt-auth-token")}})
      .then(response=>{
        console.log(response)
        return this.dialog = false
      })
      .catch(error=>{
        console.log(error)
      })
    },
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
