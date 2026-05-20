<template>
  <ion-page>
    <ion-header class="ion-no-border">
      <ion-toolbar>
        <ion-buttons slot="start">
          <ion-back-button default-href="/rooms" text="Rooms" />
        </ion-buttons>
      </ion-toolbar>
    </ion-header>

    <ion-content :fullscreen="true">
      <div class="prototype-container">
        <div class="room-image-placeholder">
          <div class="image-icon-box">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" fill="currentColor" class="image-placeholder-icon">
              <path fill-rule="evenodd" d="M1.5 6a2.25 2.25 0 0 1 2.25-2.25h16.5A2.25 2.25 0 0 1 22.5 6v12a2.25 2.25 0 0 1-2.25 2.25H3.75A2.25 2.25 0 0 1 1.5 18V6ZM3 16.06V18c0 .414.336.75.75.75h16.5A.75.75 0 0 0 21 18v-1.94l-2.69-2.689a1.5 1.5 0 0 0-2.12 0l-.88.879.97.97a.75.75 0 1 1-1.06 1.06l-5.16-5.159a1.5 1.5 0 0 0-2.12 0L3 16.06ZM18.75 9a.75.75 0 0 0 0-1.5h-.008a.75.75 0 0 0 0 1.5h.008Z" clip-rule="evenodd" />
            </svg>
          </div>
        </div>

        <div class="room-details-padding">
          <h2 class="room-title">{{ roomName }}</h2>

          <p class="description-text">text text..</p>

          <div class="extras-row">
            <span class="amenity-item">
              <ion-icon :icon="wifiOutline" class="amenity-icon" />
              Highspeed WiFi </span>
            <span class="amenity-item">
              <ion-icon :icon="desktopOutline" class="amenity-icon" />
              Workspace </span>
          </div>

          <div class="date-input-row">
            <div class="date-input-field">
              <span class="field-label">From</span>
              <div class="datetime-wrapper">
                <ion-datetime-button datetime="checkin-date" />
              </div>
              <ion-modal :keep-contents-on-hidden="true">
                <ion-datetime
                    id="checkin-date"
                    presentation="date"
                    v-model="startDate"
                    min="2026-05-20"
                />
              </ion-modal>
            </div>

            <div class="date-input-field">
              <span class="field-label">To</span>
              <div class="datetime-wrapper">
                <ion-datetime-button datetime="checkout-date" />
              </div>
              <ion-modal :keep-contents-on-hidden="true">
                <ion-datetime
                    id="checkout-date"
                    presentation="date"
                    v-model="endDate"
                    :min="startDate || '2026-05-21'"
                />
              </ion-modal>
            </div>
          </div>

          <div class="button-wrapper">
            <ion-button expand="block" class="check-button" @click="verifyDates" :disabled="loading">
              {{ loading ? 'VERIFYING...' : 'CHECK AVAILABILITY' }}
            </ion-button>
          </div>

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
import {
  IonContent,
  IonHeader,
  IonPage,
  IonToolbar,
  IonButtons,
  IonBackButton,
  IonDatetime,
  IonDatetimeButton,
  IonModal,
  IonButton,
  IonIcon
} from '@ionic/vue';
import { wifiOutline, desktopOutline } from 'ionicons/icons';




const roomId = 1;
const roomName = ref<string>('Room 1');
const startDate = ref<string>('2026-05-20');
const endDate = ref<string>('2026-05-21');
const loading = ref<boolean>(false);
const status = ref<string>('idle');
const serverErrorMessage = ref<string>('');

async function verifyDates() {
  loading.value = true;
  status.value = 'idle';
  serverErrorMessage.value = '';

  const cleanStart = startDate.value.split('T')[0];
  const cleanEnd = endDate.value.split('T')[0];

  if (new Date(cleanStart) >= new Date(cleanEnd)) {
    status.value = 'error';
    serverErrorMessage.value = 'Check-out must be later than check-in date';
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
      serverErrorMessage.value = errData.error || 'bad request';
    }
  } catch (err) {
    status.value = 'error';
    serverErrorMessage.value = 'cannot connect to backend';
  } finally {
    loading.value = false;
  }
}
</script>

<style scoped>
.prototype-container {
  background: #ffffff;
  min-height: 100%;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
}

ion-toolbar {
  --background: transparent;
  --border-color: transparent;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 10;
}

.room-image-placeholder {
  background: #cccccc;
  height: 260px;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
}

.image-icon-box {
  width: 44px;
  height: 44px;
  color: #222222;
}

.image-placeholder-icon {
  width: 100%;
  height: 100%;
}

.room-details-padding {
  padding: 32px 24px;
  text-align: center;
}

.room-title {
  font-size: 16px;
  font-weight: 800;
  color: #000000;
  margin: 0 0 16px 0;
  letter-spacing: 0.5px;
  text-transform: uppercase;
}

.description-text {
  font-size: 13px;
  line-height: 1.4;
  color: #000000;
  margin: 0 auto 28px auto;
  max-width: 92%;
}

.extras-row {
  display: flex;
  justify-content: center;
  gap: 24px;
  font-size: 14px;
  color: #000000;
  margin-bottom: 36px;
}

.amenity-item {
  display: flex;
  align-items: center;
  gap: 8px;
}

.amenity-icon {
  font-size: 18px;
  color: #000000;
}

.date-input-row {
  display: flex;
  gap: 12px;
  margin-bottom: 28px;
  justify-content: center;
}

.date-input-field {
  flex: 1;
  border: 1.5px solid #000000;
  padding: 6px 12px;
  border-radius: 4px;
  display: flex;
  flex-direction: row;
  align-items: center;
  height: 48px;
}

.field-label {
  font-size: 14px;
  color: #000000;
  margin-right: 8px;
  font-weight: 500;
}

.datetime-wrapper {
  flex: 1;
  display: flex;
  justify-content: flex-start;
}

ion-datetime-button {
  --background: transparent;
  --color: #000000;
  font-size: 14px;
}

ion-datetime-button::part(native) {
  padding: 0;
  background: transparent;
}

.button-wrapper {
  padding: 0 2px;
}

.check-button {
  --background: #3359ad;
  --color: #ffffff;
  --border-radius: 4px;
  margin: 0;
  font-weight: 600;
  font-size: 13px;
  letter-spacing: 0.3px;
  height: 46px;
}

.minimal-feedback {
  margin-top: 20px;
  border-top: 1px dashed #cbd5e1;
  padding-top: 16px;
}

.status-msg {
  font-size: 13px;
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