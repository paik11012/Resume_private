import firebase from 'firebase/app'
import 'firebase/firestore'
import 'firebase/auth'

const POSTS = 'posts'
const PORTFOLIOS = 'portfolios'

// Setup Firebase
// Do change to your own firebase configuration
var firebaseConfig = {
	apiKey: "AIzaSyCsc33T4aBhNINOam75wez6bb73_RyGpR0",
	authDomain: "ssafy-personal-project.firebaseapp.com",
	databaseURL: "https://ssafy-personal-project.firebaseio.com",
	projectId: "ssafy-personal-project",
	storageBucket: "ssafy-personal-project.appspot.com",
	messagingSenderId: "142781324500",
	appId: "1:142781324500:web:ed8ec498c21177753eaf41",
	measurementId: "G-LH682SSBNF"
};
firebase.initializeApp(firebaseConfig)

const firestore = firebase.firestore()

export default {
	getPosts() {
		const postsCollection = firestore.collection(POSTS)
		return postsCollection
				.orderBy('created_at', 'desc')
				.get()
				.then((docSnapshots) => {
					return docSnapshots.docs.map((doc) => {  // docs를 합친다
						let data = doc.data()
						data.created_at = new Date(data.created_at.toDate())  // 만들어진 데이트 순
						return data
					})
				})
	},
	postPost(title, body) {
		return firestore.collection(POSTS).add({
			title,
			body,
			created_at: firebase.firestore.FieldValue.serverTimestamp()
		})
	},
	getInterView(){
		const interViewCollection = firestore.collection('interview')
		console.log("이건 됐어?");
		
		return interViewCollection
			.orderBy('created_at', 'desc')
			.get()
			.then((docSnapshots)=>{
				console.log(docSnapshots);
				console.log("docsnap");
				return docSnapshots.docs.map((doc)=>{
					console.log("doc");
					let data = doc.data()
					return data
				})
			})
	},
	getPortfolios() {
		const postsCollection = firestore.collection(PORTFOLIOS)
		return postsCollection
				.orderBy('created_at', 'desc')
				.get()
				.then((docSnapshots) => {					
					return docSnapshots.docs.map((doc) => {
						let data = doc.data()
						data.created_at = new Date(data.created_at.toDate())
						return data
					})
				})
	},
	getResume() {
		const resumesCollection = firestore.collection("resumes")
		return resumesCollection
				.orderBy('created_at', 'desc')
				.get()
				.then((docSnapshots)=>{
					return docSnapshots.docs.map((doc)=>{
						let data = doc.data()
						return data
					})
				})
	},
	postPortfolio(title, body, img) {
		return firestore.collection(PORTFOLIOS).add({
			title,
			body,
			img,
			created_at: firebase.firestore.FieldValue.serverTimestamp()
		})
	},
	loginWithGoogle() {
		let provider = new firebase.auth.GoogleAuthProvider()
		return firebase.auth().signInWithPopup(provider).then(function(result) {
			let accessToken = result.credential.accessToken
			let user = result.user
			return result
		}).catch(function(error) {
			console.error('[Google Login Error]', error)
		})
	}
}