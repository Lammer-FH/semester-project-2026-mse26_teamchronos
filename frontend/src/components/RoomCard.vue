<template>
  <ion-card class="ion-margin-bottom">
    <ion-grid class="ion-no-padding">
      <ion-row class="ion-align-items-center" :class="{ 'desktop-reverse': reverse }">

        <ion-col size="12" size-lg="6">
          <img
              :src="image"
              :alt="title"
              style="width: 100%; display: block; object-fit: cover; max-height: 350px;"
          />
        </ion-col>

        <ion-col size="12" size-lg="6" class="ion-padding">
          <ion-card-header class="ion-no-padding ion-margin-bottom">
            <ion-card-title>
              {{ title }}
            </ion-card-title>
          </ion-card-header>

          <ion-card-content class="ion-no-padding">
            <p class="ion-margin-bottom">
              {{ description }}
            </p>

            <div class="ion-margin-vertical">
              <ion-chip v-for="feature in features" :key="feature.text" :disabled="true" style="opacity: 1;">
                <i :class="feature.icon" style="margin-right: 6px;"></i>
                <ion-label>{{ feature.text }}</ion-label>
              </ion-chip>
            </div>

            <div class="ion-margin-top">
              <ion-button
                  :router-link="'/rooms/' + id"
                  color="danger"
                  class="ion-no-margin">
                Select Room
              </ion-button>
            </div>
          </ion-card-content>
        </ion-col>

      </ion-row>
    </ion-grid>
  </ion-card>
</template>

<script setup lang="ts">
import {
  IonCard,
  IonGrid,
  IonRow,
  IonCol,
  IonCardHeader,
  IonCardTitle,
  IonCardContent,
  IonChip,
  IonLabel,
  IonButton
} from '@ionic/vue';

type Feature = {
  icon: string
  text: string
}

defineProps<{
  id: number
  title: string
  description: string
  image: string
  features: Feature[]
  reverse?: boolean
}>()
</script>

<style scoped>
@media (min-width: 992px) {
  .desktop-reverse {
    flex-direction: row-reverse;
  }
}
</style>