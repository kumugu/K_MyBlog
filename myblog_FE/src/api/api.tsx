import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080/api/",
    headers: {
        "Content-Type": "application/json; charset=utf-8",
    },
});



// Request 인터셉터
api.interceptors.request.use(
    (config) => {
        // 토큰 설정 부분 제거
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// Response 인터셉터
api.interceptors.response.use(
    (response) => {
        return response;
    },
    (error) => {
        if (error.response?.status === 401) {
            window.location.href = '/';  // 인증 에러 시 로그인 페이지로 리다이렉트
        }
        return Promise.reject(error);
    }
);

export default api;
