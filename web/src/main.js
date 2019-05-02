import Vue from 'vue'
import Vuex from 'vuex'
import VeeValidate from 'vee-validate';
import BootstrapVue from 'bootstrap-vue'
import router from './router';
import store from "./store"
import axios from "axios";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import App from './App.vue';


axios.defaults.withCredentials = true;
Vue.config.productionTip = false;
Vue.use(BootstrapVue);
Vue.use(Vuex);
Vue.use(VeeValidate, {
  classes: true
});

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app');