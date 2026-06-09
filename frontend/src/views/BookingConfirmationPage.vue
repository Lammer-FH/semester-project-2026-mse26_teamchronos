<template>
  <ion-page>
    <NavbarComponent />

    <ion-content>
      <div class="flex-wrapper">

        <ion-grid fixed class="ion-padding main-content">
          <ion-row class="ion-justify-content-center">
            <ion-col size="12" size-md="10" size-lg="8">

              <h4 class="ion-text-center ion-text-uppercase ion-margin-vertical">
                Booking Confirmation
              </h4>

              <ion-text color="medium" class="ion-text-center">
                <p>Thank you for booking! We look forward to your visit.</p>
              </ion-text>

              <!-- Booking period -->
              <ion-card class="ion-padding">
                <ion-card-header>
                  <ion-card-title>Your Stay</ion-card-title>
                </ion-card-header>
                <ion-card-content>
                  <p class="ion-margin-bottom"><strong>Check-In:</strong> {{ route.query.checkInDate }}</p>
                  <p class="ion-margin-bottom"><strong>Check-Out:</strong> {{ route.query.checkOutDate }}</p>
                  <p class="ion-no-margin" v-if="route.query.totalPrice">
                    <strong>Total Price:</strong> {{ route.query.totalPrice }} €
                  </p>
                </ion-card-content>
              </ion-card>

              <!-- Room details: image, title, extras, description -->
              <ion-card v-if="room">
                <img
                    :src="'http://localhost:8080' + room.image"
                    :alt="room.title"
                    class="room-img"
                />
                <ion-card-header>
                  <ion-card-title>{{ room.title }}</ion-card-title>
                </ion-card-header>
                <ion-card-content>
                  <ion-row class="ion-margin-bottom">
                    <ion-text color="dark" class="ion-margin-end">
                      <i :class="room.capacity === 1 ? 'bi bi-person' : 'bi bi-people'"></i>
                      Up to {{ room.capacity }} {{ room.capacity === 1 ? 'Guest' : 'Guests' }}
                    </ion-text>
                    <ion-text color="dark">
                      <i class="bi bi-tag"></i>
                      {{ room.pricePerNight }} € / Night
                    </ion-text>
                  </ion-row>

                  <ion-row class="ion-margin-bottom">
                    <ion-text v-for="extra in room.extras" :key="extra.id" color="medium" class="ion-margin-end">
                      <i :class="'bi ' + extra.icon"></i> {{ extra.name }}
                    </ion-text>
                  </ion-row>

                  <ion-text color="medium">
                    <p>{{ room.description }}</p>
                  </ion-text>
                </ion-card-content>
              </ion-card>

              <ion-card v-else class="ion-padding ion-text-center">
                <ion-spinner name="crescent" color="danger"></ion-spinner>
              </ion-card>

              <!-- Personal data -->
              <ion-card class="ion-padding">
                <ion-card-header>
                  <ion-card-title>Your Details</ion-card-title>
                </ion-card-header>
                <ion-card-content>
                  <p class="ion-margin-bottom"><strong>First name:</strong> {{ route.query.firstName }}</p>
                  <p class="ion-margin-bottom"><strong>Last name:</strong> {{ route.query.lastName }}</p>
                  <p class="ion-margin-bottom"><strong>E-Mail:</strong> {{ route.query.email }}</p>
                  <p class="ion-no-margin">
                    <strong>Breakfast:</strong>
                    {{ route.query.breakfastIncluded === 'true' ? 'Included' : 'Not included' }}
                  </p>
                </ion-card-content>
              </ion-card>

              <!-- Directions & travel info -->
              <ion-card class="ion-padding">
                <ion-card-header>
                  <ion-card-title>Getting Here</ion-card-title>
                </ion-card-header>
                <ion-card-content>
                  <p class="ion-margin-bottom">
                    <i class="bi bi-geo-alt ion-margin-end" aria-hidden="true"></i>
                    <span>Höchstädtplatz 6, 1200 Vienna, Austria</span>
                  </p>
                  <ion-text color="medium">
                    <p class="ion-margin-bottom">
                      <strong>By train:</strong> From Wien Hauptbahnhof, take the U1 to
                      Stephansplatz, change to the U2 toward Seestadt and exit at
                      Dresdner Straße — a 5-minute walk from the hotel.
                    </p>
                    <p class="ion-margin-bottom">
                      <strong>By tram:</strong> Lines 2, 5, and 33 stop directly outside at
                      Höchstädtplatz.
                    </p>
                    <p class="ion-no-margin">
                      <strong>By car:</strong> Limited on-site parking is available — please
                      let us know in advance if you require a space.
                    </p>
                  </ion-text>
                </ion-card-content>
              </ion-card>

              <!-- Contact options -->
              <ion-card class="ion-padding">
                <ion-card-header>
                  <ion-card-title>Contact Us</ion-card-title>
                </ion-card-header>
                <ion-card-content>
                  <ion-text color="medium">
                    <p>If you have any questions, please feel free to reach out:</p>
                  </ion-text>
                  <p class="ion-margin-vertical">
                    <i class="bi bi-telephone ion-margin-end" aria-hidden="true"></i>
                    <a href="tel:+431234567">+43 1 234 567</a>
                  </p>
                  <p class="ion-no-margin">
                    <i class="bi bi-envelope ion-margin-end" aria-hidden="true"></i>
                    <a href="mailto:welcome@hotel-technikum.at">welcome@hotel-technikum.at</a>
                  </p>
                </ion-card-content>
              </ion-card>

            </ion-col>
          </ion-row>
        </ion-grid>

        <FooterComponent />
      </div>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import {
  IonPage,
  IonContent,
  IonGrid,
  IonRow,
  IonCol,
  IonCard,
  IonCardHeader,
  IonCardTitle,
  IonCardContent,
  IonText,
  IonSpinner
} from '@ionic/vue';
import axios from 'axios';
import NavbarComponent from "@/components/NavbarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";

interface Extra {
  id: number;
  name: string;
  icon: string;
}

interface RoomData {
  id: number;
  title: string;
  description: string;
  capacity: number;
  pricePerNight: number;
  image: string;
  extras: Extra[];
}

const route = useRoute();
const room = ref<RoomData | null>(null);

onMounted(async () => {
  const roomId = route.query.roomId;
  try {
    const response = await axios.get(`http://localhost:8080/api/v1/rooms/${roomId}`);
    room.value = response.data;
  } catch (error) {
    console.error(error);
  }
});
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

.room-img {
  width: 100%;
  object-fit: cover;
  max-height: 40vh;
}
</style>