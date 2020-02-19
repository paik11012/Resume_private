import axios from 'axios'

export default axios.create({
		baseURL: 'http://70.12.247.99:8080',
		//baseURL : 'http://15.164.244.244:8080', //회원가입용  url
<<<<<<< HEAD
		// baseURL : 'http://localhost:8080',
=======
		//baseURL : 'http://localhost:8080/'
>>>>>>> 117fb1bb30638dc6aa62d96ea26c701cdcd6eda8
		withCredentials: false,
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'token': window.sessionStorage.getItem("jwt-auth-token"),
			'user_id': window.sessionStorage.getItem("user_id")
		}
})
