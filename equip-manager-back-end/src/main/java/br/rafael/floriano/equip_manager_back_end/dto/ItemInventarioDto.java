package br.rafael.floriano.equip_manager_back_end.dto;

public record ItemInventarioDto(
        String codigoItem,
        String numeroDeSerie,
        String descricao,
        String localizacao
) {
}
