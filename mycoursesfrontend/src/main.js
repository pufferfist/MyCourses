// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router/index';
import iView from 'iview';
import 'iview/dist/styles/iview.css';
import 'animate.css/animate.css';
import 'tachyons/css/tachyons.css';
import Cookies from 'vue-cookie';
import axios from 'axios';
import VueAxios from 'vue-axios';
import echarts from 'echarts';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

Vue.use(iView);
Vue.use(Cookies);
Vue.use(VueAxios, axios);
Vue.use(ElementUI);
Vue.prototype.$echarts = echarts;

Vue.config.productionTip = false;

const api = {
  async auth() {
    let code;
    await axios.post('/backend/auth')
      .then((res) => {
        code = res.data.code;
      });
    return code;
  }
};

router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/signup') {
    next();
  } else {
    let code = api.auth();
    if (code === 302) {
      next({path: '/login'});
    } else {
      next();
    }
  }
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
