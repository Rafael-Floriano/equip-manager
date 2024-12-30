<template>
  <div class="container mt-5">
    <div class="row justify-content-center">
      <div class="col-lg-8 col-md-10 col-sm-12">
        <h3 class="text-center mb-4">Cadastro de Item</h3>
        <form @submit.prevent="submitForm">
          <div class="row mb-3">
            <div class="col-md-6">
              <label for="codigoItem" class="form-label">Código do Item</label>
              <input
                type="text"
                id="codigoItem"
                v-model="item.codigoItem"
                class="form-control"
                placeholder="Ex: 16BCCD5"
                required
                @blur="touchedFields.codigoItem = true"
                :class="{'is-invalid': touchedFields.codigoItem && codigoItemInvalid}"
              />
              <div v-if="touchedFields.codigoItem && codigoItemInvalid" class="invalid-feedback">
                {{retornaMensagemCodigoInvalido()}}
              </div>
            </div>
          </div>
          <div class="row mb-3">
            <div class="col-md-6">
              <label for="descricao" class="form-label">Descrição</label>
              <input
                type="text"
                id="descricao"
                v-model="item.descricao"
                class="form-control"
                placeholder="Descrição do item"
                required
                @blur="touchedFields.descricao = true"
                :class="{'is-invalid': touchedFields.descricao && descricaoInvalid}"
              />
              <div v-if="touchedFields.descricao && descricaoInvalid" class="invalid-feedback">
                {{ retornaMensagemDescricaoInvalida() }}
              </div>
            </div>
            <div class="col-md-6">
              <label for="localizacao" class="form-label">Localização</label>
              <input
                type="text"
                id="localizacao"
                v-model="item.localizacao"
                class="form-control"
                placeholder="Onde o item está localizado?"
                required
                @blur="touchedFields.localizacao = true"
                :class="{'is-invalid': touchedFields.localizacao && localizacaoInvalid}"
              />
              <div v-if="touchedFields.localizacao && localizacaoInvalid" class="invalid-feedback">
                {{ retornaMensagemLocalizacaoInvalida() }}
              </div>
            </div>
          </div>
          <div class="text-center">
            <button type="submit" class="btn btn-primary w-100" :disabled="formInvalid">
              Cadastrar
            </button>
          </div>
        </form>
      </div>
    </div>
    <AlertMessage
      v-if="mensagemDeErro"
      :mensagem="mensagemDeErro"
    />
  </div>
</template>
<script lang="ts">
import { defineComponent } from 'vue';
import AlertMessage from '../molecules/AlertMessage.vue';
import { ItemInventarioDto } from '@/types/item.types';
import { salvarNovoItem } from '@/services/inventoryService';

export default defineComponent({
  components: { AlertMessage },
  data() {
    return {
      item: {
        codigoItem: '',
        numeroDeSerie: '',
        descricao: '',
        localizacao: '',
      } as ItemInventarioDto,
      loading: false as boolean,
      mensagemDeErro: null as string | null,
      touchedFields: {
        codigoItem: false,
        descricao: false,
        localizacao: false,
      },
    };
  },
  computed: {
    codigoItemInvalid(): boolean {
      const alfanumericoComLetraENumeroRegex = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]+$/;
      return !this.item.codigoItem || !alfanumericoComLetraENumeroRegex.test(this.item.codigoItem) || this.item.codigoItem.length != 7;
    },
    descricaoInvalid(): boolean {
      return !this.item.descricao || this.item?.descricao?.length < 5;
    },
    localizacaoInvalid(): boolean {
      return !this.item?.localizacao || this.item.localizacao.length < 3 || this.item.localizacao.length > 250;
    },
    formInvalid(): boolean {
      return this.codigoItemInvalid || this.descricaoInvalid || this.localizacaoInvalid;
    },
  },
  methods: {
    async submitForm() {
      if (this.formInvalid) return;

      this.loading = true;
      this.mensagemDeErro = null;

      try {
        await salvarNovoItem(this.item);
        alert('Item cadastrado com sucesso!');
        this.resetForm();
      } catch (error: any) {
        console.log('Ok, estou aqui no erro:', error);
        this.mensagemDeErro = error?.response?.data;
      } finally {
        this.loading = false;
      }
    },
    resetForm() {
      this.item = {
        codigoItem: '',
        numeroDeSerie: '',
        descricao: '',
        localizacao: '',
      };
      this.touchedFields = {
        codigoItem: false,
        descricao: false,
        localizacao: false,
      };
    },

    retornaMensagemCodigoInvalido():string|void {
      const alfanumericoComLetraENumeroRegex = /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d]+$/;
      if (!this.item.codigoItem) {
        return "É necessário informar o código do item";
      } else if (!alfanumericoComLetraENumeroRegex.test(this.item.codigoItem)) {
        return "O código do item é deve ser alfanumérico, Exemplo: 16PLOE";
      } else if (this.item.codigoItem.length != 7) {
        return "O código do item deve conter 7 caracteres obrigatoriamente";
      }
    },
    retornaMensagemDescricaoInvalida():string|void {
      if (!this.item.descricao) {
        return "É necessário informar a descrição do item";
      } else if (this.item.descricao.length < 5) {
        return "A descrição do item deve ter mais de 5 caracteres";
      }
    },
    retornaMensagemLocalizacaoInvalida():string|void {
      if (!this.item.localizacao) {
        return "É necessário informar a localização do item";
      } else if (this.item.localizacao.length < 3) {
        return "A localização do item deve ter mais de 3 caracteres";
      } else if (this.item.localizacao.length > 250) {
        return "A localização do item não pode conter mais de 250 caracteres";
      }
    }
  },
});
</script>
