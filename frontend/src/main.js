import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import 'vuetify/dist/vuetify.min.css'
import VueSimplemde from 'vue-simplemde'
import 'simplemde/dist/simplemde.min.css'
import 'font-awesome/css/font-awesome.min.css'
import 'vue-sidebar-menu/dist/vue-sidebar-menu.css'
import App from './App.vue'
import router from './router'
import store from './store'
import './registerServiceWorker'
import VueSidebarMenu from 'vue-sidebar-menu'
import vuetify from './plugins/vuetify';
 
Vue.config.productionTip = false
Vue.use(VueSidebarMenu)
Vue.use(VueSimplemde)
Vue.use(Vuetify)

new Vue({
  router,
  store,

  vuetify: new Vuetify({
      iconfont: 'fa',
      theme: {
          primary: '#3f51b5',
          secondary: '#b0bec5',
          accent: '#8c9eff',
          error: '#b71c1c'
      }
  }),

  vuetify,
  render: h => h(App)
}).$mount('#app')
