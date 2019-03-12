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
          path: 'student',
          name: 'student',
          component: () => import('../pages/Student')
        },
        {
          path: 'teacher',
          name: 'teacher',
          component: () => import('../pages/Teacher'),
          children:[
            {
              path:"home",
              name:"teacherHome",
              component:()=>import('../components/teacher/TeacherHome')
            },
            {
              path: 'createCourse',
              name: 'createCourse',
              component: () => import('../components/teacher/CreateCourse')
            },
            {
              path: 'createPublish',
              name: 'createPublish',
              component: () => import('../components/teacher/CreatePublish')
            },
          ]
        },
        {
          path: 'admin',
          name: 'admin',
          component: () => import('../pages/Admin'),
          children:[
            {
              path:"home",
              name:"adminHome",
              component:()=>import('../components/admin/Statistics')
            },
            {
              path: 'approveCourse',
              name: 'approveCourse',
              component: () => import('../components/admin/ApproveCourse')
            },
            {
              path: 'approvePublish',
              name: 'approvePublish',
              component: () => import('../components/admin/ApprovePublish')
            },
            {
              path: 'cutOff',
              name: 'cutOff',
              component: () => import('../components/admin/CutOff')
            },
          ]
        },
        {
          path:'admin/courseInfo',
          name:'adminCourseInfo',
          component:()=>import('../pages/AdminCourseInfo')
        }
      ]
    },
  ]
})
