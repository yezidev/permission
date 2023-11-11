import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from "@/views/MainView.vue";
import LoginView from "@/views/LoginView.vue";
import RoleView from "@/views/RoleView.vue";
import PermissionView from "@/views/PermissionView.vue";
import UserView from "@/views/UserView.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: LoginView
  },
  {
    path: '/main',
    name: 'main',
    component: MainView,
    children:[
      {
        path:'/user',
        name:'user',
        component:UserView
      },
      {
        path:'/role',
        name:'role',
        component:RoleView
      },
      {
        path:'/permission',
        name:'permission',
        component:PermissionView
      },
    ]
  },
]

const router = new VueRouter({
  routes
})

export default router
