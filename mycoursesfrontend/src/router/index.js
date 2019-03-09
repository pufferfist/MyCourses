import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);

export default new Router({
  mode:'history',
  base:'/',
  scrollBehavior (to, from, savedPosition) { // 路由切换时滚动到页面上方
    return { x: 0, y: 0 }
  },
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: () => import('../pages/Login')
    },
    {
      path: '/',
      redirect: '/login',
      name:"father",
      component:()=>import("../pages/FatherPage"),
      children:[
        {
          path: '/student',
          name: 'student',
          component: () => import('../pages/StudentHome')
        },
        {
          path: '/teacher',
          name: 'teacher',
          component: () => import('../pages/TeacherHome')
        },
      ]
    },
  ]
})
