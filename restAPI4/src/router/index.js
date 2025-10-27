import { createRouter, createWebHistory } from 'vue-router'
import UserList from '../views/UserList.vue'
import UserForm from '../views/UserForm.vue'

const routes = [
  {
    path: '/',
    name: 'UserList',
    component: UserList
  },
  {
    path: '/user/new',
    name: 'UserCreate',
    component: UserForm
  },
  {
    path: '/user/:id/edit',
    name: 'UserEdit',
    component: UserForm
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router 