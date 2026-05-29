<template>
  <ion-page>
    <NavbarComponent />




    <ion-content>
      <div class="container my-5 pb-5" v-if="room">
        <div class="row g-5">

          <div class="col-12 col-lg-6">
            <p class="text-uppercase text-secondary text-white fw-bold tracking-wide">Zimmerdetails</p>

            <h1 class="display-5 mb-3">{{ room.title }}</h1>

            <div class="d-flex gap-3 mb-4 text-white">
              <span class="d-flex align-items-center gap-2">
                <i class="bi bi-people fs-5"></i>
                Bis zu {{ room.capacity }} Personen
              </span>
              <span class="d-flex align-items-center gap-2">
                <i class="bi bi-tag fs-5"></i>
                Ab €{{ room.pricePerNight }} / Nacht
              </span>
            </div>

            <p class="mb-4 lead text-secondary">{{ room.description }}</p>

            <hr />

            <div class="row mt-4 g-3 mb-5">
              <div
                  v-for="extra in room.extras"
                  :key="extra.id"
                  class="col-6 col-md-4 d-flex align-items-center"
              >
                <i :class="'bi ' + extra.icon + ' text-primary me-2 fs-5'"></i>
                {{ extra.name }}
              </div>
            </div>

            <div class="card shadow-sm border-0 bg-light mt-4">
              <div class="card-body p-4">
                <h4 class="card-title mb-4">Verfügbarkeit prüfen</h4>

                <div class="row g-3 mb-4">
                  <div class="col-12 col-md-6">
                    <label class="form-label text-white small fw-bold text-uppercase">Check-in</label>
                    <input type="date" v-model="startDate" class="form-control form-control-lg border-0 shadow-sm" />
                  </div>
                  <div class="col-12 col-md-6">
                    <label class="form-label text-white small fw-bold text-uppercase">Check-out</label>
                    <input type="date" v-model="endDate" class="form-control form-control-lg border-0 shadow-sm" />
                  </div>
                </div>

                <div v-if="statusMessage"
                     :class="['alert text-center py-2 mb-4', isAvailable ? 'alert-success' : 'alert-danger']"
                     role="alert">
                  <i :class="['bi me-2', isAvailable ? 'bi-check-circle' : 'bi-exclamation-triangle']"></i>
                  {{ statusMessage }}
                </div>

                <button class="btn btn-danger btn-lg w-100 fw-bold shadow-sm"
                        @click="checkAvailability"
                        :disabled="isLoading">
                  <span v-if="isLoading" class="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                  {{ isLoading ? 'Prüfe...' : 'Buchen Sie jetzt!' }}
                </button>
              </div>
            </div>
          </div>

          <div class="col-12 col-lg-6">
            <img
                :src="room.imagePath || room.image"
                class="img-fluid rounded shadow w-100"
                style="object-fit: cover; max-height: 800px;"
                :alt="room.title"
            />
          </div>

        </div>
      </div>

      <div v-else class="d-flex justify-content-center align-items-center min-vh-100">
        <div class="spinner-border text-primary" role="status">
          <span class="visually-hidden">Lade Zimmer...</span>
        </div>
      </div>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { IonPage, IonContent } from '@ionic/vue';
import NavbarComponent from "@/components/NavbarComponent.vue";

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
  imagePath?: string;
  image?: string;
  extras: Extra[];
}

const route = useRoute();
const roomId = route.params.id;

const room = ref<RoomData | null>(null);
const startDate = ref('');
const endDate = ref('');
const statusMessage = ref('');
const isAvailable = ref(false);
const isLoading = ref(false);

onMounted(async () => {
  try {
    const response = await fetch(`http://localhost:8080/api/v1/rooms/${roomId}`);
    if (response.ok) {
      room.value = await response.json();
    }
  } catch (error) {
    console.error("Failed to fetch room details:", error);
  }
});

const checkAvailability = async () => {
  if (!startDate.value || !endDate.value) {
    statusMessage.value = "Bitte wählen Sie beide Daten aus.";
    isAvailable.value = false;
    return;
  }

  isLoading.value = true;
  statusMessage.value = "";

  try {
    const url = `http://localhost:8080/api/v1/rooms/${roomId}/availability?startDate=${startDate.value}&endDate=${endDate.value}`;
    const response = await fetch(url);

    if (response.status === 200) {
      isAvailable.value = true;
      statusMessage.value = "Zimmer ist verfügbar!";
    } else if (response.status === 409) {
      isAvailable.value = false;
      statusMessage.value = "Zimmer für diesen Zeitraum belegt.";
    } else {
      isAvailable.value = false;
      statusMessage.value = "Ungültige Datenfolge.";
    }
  } catch (error) {
    statusMessage.value = "Server-Verbindungsfehler.";
    isAvailable.value = false;
  } finally {
    isLoading.value = false;
  }
};
</script>

<style scoped>
.tracking-wide {
  letter-spacing: 0.1em;
}
</style>