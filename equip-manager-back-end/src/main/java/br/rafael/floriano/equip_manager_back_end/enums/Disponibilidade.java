package br.rafael.floriano.equip_manager_back_end.enums;

public enum Disponibilidade {

    DISPONIVEL('D'),
    INDISPONIVEL('I');

    private char valor;

    Disponibilidade(char valor) {
        this.valor = valor;
    }

    public char getValue() {
        return valor;
    }

}
