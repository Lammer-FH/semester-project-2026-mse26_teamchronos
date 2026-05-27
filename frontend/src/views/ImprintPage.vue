<template>
  <ion-page class="min-vh-100 d-flex flex-column">
    <NavbarComponent />

    <ion-content class="flex-grow-1">
      <div class="container my-5">
        <h2 class="display-4 mb-4">Chronos Hotel</h2>
        <p>
          Höchstädtplatz 6<br />
          1200 Wien<br />
          Österreich
        </p>

        <h5>Kontakt</h5>
        <p>
          E-Mail: contact@chronos-hotel.at<br />
          Telefon: +43 1 234 567
        </p>

        <h5>Verantwortlich für den Inhalt</h5>
        <p>
          Chronos Hotel Management<br />
          Höchstädtplatz 6, 1200 Wien
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
