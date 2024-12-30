<template>
    <div class="modal-overlay" @click.self="close">
      <div class="modal-content">
        <h2>✏️ Editar Item</h2>
        
        <form @submit.prevent="saveChanges">
          <div class="form-group">
            <label for="codigoItem">🔢 Código:</label>
            <input 
              type="text" 
              id="codigoItem" 
              v-model="editableItem.codigoItem" 
              class="form-control" 
              readonly
            />
          </div>
          
          <div class="form-group">
            <label for="numeroDeSerie">🔧 Número de Série:</label>
            <input 
              type="text" 
              id="numeroDeSerie" 
              v-model="editableItem.numeroDeSerie" 
              class="form-control"
              readonly
            />
          </div>
  
          <div class="form-group">
            <label for="descricao">📝 Descrição:</label>
            <input 
              type="text" 
              id="descricao" 
              v-model="editableItem.descricao" 
              class="form-control"
            />
          </div>
  
          <div class="form-group">
            <label for="localizacao">📍 Localização:</label>
            <input 
              type="text" 
              id="localizacao" 
              v-model="editableItem.localizacao" 
              class="form-control"
            />
          </div>
  
          <div class="form-group">
            <label for="disponibilidade">📦 Disponibilidade:</label>
            <select v-model="editableItem.disponibilidade" class="form-control">
              <option :value="'D'">Disponível ✅</option>
              <option :value="'I'">Indisponível ❌</option>
            </select>
          </div>
  
          <div class="form-group">
            <label for="status">📊 Status:</label>
            <select v-model="editableItem.status" class="form-control">
              <option :value="'A'">Ativo ✅</option>
              <option :value="'I'">Inativo ❌</option>
            </select>
          </div>
  
          <div class="form-group">
            <button type="submit" class="btn btn-success">Salvar alterações</button>
            <button type="button" class="btn btn-secondary" @click="close">Cancelar</button>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <script lang="ts">
  import { defineComponent, PropType } from 'vue';
  import { InventoryItem } from '@/types/pagination.types';
  
  export default defineComponent({
    props: {
      item: {
        type: Object as PropType<InventoryItem>,
        required: true,
      },
    },
    data() {
      return {
        editableItem: { ...this.item },
      };
    },
    methods: {
      saveChanges() {
        this.$emit('save', this.editableItem);
        this.close();
      },
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
    font-size: 1.5rem;
  }
  
  .form-group {
    margin-bottom: 15px;
  }
  
  .form-group label {
    display: block;
    font-size: 1rem;
    margin-bottom: 5px;
  }
  
  .form-group input,
  .form-group select {
    width: 100%;
    padding: 8px;
    font-size: 1rem;
    border-radius: 5px;
    border: 1px solid #ccc;
  }
  
  .form-group button {
    margin-top: 10px;
    padding: 10px 20px;
  }
  
  .btn-secondary {
    margin-left: 10px;
  }
  </style>
  