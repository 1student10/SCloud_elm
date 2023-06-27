import {createRouter, createWebHashHistory} from 'vue-router'

const routes = [

    { path: '/',name:'index', component: () => import('../views/index.vue') },
    { path: '/login',name:'login', component: () => import('../views/login.vue') },
    { path: '/register',name:'register', component: () => import('../views/register.vue') },
    { path: '/businessList',name:'businessList', component: () => import('../views/businessList.vue') },
    { path: '/businessInf',name:'businessInf', component: () => import('../views/businessInf.vue') },
    { path: '/historicalOrders',name:'historicalOrders', component: () => import('../views/historicalOrders.vue') },
    { path: '/order',name:'order', component: () => import('../views/order.vue') },
    { path: '/payment',name:'payment', component: () => import('../views/payment.vue') },

    { path: '/login2',name:'login2', component: () => import('../components/HelloWorld.vue') },

]

const router = createRouter({
    history: createWebHashHistory(process.env.BASE_URL),
    routes:routes,
})

// // 路由守卫
// const whiteList = ['/login', '/','/register','/business_list']
// router.beforeEach((to, from, next) => {
//     if (whiteList.includes(to.path) || localStorage.getItem('token') !== null) {
//         next()
//     } else {
//         alert('您还没有登录，请先登录');
//         next('/login')
//     }
// })

export default router
