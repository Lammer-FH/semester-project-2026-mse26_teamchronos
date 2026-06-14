<template>
  <ion-page>
    <NavbarComponent />

    <ion-content>
      <div v-if="room">
        <div class="hero-container">
          <img
              :src="'http://localhost:8080' + room.image"
              class="room-hero-img"
              :alt="room.title"
          />
        </div>

        <ion-grid class="ion-padding-bottom">
          <ion-row class="ion-justify-content-center">
            <ion-col size="12" size-md="10" size-lg="8" class="ion-padding-horizontal">

              <p class="text-uppercase text-center text-secondary fw-bold tracking-wide ion-no-margin ion-margin-top ion-margin-bottom">
                Room Details
              </p>
              <h4 class="fw-bold text-white text-center text-uppercase tracking-wide ion-no-margin ion-margin-bottom">
                {{ room.title }}
              </h4>

              <div class="info-badges-container">
                <span class="info-badge">
                  <i :class="room.capacity === 1 ? 'bi bi-person fs-5' : 'bi bi-people fs-5'"></i>
                  Up to {{ room.capacity }} {{ room.capacity === 1 ? 'Guest' : 'Guests' }}
                </span>
                <span class="info-badge">
                  <i class="bi bi-tag fs-5"></i>
                  From {{ room.pricePerNight }} € / Night
                </span>
              </div>

              <p class="text-secondary text-center description-text ion-margin-bottom">
                {{ room.description }}
              </p>

              <div class="extras-container">
                <span v-for="extra in room.extras" :key="extra.id" class="extra-item">
                  <i :class="'bi ' + extra.icon + ' fs-5'"></i> {{ extra.name }}
                </span>
              </div>

              <ion-card class="card-dark-bg ion-no-margin ion-margin-bottom ion-padding">
                <h4 class="text-center text-white ion-no-margin ion-margin-bottom">Check Availability</h4>

                <ion-grid class="ion-no-padding ion-margin-bottom">
                  <ion-row style="margin: 0 -8px;">
                    <ion-col size="6" style="padding: 0 8px;">
                      <label class="input-label">Check-In</label>
                      <input
                          type="date"
                          v-model="startDate"
                          :min="today"
                          class="dark-custom-input"
                      />
                    </ion-col>
                    <ion-col size="6" style="padding: 0 8px;">
                      <label class="input-label">Check-Out</label>
                      <input
                          type="date"
                          v-model="endDate"
                          :min="startDate || today"
                          class="dark-custom-input"
                      />
                    </ion-col>
                  </ion-row>
                </ion-grid>

                <div v-if="statusMessage" :class="['status-alert', isAvailable ? 'alert-success' : 'alert-danger']">
                  <i :class="['bi me-2', isAvailable ? 'bi-check-circle' : 'bi-exclamation-triangle']"></i>
                  {{ statusMessage }}
                </div>

                <ion-button
                    v-if="!isAvailable"
                    expand="block"
                    color="danger"
                    class="fw-bold text-uppercase"
                    @click="checkAvailability"
                    :disabled="isLoading"
                >
                  <ion-spinner v-if="isLoading" name="crescent" size="small" class="ion-margin-end"></ion-spinner>
                  {{ isLoading ? 'Checking...' : 'Check Now' }}
                </ion-button>
              </ion-card>

              <ion-card v-if="isAvailable" class="card-dark-bg ion-no-margin ion-margin-top ion-padding">
                <h5 class="fw-bold text-white text-center text-uppercase tracking-wide ion-no-margin ion-margin-bottom">
                  Booking
                </h5>

                <div class="form-container">
                  <input type="text" v-model="form.firstName" placeholder="First name"
                         :class="['dark-custom-input', errors.firstName && 'is-invalid-input']" />
                  <div v-if="errors.firstName" class="text-danger small-error">{{ errors.firstName }}</div>

                  <input type="text" v-model="form.lastName" placeholder="Last name"
                         :class="['dark-custom-input', errors.lastName && 'is-invalid-input']" />
                  <div v-if="errors.lastName" class="text-danger small-error">{{ errors.lastName }}</div>

                  <input type="email" v-model="form.email" placeholder="E-Mail"
                         :class="['dark-custom-input', errors.email && 'is-invalid-input']" />
                  <div v-if="errors.email" class="text-danger small-error">{{ errors.email }}</div>

                  <input type="email" v-model="form.confirmEmail" placeholder="Confirm E-Mail"
                         :class="['dark-custom-input', errors.confirmEmail && 'is-invalid-input']" />
                  <div v-if="errors.confirmEmail" class="text-danger small-error">{{ errors.confirmEmail }}</div>

                  <div class="checkbox-container">
                    <input class="dark-custom-checkbox" type="checkbox" v-model="form.breakfast" id="breakfastCheck">
                    <label class="checkbox-label" for="breakfastCheck">
                      Breakfast
                    </label>
                  </div>

                  <div v-if="bookingError" class="status-alert alert-danger mb-3">
                    <i class="bi bi-exclamation-triangle me-2"></i>{{ bookingError }}
                  </div>

                  <ion-button
                      expand="block"
                      color="primary"
                      class="fw-bold text-uppercase"
                      @click="submitBooking"
                      :disabled="isBooking"
                  >
                    <ion-spinner v-if="isBooking" name="crescent" size="small" class="ion-margin-end"></ion-spinner>
                    {{ isBooking ? 'Booking...' : 'Confirm Booking' }}
                  </ion-button>
                </div>
              </ion-card>
            </ion-col>
          </ion-row>
        </ion-grid>
      </div>

      <div v-else class="loading-wrapper">
        <ion-spinner name="crescent" color="danger"></ion-spinner>
      </div>

      <FooterComponent />
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted, computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { IonPage, IonContent, IonGrid, IonRow, IonCol, IonCard, IonButton, IonSpinner } from '@ionic/vue';
import axios from 'axios';
import NavbarComponent from "@/components/NavbarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";
import { useBookingStore } from '@/stores/bookingStore';

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
const router = useRouter();
const roomId = route.params.id;
const bookingStore = useBookingStore();

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

