<template>
  <ion-header class="ion-no-border">
    <ion-toolbar color="dark" class="custom-toolbar">
      <ion-title>
        <router-link to="/" class="brand-link">
          Boutique Hotel Technikum
        </router-link>
      </ion-title>

      <ion-buttons slot="end">
        <ion-button
            router-link="/rooms"
            fill="solid"
            color="danger"
        >
          Book Now
        </ion-button>
      </ion-buttons>
    </ion-toolbar>
  </ion-header>
</template>

<script setup>
import { IonHeader, IonToolbar, IonTitle, IonButtons, IonButton } from '@ionic/vue';
</script>

<style scoped>

.custom-toolbar {
  --padding-top: 12px;
  --padding-bottom: 12px;
  --padding-start: 50px;
  --padding-end: 50px;
}

.brand-link {
  color: var(--ion-color-dark-contrast, #fff);
  text-decoration: none;
  font-weight: 700;
  font-size: 1.3rem;
}
</style>