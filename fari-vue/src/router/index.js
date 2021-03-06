import Router from 'vue-router'
import Vue from 'vue'
import Index from '@/views/index'
import Home from '@/views/home'
import Main from '@/views/main'
import FariEditor from '@/views/blogEdit'
import UserMain from '@/views/userMain'
import BlogPage from '@/views/blogPage'
import UserInfo from '@/views/userInfoEdit'

Vue.use(Router)

const routes = [
  {
    path: '/',
    component: Main,
    children: [
      { path: 'fariblog', component: Index },
      {
        path: 'user/:user/:userId',
        name: 'User',
        component: UserMain,
        children: [
          { path: 'home', name: 'Home', component: Home },
          { path: 'editor', name: 'Editor', component: FariEditor },
          { path: 'blog/:blogId', name: 'Blog', component: BlogPage },
          { path: 'editor/:blogId', name: 'Updator', component: FariEditor },
          { path: 'info', name: 'About', component: UserInfo }
        ]
      }
    ]
  }
]

const router = new Router({
  mode: 'history',
  routes: routes
})

export default router
