<template>
  <ion-page>
    <NavbarComponent />

    <ion-content>
      <div class="d-flex flex-column min-vh-100">
        <div class="flex-grow-1">
          <div class="container my-5" id="rooms">

            <div
                v-for="room in rooms"
                :key="room.id"
                class="card mb-5 shadow-sm border-0 overflow-hidden"
                @click="goToRoom(room.id)"
                style="cursor: pointer; transition: transform 0.2s;"
                onmouseover="this.style.transform='scale(1.02)'"
                onmouseout="this.style.transform='scale(1)'"
            >
              <div class="row g-0">
                <div :class="['col-md-6', room.reverse ? 'order-md-last' : '']">
                  <img :src="room.image" :alt="room.title" class="img-fluid h-100" style="object-fit: cover; min-height: 300px;" />
                </div>

                <div class="col-md-6 d-flex align-items-center bg-dark text-white">
                  <div class="card-body p-5">
                    <h6 class="text-primary text-uppercase fw-bold mb-2" style="letter-spacing: 0.1em;">{{ room.subtitle }}</h6>

                    <h2 class="card-title fw-bold mb-3">{{ room.title }}</h2>

                    <p class="card-text text-light mb-4">{{ room.description }}</p>

                    <div class="row g-2 mb-4">
                      <div class="col-6" v-for="(feature, index) in room.features" :key="index">
                        <div class="d-flex align-items-center text-white-50 small">
                          <i :class="feature.icon + ' me-2 text-primary fs-5'"></i>
                          {{ feature.text }}
                        </div>
                      </div>
                    </div>

                    <button class="btn btn-primary btn-lg px-4">
                      DETAILS & BUCHEN
                    </button>
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>

        <FooterComponent />
      </div>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent } from "@ionic/vue";
import { useRouter } from "vue-router";
import NavbarComponent from "@/components/NavbarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";

const router = useRouter();

const goToRoom = (id: number) => {
  // This physically moves the user to your new room.vue page!
  router.push(`/room/${id}`);
};

const rooms = [
  {
    id: 1,
    subtitle: "Perfekt für eine Person",
    title: 'Einzelzimmer "Comfort"',
    description: "Geräumiges, schönes Zimmer mit großem Fenster. Badezimmer mit Dusche und WC.",
    image: "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85",
    reverse: false,
    features: [
      { icon: "bi bi-person", text: "1 Person" },
      { icon: "bi bi-house-door", text: "Doppelbett" },
      { icon: "bi bi-droplet", text: "Dusche" },
      { icon: "bi bi-tv", text: "TV" },
      { icon: "bi bi-wifi", text: "WLAN" },
      { icon: "bi bi-safe", text: "Safe" },
    ]
  },
  {
    id: 2,
    subtitle: "Komfortabel und geräumig",
    title: "Doppelzimmer mit Balkon",
    description: "Modernes Doppelzimmer mit Balkon, wunderschöner Aussicht und großem Badezimmer.",
    image: "https://images.unsplash.com/photo-1566665797739-1674de7a421a",
    reverse: true,
    features: [
      { icon: "bi bi-people", text: "2 Personen" },
      { icon: "bi bi-house-door", text: "Kingsize Bett" },
      { icon: "bi bi-cup-hot", text: "Frühstück" },
      { icon: "bi bi-tv", text: "Smart TV" },
      { icon: "bi bi-wifi", text: "WLAN" },
      { icon: "bi bi-safe", text: "Safe" },
    ]
  },
  {
    id: 3,
    subtitle: "Luxus und Entspannung",
    title: "Deluxe Suite",
    description: "Exklusive Suite mit Wohnbereich, Whirlpool und traumhaftem Panoramablick.",
    image: "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267",
    reverse: false,
    features: [
      { icon: "bi bi-people", text: "4 Personen" },
      { icon: "bi bi-stars", text: "Luxus Suite" },
      { icon: "bi bi-droplet-half", text: "Whirlpool" },
      { icon: "bi bi-tv", text: "Netflix TV" },
      { icon: "bi bi-wifi", text: "Highspeed WLAN" },
      { icon: "bi bi-cup-straw", text: "Minibar" },
    ]
  },
  {
    id: 4,
    subtitle: "Modern und stilvoll",
    title: "Business Zimmer",
    description: "Perfekt für Geschäftsreisen mit großem Schreibtisch, schnellem WLAN und ruhiger Atmosphäre.",
    image: "https://images.unsplash.com/photo-1590490360182-c33d57733427",
    reverse: true,
    features: [
      { icon: "bi bi-person-workspace", text: "Business" },
      { icon: "bi bi-laptop", text: "Arbeitsplatz" },
      { icon: "bi bi-wifi", text: "Highspeed WLAN" },
      { icon: "bi bi-cup-hot", text: "Kaffeemaschine" },
      { icon: "bi bi-tv", text: "Smart TV" },
      { icon: "bi bi-safe", text: "Safe" },
    ]
  },
  {
    id: 5,
    subtitle: "Familienfreundlich",
    title: "Familienzimmer",
    description: "Großes Zimmer für Familien mit zusätzlichem Schlafbereich und viel Platz für Kinder.",
    image: "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85",
    reverse: false,
    features: [
      { icon: "bi bi-people", text: "5 Personen" },
      { icon: "bi bi-house-heart", text: "Familienbett" },
      { icon: "bi bi-controller", text: "Spielecke" },
      { icon: "bi bi-tv", text: "Smart TV" },
      { icon: "bi bi-wifi", text: "WLAN" },
      { icon: "bi bi-cup-straw", text: "Minibar" },
    ]
  },
  {
    id: 6,
    subtitle: "Premium Erlebnis",
    title: "Penthouse Suite",
    description: "Luxus Penthouse mit privater Terrasse, exklusivem Service und atemberaubender Aussicht.",
    image: "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb",
    reverse: true,
    features: [
      { icon: "bi bi-gem", text: "Premium Suite" },
      { icon: "bi bi-building", text: "Dachterrasse" },
      { icon: "bi bi-cup-hot", text: "Room Service" },
      { icon: "bi bi-tv", text: "Cinema TV" },
      { icon: "bi bi-wifi", text: "Premium WLAN" },
      { icon: "bi bi-stars", text: "VIP Service" },
    ]
  }
];
</script>