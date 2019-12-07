import Vue from 'vue'
import Router from 'vue-router'
import Register from './components/Register'
import Login from './components/Login'


Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/Register',
      name: 'Register',
      component: Register
    },
    {
      path: '/',
      name:'Login',
      component: Login
    },
  
  ]
})
