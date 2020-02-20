import axios from 'axios'

export default axios.create({
		baseURL: 'http://70.12.247.99:8080',
		// baseURL : 'http://15.164.244.244:8080', //회원가입용  url
<<<<<<< HEAD
		//baseURL : 'http://localhost:8080',
=======
		// baseURL : 'http://localhost:8080',
>>>>>>> f70d8fe45d9f5b2b40cef23fd105aac94028a8a1
		withCredentials: false,
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'token': window.sessionStorage.getItem("jwt-auth-token"),
			'user_id': window.sessionStorage.getItem("user_id")
		}
})
