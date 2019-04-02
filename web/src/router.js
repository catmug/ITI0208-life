import Vue from 'vue'
import VueRouter from "vue-router";
import login from './components/Login';
import expense from './components/ExpenseView';
import store from "./store";
import register from './components/Register'

Vue.use(VueRouter);

const router = new VueRouter({
    routes: [
        {
            path: '/expense',
            name: 'expense',
            component: expense
        },
        {
            path: '/login',
            name: 'login',
            component: login
        },
        {
            path: '/register',
            name: 'register',
            component: register
        },
        { path: '*', redirect: '/login' },
    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login', '/register'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = store.getters.isAuthenticated;

    if (authRequired && !loggedIn) {
        return next('/login');
    } else if (authRequired && loggedIn){
        next();
    } else {
        next();
    }
});

export default router;