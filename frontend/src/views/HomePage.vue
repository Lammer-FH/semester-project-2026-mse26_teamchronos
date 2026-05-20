<template>
  <ion-page>
    <ion-header :translucent="false">
      <ion-toolbar color="dark">
        <ion-title>Room 1</ion-title>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">
      <div class="prototype-container">
        <div class="room-image-placeholder">
          <span class="placeholder-text">ROOM IMAGE PLACEHOLDER</span>
        </div>

        <div class="room-details-padding">
          <h2>ROOM 1</h2>
          <p class="description-text">
            text text
          </p>

          <div class="extras-row">
            <span>  Highspeed WiFi</span>
            <span>  Workspace</span>
          </div>

          <div class="date-input-row">
            <div class="date-input-field">
              <span class="field-label">From</span>
              <div class="datetime-wrapper">
                <ion-datetime-button datetime="checkin-date"></ion-datetime-button>
              </div>
              <ion-modal :keep-contents-on-hidden="true">
                <ion-datetime
                    id="checkin-date"
                    presentation="date"
                    v-model="startDate"
                    min="2026-05-20"
                ></ion-datetime>
              </ion-modal>
            </div>

            <div class="date-input-field">
              <span class="field-label">To</span>
              <div class="datetime-wrapper">
                <ion-datetime-button datetime="checkout-date"></ion-datetime-button>
              </div>
              <ion-modal :keep-contents-on-hidden="true">
                <ion-datetime
                    id="checkout-date"
                    presentation="date"
                    v-model="endDate"
                    :min="startDate || '2026-05-21'"
                ></ion-datetime>
              </ion-modal>
            </div>
          </div>

          <ion-button expand="block" class="check-button" @click="verifyDates" :disabled="loading">
            {{ loading ? 'VERIFYING...' : 'CHECK AVAILABILITY' }}
          </ion-button>

          <div v-if="status !== 'idle'" class="minimal-feedback">
            <div v-if="status === 'available'" class="status-msg available-text">
              ✓ Room is completely available for your chosen period.
            </div>

            <div v-else-if="status === 'conflict'" class="status-msg conflict-text">
              ✕ 409 Conflict: Those dates are already occupied.
            </div>

            <div v-else-if="status === 'error'" class="status-msg error-text">
              ! Error: {{ serverErrorMessage }}
            </div>
          </div>
        </div>
      </div>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar, IonDatetime, IonDatetimeButton, IonModal, IonButton } from '@ionic/vue';

const startDate = ref<string>('2026-05-20');
const endDate = ref<string>('2026-05-21');
const loading = ref<boolean>(false);
const status = ref<string>('idle');
const serverErrorMessage = ref<string>('');
const roomId = 1;

async function verifyDates() {
  loading.value = true;
  status.value = 'idle';
  serverErrorMessage.value = '';

  const cleanStart = startDate.value.split('T')[0];
  const cleanEnd = endDate.value.split('T')[0];

  if (new Date(cleanStart) >= new Date(cleanEnd)) {
    status.value = 'error';
    serverErrorMessage.value = 'Check-out must follow check-in date.';
    loading.value = false;
    return;
  }

  try {
    const url = `http://localhost:8080/api/v1/rooms/${roomId}/availability?startDate=${cleanStart}&endDate=${cleanEnd}`;
    const response = await fetch(url, { method: 'GET' });

    if (response.status === 200) {
      status.value = 'available';
    } else if (response.status === 409) {
      status.value = 'conflict';
    } else {
      const errData = await response.json();
      status.value = 'error';
      serverErrorMessage.value = errData.error || 'The request could not be executed.';
    }
  } catch (err) {
    status.value = 'error';
    serverErrorMessage.value = 'Connection to backend registry failed.';
  } finally {
    loading.value = false;
  }
}
</script>

<style scoped>
.prototype-container {
  background: #ffffff;
  min-height: 100%;
}
.room-image-placeholder {
  background: #f1f5f9;
  height: 220px;
  display: flex;
  align-items: center;
  justify-content: center;
  border-bottom: 1px solid #cbd5e1;
}

.placeholder-text {
  font-size: 12px;
  font-weight: 600;
  letter-spacing: 1px;
  color: #94a3b8;
}
.room-details-padding {
  padding: 24px;
}
h2 {
  font-size: 20px;
  font-weight: 700;
  color: #000000;
  margin: 0 0 12px 0;
  letter-spacing: 0.5px;
}
.description-text {
  font-size: 14px;
  line-height: 1.5;
  color: #334155;
  margin: 0 0 16px 0;
}
.extras-row {
  display: flex;
  gap: 16px;
  font-size: 13px;
  color: #475569;
  margin-bottom: 24px;
}
.date-input-row {
  display: flex;
  gap: 16px;
  margin-bottom: 20px;
}
.date-input-field {
  flex: 1;
  border: 1px solid #000000;
  padding: 10px;
  border-radius: 4px;
  display: flex;
  flex-direction: column;
  gap: 8px;
  min-height: 75px;
}
.field-label {
  font-size: 11px;
  font-weight: 600;
  color: #64748b;
}
.datetime-wrapper {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}
.check-button {
  --background: #2b5bc4;
  --border-radius: 4px;
  margin: 0;
  font-weight: 600;
  font-size: 14px;
  letter-spacing: 0.5px;
  height: 44px;
}
.minimal-feedback {
  margin-top: 20px;
  border-top: 1px dashed #cbd5e1;
  padding-top: 16px;
}
.status-msg {
  font-size: 14px;
  font-weight: 500;
}
.available-text {
  color: #15803d;
}
.conflict-text {
  color: #b45309;
}
.error-text {
  color: #b91c1c;
}
</style>