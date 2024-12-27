package br.rafael.floriano.equip_manager_back_end.services;

import br.rafael.floriano.equip_manager_back_end.dto.ItemInventarioDto;
import br.rafael.floriano.equip_manager_back_end.dto.ItemVisualizacaoDto;
import br.rafael.floriano.equip_manager_back_end.entity.InventoryItemEntity;
import br.rafael.floriano.equip_manager_back_end.mapper.ItemInventarioMapper;
import br.rafael.floriano.equip_manager_back_end.repository.ItemInventarioRepository;
import br.rafael.floriano.equip_manager_back_end.services.validadores.CodigoItemValidadorService;
import br.rafael.floriano.equip_manager_back_end.services.validadores.DescricaoItemValidadorService;
import br.rafael.floriano.equip_manager_back_end.services.validadores.LocalizacaoItemValidadorService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemInventarioService {

    @Autowired
    private CodigoItemValidadorService codigoItemValidadorService;
    @Autowired
    private DescricaoItemValidadorService descricaoItemValidadorService;
    @Autowired
    private LocalizacaoItemValidadorService localizacaoItemValidadorService;
    @Autowired
    private ItemInventarioRepository itemInventarioRepository;
    @Autowired
    private ItemInventarioMapper itemInventarioMapper;

    public ItemVisualizacaoDto criarItemInventario(ItemInventarioDto itemInventarioDto) {
        defaultValidations(itemInventarioDto);
        return itemInventarioMapper.toDto(
                itemInventarioRepository.save(
                        new InventoryItemEntity(
                                itemInventarioDto.codigoItem(),
                                itemInventarioDto.numeroDeSerie(),
                                itemInventarioDto.descricao(),
                                itemInventarioDto.localizacao()
                        )
                )
        );
    }

    public void defaultValidations(final ItemInventarioDto itemInventarioDto) {
        codigoItemValidadorService.codigoItemIsValid(itemInventarioDto.codigoItem());
        descricaoItemValidadorService.descricaoItemIsValid(itemInventarioDto.descricao());
        localizacaoItemValidadorService.localizacaoItemIsValid(itemInventarioDto.localizacao());
    }



}
