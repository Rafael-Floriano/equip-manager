package br.rafael.floriano.equip_manager_back_end.mapper;

import br.rafael.floriano.equip_manager_back_end.dto.ItemVisualizacaoDto;
import br.rafael.floriano.equip_manager_back_end.entity.InventoryItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemInventarioMapper {

    public ItemVisualizacaoDto toDto(InventoryItemEntity inventoryItemEntity) {
        return new ItemVisualizacaoDto(
                inventoryItemEntity.getCodigoItem(),
                inventoryItemEntity.getNumeroDeSerie(),
                inventoryItemEntity.getDisponibilidade(),
                inventoryItemEntity.getStatus(),
                inventoryItemEntity.getDescricao(),
                inventoryItemEntity.getLocalizacao()
        );
    }

}
