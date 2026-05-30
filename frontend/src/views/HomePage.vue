<template>
  <ion-page>
    <NavbarComponent />
    <ion-content>
      <div class="hero-banner">
        <img
            src="https://images.unsplash.com/photo-1566073771259-6a8506099945"
            alt="Boutique Hotel Technikum"
            class="hero-img"
        />
      </div>
      <ion-grid>
        <ion-row class="ion-justify-content-center ion-text-center ion-margin-top">
          <ion-col>
            <ion-text color="dark">
              <h1 class="ion-margin-vertical"><strong>Boutique Hotel Technikum</strong></h1>
              <p class="ion-padding-vertical">
                Welcome to <strong>Boutique Hotel Technikum</strong>, a modern hotel located in
                the heart of Vienna.<br>We combine elegant design, comfort, and
                exceptional hospitality to create a memorable experience for every
                guest.
              </p>
            </ion-text>

            <ion-button
                router-link="/rooms"
                color="danger"
                size="large"
            >
              Book Now
            </ion-button>
          </ion-col>
        </ion-row>

        <ion-row class="ion-justify-content-center ion-margin-top">
          <ion-col size-lg="4">
            <ion-text color="dark" class="ion-text-center">
              <h2 class="ion-padding-vertical"><strong>ABOUT US</strong></h2>
            </ion-text>
            <ion-text class="ion-text-center" color="dark">
              <p>
                At the <strong>Boutique Hotel Technikum</strong>, we want to offer our guests a completely relaxing and
                unforgettable stay – whether you're in Vienna for business, pleasure, or a short break.
                Our rooms combine modern elegance with homely comfort.
                With light-filled rooms, high-quality furnishings, and a welcoming atmosphere,
                we ensure you feel right at home from the moment you arrive.
              </p>
            </ion-text>
          </ion-col>
        </ion-row>
        <ion-row>
          <ion-col>
            <ion-text color="dark" class="ion-text-center">
              <p>
                <strong><em>Boutique Hotel Technikum stands for quality, comfort, and modern hospitality.</em></strong>
              </p>
            </ion-text>
          </ion-col>
        </ion-row>
      </ion-grid>
      <FooterComponent />
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import {
  IonPage,
  IonContent,
  IonButton,
  IonText,
  IonGrid,
  IonRow,
  IonCol
} from "@ionic/vue";
import NavbarComponent from "@/components/NavbarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";
</script>

<style scoped>
  .hero-banner {
    width: 100%;
    overflow: hidden;
    line-height: 0;
  }

  .hero-img {
    width: 100%;
    height: auto;
    object-fit: cover;
    max-height: 60vh;
  }
</style>