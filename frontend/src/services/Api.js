import axios from 'axios'

export default axios.create({
<<<<<<< HEAD
		baseURL: 'http://70.12.247.99:8080',
		//baseURL : 'http://15.164.244.244:8080', //회원가입용  url
=======
		// baseURL: 'http://70.12.247.99:8080',
		baseURL : 'http://15.164.244.244:8080', //회원가입용  url
		// baseURL : 'http://localhost:8080',
>>>>>>> c38b7d53f96ec94674a4904abf280f952e32d2c9
		withCredentials: false,
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'token': window.sessionStorage.getItem("jwt-auth-token"),
			'user_id': window.sessionStorage.getItem("user_id")
		}
})
