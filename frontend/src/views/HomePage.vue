<template>
  <ion-page>
    <NavbarComponent />

    <ion-content>
      <div class="container-fluid p-0">
        <img
            src="https://images.unsplash.com/photo-1566073771259-6a8506099945"
            class="img-fluid w-100 hero-img"
            alt="Chronos Hotel"
            fetchpriority="high"
        />
      </div>

      <section class="container mt-5 px-4">
        <div class="row justify-content-center">
          <div class="col-12 col-md-10 col-lg-8 text-center">
            <h2 class="fw-bold text-uppercase mb-3 text-white tracking-wide">Chronos Hotel</h2>

            <p class="text-secondary mb-4 fs-5">
              Welcome to <strong>Chronos Hotel</strong>, a modern hotel located in the heart of Vienna. We combine elegant design, comfort, and exceptional hospitality to create a memorable experience for every guest.
            </p>

            <button @click="scrollToRooms" class="btn btn-primary btn-lg px-5 py-2 fw-bold mb-5 shadow-sm">
              JETZT BUCHEN
            </button>
          </div>
        </div>
      </section>

      <section class="container mb-5 px-4">
        <div class="row justify-content-center">
          <div class="col-12 col-md-10 col-lg-8 text-center">
            <h5 class="fw-bold text-uppercase mb-3 text-white tracking-wide">Über Uns</h5>

            <p class="text-secondary fs-5">
              At <strong>Chronos Hotel</strong>, our mission is to provide guests with a relaxing and unforgettable stay, whether they are visiting Vienna for business, leisure, or a weekend getaway. Our rooms have been carefully designed to combine <em>modern elegance</em> with practical comfort. Bright interiors, high-quality furnishings, and a welcoming atmosphere ensure that guests feel at home from the moment they arrive.
            </p>
          </div>
        </div>
      </section>

      <section class="container mt-5 pt-4" id="rooms">
        <h5 class="text-center fw-bold text-uppercase mb-4 text-white tracking-wide">Unsere Zimmer</h5>

        <div class="row justify-content-center">
          <div class="col-12 col-lg-10">
            <div
                v-for="room in rooms"
                :key="room.id"
                class="card mb-5 border-0 shadow custom-room-card"
                @click="goToRoom(room.id)"
            >
              <img :src="room.image" class="card-img-top room-img" :alt="room.title" />

              <div class="card-body card-dark-bg text-white d-flex justify-content-between align-items-center p-4">
                <div>
                  <h5 class="fw-bold mb-1">{{ room.title }}</h5>
                  <small class="text-secondary">{{ room.subtitle }}</small>
                </div>

                <div class="d-flex gap-3 text-secondary">
                  <i class="bi bi-wifi fs-4"></i>
                  <i class="bi bi-tv fs-4"></i>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <FooterComponent />
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent } from "@ionic/vue";
import { useRouter } from "vue-router";
import NavbarComponent from "@/components/NavbarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";

const router = useRouter();

const scrollToRooms = () => {
  const roomsSection = document.getElementById("rooms");
  if (roomsSection) {
    roomsSection.scrollIntoView({ behavior: "smooth" });
  }
};

const goToRoom = (id: number) => {
  router.push(`/room/${id}`);
};

const rooms = [
  {
    id: 1,
    subtitle: "Perfekt für eine Person",
    title: 'Einzelzimmer "Comfort"',
    image: "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85",
  },
  {
    id: 2,
    subtitle: "Komfortabel und geräumig",
    title: "Doppelzimmer mit Balkon",
    image: "https://images.unsplash.com/photo-1566665797739-1674de7a421a",
  },
  {
    id: 3,
    subtitle: "Luxus und Entspannung",
    title: "Deluxe Suite",
    image: "https://images.unsplash.com/photo-1522708323590-d24dbb6b0267",
  },
  {
    id: 4,
    subtitle: "Modern und stilvoll",
    title: "Business Zimmer",
    image: "https://images.unsplash.com/photo-1590490360182-c33d57733427",
  },
  {
    id: 5,
    subtitle: "Familienfreundlich",
    title: "Familienzimmer",
    image: "https://images.unsplash.com/photo-1505693416388-ac5ce068fe85",
  },
  {
    id: 6,
    subtitle: "Premium Erlebnis",
    title: "Penthouse Suite",
    image: "https://images.unsplash.com/photo-1542314831-068cd1dbfeeb",
  }
];
</script>

<style scoped>
ion-content {
  --background: #121212;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
}

.hero-img {
  object-fit: cover;
  max-height: 50vh;
}

.room-img {
  object-fit: cover;
  height: 350px;
}

.tracking-wide {
  letter-spacing: 0.1em;
}

.custom-room-card {
  background-color: transparent;
  cursor: pointer;
  transition: transform 0.2s ease-in-out;
}

.custom-room-card:hover {
  transform: translateY(-5px);
}

.card-dark-bg {
  background-color: #1e1e1e !important;
  border-bottom-left-radius: 0.375rem;
  border-bottom-right-radius: 0.375rem;
}
</style>