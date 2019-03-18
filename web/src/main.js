import Vue from 'vue'
import Vuex from 'vuex'
import BootstrapVue from 'bootstrap-vue'
import router from './router';
import store from "./store"
import axios from "axios";

axios.defaults.withCredentials = true;

Vue.use(BootstrapVue);
Vue.use(Vuex);
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false;
import App from './App.vue';

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app');