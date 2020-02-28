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

