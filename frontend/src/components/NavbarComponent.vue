<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark py-3 shadow">
    <div class="container">
      <a class="navbar-brand fw-bold fs-3" href="#">Chronos Hotel</a>

      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div id="navbarNav" class="collapse navbar-collapse">
        <ul class="navbar-nav ms-auto align-items-lg-center">
          <li class="nav-item mx-2">
            <a class="nav-link active" href="#">Home</a>
          </li>

          <li class="nav-item mx-2">
            <a class="nav-link" href="/about">Über uns</a>
          </li>

          <li class="nav-item mx-2">
            <a class="nav-link" href="/imprint">Kontakt</a>
          </li>

          <li class="nav-item ms-lg-4 mt-3 mt-lg-0">
            <button class="btn btn-danger px-4">Jetzt buchen</button>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>