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

    public static Disponibilidade fromValueToEnum(char value) {
        if (value == 'D') {
            return DISPONIVEL;
        } else if (value == 'I') {
            return INDISPONIVEL;
        }
        throw new IllegalArgumentException("Não foi informado uma disponibilidade compativel");
    }

}
