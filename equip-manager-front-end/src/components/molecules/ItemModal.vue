<template>
  <div class="modal-overlay" @click.self="close">
    <div class="modal-content">
      <h2>{{ isEditMode() ? "✏️ Editar Item" : "✏️ Cadastrar Item" }}</h2>
      <div class="d-flex justify-content-center align-items-center mt-3">
        <div v-if="loading" class="spinner-border text-primary" role="status"></div>
      </div>
      <form @submit.prevent="saveChanges">
        <div class="form-group">
          <label for="codigoItem">🔢 Código:</label>
          <input type="text" id="codigoItem" v-model="item.codigoItem" class="form-control" :readonly="isEditMode()" />
        </div>
        <div class="form-group" v-if="isEditMode()">
          <label for="numeroDeSerie">🔧 Número de Série:</label>
          <input type="text" id="numeroDeSerie" v-model="item.numeroDeSerie" class="form-control" readonly />
        </div>
        <div class="form-group">
          <label for="descricao">📝 Descrição:</label>
          <input type="text" id="descricao" v-model="item.descricao" class="form-control" />
        </div>
        <div class="form-group">
          <label for="localizacao">📍 Localização:</label>
          <input type="text" id="localizacao" v-model="item.localizacao" class="form-control" />
        </div>
        <div class="form-group" v-if="isEditMode()">
          <label for="disponibilidade">📦 Disponibilidade:</label>
          <select v-model="item.disponibilidade" class="form-control">
            <option :value="'D'">Disponível ✅</option>
            <option :value="'I'">Indisponível ❌</option>
          </select>
        </div>
        <div class="form-group" v-if="isEditMode()">
          <label for="status">📊 Status:</label>
          <select v-model="item.status" class="form-control">
            <option :value="'A'">Ativo ✅</option>
            <option :value="'I'">Inativo ❌</option>
          </select>
        </div>
        <div class="form-group" v-if="isEditMode()">
          <label for="status">📅 Data de movimentação:</label>
          <input type="text" id="dataMovimentacao" :value="formatarData(item.dataMovimentacao)" class="form-control"
            readonly />
        </div>
        <div class="form-group">
          <button type="submit" class="btn btn-success">Salvar</button>
          <button type="button" class="btn btn-secondary" @click="close">Cancelar</button>
        </div>
      </form>
    </div>
  </div>

  <AlertMessage v-if="showAlertModal" :mensagem="mensagemDeErro" />
</template>

<script lang="ts">
import { defineComponent, PropType } from 'vue';
import { InventoryItem } from '@/types/pagination.types';
import { fetchItemByNumeroDeSerie, putItemByNumeroDeSerie, salvarNovoItem } from '@/services/inventoryService';
import { AcoesModal } from '@/constants/enums/AcoesModal';
import AlertMessage from './AlertMessage.vue';
import { AxiosError } from 'axios';
import { formatDate } from '@/utils/formatDate';

export default defineComponent({
  components: {
    AlertMessage,
  },
  props: {
    numeroDeSerie: {
      type: String as PropType<string>,
      required: true,
    },
    acaoModal: {
      type: String as PropType<AcoesModal>,
      required: true,
    },
  },
  data() {
    return {
      item: {
        codigoItem: '',
        numeroDeSerie: '',
        descricao: '',
        localizacao: '',
        disponibilidade: '',
        status: ''
      } as InventoryItem,
      loading: false as boolean,
      mensagemDeErro: '' as string,
      showAlertModal: false as boolean,
    };
  },
  methods: {
    formatarData(data: string) {
      return formatDate(data);
    },
    isEditMode(): boolean {
      return this.acaoModal === AcoesModal.editar;
    },
    async saveChanges(): Promise<any> {
      try {
        let item: InventoryItem;
        if (this.isEditMode()) {
          item = await putItemByNumeroDeSerie(
            this.numeroDeSerie,
            this.item
          );
        } else {
          item = await salvarNovoItem(
            {
              codigoItem: this.item.codigoItem,
              numeroDeSerie: this.item.numeroDeSerie,
              descricao: this.item.descricao,
              localizacao: this.item.localizacao
            }
          );
        }
        this.$emit('save', item);
        this.close();
      } catch (error) {
        const axiosError = error as AxiosError;
        if (axiosError.response && axiosError.response.data) {
          this.mensagemDeErro = axiosError.response.data;
        } else {
          this.mensagemDeErro = 'Ocorreu um erro desconhecido.';
        }
        this.showAlertModal = true;
      }

    },
    close() {
      this.item = {
        codigoItem: '',
        numeroDeSerie: '',
        descricao: '',
        localizacao: '',
        disponibilidade: '',
        status: '',
        dataMovimentacao: '',
      };
      this.$emit('close');
    },
    async onModalLoad() {
      this.loading = true;
      if (this.numeroDeSerie && this.isEditMode()) {
        this.item = await fetchItemByNumeroDeSerie(this.numeroDeSerie);
      }
      this.loading = false;
    },
  },
  mounted() {
    this.onModalLoad();
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