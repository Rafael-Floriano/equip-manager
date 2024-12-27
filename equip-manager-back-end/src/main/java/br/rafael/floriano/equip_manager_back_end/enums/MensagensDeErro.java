package br.rafael.floriano.equip_manager_back_end.enums;

public enum MensagensDeErro {

    CODIGO_ITEM_NULO("O código do item não pode ser nulo"),
    DESCRICAO_ITEM_NULA("A descrição do item não pode ser nula"),
    NUMERO_SERIE_NULO("O número de série não pode ser nulo"),
    LOCALIZACAO_NULA("A localização não pode ser nula"),
    CODIGO_ITEM_INVALIDO_DEVE_SER_ALFANUMERICO("O código do item deve ser alfanumérico"),
    CODIGO_ITEM_INVALIDO_DEVE_TER_SETE_CARACTERS("O código do item deve ter no máximo 7 caracteres"),
    CODIGO_ITEM_INVALIDO_NAO_PODE_SER_SEQUENCIAL("O código do item não pode ser sequencial");

    private final String message;

    MensagensDeErro(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
