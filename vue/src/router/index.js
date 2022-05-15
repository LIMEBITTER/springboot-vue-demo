import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "../store"
// import i18n from './i18n/index';

Vue.use(VueRouter)

//动态设置网站标题
// Vue.directive('title',{
//   inserted:function (el,binding) {
//     document.title = el.dataset.title
//   }
// })

const routes = [
  {
    path: '/',
    name: 'Manager',
    component: ()=> import('../views/Manager.vue'),
    meta:{title:"首页",requireAuth: true},
    //  / 会重定向至/home
    redirect:"/home",
    children:[
      //主页界面  requireAuth 是否需要登录权限
      {path: 'home', name: 'Home', component: ()=> import('../views/Home.vue'), meta:{title:"小区疫情信息",requireAuth: true}},
      //表格管理界面（CRUD）
      {path: 'user', name: 'User', component: ()=> import('../views/User.vue'), meta:{title:"后台管理人员信息",requireAuth: true}},
        //个人信息界面
      {path: 'person', name: 'Person', component: ()=> import('../views/Person.vue'), meta:{title:"用户信息",requireAuth: true}},
        //居民管理
      {path: 'resident', name: 'Resident', component: ()=> import('../views/Resident.vue'), meta:{title:"小区居住人员信息",requireAuth: true}},
      {path: 'outsider', name: 'Outsider', component: ()=> import('../views/Outsider.vue'), meta:{title:"非小区人员信息",requireAuth: true}},
      {path: 'residentTravel', name: 'ResidentTravel', component: ()=> import('../views/ResidentTravel.vue'), meta:{title:"小区人员出入信息",requireAuth: true}},
      {path: 'outsiderTravel', name: 'OutsiderTravel', component: ()=> import('../views/OutsiderTravel.vue'), meta:{title:"非小区人员出入信息",requireAuth: true}},
      {path: 'health', name: 'Health', component: ()=> import('../views/Health.vue'), meta:{title:"小区人员健康信息",requireAuth: true}},
      {path: 'volunteer', name: 'Volunteer', component: ()=> import('../views/Volunteer.vue'), meta:{title:"小区志愿者人员管理",requireAuth: true}},

      //
      {path: 'records', name: 'Records', component: ()=> import('../views/Records.vue'), meta:{title:"诊断记录",requireAuth: true}},
      {path: 'realTimeData', name: 'RealTimeData', component: ()=> import('../views/RealTimeData.vue'), meta:{title:"全国疫情形势",requireAuth: true}},
        //




    ]
  },

  //登录页面
  {
    path:'/login',
    name:'Login',
    component: ()=> import('../views/Login.vue'),
    meta:{title: "登录页面"}
  },
  //注册页面
  {
    path:'/register',
    name:'Register',
    component: () => import('../views/Register.vue'),
    meta:{title:"注册页面"}
  },

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//路由守卫
router.beforeEach((to, from, next) => {
  // document.title = i18n.t("router." + to.name)
  //单语言项目 实现动态标题
  let token = localStorage.getItem("user")
  console.log('token',JSON.stringify(token))
  console.log('from',from)
  document.title = to.meta.title
  if (to.meta.requireAuth) { // 判断跳转的路由是否需要登录
    if (token) { // vuex.state判断token是否存在
      next() // 已登录

    } else {
      next({
        path: '/login',
        query: {redirect: to.fullPath} // 将跳转的路由path作为参数，登录成功后跳转到该路由
      })
    }
  } else {
    next()
  }



})


export default router
