import Vue from 'vue'
import Vuex from 'vuex'
import Router from 'vue-router'
import router from './router'
import API from "./services/Api"

Vue.use(Vuex)
Vue.use(Router)
export default new Vuex.Store({
  state: {
    reseumes: 0,
    interviews: 0,
    token: '',
    status: '',
    isLogin: false,
    isLoginError: '',
    userInfo: null,
    loginObj: {
      user_id : 3,
      user_password: 2,
    }
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

    signup(dispatch, signupObj, state) {
      const storage = window.sessionStorage;
      if (signupObj.user_id == null
        || signupObj.user_name == null
        || signupObj.user_password == null
        ) {
          return alert('전화번호 외에 모든 정보를 입력해주세요')
        } else {
          API.post("/users/signup", signupObj)
            .then(res => {
            alert("회원가입이 성공적으로 이루어졌습니다");     
            setTimeout(() => {
              const loginObj = {
                user_id : signupObj.user_id,
                user_password: signupObj.user_password
              }
              console.log(loginObj)
            API.post('/users/signin', loginObj)
            .then(res => {
              console.log(res)
              if (res.data.status) {
                console.log(res.data)
                storage.setItem('jwt-auth-token',res.headers['jwt-auth-token'])
                storage.setItem('user_id',res.data.data.user_id);
                router.push('home')
              } else {
                alert("입력 정보를 확인하세요")
              }
            })
            .catch((error) => {
              console.log(error)
              alert("로그인 정보를 확인하세요")
            })
          }, 500);
        })
        .catch((error) => {
          alert("입력하신 정보를 확인하세요")
          console.log(error)
        })
        }
    },


    login({state, commit, dispatch}, loginObj) {
      const storage = window.sessionStorage;
      storage.setItem("jwt-auth-token", "");
      storage.setItem("user_id", "");
      API.post("/users/signin", loginObj)
      .then(res => {
        if (res.data.status) {
          alert("로그인이 성공적으로 이루어졌습니다");
          console.log(res.data)
          storage.setItem('jwt-auth-token',res.headers['jwt-auth-token'])
          storage.setItem('user_id',res.data.data.user_id);
          // 새로고침시 state 날라가는 경우
          // 토큰만 갖고 멤버정보 요청 가능 , session
          // commit("loginSuccess", user_info)
          router.push('home')
        } else {
          alert("입력 정보를 확인하세요")
        }
      })
      .catch((error) => {
        console.log(error)
        commit('loginError')
        alert("입력 정보를 확인하세요")
      })
    },
  }, // action  끝  
})
