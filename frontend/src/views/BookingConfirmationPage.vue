<template>
  <ion-page>
    <NavbarComponent class="no-print" />

    <ion-content class="print-content">
      <div class="flex-wrapper">
        <ion-grid fixed class="ion-padding main-content" id="printable-area">
          <ion-row class="ion-justify-content-center">
            <ion-col size="12" size-md="10" size-lg="8">

              <h4 class="ion-text-center ion-text-uppercase ion-margin-vertical">
                Booking Confirmation
              </h4>

              <ion-text color="medium" class="ion-text-center">
                <p>Thank you for booking! We look forward to your visit.</p>
              </ion-text>

              <ion-card class="ion-padding hide-shadows-on-print">
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

              <ion-card v-if="room" class="hide-shadows-on-print">
                <img
                    :src="'http://localhost:8080' + room.image"
                    :alt="room.title"
                    class="room-img"
                />
                <ion-card-header>
                  <ion-card-title>{{ room.title }}</ion-card-title>
                </ion-card-header>
                <ion-card-content>
                  <ion-row class="ion-margin-bottom ion-align-items-center">
                    <ion-text color="dark" class="ion-margin-end" style="display: flex; align-items: center; gap: 4px;">
                      <ion-icon :icon="room.capacity === 1 ? person : people"></ion-icon>
                      Up to {{ room.capacity }} {{ room.capacity === 1 ? 'Guest' : 'Guests' }}
                    </ion-text>
                    <ion-text color="dark" style="display: flex; align-items: center; gap: 4px;">
                      <ion-icon :icon="pricetag"></ion-icon>
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

              <ion-card v-else class="ion-padding ion-text-center hide-shadows-on-print">
                <ion-spinner name="crescent" color="danger"></ion-spinner>
              </ion-card>

              <ion-card class="ion-padding hide-shadows-on-print">
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

              <ion-card class="ion-padding hide-shadows-on-print">
                <ion-card-header>
                  <ion-card-title>Getting Here</ion-card-title>
                </ion-card-header>
                <ion-card-content>
                  <p class="ion-margin-bottom" style="display: flex; align-items: center; gap: 8px;">
                    <ion-icon :icon="location" aria-hidden="true"></ion-icon>
                    <span>Höchstädtplatz 6, 1200 Vienna, Austria</span>
                  </p>
                  <ion-text color="medium">
                    <p class="ion-margin-bottom">
                      <strong>By train:</strong> From Wien Hauptbahnhof...
                    </p>
                  </ion-text>
                </ion-card-content>
              </ion-card>

              <div class="ion-text-center ion-margin-top no-print">
                <ion-button color="danger" @click="printReceipt" :disabled="!room">
                  <ion-icon :icon="print" class="ion-margin-end"></ion-icon> Print Confirmation
                </ion-button>
              </div>

            </ion-col>
          </ion-row>
        </ion-grid>

        <FooterComponent class="no-print" />
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
  IonSpinner,
  IonButton,
  IonIcon
} from '@ionic/vue';
import {
  person,
  people,
  pricetag,
  location,
  print
} from 'ionicons/icons';
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

const printReceipt = () => {
  window.print();
};
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

@media print {
  .no-print {
    display: none !important;
  }

  ion-content {
    --background: white;
    overflow: visible !important;
    contain: none !important;
  }

  ion-content::part(scroll) {
    overflow: visible !important;
    position: relative !important;
  }

  .flex-wrapper {
    display: block !important;
  }

  .hide-shadows-on-print {
    box-shadow: none !important;
    border: 1px solid #ddd !important;
    break-inside: avoid;
  }

  ion-text, p, h4 {
    color: black !important;
  }
}
</style>