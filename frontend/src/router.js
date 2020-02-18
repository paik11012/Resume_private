import Vue from 'vue'
import Router from 'vue-router'
import HomePage from './views/HomePage.vue'
import InterviewPage from './views/InterviewPage.vue'
import ResumePage from './views/ResumePage.vue'
import TeamPage from './views/TeamPage.vue'
import MyPage from './views/MyPage.vue'
import MainPage from './views/MainPage.vue'
import SignupPage from './views/SignupPage.vue'
import UserInfo from './views/UserInfo.vue'
import AdminPage from './views/AdminPage.vue'
import EditInfo from './views/EditInfo.vue'

Vue.use(Router)

export default new Router({
	
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
		{
			path: '/home',
			name: 'home',
			component: HomePage
		},
		{
			path: '/interview',
			name: 'interview',
			component: InterviewPage
		},
		{
			path: '/resume',
			name: 'resume',
			component: ResumePage
		},
		{
			path: '/team',
			name: 'team',
			component: TeamPage
		},
		{
			path: '/info',
			name: 'info',
			component: MyPage
		},
		{
			path: '/',
			name: 'main',
			component: MainPage
		},
		{
			path: '/signup',
			name: 'signup',
			component: SignupPage
		},
		{
			path: '/userinfo',
			name: 'userinfo',
			component: UserInfo
		},
		{
			path: '/admin',
			name: 'admin',
			component: AdminPage
		},
		{
			path: '/editinfo',
			name: 'editinfo',
			component: EditInfo
		}
  ]
})
