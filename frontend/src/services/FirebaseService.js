import firebase from 'firebase/app'
import 'firebase/firestore'
import 'firebase/auth'

const POSTS = 'posts'
const PORTFOLIOS = 'portfolios'

// Setup Firebase
// Do change to your own firebase configuration
var firebaseConfig = {
	apiKey: "AIzaSyAI5eaFNRw48Cu6HxWmer_19EGEOfQCi3Q",
    authDomain: "web-9to6.firebaseapp.com",
    databaseURL: "https://web-9to6.firebaseio.com",
    projectId: "web-9to6",
    storageBucket: "web-9to6.appspot.com",
    messagingSenderId: "1089047599945",
    appId: "1:1089047599945:web:1d2ed7149ef3ecec255898",
    measurementId: "G-2JF1PT9DQ3"
};
export const app= firebase.initializeApp(firebaseConfig);

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