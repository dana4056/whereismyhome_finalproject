import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// for using toasts
// import VueBootstrapToasts from "vue-bootstrap-toasts"
// Vue.use(VueBootstrapToasts);


// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)




import VueToast from 'vue-toast-notification';
import 'vue-toast-notification/dist/theme-default.css';
Vue.use(VueToast);

Vue.config.productionTip = false

const KAKAKO_API_KEY = '3858f416d945e0f39ae7edec68ad3c6e';
window.Kakao.init(KAKAKO_API_KEY);


new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
