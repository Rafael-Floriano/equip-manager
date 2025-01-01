package br.rafael.floriano.equip_manager_back_end.enums;

public enum Status {

    ATIVO('A'),
    INATIVO('I');

    private char valor;

    Status(char valor) {
        this.valor = valor;
    }

    public char getValue() {
        return valor;
    }

    public static Status fromValueToEnum(char value) {
        if (value == 'A') {
            return ATIVO;
        } else if (value == 'I') {
            return INATIVO;
        }
        throw new IllegalArgumentException("Não foi informado um status compativel");
    }

}
