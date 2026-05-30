import { createRouter, createWebHistory } from "@ionic/vue-router";
import { RouteRecordRaw } from "vue-router";

import HomePage from "../views/HomePage.vue";
import BookingConfirmation from '../views/BookingConfirmation.vue';

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
    path: '/confirmation',
    name: 'Confirmation',
    component: BookingConfirmation
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
