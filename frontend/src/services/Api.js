import axios from 'axios'

export default axios.create({
		baseURL: 'http://70.12.247.99:8080',
		// baseURL : 'http://15.164.244.244:8080',
		withCredentials: false,
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'token': window.sessionStorage.getItem("jwt-auth-token"),
			'user_id': window.sessionStorage.getItem("user_id")
		}
})
