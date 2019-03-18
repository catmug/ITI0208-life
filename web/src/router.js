import Vue from 'vue'
import VueRouter from "vue-router";
import login from './components/Login'
import store from "./store";

Vue.use(VueRouter);

const router = new VueRouter({
    routes: [
        {
            path: '/login',
            name: 'login',
            component: login
        }
    ]
});

router.beforeEach((to, from, next) => {
    const publicPages = ['/login'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = store.getters.isAuthenticated;

    if (authRequired && !loggedIn) {
        return next('/login');
    }
    next();
});

export default router;