<template>
  <v-dialog
    v-if="$vuetify.breakpoint.mdAndUp"
    v-model="show"
    transition="pop-out"
    :width="656"
    :height="400"
  >

    <div class="box" v-if="$vuetify.breakpoint.mdAndUp">
      
      <div class="box-part" id="bp-left">
        <div class="partition" id="partition-register">
          <div class="partition-title">LOGIN</div>
          <div class="partition-form">
            <form autocomplete="false">
              <input v-model="user_id" type="text" placeholder="User ID"/>
              <input v-model="user_password" type="password" placeholder="Password" @keydown.enter="login({user_id, user_password})"/>

            </form>

            <div style="margin-top: 42px"></div>

            <button class="large-btn login-btn" @click="login({user_id, user_password})">
              <span>Login</span>
            </button>
            <button class="large-btn github-btn" @click="loginNaver">
              connect with
              <span>naver</span>
            </button>
            <button class="large-btn facebook-btn" @click="loginKakao">
              connect with
              <span>kakao</span>
            </button>
           <v-dialog v-model="dialog" persistent max-width="290">
            <template v-slot:activator="{ on }">
              <!-- <v-btn color="primary" dark v-on="on">Open Dialog</v-btn> -->
                <button class="large-btn login-btn" v-on="on">
                  <span>Find password</span>
                </button>
            </template>
            <v-card>
              <v-card-title class="headline indigo lighten-4">Find Password</v-card-title>
                <v-col cols="12" sm="11">
                    <v-text-field
                      v-model="email"
                      label="Email"
                      outlined
                      shaped
                    ></v-text-field>
                  </v-col>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="indigo lighten-2 darken-1" text @click="passwordFind">send</v-btn>
                    <v-btn color="indigo lighten-2 darken-1" text @click="dialog = false">cancel</v-btn>
                  </v-card-actions>
            </v-card>
          </v-dialog>
          </div>
        </div>
      </div>
      <div class="box-part" id="bp-right">
        <img
          src="https://cdn.pixabay.com/photo/2016/03/09/09/14/books-1245690_960_720.jpg"
          alt="pastel"
          style="width:800px"
        />
        <div class="box-messages"></div>
      </div>
    </div>
    <div class="box1" v-else>
      <div class="box-part" id="bp-left">
        <div class="partition" id="partition-register">
          <div class="partition-title">LOGIN</div>
          <div class="partition-form">
            <form autocomplete="false">
              <input v-model="user_id" type="text" placeholder="User ID"/>
              <input v-model="user_password" type="password" placeholder="Password" @keydown.enter="login({user_id,user_password})"/>
              
            </form>

            <div style="margin-top: 42px"></div>
            
            <button class="large-btn login-btn" @click="login({user_id, user_password})">
              <span>Login</span>
            </button>
            <button class="large-btn github-btn" @click="loginNaver">
              connect with
              <span>naver</span>
            </button>
            <button class="large-btn facebook-btn" @click="loginKakao">
              connect with
              <span>kakao</span>
            </button>
             <button class="large-btn login-btn" @click="passwordFind">
              <span>Find password</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </v-dialog>
  <v-dialog v-else v-model="show" transition="pop-out" :width="328" :height="400">
    <div class="box" v-if="$vuetify.breakpoint.mdAndUp">
      <div class="box-part" id="bp-left">
        <div class="partition" id="partition-register">
          <div class="partition-title">LOGIN</div>
          <div class="partition-form">
            <form autocomplete="false">
              <input v-model="user_id" type="text" id="id" placeholder="User ID" />
              <input v-model="user_password" type="password" id="pw" placeholder="Password" @keydown.enter="login({user_id, user_password})"/>
            </form>

            <div style="margin-top: 42px"></div>
            <button class="large-btn login-btn" @click="login({user_id, user_password})">
              <span>Login</span>
            </button>
            <button class="large-btn github-btn" @click="loginNaver">
              connect with
              <span>naver</span>
            </button>
            <button class="large-btn facebook-btn" @click="loginKakao">
              connect with
              <span>kakao</span>
            </button>
             <button class="large-btn login-btn" @click="passwordFind">
              <span>Find password</span>
            </button>
          </div>
        </div>
      </div>
      <div class="box-part" id="bp-right">
        <img
          src="https://cdn.pixabay.com/photo/2016/03/09/09/14/books-1245690_960_720.jpg"
          alt="pastel"
          style="width:800px"
        />
        <div class="box-messages"></div>
      </div>
    </div>
    <div class="box1" v-else>
      <div class="box-part" id="bp-left">
        <div class="partition" id="partition-register">
          <div class="partition-title">LOGIN</div>
          <div class="partition-form">
            <form autocomplete="false">
              <input v-model="user_id" id="id" type="text" placeholder="User ID" />
              <input v-model="user_password" id="pw" type="password" placeholder="Password" @keydown.enter="login({user_id, user_password})"/>
            </form>

            <div style="margin-top: 42px"></div>

            <button class="large-btn login-btn" @click="login({user_id, user_password})">
            <span>Login</span>
            </button>
            <button class="large-btn github-btn" @click="loginNaver">
              connect with
              <span>naver</span>
            </button>
            <button class="large-btn facebook-btn" @click="loginKakao">
              connect with
              <span>kakao</span>
            </button>
              <button class="large-btn login-btn" @click="passwordFind">
              <span>Find password</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </v-dialog>