const errors = reactive({
  firstName: '',
  lastName: '',
  email: '',
  confirmEmail: ''
});

const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

const validateForm = (): boolean => {
  errors.firstName = '';
  errors.lastName = '';
  errors.email = '';
  errors.confirmEmail = '';

  if (!form.firstName.trim()) {
    errors.firstName = 'First name is required.';
  }

  if (!form.lastName.trim()) {
    errors.lastName = 'Last name is required.';
  }

  if (!form.email.trim()) {
    errors.email = 'E-Mail is required.';
  } else if (!emailRegex.test(form.email.trim())) {
    errors.email = 'Please enter a valid e-mail address.';
  }

  if (!form.confirmEmail.trim()) {
    errors.confirmEmail = 'Please confirm your e-mail.';
  } else if (form.email.trim() !== form.confirmEmail.trim()) {
    errors.confirmEmail = 'The e-mail addresses do not match.';
  }

  return !errors.firstName && !errors.lastName && !errors.email && !errors.confirmEmail;
};

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
    statusMessage.value = "Please select dates..";
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
      statusMessage.value = "Room is available!";
    }
  } catch (error) {
    if (axios.isAxiosError(error) && error.response) {
      if (error.response.status === 409) {
        isAvailable.value = false;
        statusMessage.value = "Room is unavailable.";
      } else {
        isAvailable.value = false;
        statusMessage.value = "Invalid data sequence.";
      }
    } else {
      statusMessage.value = "Server connection error.";
      isAvailable.value = false;
    }
  } finally {
    isLoading.value = false;
  }
};

const isBooking = ref(false);
const bookingError = ref('');

