<template>
    <div>
      <input
        type="text"
        class="form-control"
        placeholder="Pesquise pelo item..."
        v-model="searchValue"
      />
    </div>
  </template>
  
  <script>
  export default {
    props: {
      modelValue: {
        type: String,
        required: true,
      },
    },
    computed: {
      searchValue: {
        get() {
          return this.modelValue;
        },
        set(value) {
          this.$emit('update:modelValue', value);
        },
      },
    },
  };
  </script>
  