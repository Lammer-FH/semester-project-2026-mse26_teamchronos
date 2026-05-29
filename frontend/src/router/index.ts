import { createRouter, createWebHistory } from "@ionic/vue-router";
import { RouteRecordRaw } from "vue-router";

import LandingPage from "../views/HomePage.vue";
import AboutPage from "../views/AboutPage.vue";
import ImprintPage from "../views/ImprintPage.vue";
import HomePage from "../views/HomePage.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    redirect: "/home",
  },

  {
    path: "/home",
    name: "Home",
    component: HomePage,
  },

  {
    path: '/room/:id',
    name: 'Room',
    component: () => import('@/views/Room.vue')
  },

  {
    path: "/about",
    name: "About",
    component: AboutPage,
  },

  {
    path: "/imprint",
    name: "Imprint",
    component: ImprintPage,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;