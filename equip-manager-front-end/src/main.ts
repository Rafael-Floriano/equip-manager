import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import { library } from '@fortawesome/fontawesome-svg-core';
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
import { faTrash,faPen } from '@fortawesome/free-solid-svg-icons';
import 'jquery';
import router from './router/index';

library.add(faTrash);
library.add(faPen);

const app = createApp(App);
app.component('font-awesome-icon', FontAwesomeIcon);
app.use(router).mount('#app')