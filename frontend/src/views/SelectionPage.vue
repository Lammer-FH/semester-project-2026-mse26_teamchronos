<template>
  <ion-page>
    <NavbarComponent />
    <ion-content>
      <div class="flex-wrapper">
        <ion-grid fixed class="ion-padding main-content">
          <div v-if="isLoading" class="ion-text-center ion-padding-vertical">
            <ion-spinner name="crescent" color="primary"></ion-spinner>
            <ion-text color="medium">
              <p class="ion-no-margin ion-margin-top">Loading rooms...</p>
            </ion-text>
          </div>

          <template v-else-if="rooms.length > 0">
            <RoomCard v-for="room in rooms" :key="room.title" v-bind="room" />
          </template>

          <div v-else class="ion-text-center ion-padding-vertical">
            <ion-text color="medium">
              <p>No rooms found.</p>
            </ion-text>
          </div>

          <ion-row class="ion-align-items-center ion-justify-content-center">

            <ion-col size="auto">
              <ion-button
                  fill="clear"
                  color="dark"
                  :disabled="pageIndex === 0 || isLoading"
                  @click="changePage(pageIndex - 1)"
              >
                <ion-icon slot="icon-only" :icon="chevronBack"></ion-icon>
              </ion-button>
            </ion-col>
            <ion-col size="auto" class="ion-text-center">
              <ion-text color="dark">
        <span style="font-weight: 500; font-size: 1.1rem;">
          Page {{ pageIndex + 1 }}
        </span>
              </ion-text>
            </ion-col>

            <ion-col size="auto">
              <ion-button
                  fill="clear"
                  color="dark"
                  :disabled="rooms.length < pageSize || isLoading"
                  @click="changePage(pageIndex + 1)"
              >
                <ion-icon slot="icon-only" :icon="chevronForward"></ion-icon>
              </ion-button>
            </ion-col>
          </ion-row>
        </ion-grid>
        <FooterComponent />
      </div>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { ref, onMounted } from "vue";
import {
  IonPage,
  IonContent,
  IonGrid,
  IonRow,
  IonCol,
  IonButton,
  IonSpinner,
  IonText
} from "@ionic/vue";
import NavbarComponent from "@/components/NavbarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";
import RoomCard from "@/components/RoomCard.vue";
import axios from "axios";
import {chevronBack, chevronForward} from "ionicons/icons";

const rooms = ref<any[]>([]);
const isLoading = ref(false);
const pageIndex = ref(0);
const pageSize = ref(5);

const fetchRooms = async () => {
  isLoading.value = true;
  try {
    const response = await axios.get("http://localhost:8080/api/v1/rooms", {
      params: {
        pageIndex: pageIndex.value,
        pageSize: pageSize.value
      }
    });

    const data = response.data;
    rooms.value = data.rooms.map((room: any, index: number) => ({
      id: room.id,
      title: room.title,
      description: room.description,
      reverse: index % 2 !== 0,
      image: `http://localhost:8080${room.image}`,
      features: [
        { icon: "bi bi-people-fill", text: `${room.capacity} Guests` },
        { icon: "bi bi-credit-card-fill", text: `${room.pricePerNight} € / Night` },
        { icon: "bi bi-wifi", text: "Free Wi-Fi" },
        { icon: "bi bi-tv", text: "Smart TV" }
      ]
    }));
  } catch (error) {
    console.error("Error fetching rooms:", error);
    rooms.value = [];
  } finally {
    isLoading.value = false;
  }
};

const changePage = (newPageIndex: number) => {
  pageIndex.value = newPageIndex;
  fetchRooms();
};

onMounted(() => {
  fetchRooms();
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