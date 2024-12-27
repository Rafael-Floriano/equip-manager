package br.rafael.floriano.equip_manager_back_end.enums;

public enum Disponibilidade {

    DISPONIVEL('D'),
    INDISPONIVEL('I');

    private char value;

    Disponibilidade(final char value) {
        this.value = value;
    }

}