</template>
<script>
import { mapActions } from 'vuex';
import router from '../router'
import API from "../services/Api";
const MODAL_WIDTH = 656;

export default {
  name: "DemoLoginModal",
  props: {
    value: {
      required: true
    }
  },
  created() {
    // naver //
    this.naverLoginUrl += '&client_id=' + this.naver_client_id
    this.naverLoginUrl += '&redirect_uri=' + this.naver_redirectURI
    this.naverLoginUrl += '&state=' + this.state
    // END: naver //

    // kakao //
    this.kakaoLoginUrl += '&client_id=' + this.kakao_client_id
    this.kakaoLoginUrl += '&redirect_uri=' + this.kakao_redirectURI
      // END:kakao //
  },
  computed: {
    show: {
      get() {
        return this.value;
      },
      set(value) {
        this.$emit("input", value);
      }
    }
  },
  data() {
    return {
      modalWidth: MODAL_WIDTH,
      autogrow: true,
      dialog: false,
      user_id : '',
      user_password : '',

      // naver //
      naver_client_id: 'oEALeUqtjER7Ufo5R8f7',
      naver_redirectURI: 'http://i02a409.p.ssafy.io/',
      naverLoginUrl: 'https://nid.naver.com/oauth2.0/authorize?response_type=code',
      // END: naver //

      // kakao //
      kakao_client_id: 'ae103391c8a497b8820341af6a961a77',
      kakao_redirectURI: 'http://i02a409.p.ssafy.io/',
      kakaoLoginUrl: 'https://kauth.kakao.com/oauth/authorize?response_type=code',
      // END:kakao //
      state: 123,
      dialog: false,
      email : null
    };
  },
  methods: {
  ...mapActions(["login"]),

    loginNaver(){
      location.replace(this.naverLoginUrl)
    },
    loginKakao(){
      location.replace(this.kakaoLoginUrl)
    },
    passwordFind() {
        console.log("heere")
        API.get(`/users/sendtmp/${this.email}`)
      .then(response=>{
         this.dialog = false;
         alert("임시 비밀번호를 이메일로 발송했습니다.")
       
      })
      .catch(error=>{
        if(error.response.data.message == "임시 비밀번호 발송 에러")
            alert("인증 비밀번호 발송에 실패하였습니다.")
      })
    }
  },
};
</script>
<style lang="scss">
$background_color: #404142;
$login_color: lightgray;
$github_color: rgb(252, 186, 185);
$facebook_color: rgb(146, 168, 209);
.box {
  &1 {
    width: 328;
    &.box-part {
      display: inline-block;
      position: relative;
      vertical-align: top;
      box-sizing: border-box;
      height: 100%;
      width: 100%;
    }
  }
  background: white;
  overflow: hidden;
  width: 656px;
  height: 380px;
  border-radius: 2px;
  box-sizing: border-box;
  box-shadow: 0 0 40px black;
  color: #8b8c8d;
  font-size: 0;
  .box-part {
    display: inline-block;
    position: relative;
    vertical-align: top;
    box-sizing: border-box;
    height: 100%;
    width: 50%;
    &#bp-right {
      background: url("/static/panorama.jpg") no-repeat top left;
      border-left: 1px solid #eee;
    }
  }
  .box-messages {
    position: absolute;
    left: 0;
    bottom: 0;
    width: 100%;
  }
  .box-error-message {
    position: relative;
    overflow: hidden;
    box-sizing: border-box;
    height: 0;
    line-height: 32px;
    padding: 0 12px;
    text-align: center;
    width: 100%;
    font-size: 11px;
    color: white;
    background: #f38181;
  }
  .partition {
    width: 100%;
    height: 100%;
    .partition-title {
      box-sizing: border-box;
      padding: 30px;
      width: 100%;
      text-align: center;
      letter-spacing: 1px;
      font-size: 20px;
      font-weight: 300;
    }
    .partition-form {
      padding: 0 20px;
      box-sizing: border-box;
    }
  }
  input[type="password"],
  input[type="text"] {
    display: block;
    box-sizing: border-box;
    margin-bottom: 4px;
    width: 100%;
    font-size: 12px;
    line-height: 2;
    border: 0;
    border-bottom: 1px solid #dddedf;
    padding: 4px 8px;
    font-family: inherit;
    transition: 0.5s all;
    outline: none;
  }
  button {
    background: white;
    border-radius: 4px;
    box-sizing: border-box;
    padding: 10px;
    letter-spacing: 1px;
    font-family: "Open Sans", sans-serif;
    font-weight: 400;
    min-width: 140px;
    margin-top: 8px;
    color: #8b8c8d;
    cursor: pointer;
    border: 1px solid #dddedf;
    text-transform: uppercase;
    transition: 0.1s all;
    font-size: 10px;
    outline: none;
    &:hover {
      border-color: mix(#dddedf, black, 90%);
      color: mix(#8b8c8d, black, 80%);
    }
  }
  .large-btn {
    width: 100%;
    background: white;
    span {
      font-weight: 600;
    }
    &:hover {
      color: white !important;
    }
  }
  .button-set {
    margin-bottom: 8px;
  }
  #register-btn,
  #signin-btn {
    margin-left: 8px;
  }
  .facebook-btn {
    border-color: $facebook_color;
    color: $facebook_color;
    &:hover {
      border-color: $facebook_color;
      background: $facebook_color;
    }
  }
  .github-btn {
    border-color: $github_color;
    color: $github_color;
    &:hover {
      border-color: $github_color;
      background: $github_color;
    }
  }
    .login-btn {
    border-color: $login_color;
    color: $login_color;
    &:hover {
      border-color: $login_color;
      background: $login_color;
    }
  }
  .autocomplete-fix {
    position: absolute;
    visibility: hidden;
    overflow: hidden;
    opacity: 0;
    width: 0;
    height: 0;
    left: 0;
    top: 0;
  }
}
.box1 {
  background: white;
  overflow: hidden;
  width: 328px;
  height: 380px;
  border-radius: 2px;
  box-sizing: border-box;
  box-shadow: 0 0 40px black;
  color: #8b8c8d;
  font-size: 0;
  .box-part {
    display: inline-block;
    position: relative;
    vertical-align: top;
    box-sizing: border-box;
    height: 100%;
    width: 100%;
    &#bp-right {
      background: url("/static/panorama.jpg") no-repeat top left;
      border-left: 1px solid #eee;
    }
  }
  .box-messages {
    position: absolute;
    left: 0;
    bottom: 0;
    width: 100%;
  }
  .box-error-message {
    position: relative;
    overflow: hidden;
    box-sizing: border-box;
    height: 0;
    line-height: 32px;
    padding: 0 12px;
    text-align: center;
    width: 100%;
    font-size: 11px;
    color: white;
    background: #f38181;
  }
  .partition {
    width: 100%;
    height: 100%;
    .partition-title {
      box-sizing: border-box;
      padding: 30px;
      width: 100%;
      text-align: center;
      letter-spacing: 1px;
      font-size: 20px;
      font-weight: 300;
    }
    .partition-form {
      padding: 0 20px;
      box-sizing: border-box;
    }
  }
  input[type="password"],
  input[type="text"] {
    display: block;
    box-sizing: border-box;
    margin-bottom: 4px;
    width: 100%;
    font-size: 12px;
    line-height: 2;
    border: 0;
    border-bottom: 1px solid #dddedf;
    padding: 4px 8px;
    font-family: inherit;
    transition: 0.5s all;
    outline: none;
  }
  button {
    background: white;
    border-radius: 4px;
    box-sizing: border-box;
    padding: 10px;
    letter-spacing: 1px;
    font-family: "Open Sans", sans-serif;
    font-weight: 400;
    min-width: 140px;
    margin-top: 8px;
    color: #8b8c8d;
    cursor: pointer;
    border: 1px solid #dddedf;
    text-transform: uppercase;
    transition: 0.1s all;
    font-size: 10px;
    outline: none;
    &:hover {
      border-color: mix(#dddedf, black, 90%);
      color: mix(#8b8c8d, black, 80%);
    }
  }
  .large-btn {
    width: 100%;
    background: white;
    span {
      font-weight: 600;
    }
    &:hover {
      color: white !important;
    }
  }
  .button-set {
    margin-bottom: 8px;
  }
  #register-btn,
  #signin-btn {
    margin-left: 8px;
  }
  .facebook-btn {
    border-color: $facebook_color;
    color: $facebook_color;
    &:hover {
      border-color: $facebook_color;
      background: $facebook_color;
    }
  }
  .github-btn {
    border-color: $github_color;
    color: $github_color;
    &:hover {
      border-color: $github_color;
      background: $github_color;
    }
  }
    .login-btn {
    border-color: $login_color;
    color: $login_color;
    &:hover {
      border-color: $login_color;
      background: $login_color;
    }
  }


  .autocomplete-fix {
    position: absolute;
    visibility: hidden;
    overflow: hidden;
    opacity: 0;
    width: 0;
    height: 0;
    left: 0;
    top: 0;
  }
}
.pop-out-enter-active,
.pop-out-leave-active {
  transition: all 0.5s;
}
.pop-out-enter,
.pop-out-leave-active {
  opacity: 0;
  transform: translateY(24px);
}
</style>