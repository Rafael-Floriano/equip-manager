package br.rafael.floriano.equip_manager_back_end.enums;

public enum Status {

    ATIVO('A'),
    INATIVO('I');

    private char valor;

    Status(final char valor) {
        this.valor = valor;
    }

}
