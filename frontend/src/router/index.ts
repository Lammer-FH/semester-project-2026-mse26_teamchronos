import { createRouter, createWebHistory } from "@ionic/vue-router";
import { RouteRecordRaw } from "vue-router";

import HomePage from "../views/HomePage.vue";
import ImprintPage from "../views/ImprintPage.vue";
import SelectionPage from "../views/SelectionPage.vue";

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
    path: "/imprint",
    name: "Imprint",
    component: ImprintPage,
  },

  {
    path: "/rooms",
    name: "Room Selection",
    component: SelectionPage,
  },

  {
    path: '/room/:id',
    name: 'Room',
    component: HomePage,
  },
];

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
