import './style.scss'
import {createApp} from 'vue'
import App from './App.vue'
import axios from 'axios'
import router from "./router"

axios.defaults.baseURL = 'http://localhost:8080'

const app = createApp(App);
app.use(router)
app.mount('#app')
app.config.globalProperties.$http = axios