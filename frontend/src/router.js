import Vue from 'vue'
import Router from 'vue-router'
import HomePage from './views/HomePage.vue'
import PostPage from './views/PostPage.vue'
import PortfolioPage from './views/PortfolioPage.vue'
import LoginPage from './views/LoginPage.vue'
import MJPage from './views/MJPage.vue'
import MainPage from './views/MainPage.vue'
import SignupPage from './views/SignupPage.vue'
 
Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
		{
			path: '/',
			name: 'home',
			component: HomePage
		},
		{
			path: '/post',
			name: 'post',
			component: PostPage
		},
		{
			path: '/portfolio',
			name: 'portfolio',
			component: PortfolioPage
		},
		{
			path: '/login',
			name: 'login',
			component: LoginPage
		},
		{
			path: '/info',
			name: 'info',
			component: MJPage
		},
		{
			path: '/main',
			name: 'main',
			component: MainPage
		},
		{
			path: '/signup',
			name: 'signup',
			component: SignupPage
		},
  ]
})
