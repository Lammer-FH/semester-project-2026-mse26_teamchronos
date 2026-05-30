<template>
  <footer class="custom-mobile-footer">
    <ion-grid fixed>
      <ion-row class="ion-align-items-center ion-justify-content-between view-row">

        <ion-col size="12" size-sm="auto" class="ion-text-center ion-text-sm-start">
          <router-link to="/imprint" class="footer-link">
            Legal Notice
          </router-link>
        </ion-col>

        <ion-col size="12" size-sm="auto" class="ion-text-center ion-text-sm-end">
          <ion-text color="medium">
            <span class="copyright-text">© 2026 Boutique Hotel Technikum</span>
          </ion-text>
        </ion-col>

      </ion-row>
    </ion-grid>
  </footer>
</template>

<script setup lang="ts">
import { IonGrid, IonRow, IonCol, IonText } from '@ionic/vue';
</script>

<style scoped>
  .custom-mobile-footer {
    background-color: var(--ion-color-light);
    border-top: 1px solid var(--ion-color-light-shade, #e0e0e0);
    padding: 24px 0;
  }

  .view-row {
    row-gap: 12px;
  }

  .footer-link {
    color: var(--ion-color-dark);
    text-decoration: none;
    font-size: 0.9rem;
    font-weight: 500;
  }

  .footer-link:focus, .footer-link:active {
    opacity: 0.7;
  }

  .copyright-text {
    font-size: 0.85rem;
  }
</style>