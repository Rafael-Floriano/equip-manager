package br.rafael.floriano.equip_manager_back_end.dto;

public record ItemVisualizacaoDto(
        String codigoItem,
        String numeroDeSerie,
        char disponibilidade,
        char status,
        String descricao,
        String localizacao
) {
}
