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

              <ion-card class="ion-padding">
                <ion-card-content>
                  <p class="ion-margin-bottom"><strong>Room:</strong> {{ roomTitle }}</p>
                  <p class="ion-margin-bottom"><strong>First name:</strong> {{ route.query.firstName }}</p>
                  <p class="ion-margin-bottom"><strong>Last name:</strong> {{ route.query.lastName }}</p>
                  <p class="ion-no-margin"><strong>E-Mail:</strong> {{ route.query.email }}</p>
                </ion-card-content>
              </ion-card>

              <div class="ion-padding">
                <h5 class="ion-text-uppercase ion-margin-bottom">Arrival</h5>

                <ion-text color="medium">
                  <p>Thank you for booking! We look forward to your visit.</p>
                  <p>If you have any questions, please feel free to contact us at:</p>
                </ion-text>

                <p class="ion-margin-vertical">
                  <i class="bi bi-telephone ion-margin-end" aria-hidden="true"></i>
                  <span>+43 1 234 567</span>
                </p>

                <p class="ion-margin-vertical">
                  <i class="bi bi-envelope ion-margin-end" aria-hidden="true"></i>
                  <span>welcome@hotel-technikum.at</span>
                </p>
              </div>

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
  IonCardContent,
  IonText
} from '@ionic/vue';
import axios from 'axios';
import NavbarComponent from "@/components/NavbarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";

const route = useRoute();
const roomTitle = ref('Loading...');

onMounted(async () => {
  const roomId = route.query.roomId;
  try {
    const response = await axios.get(`http://localhost:8080/api/v1/rooms/${roomId}`);
    roomTitle.value = response.data.title;
  } catch (error) {
    roomTitle.value = "Room details not found.";
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
</style>