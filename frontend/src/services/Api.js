import axios from 'axios'

export default axios.create({
<<<<<<< HEAD
		// baseURL: 'http://70.12.247.99:8080',
		baseURL : 'http://15.164.244.244:8080',
=======
		baseURL: 'http://70.12.247.99:8080',
		//baseURL : 'http://15.164.244.244:8080', //회원가입용  url
>>>>>>> 50688dd93665ef7db098ec85963d93168c80f5de
		withCredentials: false,
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'token': window.sessionStorage.getItem("jwt-auth-token"),
			'user_id': window.sessionStorage.getItem("user_id")
		}
})
