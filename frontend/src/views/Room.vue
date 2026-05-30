<template>
  <ion-page>
    <NavbarComponent />

    <ion-content>
      <div v-if="room">
        <div class="container-fluid p-0">
          <img
              :src="room.imagePath || room.image"
              class="img-fluid w-100 room-hero-img"
              :alt="room.title"
          />
        </div>

        <div class="container mt-5 px-4 mb-5">
          <div class="row justify-content-center">
            <div class="col-12 col-md-10 col-lg-8">
              <p class="text-uppercase text-center text-secondary fw-bold tracking-wide mb-2">Zimmerdetails</p>
              <h4 class="fw-bold text-white text-center text-uppercase tracking-wide mb-4">
                {{ room.title }}
              </h4>

              <div class="d-flex justify-content-center gap-4 text-light mb-3">
                <span class="d-flex align-items-center gap-2">
                  <i class="bi bi-people fs-5"></i>
                  Bis zu {{ room.capacity }} Personen
                </span>
                <span class="d-flex align-items-center gap-2">
                  <i class="bi bi-tag fs-5"></i>
                  Ab €{{ room.pricePerNight }} / Nacht
                </span>
              </div>

              <p class="text-secondary text-center mx-auto mb-4 fs-5">
                {{ room.description }}
              </p>

              <div class="d-flex justify-content-center flex-wrap gap-4 text-secondary mb-5">
                <span v-for="extra in room.extras" :key="extra.id" class="d-flex align-items-center gap-2">
                  <i :class="'bi ' + extra.icon + ' fs-5'"></i> {{ extra.name }}
                </span>
              </div>

              <div class="card card-dark-bg border-0 shadow-sm p-4 mb-4">
                <h4 class="card-title text-center text-white mb-4">Verfügbarkeit prüfen</h4>

                <div class="row g-3 mb-4">
                  <div class="col-6">
                    <label class="form-label text-secondary small fw-bold text-uppercase">Check-in</label>
                    <input
                        type="date"
                        v-model="startDate"
                        :min="today"
                        class="form-control form-control-lg border-0 dark-input"
                    />
                  </div>
                  <div class="col-6">
                    <label class="form-label text-secondary small fw-bold text-uppercase">Check-out</label>
                    <input
                        type="date"
                        v-model="endDate"
                        :min="startDate || today"
                        class="form-control form-control-lg border-0 dark-input"
                    />
                  </div>
                </div>

                <div v-if="statusMessage" :class="['alert text-center py-2 mb-4', isAvailable ? 'alert-success' : 'alert-danger']" role="alert">
                  <i :class="['bi me-2', isAvailable ? 'bi-check-circle' : 'bi-exclamation-triangle']"></i>
                  {{ statusMessage }}
                </div>

                <button
                    v-if="!isAvailable"
                    class="btn btn-danger btn-lg w-100 fw-bold shadow-sm"
                    @click="checkAvailability"
                    :disabled="isLoading"
                >
                  <span v-if="isLoading" class="spinner-border spinner-border-sm me-2" role="status" aria-hidden="true"></span>
                  {{ isLoading ? 'Prüfe...' : 'Buchen Sie jetzt!' }}
                </button>
              </div>

              <div v-if="isAvailable" class="card card-dark-bg border-0 shadow-sm p-4 mt-4">
                <h5 class="fw-bold text-white text-center text-uppercase tracking-wide mb-4">Buchung</h5>

                <div class="mb-3">
                  <input type="text" v-model="form.firstName" placeholder="Vorname" class="form-control form-control-lg border-0 dark-input mb-3" />
                  <input type="text" v-model="form.lastName" placeholder="Nachname" class="form-control form-control-lg border-0 dark-input mb-3" />
                  <input type="email" v-model="form.email" placeholder="E-Mail" class="form-control form-control-lg border-0 dark-input mb-3" />
                  <input type="email" v-model="form.confirmEmail" placeholder="E-Mail bestätigen" class="form-control form-control-lg border-0 dark-input mb-4" />

                  <div class="form-check text-secondary mb-4 d-flex align-items-center gap-2">
                    <input class="form-check-input dark-checkbox fs-5 mt-0" type="checkbox" v-model="form.breakfast" id="breakfastCheck">
                    <label class="form-check-label pt-1" for="breakfastCheck">
                      Frühstück
                    </label>
                  </div>

                  <button class="btn btn-primary btn-lg w-100 fw-bold shadow-sm" @click="submitBooking">
                    BUCHUNG BESTÄTIGEN
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div v-else class="d-flex justify-content-center align-items-center min-vh-100">
        <div class="spinner-border text-danger" role="status">
          <span class="visually-hidden">Lade Zimmer...</span>
        </div>
      </div>

      <FooterComponent />
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { IonPage, IonContent } from '@ionic/vue';
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
  imagePath?: string;
  image?: string;
  extras: Extra[];
}

const route = useRoute();
const router = useRouter();
const roomId = route.params.id;

const room = ref<RoomData | null>(null);
const startDate = ref('');
const endDate = ref('');
const statusMessage = ref('');
const isAvailable = ref(false);
const isLoading = ref(false);

const form = reactive({
  firstName: '',
  lastName: '',
  email: '',
  confirmEmail: '',
  breakfast: false
});

const today = computed(() => {
  const dt = new Date();
  return dt.toISOString().split('T')[0];
});

onMounted(async () => {
  try {
    const response = await axios.get(`http://localhost:8080/api/v1/rooms/${roomId}`);
    room.value = response.data;
  } catch (error) {
    console.error(error);
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
    const response = await axios.get(`http://localhost:8080/api/v1/rooms/${roomId}/availability`, {
      params: {
        startDate: startDate.value,
        endDate: endDate.value
      }
    });

    if (response.status === 200) {
      isAvailable.value = true;
      statusMessage.value = "Zimmer ist verfügbar!";
    }
  } catch (error) {
    if (axios.isAxiosError(error) && error.response) {
      if (error.response.status === 409) {
        isAvailable.value = false;
        statusMessage.value = "Zimmer für diesen Zeitraum belegt.";
      } else {
        isAvailable.value = false;
        statusMessage.value = "Ungültige Datenfolge.";
      }
    } else {
      statusMessage.value = "Server-Verbindungsfehler.";
      isAvailable.value = false;
    }
  } finally {
    isLoading.value = false;
  }
};

const submitBooking = () => {
  if (form.email !== form.confirmEmail) {
    alert("Die E-Mail-Adressen stimmen nicht überein.");
    return;
  }

  router.push({
    path: '/confirmation',
    query: {
      roomId: roomId,
      firstName: form.firstName,
      lastName: form.lastName,
      email: form.email
    }
  });
};
</script>

<style scoped>
ion-content {
  --background: #121212;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
}

.room-hero-img {
  object-fit: cover;
  max-height: 50vh;
}

.tracking-wide {
  letter-spacing: 0.1em;
}

.card-dark-bg {
  background-color: #1e1e1e !important;
}

.dark-input {
  background-color: #2c2c2c !important;
  color: #ffffff !important;
}

.dark-input::placeholder {
  color: #888888;
}

.dark-input::-webkit-calendar-picker-indicator {
  filter: invert(1);
}

.dark-checkbox {
  background-color: #2c2c2c;
  border-color: #444;
}

.dark-checkbox:checked {
  background-color: #0d6efd;
  border-color: #0d6efd;
}
</style>