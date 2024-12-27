package br.rafael.floriano.equip_manager_back_end.dto;

import java.time.LocalDateTime;

public record ItemInventarioDto(
        String codigoItem,
        String numeroDeSerie, String descricao,
        String localizacao, LocalDateTime dataMovimentacao
) {
}
