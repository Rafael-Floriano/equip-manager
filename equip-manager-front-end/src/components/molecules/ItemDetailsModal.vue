<template>
    <div class="modal-overlay" @click.self="close">
      <div class="modal-content">
        <h2>📋 Detalhes do Item</h2>
        <p><strong>🔢 Código:</strong> {{ item?.codigoItem }}</p>
        <p><strong>🔧 Número de Série:</strong> {{ item?.numeroDeSerie }}</p>
        <p><strong>📦 Disponibilidade:</strong> {{ item?.disponibilidade ? 'Disponível ✅' : 'Indisponível ❌' }}</p>
        <p><strong>📊 Status:</strong> {{ item?.status }}</p>
        <p><strong>📝 Descrição:</strong> {{ item?.descricao }}</p>
        <p><strong>📍 Localização:</strong> {{ item?.localizacao }}</p>
        <button @click="close" class="btn-close">Fechar</button>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { InventoryItem } from '@/types/pagination.types';
import { defineComponent, PropType } from 'vue';
  
  export default defineComponent({
    props: {
      item: {
        type: Object as PropType<InventoryItem>,
        required: true,
      },
    },
    methods: {
      close() {
        this.$emit('close');
      },
    },
  });
  </script>
  
  <style scoped>
  .modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    align-items: center;
    justify-content: center;
    z-index: 1000;
  }
  
  .modal-content {
    background: white;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
    width: 90%;
    max-width: 500px;
  }
  
  .modal-content h2 {
    margin-top: 0;
  }
  
  .modal-content p {
    margin: 10px 0;
  }
  
  .btn-close {
    background: #d9534f;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 20px;
  }
  
  .btn-close:hover {
    background: #c9302c;
  }
  </style>
  