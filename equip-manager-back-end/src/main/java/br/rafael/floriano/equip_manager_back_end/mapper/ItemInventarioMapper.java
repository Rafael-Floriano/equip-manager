package br.rafael.floriano.equip_manager_back_end.mapper;

import br.rafael.floriano.equip_manager_back_end.dto.ItemVisualizacaoDto;
import br.rafael.floriano.equip_manager_back_end.entity.DisponibilidadeConverter;
import br.rafael.floriano.equip_manager_back_end.entity.InventoryItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemInventarioMapper {

    public ItemVisualizacaoDto toDto(InventoryItemEntity inventoryItemEntity) {
        return new ItemVisualizacaoDto(
                inventoryItemEntity.getCodigoItem(),
                inventoryItemEntity.getNumeroDeSerie(),
                inventoryItemEntity.getDisponibilidade().getValue(),
                inventoryItemEntity.getStatus().getValue(),
                inventoryItemEntity.getDescricao(),
                inventoryItemEntity.getLocalizacao()
        );
    }

    public Page<ItemVisualizacaoDto> toDtoPagination(Page<InventoryItemEntity> inventoryItemEntityPage) {
        final List<ItemVisualizacaoDto> dtoList = inventoryItemEntityPage.getContent().stream()
                .map(this::toDto)
                .toList();
        return new PageImpl<>(dtoList, inventoryItemEntityPage.getPageable(), inventoryItemEntityPage.getTotalElements());
    }

}
