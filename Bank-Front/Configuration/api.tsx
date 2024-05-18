import axios from "axios";


const api = axios.create({
    baseURL: process.env.EXPO_PUBLIC_BACKEND_API,
    headers: {
        'Accept': 'application/json, text/plain, */*',
    }
})

export default api;