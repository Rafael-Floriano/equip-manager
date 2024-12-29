package br.rafael.floriano.equip_manager_back_end.services;

import br.rafael.floriano.equip_manager_back_end.dto.ItemInventarioDto;
import br.rafael.floriano.equip_manager_back_end.dto.ItemVisualizacaoDto;
import br.rafael.floriano.equip_manager_back_end.entity.InventoryItemEntity;
import br.rafael.floriano.equip_manager_back_end.mapper.ItemInventarioMapper;
import br.rafael.floriano.equip_manager_back_end.repository.ItemInventarioRepository;
import br.rafael.floriano.equip_manager_back_end.services.validadores.CodigoItemValidadorService;
import br.rafael.floriano.equip_manager_back_end.services.validadores.DescricaoItemValidadorService;
import br.rafael.floriano.equip_manager_back_end.services.validadores.LocalizacaoItemValidadorService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public Page<ItemVisualizacaoDto> buscaPagina(int paginaAtual) {
        return itemInventarioMapper.toDtoPagination(
                itemInventarioRepository.findAll(
                        PageRequest.of(paginaAtual,15)
                )
        );
    }

    public void deletar(final String codigoItem) {
        if (codigoItem == null || codigoItem.isBlank()) {
            throw new IllegalArgumentException("Deve ser informado o código do item para realizar a exclução do mesmo");
        }
        itemInventarioRepository.setStatusEqualsInativo(codigoItem);
    }

    public ItemVisualizacaoDto atualizar(final ItemInventarioDto itemInventarioDto) {
        defaultValidations(itemInventarioDto);

    }

}
