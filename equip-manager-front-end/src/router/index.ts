import HelloWorld from '@/components/molecules/HelloWorld.vue';
import InventoryItemform from '@/components/organisms/InventoryItemform.vue';
import SearchWithPagination from '@/components/organisms/SearchWithPagination.vue';
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  { path: '/', name: 'Search', component: SearchWithPagination }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
