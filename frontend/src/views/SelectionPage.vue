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
    subtitle: "Perfect for one guest",
    title: 'Single Room "Comfort"',
    description:
      "Spacious and comfortable room with a large window. Private bathroom with shower and toilet.",
    image: "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85",
    reverse: false,
    features: [
      { icon: "bi bi-person", text: "1 Guest" },
      { icon: "bi bi-house-door", text: "Double Bed" },
      { icon: "bi bi-droplet", text: "Shower" },
      { icon: "bi bi-tv", text: "TV" },
      { icon: "bi bi-wifi", text: "Wi-Fi" },
      { icon: "bi bi-safe", text: "Safe" },
    ],
  },
  {
    subtitle: "Comfortable and Spacious",
    title: "Double Room with Balcony",
    description:
      "Modern double room with balcony, beautiful view, and a spacious bathroom.",
    image: "https://images.unsplash.com/photo-1566665797739-1674de7a421a",
    reverse: true,
    features: [
      { icon: "bi bi-people", text: "2 Guests" },
      { icon: "bi bi-house-door", text: "King Size Bed" },
      { icon: "bi bi-cup-hot", text: "Breakfast Included" },
      { icon: "bi bi-tv", text: "Smart TV" },
      { icon: "bi bi-wifi", text: "Wi-Fi" },
      { icon: "bi bi-safe", text: "Safe" },
    ],
  },
  {
    subtitle: "Luxury and Relaxation",
    title: "Deluxe Suite",
    description:
      "Exclusive suite featuring a living area, whirlpool, and stunning panoramic views.",
    image: "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267",
    reverse: false,
    features: [
      { icon: "bi bi-people", text: "4 Guests" },
      { icon: "bi bi-stars", text: "Luxury Suite" },
      { icon: "bi bi-droplet-half", text: "Whirlpool" },
      { icon: "bi bi-tv", text: "Netflix TV" },
      { icon: "bi bi-wifi", text: "High-Speed Wi-Fi" },
      { icon: "bi bi-cup-straw", text: "Mini Bar" },
    ],
  },
  {
    subtitle: "Modern and Stylish",
    title: "Business Room",
    description:
      "Perfect for business travelers, featuring a large workspace, high-speed internet, and a quiet atmosphere.",
    image: "https://images.unsplash.com/photo-1590490360182-c33d57733427",
    reverse: true,
    features: [
      { icon: "bi bi-person-workspace", text: "Business Ready" },
      { icon: "bi bi-laptop", text: "Workspace" },
      { icon: "bi bi-wifi", text: "High-Speed Wi-Fi" },
      { icon: "bi bi-cup-hot", text: "Coffee Machine" },
      { icon: "bi bi-tv", text: "Smart TV" },
      { icon: "bi bi-safe", text: "Safe" },
    ],
  },
  {
    subtitle: "Family Friendly",
    title: "Family Room",
    description:
      "Spacious family room with an additional sleeping area and plenty of space for children.",
    image: "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85",
    reverse: false,
    features: [
      { icon: "bi bi-people", text: "5 Guests" },
      { icon: "bi bi-house-heart", text: "Family Bed" },
      { icon: "bi bi-controller", text: "Play Area" },
      { icon: "bi bi-tv", text: "Smart TV" },
      { icon: "bi bi-wifi", text: "Wi-Fi" },
      { icon: "bi bi-cup-straw", text: "Mini Bar" },
    ],
  },
  {
    subtitle: "Premium Experience",
    title: "Penthouse Suite",
    description:
      "Luxury penthouse suite with a private terrace, exclusive service, and breathtaking views.",
    image: "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb",
    reverse: true,
    features: [
      { icon: "bi bi-gem", text: "Premium Suite" },
      { icon: "bi bi-building", text: "Rooftop Terrace" },
      { icon: "bi bi-cup-hot", text: "Room Service" },
      { icon: "bi bi-tv", text: "Cinema TV" },
      { icon: "bi bi-wifi", text: "Premium Wi-Fi" },
      { icon: "bi bi-stars", text: "VIP Service" },
    ],
  },
];
</script>
