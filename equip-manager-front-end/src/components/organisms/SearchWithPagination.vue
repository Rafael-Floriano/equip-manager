<template>
  <div class="container my-5">
    <h1 class="mb-4">🗃️ Itens no inventário</h1>

    <SearchInput v-model="searchQuery" @input="handleInput" />

    <div v-if="loading" class="spinner-border text-primary" role="status">
    </div>

    <ul class="list-group mb-4" v-if="!loading" ref="itemList">
      <li v-for="item in items" :key="item.codigoItem" class="list-group-item" style="display: flex; align-items: center; justify-content: space-between;">
        {{ item.descricao }} 
        <div style="display: flex; align-items: center; gap: 5px;">
          <button type="button" class="btn btn-primary" @click="deleteItem(item)"><font-awesome-icon icon="pen" /></button>
          <button type="button" class="btn btn-danger" @click="deleteItem(item)"><font-awesome-icon icon="trash" /></button>
        </div>
      </li>
    </ul>

    <div v-if="temResultadoVazio()">
      <p class="user-select-auto">Não encontramos resultados. Que tal tentar uma nova busca?</p>
    </div>
  </div>

  <ConfirmationDialog
      :message="`Deseja realmente excluir o item <strong>${itemToDelete?.descricao}?</strong>`"
      :isVisible="isDialogVisible"
      @confirm="confirmDelete"
      @cancel="cancelDelete"
    />
</template>

<script lang="ts">
import { InventoryItem, InventoryResponse } from '@/types/pagination.types';
import SearchInput from '../molecules/SearchInput.vue';
import { fetchInventoryItems } from '@/services/inventoryService';
import { defineComponent } from 'vue';
import { debounce } from 'lodash';
import ConfirmationDialog from '../molecules/ConfirmationDialog.vue';

export default defineComponent({
  components: {
    SearchInput,
    ConfirmationDialog,
  },
  data() {
    return {
      searchQuery: '',
      items: [] as InventoryItem[],
      currentPage: 0,
      itemsPerPage: 5,
      totalItems: 0,
      loading: false,
      lastPage: false,
      itemToDelete: null as InventoryItem | null,
      isDialogVisible: false as Boolean
    };
  },
  methods: {

    debouncedFetchItems(): void {
      const debounced = debounce(() => {
        this.fetchItems();
      }, 500);

      return debounced();
    },

    deleteItem(item: InventoryItem): void {
      console.log("deleteItem:", item);
      this.itemToDelete = item;
      this.isDialogVisible = true;
    },

    confirmDelete() {
      console.log('Deletando item:', this.itemToDelete);
      this.isDialogVisible = false;
    },

    cancelDelete() {
      console.log('Exclusão cancelada');
      this.isDialogVisible = false;
    },

    async fetchItems(): Promise<void> {
      try {
        this.loading = true;
        const response: InventoryResponse = await fetchInventoryItems(
          this.searchQuery,
          this.currentPage
        );
        this.items = [...this.items, ...response.content];
        this.totalItems = response.numberOfElements;
        this.lastPage = response.last;
      } catch (error) {
        console.error('Erro ao buscar itens do inventário:', error);
      } finally {
        this.loading = false;
      }
    },

    temResultadoVazio(): boolean {
      return (this.items == null || this.items.length == 0) && !this.loading;
    },

    handleInput() {
      this.loading = true;
      this.items = [];
      this.currentPage = 0;
      this.debouncedFetchItems();
    },

    handleScroll() {
      const listElement = this.$refs.itemList as HTMLElement;
      if (listElement) {
        const { scrollTop, scrollHeight, clientHeight } = listElement;
        if (scrollTop + clientHeight >= scrollHeight - 10 && !this.loading && !this.lastPage) {
          this.currentPage += 1;
          this.fetchItems();
        }
      }
    },
  },
  created() {
    this.debouncedFetchItems = debounce(this.fetchItems, 500);
  },
  mounted() {
    this.fetchItems();
    window.addEventListener('scroll', this.handleScroll);
  },
  beforeUnmount() {
    window.removeEventListener('scroll', this.handleScroll);
  },
});
</script>

<style scoped>
.list-group {
  max-height: 500px;
  overflow-y: auto;
}
</style>