<template>
    <div>
      <button v-if="showButton" class="scroll-to-top" @click="scrollToTop">
        <font-awesome-icon icon="arrow-up" />
      </button>
    </div>
  </template>
  
  <script lang="ts">
  export default {
    data() {
      return {
        showButton: false,
      };
    },
    methods: {
      scrollToTop() {
        window.scrollTo({
          top: 0,
          behavior: 'smooth',
        });
      },
      handleScroll() {
        this.showButton = window.scrollY > 200;
      },
    },
    mounted() {
      window.addEventListener('scroll', this.handleScroll);
    },
    beforeUnmount() {
      window.removeEventListener('scroll', this.handleScroll);
    },
  };
  </script>
  
  <style scoped>
  .scroll-to-top {
    position: fixed;
    bottom: 20px;
    right: 20px;
    background-color: #007bff;
    color: white;
    border: none;
    padding: 18px 25px;
    border-radius: 50%;
    font-size: 24px;
    cursor: pointer;
    z-index: 1000;
  }
  
  .scroll-to-top:hover {
    background-color: #0056b3;
  }
  </style>
  