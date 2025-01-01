<template>
  <div class="container my-5">
    <h1 class="mb-4">🗃️ Itens no inventário</h1>

    <div class="d-flex align-items-center w-100 mb-3">
      <div class="flex-grow-1 me-2 mr-3">
        <SearchInput v-model="searchQuery" @input="handleInput" class="w-100" />
      </div>
      <button 
        type="button" 
        class="btn btn-success"
        @click="showItemModal()">
        Registrar um novo item
      </button>
    </div>
    
    <table class="table table-striped table-bordered" ref="itemList">
      <thead>
        <tr>
          <th>Código</th>
          <th>Descrição</th>
          <th>Disponibilidade</th>
          <th>Status</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr 
          v-for="item in filteredItems" 
          :key="item.codigoItem" 
          :title="`Ver mais sobre o item: ${item.descricao}`"
          @click="openModal(item)">
          <td>{{ item.codigoItem }}</td>
          <td>{{ item.descricao }}</td>
          <td>{{ showDisponibilidade(item.disponibilidade) }}</td>
          <td>{{ showStatus(item.status) }}</td>
          <td>
            <button 
              type="button" 
              class="btn btn-primary" 
              @click.stop="editItem(item)" 
              :title="`Editar Item: ${item.descricao}`">
              <font-awesome-icon icon="pen" />
            </button>
            <button 
              type="button" 
              class="btn btn-danger" 
              @click.stop="showdeletarItemConfirmModal(item)"
              :title="`Excluir Item: ${item.descricao}`">
              <font-awesome-icon icon="trash" />
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <div v-if="loading" class="spinner-border text-primary" role="status"></div>

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

  <ItemDetailsModal
    v-if="showModal"
    :item="selectedItem"
    @close="closeModal"
  />

  <ItemModal 
    v-if="showEditModal" 
    :numeroDeSerie="selectedItem?.numeroDeSerie"
    :acaoModal="acaoModal"
    @close="closeEditModal" 
    @save="updateItem" 
  />
  <ScrollToTopButton/>
</template>


<script lang="ts">
import { InventoryItem, InventoryResponse } from '@/types/pagination.types';
import SearchInput from '../molecules/SearchInput.vue';
import { deleteItemPeloNumeroDeSerie, fetchInventoryItems } from '@/services/inventoryService';
import { defineComponent } from 'vue';
import { debounce } from 'lodash';
import ConfirmationDialog from '../molecules/ConfirmationDialog.vue';
import ItemDetailsModal from '../molecules/ItemDetailsModal.vue';
import ItemModal from '../molecules/ItemModal.vue';
import ScrollToTopButton from '../atoms/ScrollToTopButton.vue';
import $ from 'jquery';
import { AcoesModal } from '@/constants/enums/AcoesModal';
export default defineComponent({
  components: {
    SearchInput,
    ConfirmationDialog,
    ItemDetailsModal,
    ItemModal,
    ScrollToTopButton,
  },
  data() {
    return {
      searchQuery: '',
      statusFilter: '', 
      items: [] as InventoryItem[],
      currentPage: 0,
      itemsPerPage: 5,
      totalItems: 0,
      loading: false,
      lastPage: false,
      itemToDelete: null as InventoryItem | null,
      isDialogVisible: false as Boolean,
      selectedItem: null as InventoryItem | null,
      showModal: false,
      showEditModal: false,
      qtdItensPorPagina: 12 as number,
      showCreationItemModal: false as Boolean,
      acaoModal: AcoesModal.cadastrar as AcoesModal,
    };
  },
  computed: {
    filteredItems(): InventoryItem[] {
      return this.items.filter(item => {
        if (this.statusFilter === '') return true;
        return item.status === this.statusFilter;
      });
    },
  },
  methods: {
    showItemModal():void {
      this.acaoModal = AcoesModal.cadastrar;
      this.showCreationItemModal = true;
      this.showEditModal = true;
    },
    openModal(item: any) {
      this.selectedItem = item;
      this.showModal = true;
    },
    openEditItemModal(item: InventoryItem) {
      this.selectedItem = item;
      this.acaoModal = AcoesModal.editar;
      this.showEditModal = true;
    },
    closeModal() {
      this.showModal = false;
    },
    editItem(item: InventoryItem) {
      this.openEditItemModal(item);
    },
    closeEditModal() {
      this.showEditModal = false;
    },
    updateItem(updatedItem: InventoryItem) {
      const index = this.items.findIndex(item => item.codigoItem === updatedItem.codigoItem);
      if (index !== -1) {
        this.items[index] = updatedItem;
      }
    },
    debouncedFetchItems(): void {
      const debounced = debounce(() => {
        this.fetchItems();
      }, 500);

      return debounced();
    },

    showdeletarItemConfirmModal(item: InventoryItem): void {
      this.itemToDelete = item;
      this.isDialogVisible = true;
    },

    confirmDelete() {
      console.log('Deletando item:', this.itemToDelete);
      this.deletarItemSelecionado();
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

    async deletarItemSelecionado(): Promise<void> {
      try {
        this.loading = true;
        await deleteItemPeloNumeroDeSerie(this.itemToDelete?.numeroDeSerie);
        this.items = this.items.filter(item => item.numeroDeSerie !== this.itemToDelete?.numeroDeSerie);
        this.items
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
        console.log("handleScroll: ", scrollTop, scrollHeight, clientHeight);
        if (scrollTop + clientHeight >= scrollHeight - 10 && !this.loading && !this.lastPage) {
          this.currentPage += 1;
          this.fetchItems();
        }
      }
    },


    handleStatusFilterChange() {
      if (this.statusFilter) {
        this.filteredItems = this.items.filter(
          item => item.status.toLowerCase() === this.statusFilter.toLowerCase()
        );
      } else {
        this.filteredItems = this.items;
      }
    },

    showDisponibilidade(disponibilidade:string):string {
      if (disponibilidade === 'D') {
        return 'Disponível';
      }
      return 'Indisponível';
    },
    showStatus(status:string):string {
      if (status === 'A') {
        return 'Ativo';
      }
      return 'Inativo';
    }
  },
  created() {
    this.debouncedFetchItems = debounce(this.fetchItems, 500);
  },
  mounted() {
    this.fetchItems();
    $(window).on('scroll', this.handleScroll);
    $(window).on('resize', function () {
      console.log("Detectado mudança de tamanho de tela, Heigth:", $(window).height());
    });
},
  beforeUnmount(): void {
    $(window).off('scroll', this.handleScroll);
    $(window).off('resize');
  },
});
</script>

<style>

</style>
