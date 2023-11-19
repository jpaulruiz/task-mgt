import { createRouter, createWebHistory } from 'vue-router'

const isAuthenticated = async () => {
  let res = false
  await fetch('http://localhost:9000/authentication', {
    method: 'GET',
    credentials: 'include'
  })
  .then(data => {
    if (data.status == 200) res = true
    else res = false
  })
  .catch(error => {
    console.error('Error:', error)
  })
  return res
}

import Login from '../components/pages/Login.vue'
import Register from '../components/pages/Register.vue'
import Dashboard from '../components/pages/Dashboard.vue'
import Tasks from '../components/pages/Tasks.vue'
import NotFound from '../components/pages/NotFound.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'logon-form',
      component: Login
    },
    {
      path: '/register-form',
      name: 'register-form',
      component: Register
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: Dashboard,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/tasks',
      name: 'tasks-form',
      component: Tasks,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/:notFound',
      name: 'not-found',
      component: NotFound
    }
  ]
})

router.beforeEach(async (to, from, next) => {
  if (to.meta.requiresAuth) {
    if (await isAuthenticated()) {
      return next()
    } else {
      return next('/')
    }
  }

  if (to.name === 'logon-form' && await isAuthenticated()) {
    return next('/dashboard')
  }
  console.clear()
  return next()
})

export default router