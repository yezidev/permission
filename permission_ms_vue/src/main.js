import Vue from 'vue'
import App from './App.vue'
import router from './router'
//配置axios框架
import axios from "axios";
//配置element ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(ElementUI);
//设置原型  项目中this.axios.get
Vue.prototype.axios = axios;
//配置基础路径
Vue.prototype.axios.defaults.baseURL = "http://localhost:8080/";

//设置axios请求拦截器
axios.interceptors.request.use(config => {
  //拦截到请求后，将token加到请求头中
    let token = localStorage.getItem("token");
    if (token){
        config.headers.Authorization = token;
    }
    return config;
})

Vue.config.productionTip = false

new Vue({
    router,
    render: function (h) {
        return h(App)
    }
}).$mount('#app')