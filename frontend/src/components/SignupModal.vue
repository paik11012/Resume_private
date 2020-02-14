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
          <div class="partition-title">SIGN UP</div>

          <div class="partition-form">
            <form autocomplete="false">
              <input type="text" required :rules="emailRules" v-model="user_id" placeholder="Email" :state="emailValidation">
                <button @click="signup({user_id, user_email, user_password, user_phone, user_name})" class="large-btn github-btn">
              <input type="text" :placeholder="message">
               
              <span>인증 번호 보내기</span>
            </button>
             <input required v-model="auth_num" type="text" placeholder="인증번호" />
              <button @click="signup({user_id, user_email, user_password, user_phone, user_name})" class="large-btn github-btn">
              <span>인증 번호 보내기</span>
            </button>
              <input
                v-model="user_password"
                id="n-password2"
                type="password"
                placeholder="Password"
                required
              />
              <input required v-model="user_phone" type="text" placeholder="Phone Number" />
              <input v-model="user_name" type="text" placeholder="Name" />
            </form>

            <div style="margin-top: 42px"></div>

            <button @click="signup({user_id, user_email, user_password, user_phone, user_name})" class="large-btn github-btn">
              <span>Sign Up</span>
            </button>
          </div>

        </div>
      </div>
      <div class="box-part" id="bp-right">
        <img
          src="https://cdn.pixabay.com/photo/2016/11/23/00/37/art-1851483_960_720.jpg"
          alt="pastel"
          style="width:800px"
        />
        <div class="box-messages"></div>
      </div>
    </div>
    <div class="box1" v-else>
      <div class="box-part" id="bp-left">
        <div class="partition" id="partition-register">
          <div class="partition-title">Sign Up</div>
          <div class="partition-form">
            <form autocomplete="false">
              <input data-vv-as="email" v-model="user_id" name="email_field" type="text" placeholder="Email">
              <input
                v-model="user_password"
                id="n-password2"
                type="password"
                placeholder="Password"
                required
              />
              <input required v-model="user_phone" type="text" placeholder="Phone Number" />
              <input v-model="user_name" type="text" placeholder="Name" />
            </form>

            <div style="margin-top: 42px"></div>

            <button @click="signup({user_id, user_email, user_password, user_phone, user_name})" class="large-btn github-btn">
              <span>Sign Up</span>
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
          <div class="partition-title">Sign Up</div>
            <div class="partition-form">
            <form autocomplete="false">
              <input placeholder="Email" data-vv-as="email" v-model="user_id" name="email_field" type="text">
              <input
                v-model="user_password"
                id="n-password2"
                type="password"
                placeholder="Password"
                required
              />
              <input required v-model="user_phone" type="text" placeholder="Phone Number" />
              <input v-model="user_name" type="text" placeholder="Name" />

            </form>

            <div style="margin-top: 42px"></div>

            <button class="large-btn github-btn" @click="signup({user_id, user_email, user_password, user_phone, user_name})">
              <span>Sign Up</span>
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
          <div class="partition-title">Sign Up</div>
          <div class="partition-form">
            <form autocomplete="false">
              <input placeholder="Email" data-vv-as="email" v-model="user_id" name="email_field" type="text">
              <input
                v-model="user_password"
                id="n-password2"
                type="password"
                placeholder="Password"
                required
              />
              <input required v-model="user_phone" type="text" placeholder="Phone Number" />
              <input v-model="user_name" type="text" placeholder="Name" />
            </form>

            <div style="margin-top: 42px"></div>

            <button @click="signup({user_id, user_email, user_password, user_phone, user_name})" class="large-btn github-btn">
              <span>Sign Up</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  </v-dialog>
</template>
<script>

import { mapActions } from 'vuex';

const MODAL_WIDTH = 656;
export default {
  name: "SignupModal",
  props: {
    value: {
      required: true
    }
  },
  created() {},
  computed: {
    show: {
      get() {
        return this.value;
      },
      set(value) {
        this.$emit("input", value);
      }
    },

    // 이메일 형식 체크 //
    emailValidation() {
      let re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
        if(!re.test(this.user_id)){
          this.message = "이메일 형식이 올바르지 않습니다."
          return false;
        }
        else {
          this.message = "올바른 이메일 형식입니다."
          return true;
        }
    }
    // END: 이메일 형식 체크 //
  },
  data() {
    return {
      errors: [],
      modalWidth: MODAL_WIDTH,
      autogrow: true,
      dialog: false,
      emailRules: [
        value => !!value || "Required.",
        value => (value || "").length <= 20 || "Max 20 characters",
        value => {
          const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
          return pattern.test(value) || "Invalid e-mail.";
        }
      ],
      user_id: null,
      user_password: null,
      user_name: null,
      user_email: null,
      user_phone:null,
      storeData: 'Yes',

      // 이메일 형식 체크 메세지 //
      message: '',
      auth_num : ''
    }; 
  },
  methods: {
    submitted() {
      this.isSubmitted = true;
    },
    sendMail() {
        API.GET('/users/sendmail/${this.user_id}')
      .then(response=>{
        console.log(response.data)
      })
      .catch(error=>{
        console.log(error)
      })

    },
    ...mapActions(["signup"]),
  },                        
}
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
  height: 400px;
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
  height: 400px;
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