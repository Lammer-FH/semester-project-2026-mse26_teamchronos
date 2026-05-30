import { createRouter, createWebHistory } from "@ionic/vue-router";
import { RouteRecordRaw } from "vue-router";

import HomePage from "../views/HomePage.vue";
import ImprintPage from "../views/ImprintPage.vue";
import SelectionPage from "../views/SelectionPage.vue";
import RoomPage from "../views/RoomPage.vue";
import BookingConfirmationPage from '../views/BookingConfirmationPage.vue';

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
    path: "/rooms",
    name: "Room Selection",
    component: SelectionPage,
  },

  {
    path: '/rooms/:id',
    name: 'Room',
    component: RoomPage,
  },
  {
    path: '/confirmation',
    name: 'Confirmation',
    component: BookingConfirmationPage
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
