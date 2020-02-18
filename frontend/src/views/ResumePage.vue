<template>
  <div class="full res_page">
    <v-layout justify-center align-center style="height:100%;" v-if="loading">
      <Load />
    </v-layout>
    <v-flex xs12 text-xs-center round my-5>
      <v-btn v-on:click="showWrite" class="mx-2 corner" small fab dark color="cyan">
        <v-icon dark>edit</v-icon>
      </v-btn>
    </v-flex>
  <!-- resume 작성하기 -->
  <template>
  <v-row justify="center">
    <v-dialog v-model="dialog" :persistent="true" max-width="80%" max-height="80%" style="z-index:30;">
      <!-- v-dialog의 persistent속성 - 주위 클릭해도 안사라짐 -->
      <v-card>
        <v-card-title class="justify-center">
          <span id="headline" style="margin-top:0px;">Write a Resume</span>
        </v-card-title>
        <v-card-text style="padding-bottom:0;">
          <v-container style="padding-bottom:0;">
            <v-row justify="center" >
              <v-col cols="12" sm="2" md="2" style="padding-bottom:0; padding-top:0">
              <v-text-field v-model="resume_company" label="회사명" required
              ></v-text-field>
              </v-col>
              <v-col cols="12" sm="2" md="2" style="padding-bottom:0; padding-top:0">
              <v-text-field v-model="resume_task" label="직무" required
              ></v-text-field>
              </v-col>
              <v-col cols="12" sm="3" md="3" style="padding-bottom:0; padding-top:0">
              <v-text-field v-model="resume_date" label="지원시기" required
              ></v-text-field>
              </v-col>
            </v-row>
            <div style="margin:0px;">
              <label>Tag</label>
            </div>
            <v-row class="dig">
              <v-col cols="12" sm="3" md="3" style="padding-top:1px">
                <v-checkbox v-model="tag_name" class="mx-2" value="신뢰" label="신뢰" hide-details></v-checkbox>
                <v-checkbox v-model="tag_name" class="mx-2" value="혁신" label="혁신" hide-details></v-checkbox>
                <v-checkbox v-model="tag_name" class="mx-2" value="글로벌" label="글로벌" hide-details></v-checkbox>
              </v-col>
              <v-col cols="12" sm="3" md="3" style="padding-top:1px">
                <v-checkbox v-model="tag_name" class="mx-2" value="책임감" label="책임감" hide-details></v-checkbox>
                <v-checkbox v-model="tag_name" class="mx-2" value="열정" label="열정" hide-details></v-checkbox>
                <v-checkbox v-model="tag_name" class="mx-2" value="협력" label="협력" hide-details></v-checkbox>
              </v-col>
              <v-col cols="12" sm="3" md="3" style="padding-top:1px">
                <v-checkbox v-model="tag_name" class="mx-2" value="창의성" label="창의성" hide-details></v-checkbox>
                <v-checkbox v-model="tag_name" class="mx-2" value="도덕성" label="도덕성" hide-details></v-checkbox>
                <v-checkbox v-model="tag_name" class="mx-2" value="전문성" label="전문성" hide-details></v-checkbox>
              </v-col>
              <v-col cols="12" sm="3" md="3" style="padding-top:1px">
                <v-checkbox v-model="tag_name" class="mx-2" value="도전정신" label="도전정신" hide-details></v-checkbox>
                <v-checkbox v-model="tag_name" class="mx-2" value="가치창출" label="가치창출" hide-details></v-checkbox>
                <v-checkbox v-model="tag_name" class="mx-2" value="배려" label="배려" hide-details></v-checkbox>
              </v-col>
            </v-row>      
            <v-text-field label="질문"
            v-model="resume_question"
            required
            ></v-text-field>
            <v-textarea label="대답"
            required
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
      <v-layout class="justify-end">
        <div style="position:relative; width:90px; margin-left:50%; hei ght:40px; margin-bottom:20px;">
        <div v-if="searchpick" @click="searching" style="background:white; position:relative; z-index:29; width:100%; height:100%; padding: 0.7% 1.5%; border:1px solid #92A8D1; margin-right:10px; border-radius:10px;">
          <p class="keyset">
          {{ searkey[pickkey] }}
          </p>
          </div>
          <!-- 검색 부분 -->
        <div v-else style="position:absolute; z-index:30; background:white; left:0px; width:100%; height:140px; padding: 0.7% 1.5%; border:1px solid #92A8D1; margin-right:10px; border-radius:10px;">
          <p v-for="i in searkey.length" :key="i" class="keyset" @click="selkey(i-1)">{{ searkey[i-1] }}</p>
        </div>
        </div>
        <div style="width: 250px !important; z-index:29; margin-left:20px; height:40px; padding: 6px 12px; border:1px solid; margin-right:10px; border-radius:10px;"><v-icon>mdi-magnify</v-icon>
        <input style="width:85%; margin-left:5px;" v-model="search" type="text"></div>
      </v-layout>

      <v-layout>
        <v-row class="mb-6">
          <v-col v-for="i in tags.length" :key="i" lg="2" xs="3" md="2" class="layout justify-center">
            <v-btn id="tag_button"  style="width:85px" :class="{nocheck: tags[i-1]['state'], check: !tags[i-1]['state']}" 
            depressed @click="changeTag(i)">#{{tags[i-1]["name"]}}</v-btn></v-col>
        </v-row>
      </v-layout>
      <v-layout>
        <v-flex xs12>
          <ResumeList ref="updating" @load="complete"
          :filter_tag="filter_tag"
          :tag_name="tag_name"
          :search="search"
          :keypick="pickkey"
          >
          </ResumeList>
        </v-flex>
      </v-layout>
    </v-container>
  </div>
