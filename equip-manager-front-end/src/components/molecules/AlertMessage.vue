<template>
    <div v-if="visible" class="confirmation-dialog alert alert-danger alert-dismissible fade show" role="alert">
      <div class="dialog-content">
        <strong>Oops!</strong> 
      <p class="user-select-auto">{{ mensagem }}</p>
      <button type="button" class="btn btn-warning" @click="closeAlert" aria-label="Close">Fechar</button>
      </div>
    </div>
</template>
  
<script>
  export default {
    props: {
      mensagem: String,
    },
    data() {
      return {
        visible: true,
      };
    },
    methods: {
      closeAlert() {
        this.visible = false;
        this.$emit('close');
      },
    },
  };
</script>

<style>
  .confirmation-dialog {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
  }
  
  .dialog-content {
    background-color: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
    text-align: center;
  }
  
  button {
    margin: 5px;
    padding: 10px 20px;
    font-size: 16px;
  }
</style>
