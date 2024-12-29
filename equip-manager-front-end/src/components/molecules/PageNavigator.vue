<template>
    <nav style="display: flex;">
        <div class="page-item" :class="{ disabled: currentPage === 1 }">
          <button class="page-link" @click="$emit('page-changed', currentPage - 1)">
            Previous
          </button>
        </div>
        <div
          v-for="page in totalPages"
          :key="page"
          class="page-item"
          :class="{ active: currentPage === page }"
        >
          <button class="page-link" @click="$emit('page-changed', page)">
            {{ page }}
          </button>
        </div>
        <div class="page-item" :class="{ disabled: currentPage === totalPages }">
          <button class="page-link" @click="$emit('page-changed', currentPage + 1)">
            Next
          </button>
        </div>
    </nav>
  </template>
  
  <script>
  export default {
    props: {
      currentPage: {
        type: Number,
        required: true,
      },
      totalPages: {
        type: Number,
        required: true,
      },
    },
  };
  </script>
  