</template>
<script>
import API from "../services/Api"
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
  computed:{
    

  },
  data() {
    return {
      searchpick:true,
      loading:true,
      resume_company:null,
      resume_task:null,
      resume_date:null,
      resume_question:null,
      resume_answer:null,
      dialog: false,
      drawer: null,
      tag_name: [],
      resumes: [],
      reload:false,
      filter_tag: [false,false,false,false,false,false,false,false,false,false,false,false],
      items :["전체","회사명","내용"],
      searkey:["회사명","직무","질문","답변"],
      value : "전체",
      pickkey:0,
      search:'',
      option :"전체",
      	result : [],
					condition : '',
					word : '',
      tags: [
        {name: "신뢰", state: false},
        {name: "책임감", state: false},
        {name: "창의성", state: false},
        {name: "도전정신", state: false},
        {name: "혁신", state: false},
        {name: "열정", state: false},
        {name: "도덕성", state: false},
        {name: "가치창출", state: false},
        {name: "글로벌", state: false},
        {name: "협력", state: false},
        {name: "전문성", state: false},
        {name: "배려", state: false},
      ],
    };
  },
  computed:{
    keyword:function(){
      console.log('ghaha')
      return this.search.split(' ')
    }
  },
  methods: {
    selkey(i){
      this.pickkey = i
      this.searchpick = true
    },
    searching(){
      this.searchpick = false
    },
    changeTag(i){
      this.tags[i-1]['state'] = !this.tags[i-1]['state']
      this.filter_tag[i-1] = this.tags[i-1]['state']
      this.$refs.updating.filter()
    },
    showWrite() {
      return this.dialog = true
    },
    complete(){
      return this.loading = false
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
      if (resume_info.resume_company == null
          || resume_info.resume_task == null
          || resume_info.resume_question == null
          || resume_info.resume_answer == null
          ) {
        return alert('태그와 지원시기 외 정보는 모두 입력해주세요')
      } else {
        console.log(r_data);
        
        API.post(
        '/resume/save', r_data)
      .then(response=>{
        console.log(response.data)
        this.$refs.updating.getResume()
        this.resume_company = null;
        this.resume_task = null;
        this.resume_question = null;
        this.resume_answer = null;
        this.resume_date = null;
        this.tag_name = [];
        return this.dialog = false
      })
      .catch(error=>{
        console.log(error)
      })
    }
  },
  
  },
}
</script>
<style lang="scss">
#write{
  margin-left:85% !important
}
.dig{
  & .v-icon{
    z-index: 0;
  }
  & .v-input--selection-controls__ripple{
    z-index: 3;
  }
}
.corner{
  position: fixed;
  right:30px;
  bottom:30px;
  z-index: 30;
}
i{
  z-index: 22; 
}

.mb-6{
  & v-btn & v-col {
    font-family: 'Jua';
    font-size: 15px;
  }
}
*:focus{
  outline: none
}
#tag_button{
  color:white;
  border: solid white 2px;
  font-family: Jua;
  font-size: 17px;
  border-radius: 10px;
}
#headline{
  font-family: 'Fredoka One', cursive;
  font-size: 3vh; 
}
.nocheck{
  background-color: #ffb3b3 !important;
}
.check{
  background-color:  #92A8D1 !important;
}
.keyset{
  margin:0 !important; height:35px; font-size:16px; font-family:Jua; text-align:center; padding: 6px 0;
}

</style> 