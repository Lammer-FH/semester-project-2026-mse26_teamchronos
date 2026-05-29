<template>
  <ion-page>
    <NavbarComponent />

    <ion-content>
      <div class="container my-5">
        <h1 class="display-4 mb-4 fw-light">
          Über das <span class="fw-bold">Chronos Hotel</span>
        </h1>

        <p class="lead mb-4">
          Das <strong>Chronos Hotel</strong> ist ein modernes
          <em> Hotel im Herzen von Wien</em> und verbindet stilvolles
          Design mit höchstem Komfort.
        </p>

        <p class="mb-4">
          Unser Ziel ist es, unseren Gästen einen
          <strong>entspannten und unvergesslichen Aufenthalt</strong>
          zu bieten — egal ob für einen Kurzurlaub, eine Geschäftsreise oder
          einen längeren Aufenthalt in der Stadt.
        </p>

        <p class="mb-4">
          Unsere Zimmer wurden mit viel Liebe zum Detail gestaltet und
          kombinieren <em>moderne Eleganz</em> mit praktischer Ausstattung.
          Helle Räume, hochwertige Möbel und eine angenehme Atmosphäre sorgen
          dafür, dass sich unsere Gäste vom ersten Moment an wohlfühlen.
        </p>

        <p class="mb-4">
          Von gemütlichen Einzelzimmern bis hin zu luxuriösen Suiten bietet das
          <strong>Chronos Hotel</strong> die passende Unterkunft für jede Art
          von Reise.
        </p>

        <p class="mb-4">
          Besonderen Wert legen wir auf
          <strong>erstklassigen Service, Sauberkeit und Gastfreundschaft</strong
          >. Unser freundliches Team steht unseren Gästen jederzeit zur
          Verfügung und sorgt dafür, dass keine Wünsche offenbleiben.
        </p>

        <p class="mb-4">
          Kostenloses Highspeed-WLAN, moderne Unterhaltungssysteme und
          komfortable Arbeitsbereiche machen das Hotel sowohl für Urlauber als
          auch für Geschäftsreisende attraktiv.
        </p>

        <p class="mb-4">
          Durch die zentrale Lage des Hotels sind viele Sehenswürdigkeiten,
          Restaurants und Einkaufsmöglichkeiten Wiens schnell erreichbar.
          Gleichzeitig bietet das Hotel eine
          <em>ruhige und entspannte Umgebung</em>, in der Gäste dem Alltag
          entfliehen können.
        </p>

        <p class="fs-5 fw-semibold mt-5">
          Chronos Hotel steht für Qualität, Komfort und moderne
          Gastfreundschaft.
        </p>
      </div>
    </ion-content>
    <FooterComponent />
  </ion-page>
</template>

<script setup lang="ts">
import { IonPage, IonContent } from "@ionic/vue";
import NavbarComponent from "@/components/NavbarComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";
</script>
