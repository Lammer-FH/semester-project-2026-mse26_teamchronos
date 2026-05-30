<template>
  <ion-page>
    <NavbarComponent />

    <ion-content>
      <div class="container-fluid p-0">
        <img
          src="https://images.unsplash.com/photo-1566073771259-6a8506099945"
          class="img-fluid w-100"
          alt="Chronos Hotel"
        />
      </div>

      <section class="container text-center py-5">
        <h1 class="fw-bold text-uppercase mb-4">Chronos Hotel</h1>

        <p class="lead mx-auto" style="max-width: 800px">
          Welcome to <strong>Chronos Hotel</strong>, a modern hotel located in
          the heart of Vienna. We combine elegant design, comfort, and
          exceptional hospitality to create a memorable experience for every
          guest.
        </p>

        <router-link to="/rooms" class="btn btn-danger px-4 btn-lg mt-4">
          Book Now
        </router-link>
      </section>

      <section class="container">
        <h2 class="text-center fw-bold text-uppercase mb-4">About Us</h2>

        <div class="mx-auto" style="max-width: 900px">
          <p class="mb-4">
            At <strong>Chronos Hotel</strong>, our mission is to provide guests
            with a relaxing and unforgettable stay, whether they are visiting
            Vienna for business, leisure, or a weekend getaway.
          </p>

          <p class="mb-4">
            Our rooms have been carefully designed to combine
            <em>modern elegance</em> with practical comfort. Bright interiors,
            high-quality furnishings, and a welcoming atmosphere ensure that
            guests feel at home from the moment they arrive.
          </p>

          <p class="mb-4">
            From cozy single rooms to luxurious suites,
            <strong>Chronos Hotel</strong> offers accommodation options suitable
            for every type of traveler.
          </p>

          <p class="mb-4">
            We place great importance on
            <strong>excellent service, cleanliness, and hospitality</strong>.
            Our dedicated team is always available to assist guests and ensure
            that every stay exceeds expectations.
          </p>

          <p class="mb-4">
            Complimentary high-speed Wi-Fi, modern entertainment systems, and
            comfortable workspaces make our hotel ideal for both tourists and
            business travelers.
          </p>

          <p class="mb-4">
            Thanks to our central location, many of Vienna’s famous attractions,
            restaurants, and shopping areas are within easy reach. At the same
            time, the hotel offers a
            <em>peaceful and relaxing environment</em>
            where guests can unwind and recharge.
          </p>

          <p class="fs-5 fw-semibold mt-5 text-center">
            Chronos Hotel stands for quality, comfort, and modern hospitality.
          </p>
        </div>
      </section>

      <FooterComponent />
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent } from "@ionic/vue";
import NavbarComponent from "@/components/NavbarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";
</script>
