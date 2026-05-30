<template>
  <ion-page>
    <NavbarComponent />

    <ion-content>
      <div class="flex-wrapper">

        <ion-grid fixed class="ion-padding main-content">

          <h1 class="ion-padding-vertical">Legal Notice</h1>

          <h3 class="ion-padding-vertical">Boutique Hotel Technikum</h3>

          <ion-text color="medium">
            <h5>Address</h5>
          </ion-text>
          <p>
            Höchstädtplatz 6<br />
            1200 Vienna<br />
            Austria
          </p>

          <ion-text color="medium">
            <h5>Contact Information</h5>
          </ion-text>
          <p>
            Email: contact@hotel-technikum.at<br />
            Phone: +43 1 234 567
          </p>

          <ion-text color="medium">
            <h5>Responsible for Content</h5>
          </ion-text>
          <p>
            Boutique Hotel Technikum Management<br />
            Höchstädtplatz 6<br />
            1200 Vienna<br />
            Austria
          </p>

          <ion-text color="medium">
            <h5>Disclaimer</h5>
          </ion-text>
          <p>
            The content of this website has been created with the greatest
            possible care.<br>However, we cannot guarantee the accuracy,
            completeness, or timeliness of the information provided.
          </p>

        </ion-grid>

        <FooterComponent />
      </div>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent, IonGrid, IonText } from "@ionic/vue";
import NavbarComponent from "@/components/NavbarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";
</script>

<style scoped>
.flex-wrapper {
  display: flex;
  flex-direction: column;
  min-height: 100%;
}

.main-content {
  flex: 1;
}
</style>