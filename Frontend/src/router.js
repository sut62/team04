import Vue from 'vue'
import Router from 'vue-router'
import Register from './components/Register'
import Login from './components/Login'
import Dashboad from './components/Dashboad'
import Borrow from './components/Borrow'
import ManageEquipment from './components/ManageEquipment'
import ManageStatus from './components/ManageStatus'
import Returns from './components/Returns'

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
    {
      path: '/Dashboad',
      name:'Dashboad',
      component: Dashboad
    },
    {
      path: '/Borrow',
      name:'Borrow',
      component: Borrow
    },
    {
      path: '/ManageEquipment',
      name:'ManageEquipment',
      component: ManageEquipment
    },
    {
      path: '/ManageStatus',
      name:'ManageStatus',
      component: ManageStatus
    },
    {
      path: '/Returns',
      name:'Returns',
      component: Returns
    },
  
  ]
})
