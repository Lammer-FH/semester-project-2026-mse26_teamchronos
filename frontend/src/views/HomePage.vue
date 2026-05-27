<template>
  <ion-page>
    <NavbarComponent />

    <ion-content>
      <div class="d-flex flex-column min-vh-100">
        <!-- CONTENT -->
        <div class="flex-grow-1">
          <div class="container my-5" id="rooms">
            <RoomCard v-for="room in rooms" :key="room.title" v-bind="room" />
          </div>
        </div>

        <!-- FOOTER -->
        <FooterComponent />
      </div>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent } from "@ionic/vue";
import NavbarComponent from "@/components/NavbarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";
import RoomCard from "@/components/RoomCard.vue";

const rooms = [
  {
    subtitle: "Perfekt für eine Person",
    title: 'Einzelzimmer "Comfort"',
    description:
      "Geräumiges, schönes Zimmer mit großem Fenster. Badezimmer mit Dusche und WC.",
    image: "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85",
    reverse: false,
    features: [
      { icon: "bi bi-person", text: "1 Person" },
      { icon: "bi bi-house-door", text: "Doppelbett" },
      { icon: "bi bi-droplet", text: "Dusche" },
      { icon: "bi bi-tv", text: "TV" },
      { icon: "bi bi-wifi", text: "WLAN" },
      { icon: "bi bi-safe", text: "Safe" },
    ],
  },
  {
    subtitle: "Komfortabel und geräumig",
    title: "Doppelzimmer mit Balkon",
    description:
      "Modernes Doppelzimmer mit Balkon, wunderschöner Aussicht und großem Badezimmer.",
    image: "https://images.unsplash.com/photo-1566665797739-1674de7a421a",
    reverse: true,
    features: [
      { icon: "bi bi-people", text: "2 Personen" },
      { icon: "bi bi-house-door", text: "Kingsize Bett" },
      { icon: "bi bi-cup-hot", text: "Frühstück" },
      { icon: "bi bi-tv", text: "Smart TV" },
      { icon: "bi bi-wifi", text: "WLAN" },
      { icon: "bi bi-safe", text: "Safe" },
    ],
  },
  {
    subtitle: "Luxus und Entspannung",
    title: "Deluxe Suite",
    description:
      "Exklusive Suite mit Wohnbereich, Whirlpool und traumhaftem Panoramablick.",
    image: "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267",
    reverse: false,
    features: [
      { icon: "bi bi-people", text: "4 Personen" },
      { icon: "bi bi-stars", text: "Luxus Suite" },
      { icon: "bi bi-droplet-half", text: "Whirlpool" },
      { icon: "bi bi-tv", text: "Netflix TV" },
      { icon: "bi bi-wifi", text: "Highspeed WLAN" },
      { icon: "bi bi-cup-straw", text: "Minibar" },
    ],
  },
  {
    subtitle: "Modern und stilvoll",
    title: "Business Zimmer",
    description:
      "Perfekt für Geschäftsreisen mit großem Schreibtisch, schnellem WLAN und ruhiger Atmosphäre.",
    image: "https://images.unsplash.com/photo-1590490360182-c33d57733427",
    reverse: true,
    features: [
      { icon: "bi bi-person-workspace", text: "Business" },
      { icon: "bi bi-laptop", text: "Arbeitsplatz" },
      { icon: "bi bi-wifi", text: "Highspeed WLAN" },
      { icon: "bi bi-cup-hot", text: "Kaffeemaschine" },
      { icon: "bi bi-tv", text: "Smart TV" },
      { icon: "bi bi-safe", text: "Safe" },
    ],
  },
  {
    subtitle: "Familienfreundlich",
    title: "Familienzimmer",
    description:
      "Großes Zimmer für Familien mit zusätzlichem Schlafbereich und viel Platz für Kinder.",
    image: "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85",
    reverse: false,
    features: [
      { icon: "bi bi-people", text: "5 Personen" },
      { icon: "bi bi-house-heart", text: "Familienbett" },
      { icon: "bi bi-controller", text: "Spielecke" },
      { icon: "bi bi-tv", text: "Smart TV" },
      { icon: "bi bi-wifi", text: "WLAN" },
      { icon: "bi bi-cup-straw", text: "Minibar" },
    ],
  },
  {
    subtitle: "Premium Erlebnis",
    title: "Penthouse Suite",
    description:
      "Luxus Penthouse mit privater Terrasse, exklusivem Service und atemberaubender Aussicht.",
    image: "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb",
    reverse: true,
    features: [
      { icon: "bi bi-gem", text: "Premium Suite" },
      { icon: "bi bi-building", text: "Dachterrasse" },
      { icon: "bi bi-cup-hot", text: "Room Service" },
      { icon: "bi bi-tv", text: "Cinema TV" },
      { icon: "bi bi-wifi", text: "Premium WLAN" },
      { icon: "bi bi-stars", text: "VIP Service" },
    ],
  },
];
</script>
