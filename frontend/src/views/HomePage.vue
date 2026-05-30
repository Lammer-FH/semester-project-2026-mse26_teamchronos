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
            <ion-text color="dark">
              <p>
                At <strong>Boutique Hotel Technikum</strong>, our mission is to provide guests
                with a relaxing and unforgettable stay, whether they are visiting
                Vienna for business, leisure, or a weekend getaway.
              </p>

              <p>
                Our rooms have been carefully designed to combine
                <em>modern elegance</em> with practical comfort. Bright interiors,
                high-quality furnishings, and a welcoming atmosphere ensure that
                guests feel at home from the moment they arrive.
              </p>

              <p>
                From cozy single rooms to luxurious suites,
                <strong>Boutique Hotel Technikum</strong> offers accommodation options suitable
                for every type of traveler.
              </p>

              <p>
                We place great importance on
                <strong>excellent service, cleanliness, and hospitality</strong>.
                Our dedicated team is always available to assist guests and ensure
                that every stay exceeds expectations.
              </p>

              <p>
                Complimentary high-speed Wi-Fi, modern entertainment systems, and
                comfortable workspaces make our hotel ideal for both tourists and
                business travelers.
              </p>

              <p>
                Thanks to our central location, many of Vienna’s famous attractions,
                restaurants, and shopping areas are within easy reach. At the same
                time, the hotel offers a
                <em>peaceful and relaxing environment</em>
                where guests can unwind and recharge.
              </p>
            </ion-text>
          </ion-col>
        </ion-row>
        <ion-row class="ion-justify-content-center ion-margin-top">
          <ion-col>
            <ion-text color="dark" class="ion-text-center">
              <p class="ion-margin-top ion-padding-top">
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