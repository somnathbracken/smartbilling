import { createRouter, createWebHistory } from 'vue-router';
import ProductView from '../views/ProductView.vue';

const routes = [
  { path: '/', name: 'Product', component: ProductView },
];

const router = createRouter({ history: createWebHistory(), routes });
export default router;