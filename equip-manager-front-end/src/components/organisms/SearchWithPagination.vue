<template>
  <div class="container my-5">
    <h1 class="mb-4">Pagination with Search</h1>

    <SearchInput v-model="searchQuery" @input="debouncedFetchItems" />

    <div v-if="loading" class="spinner-border text-primary" role="status">
      <span class="visually-hidden"></span>
    </div>

    <ul class="list-group mb-4" v-if="!loading">
      <li v-for="item in items" :key="item.codigoItem" class="list-group-item">
        {{ item.descricao }}
      </li>
    </ul>
    <div v-if="temResultadoVazio()">
        <p class="user-select-auto">Não encontramos resultados. Que tal tentar uma nova busca?</p>
    </div>

    <PageNavigator
      :currentPage="currentPage"
      :totalPages="totalPages"
      @page-changed="changePage"
    />
  </div>
</template>

<script lang="ts">
import { InventoryItem, InventoryResponse } from '@/types/pagination.types';
import PageNavigator from '../molecules/PageNavigator.vue';
import SearchInput from '../molecules/SearchInput.vue';
import { fetchInventoryItems } from '@/services/inventoryService';
import { defineComponent } from 'vue';
import { debounce } from 'lodash';

export default defineComponent({
  components: {
    PageNavigator,
    SearchInput,
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
    };
  },
  methods: {
    async fetchItems():Promise<void> {
      try {
        this.loading = true;
        const response: InventoryResponse = await fetchInventoryItems(
          this.searchQuery,
          this.currentPage
        );
        this.items = response.content;
        this.totalItems = response.numberOfElements;
        this.lastPage = response.last;
      } catch (error) {
        console.error('Erro ao buscar itens do inventário:', error);
      } finally {
        this.loading = false;
      }
    },
    
    temResultadoVazio():boolean {
      return (this.items == null || this.items.length == 0) && !this.loading;
    },

    debouncedFetchItems():void {
    const debounced = debounce(() => {
      this.fetchItems();
    }, 500);

    return debounced();
  },

    changePage(page: number):void {
      if (page < 1 || !this.lastPage) return;
      this.currentPage = page;
      this.fetchItems();
    },
  },
  watch: {
    searchQuery() {
      this.loading = true;
      this.debouncedFetchItems();
    },
  },
  created() {
    this.debouncedFetchItems = debounce(this.fetchItems, 500);
  },
  mounted() {
    this.fetchItems();
  },
});
</script>
