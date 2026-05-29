<template>
  <div
    class="row align-items-center g-5 mb-5"
    :class="{ 'flex-lg-row-reverse': reverse }"
  >
    <div class="col-12 col-lg-6">
      <p class="text-uppercase text-secondary">{{ subtitle }}</p>

      <h1 class="display-5 mb-4">{{ title }}</h1>

      <p class="mb-4">{{ description }}</p>

      <hr />

      <div class="row mt-4 g-3">
        <div
          v-for="feature in features"
          :key="feature.text"
          class="col-6 col-md-4"
        >
          <i :class="feature.icon"></i>
          {{ feature.text }}
        </div>
      </div>

      <div class="mt-4">
        <button class="btn btn-danger me-3">Buchen Sie jetzt!</button>
      </div>
    </div>

    <div class="col-12 col-lg-6">
      <img
        :src="image"
        class="img-fluid rounded shadow"
        :alt="title"
      />
    </div>
  </div>
</template>

<script setup lang="ts">
type Feature = {
  icon: string
  text: string
}

defineProps<{
  subtitle: string
  title: string
  description: string
  image: string
  features: Feature[]
  reverse?: boolean
}>()
</script>