package br.rafael.floriano.equip_manager_back_end.enums;

public enum MensagensDeErro {

    CODIGO_ITEM_NULO("O código do item não pode ser nulo"),
    DESCRICAO_ITEM_NULA("A descrição do item não pode ser nula"),
    NUMERO_SERIE_NULO("O número de série não pode ser nulo"),
    LOCALIZACAO_NULA("A localização não pode ser nula");

    private final String message;

    MensagensDeErro(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
