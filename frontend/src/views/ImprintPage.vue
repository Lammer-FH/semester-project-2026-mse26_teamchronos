<template>
  <ion-page class="min-vh-100 d-flex flex-column">
    <NavbarComponent />

    <ion-content class="flex-grow-1">
      <div class="container my-5">
        <h1 class="display-4 fw-bold mb-5">Legal Notice</h1>

        <h3 class="mb-4">Chronos Hotel</h3>

        <h5>Address</h5>
        <p class="mb-4">
          Höchstädtplatz 6<br />
          1200 Vienna<br />
          Austria
        </p>

        <h5>Contact Information</h5>
        <p class="mb-4">
          Email: contact@chronos-hotel.at<br />
          Phone: +43 1 234 567
        </p>

        <h5>Responsible for Content</h5>
        <p class="mb-4">
          Chronos Hotel Management<br />
          Höchstädtplatz 6<br />
          1200 Vienna<br />
          Austria
        </p>

        <h5>Disclaimer</h5>
        <p>
          The content of this website has been created with the greatest
          possible care. However, we cannot guarantee the accuracy,
          completeness, or timeliness of the information provided.
        </p>
      </div>
    </ion-content>

    <FooterComponent />
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent } from "@ionic/vue";
import NavbarComponent from "@/components/NavbarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";
</script>
