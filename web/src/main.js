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
import { library } from '@fortawesome/fontawesome-svg-core'
import { faEdit, faTrash } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'


Vue.component('font-awesome-icon', FontAwesomeIcon);
axios.defaults.withCredentials = true;
Vue.config.productionTip = false;
Vue.use(BootstrapVue);
Vue.use(Vuex);
Vue.use(VeeValidate, {
  classes: true
});
library.add(faEdit, faTrash);

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app');