import Vue from 'vue'
import Vuex, { Store } from 'vuex'
import axois from "axios"
import Router from 'vue-router'
import router from './router'
Vue.use(Vuex)
Vue.use(Router)
export default new Vuex.Store({
  actions: {
    logout() {  // 로그아웃
      const storage = window.sessionStorage
      storage.setItem('jwt-auth-token','')
      storage.setItem('user_id','');
      commit('logout')
    },

    signup(dispatch, signupObj) {
      const SERVER_IP = 'http://70.12.247.99:8080'
      axois.post(SERVER_IP + "/users/signup", signupObj)
        .then(res => {
          alert("회원가입이 성공적으로 이루어졌습니다");
        }) 
        .catch((error) => {
          alert("입력하신 정보를 확인하세요")
          console.log(error)
        })
    },

    login({state, commit, dispatch}, loginObj) {
      console.log(loginObj)
      const SERVER_IP = 'http://70.12.247.99:8080'
      const storage = window.sessionStorage;
      storage.setItem("jwt-auth-token", "");
      storage.setItem("user_id", "");
      axois.post(SERVER_IP + "/users/signin2", loginObj)
      .then(res => {
        if (res.data.status) {
          alert("로그인이 성공적으로 이루어졌습니다");
          commit("loginSuccess")
          // console.dir(res.headers["jwt-auth-token"]);
          // this.token = res.headers["jwt-auth-token"]
          // this.setInfo(
          //   "성공", res.headers["jwt-auth-token"], JSON.stringify(res.data.data)
          // );
          // storage.setItem('jwt-auth-token',res.headers['jwt-auth-token'])
          storage.setItem('jwt-auth-token','dfssdfse')
          storage.setItem('user_id',res.data.data.user_id);
          router.push('home')
        } else {
          this.message = "로그인해주세요"
          alert("입력 정보를 확인하세요")
        }
      })
      .catch((error) => {
        console.log(error)
        commit('loginError')
      })
    // },
    // init() {
    //   if (storage.getItem('jwt-auth-token')) {
    //     this.message = storage.getItem("user_id" + "로 로그인되었습니다")
    //   } else {
    //     storage.setItem('jwt-auth-token', '');
    //   } 
    // },
    // mounted() {
    //   this.init()
    }
  }, // action  끝
  state: {
    token: '',
    status: '',
    isLogin: false,
    isLoginError: '',
    userInfo: '',
    career: [
      {
        onetitle: '내사진',
        onecontent: '사진',
      },
      {
        onetitle: '병역 구분',
        onecontent: '육군 만기전역',
      },
      {
        onetitle: '계급',
        onecontent: '병장',
      },
      {
        onetitle: '복무 기간',
        onecontent: '2015-12-01 ~ 2017-11-29',
      },

      {
        onetitle: '메모',
        onecontent: 'Caught a mistake or want to contribute to the documentation? Edit this page on GitHub!',
      },
    ],
    edu: [
      {
        onetitle: '고등학교',
        onecontent: '람쥐고등학교',
      },
      {
        onetitle: '토순대학교',
        onecontent: '2013-03-02 ~ 2017-02-24',
      },
      {
        onetitle: 'Eclair',
        onecontent: 262,
      },
      {
        onetitle: 'Cupcake',
        onecontent: 305,
      },
    ],
  },
  mutations: {
    loginSuccess(state, payload) {
      state.isLogin = true
      state.isLoginError = false
      state.userInfo = payload
    },
    // 로그인이 실패했을 때,
    loginError(state) {
      state.islogin = false
      state.isLoginError = true
    },
    logout(state) {
      state.isLogin = false
      state.isLoginError = false
      state.userInfo = null
    }
  }
})
