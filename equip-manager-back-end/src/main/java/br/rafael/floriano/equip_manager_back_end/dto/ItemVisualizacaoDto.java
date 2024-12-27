package br.rafael.floriano.equip_manager_back_end.dto;

import br.rafael.floriano.equip_manager_back_end.enums.Disponibilidade;
import br.rafael.floriano.equip_manager_back_end.enums.Status;

public record ItemVisualizacaoDto(
        String codigoItem,
        String numeroDeSerie,
        Disponibilidade disponibilidade,
        Status status,
        String descricao,
        String localizacao
) {
}
