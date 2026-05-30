<template>
  <ion-page>
    <NavbarComponent />

    <ion-content>
      <div class="container mt-5 px-4 mb-5">
        <div class="row justify-content-center">
          <div class="col-12 col-md-10 col-lg-8">
            <h4 class="fw-bold text-white text-center text-uppercase tracking-wide mb-5">
              BUCHUNGSBESTÄTIGUNG
            </h4>

            <div class="card card-dark-bg border-0 shadow-sm p-4 mb-4">
              <div class="card-body text-white">
                <p class="mb-2"><strong>Zimmer:</strong> {{ roomTitle }}</p>
                <p class="mb-2"><strong>Vorname:</strong> {{ route.query.firstName }}</p>
                <p class="mb-2"><strong>Nachname:</strong> {{ route.query.lastName }}</p>
                <p class="mb-0"><strong>E-Mail:</strong> {{ route.query.email }}</p>
              </div>
            </div>

            <div class="text-white mt-5">
              <h5 class="fw-bold text-uppercase mb-3">ANREISE</h5>
              <p class="text-secondary">
                Wir freuen uns auf Ihren Besuch. Falls Sie Fragen haben oder Hilfe bei der Anreise benötigen, können Sie uns jederzeit kontaktieren.
              </p>
              <p class="mb-1"><i class="bi bi-telephone me-2"></i>+43 13334077</p>
              <p><i class="bi bi-envelope me-2"></i>welcome@hotel-technikum.at</p>
            </div>
          </div>
        </div>
      </div>

      <FooterComponent />
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
import { IonPage, IonContent } from '@ionic/vue';
import axios from 'axios';
import NavbarComponent from "@/components/NavbarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";

const route = useRoute();
const roomTitle = ref('Lade...');

onMounted(async () => {
  const roomId = route.query.roomId;
  try {
    const response = await axios.get(`http://localhost:8080/api/v1/rooms/${roomId}`);
    roomTitle.value = response.data.title;
  } catch (error) {
    roomTitle.value = "Zimmerdetails nicht verfügbar";
  }
});
</script>

<style scoped>
ion-content {
  --background: #121212;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
}

.card-dark-bg {
  background-color: #1e1e1e !important;
}

.tracking-wide {
  letter-spacing: 0.1em;
}
</style>