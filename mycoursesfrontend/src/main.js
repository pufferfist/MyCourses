// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import Vuex from 'vuex'
import vuexAlong from 'vuex-along'
import App from './App';
import router from './router/index';
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import 'animate.css/animate.css';
import 'tachyons/css/tachyons.css';
import './assets/css/main.css';
import Cookies from 'vue-cookie';
import axios from 'axios';
import VueAxios from 'vue-axios';
import echarts from 'echarts';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import util from "./assets/js/util.js";

Vue.use(Vuex);
Vue.use(iView);
Vue.use(Cookies);
Vue.use(VueAxios, axios);
Vue.use(ElementUI);
Vue.prototype.$echarts = echarts;
Vue.use(util);

Vue.config.productionTip = false;

router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/signUp' || to.path === '/verifySuccess') {
    next();
  } else {
    axios.post('/backend/auth')
      .then((res) => {
        let code = res.data.code;
        if (code === 302) {
          next({path: '/login'});
        } else {
          next();
        }
      });
  }
});

const store = new Vuex.Store({
  state: {
    type: "",
    user: {},
    course: {},
    publish: {},
    assignment: {},
    post: {}
  },
  mutations: {
    set(state, payload) {
      if (payload.type === "course") {
        state.course = payload.data;
      } else if (payload.type === "publish") {
        state.publish = payload.data;
      } else if (payload.type === "assignment") {
        state.assignment = payload.data;
      } else if (payload.type === "post") {
        state.post = payload.data;
      } else if (payload.type === "user") {
        state.user = payload.data;
      }
    },
    type(state, payload) {
      state.type = payload
    }
  },
  plugins: [vuexAlong]
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: {App},
  template: '<App/>'
});
