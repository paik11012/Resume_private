import Vue from 'vue'
import Vuex, { Store } from 'vuex'
import axois from "axios"
import Router from 'vue-router'
import router from './router'
import Axios from 'axios'
Vue.use(Vuex)
Vue.use(Router)
export default new Vuex.Store({
  state: {
    token: '',
    status: '',
    isLogin: false,
    isLoginError: '',
    userInfo: null,
  },
  mutations: {
    loginSuccess(state, userInfo) {
      state.isLogin = true
      state.isLoginError = false
      state.userInfo = userInfo 
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
  },
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
      const SERVER_IP = 'http://70.12.247.99:8080'
      const storage = window.sessionStorage;
      storage.setItem("jwt-auth-token", "");
      storage.setItem("user_id", "");
      axois.post(SERVER_IP + "/users/signin", loginObj)
      .then(res => {
        if (res.data.status) {
          alert("로그인이 성공적으로 이루어졌습니다");
          console.log(res.data)
          storage.setItem('jwt-auth-token',res.headers['jwt-auth-token'])
          // storage.setItem('jwt-auth-token','dfssdfse')
          storage.setItem('user_id',res.data.data.user_id);
          dispatch("getMemberInfo")
          
          // 새로고침시 state 날라가는 경우
          // 토큰만 갖고 멤버정보 요청 가능 , session
          // commit("loginSuccess", user_info)
          router.push('home')
        } else {
          this.message = "로그인해주세요"
          alert("입력 정보를 확인하세요")
        }
      })
      .catch((error) => {
        console.log(error)
        commit('loginError')
        alert("입력 정보를 확인하세요")
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
    },
    getMemberInfo({ commit }) {
      // 저장된 토큰 불러오기
      let token = sessionStorage.getItem("jwt-auth-token")
      let config = {headers : {"jwt-auth-token": token}}
      let id = res.data.data.user_id
      axios.get(`http://70.12.247.99:8080/users/findOne/${id}`, config)
      .then(res => {
        const userInfo = {
          user_id : res.data.data.user_id,
          user_phone : res.data.data.user_phone,
          user_name : res.data.data.user_name
        }
        commit("loginSuccess", userInfo)
      })
      .catch(() => {alert("이메일과 비밀번호를 확인하세요.")}
      )
    }
  }, // action  끝  
})
