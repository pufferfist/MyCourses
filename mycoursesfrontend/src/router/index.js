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
    },{
      path: '/signUp',
      name: 'SignUp',
      component: () => import('../pages/SignUp')
    },{
      path: '/verifySuccess',
      name: 'VerifySuccess',
      component: () => import('../pages/VerifySuccess')
    },
    {
      path: '/',
      redirect: '/login',
      name:"father",
      component:()=>import("../pages/FatherPage"),
      children:[
        {
          path:'post',
          name:'postPage',
          component:()=>import('../pages/PostPage')
        },
        {
          path: 'userInfo',
          name: 'UserInfo',
          component: () => import('../pages/UserInfoPage')
        },
        {
          path: 'student',
          name: 'student',
          component: () => import('../pages/Student'),
          children:[
            {
              path:"home",
              name:"studentHome",
              component:()=>import('../components/student/StudentHome')
            },{
              path:"allPublishList",
              name:"studentElectiveList",
              component:()=>import('../components/student/allPublishList')
            },{
              path:"withdraw",
              name:"studentWithdraw",
              component:()=>import('../components/student/StudentWithdraw')
            },{
              path:"teacherPublishList/:teacherId",
              name:"teacherPublishList",
              component:()=>import('../components/student/TeacherPublishList')
            }
          ]
        },{
          path:'student/publishInfo',
          name:'studentPublishInfo',
          component:()=>import('../pages/StudentPublishInfo')
        },{
          path:'student/assignmentInfo',
          name:'studentAssignmentInfo',
          component:()=>import('../pages/StudentAssignmentInfo')
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
              path: 'courseList',
              name: 'courseList',
              component: () => import('../components/teacher/TeacherCourseList')
            },
            {
              path: 'createPublish',
              name: 'createPublish',
              component: () => import('../components/teacher/CreatePublish')
            },
          ]
        },
        {
          path:'teacher/courseInfo',
          name:'teacherCourseInfo',
          component:()=>import('../pages/TeacherCourseInfo')
        },{
          path:'teacher/publishInfo',
          name:'teacherPublishInfo',
          component:()=>import('../pages/TeacherPublishInfo')
        },{
          path:'teacher/assignmentInfo',
          name:'teacherAssignmentInfo',
          component:()=>import('../pages/TeacherAssignmentInfo')
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
        },{
          path:'admin/publishInfo',
          name:'adminPublishInfo',
          component:()=>import('../pages/AdminPublishInfo')
        },
      ]
    },
  ]
})
