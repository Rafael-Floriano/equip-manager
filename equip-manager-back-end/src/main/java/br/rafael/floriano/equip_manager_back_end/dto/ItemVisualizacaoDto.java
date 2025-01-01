package br.rafael.floriano.equip_manager_back_end.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record ItemVisualizacaoDto(
        String codigoItem,
        String numeroDeSerie,
        char disponibilidade,
        char status,
        String descricao,
        String localizacao,
        LocalDateTime dataMovimentacao
) {
}
