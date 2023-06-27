import axios from "axios";
import cacheService from "./cacheService";

const tokenKey = 'Access-Token';

// 创建 axios 实例
/** @type {import('node_modules/axios/index').AxiosInstance} request */
const request = axios.create({
    // API 请求的默认前缀
    // baseURL: process.env.VUE_APP_API_BASE_URL,
    baseURL: 'http://127.0.0.1:8080',
    timeout: 30000 // 请求超时时间
})

// 异常拦截处理器
const errorHandler = (error: any) => {
    if (error.response) {
        const data = error.response.data
        // 从 localstorage 获取 token
        const token = cacheService.get(tokenKey)
        if (error.response.status === 403) {
            //todo: 决定是否引发错误通知
            // notification.error({
            //     message: 'Forbidden',
            //     description: data.message
            // })
        }
        if (error.response.status === 401 && !(data.result && data.result.isLogin)) {
            if (token) {
                cacheService.remove(tokenKey);
                window.location.reload();

                //todo: 决定是否引发 登出 事件
                // cacheService.dispatch('Logout').then(() => {
                //     window.location.reload()
                // })
            }
        }
    }
    return Promise.reject(error)
}

// request interceptor
request.interceptors.request.use(config => {
    const token = cacheService.get('Access-Token')
    // 如果 token 存在
    // 让每个请求携带自定义 token 请根据实际情况自行修改
    if (token && config.headers) {
        config.headers['Authorization'] = token
    }
    return config
}, errorHandler)

// response interceptor
request.interceptors.response.use(response => {
    if (response.config.url === '/login') {
        return response
    } else {

        if (response.config?.responseType === 'blob') {
            return response
        } else {
            console.warn(response)
            //todo: 决定是否 弹出提醒
            //alert(response.data.msg)
            // eslint-disable-next-line prefer-promise-reject-errors
            return Promise.reject({code: response.data.code, msg: response.data.msg})
        }
    }
}, errorHandler)