const submitBooking = async () => {
  if (!validateForm()) {
    return;
  }

  isBooking.value = true;
  bookingError.value = '';

  try {
    const response = await axios.post('http://localhost:8080/api/v1/bookings', {
      roomId: Number(roomId),
      firstName: form.firstName.trim(),
      lastName: form.lastName.trim(),
      email: form.email.trim(),
      checkInDate: startDate.value,
      checkOutDate: endDate.value,
      breakfastIncluded: form.breakfast
    });

    bookingStore.setLatestBooking({
      ...response.data,
      firstName: form.firstName.trim(),
      lastName: form.lastName.trim()
    });

    router.push('/confirmation');
  } catch (error) {
    if (axios.isAxiosError(error) && error.response) {
      if (error.response.status === 409) {
        bookingError.value = 'This room was just booked for those dates. Please check availability again.';
        isAvailable.value = false;
        statusMessage.value = 'Room is unavailable.';
      } else if (error.response.status === 400) {
        bookingError.value = error.response.data?.error || 'Please check your booking details.';
      } else {
        bookingError.value = 'Something went wrong creating your booking.';
      }
    } else {
      bookingError.value = 'Server connection error.';
    }
  } finally {
    isBooking.value = false;
  }
};
</script>

<style scoped>
ion-content {
  --background: #121212;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
}

.hero-container {
  width: 100%;
  padding: 0;
}

.room-hero-img {
  width: 100%;
  object-fit: cover;
  max-height: 50vh;
  display: block;
}

.fw-bold {
  font-weight: bold;
}

.text-uppercase {
  text-transform: uppercase;
}

.text-center {
  text-align: center;
}

.text-secondary {
  color: #888888;
}

.text-white {
  color: #ffffff;
}

.tracking-wide {
  letter-spacing: 0.1em;
}

.info-badges-container {
  display: flex;
  justify-content: center;
  gap: 24px;
  color: #f4f5f8;
  margin-bottom: 16px;
}

.info-badge {
  display: flex;
  align-items: center;
  gap: 8px;
}

.fs-5 {
  font-size: 1.25rem;
}

.description-text {
  max-width: 600px;
  margin-left: auto;
  margin-right: auto;
  font-size: 1.1rem;
  line-height: 1.5;
}

.extras-container {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 24px;
  color: #888888;
  margin-bottom: 40px;
}

.extra-item {
  display: flex;
  align-items: center;
  gap: 8px;
}

.card-dark-bg {
  background: #1e1e1e !important;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0,0,0,0.1);
}

.input-label {
  color: #888888;
  font-size: 0.75rem;
  font-weight: bold;
  text-transform: uppercase;
  display: block;
  margin-bottom: 8px;
}

.dark-custom-input {
  background-color: #2c2c2c !important;
  color: #ffffff !important;
  border: none;
  width: 100%;
  padding: 12px 16px;
  border-radius: 6px;
  font-size: 1rem;
  box-sizing: border-box;
  margin-bottom: 12px;
}

.dark-custom-input::placeholder {
  color: #666666;
}

.dark-custom-input::-webkit-calendar-picker-indicator {
  filter: invert(1);
}

.is-invalid-input {
  border: 1px solid #eb445a !important;
}

.small-error {
  color: #eb445a;
  font-size: 0.85rem;
  margin-top: -8px;
  margin-bottom: 12px;
  padding-left: 4px;
}

.status-alert {
  padding: 12px;
  border-radius: 6px;
  text-align: center;
  margin-bottom: 16px;
  font-size: 0.95rem;
}

.alert-success {
  background-color: rgba(45, 211, 111, 0.15);
  color: #2dd36f;
}

.alert-danger {
  background-color: rgba(235, 68, 90, 0.15);
  color: #eb445a;
}

.form-container {
  margin-top: 16px;
}

.checkbox-container {
  display: flex;
  align-items: center;
  gap: 8px;
  color: #888888;
  margin-top: 8px;
  margin-bottom: 24px;
}

.dark-custom-checkbox {
  width: 20px;
  height: 20px;
  background-color: #2c2c2c;
  border: 1px solid #444;
  accent-color: #3880ff;
  cursor: pointer;
}

.checkbox-label {
  cursor: pointer;
  user-select: none;
}

.loading-wrapper {
  display: flex;
  justify-content: center;
  align-items: center;
  min-vh: 100vh;
}

.ion-margin-end {
  margin-inline-end: 8px;
}
</style>