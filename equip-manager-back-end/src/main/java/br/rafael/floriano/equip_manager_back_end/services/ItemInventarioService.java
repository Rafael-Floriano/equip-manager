package br.rafael.floriano.equip_manager_back_end.services;

import br.rafael.floriano.equip_manager_back_end.dto.ItemInventarioDto;
import br.rafael.floriano.equip_manager_back_end.dto.ItemVisualizacaoDto;
import br.rafael.floriano.equip_manager_back_end.entity.InventoryItemEntity;
import br.rafael.floriano.equip_manager_back_end.mapper.ItemInventarioMapper;
import br.rafael.floriano.equip_manager_back_end.repository.ItemInventarioRepository;
import br.rafael.floriano.equip_manager_back_end.services.validadores.CodigoItemValidadorService;
import br.rafael.floriano.equip_manager_back_end.services.validadores.DescricaoItemValidadorService;
import br.rafael.floriano.equip_manager_back_end.services.validadores.LocalizacaoItemValidadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.rafael.floriano.equip_manager_back_end.enums.MensagensDeErro.CODIGO_ITEM_JA_ESTA_EM_USO;

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
        if (existeItemComCodigo(itemInventarioDto.codigoItem())) {
            throw new IllegalArgumentException(CODIGO_ITEM_JA_ESTA_EM_USO.getMessage());
        }
        return itemInventarioMapper.toDto(
                itemInventarioRepository.save(
                        new InventoryItemEntity(
                                itemInventarioDto.codigoItem(),
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

    public Page<ItemVisualizacaoDto> buscaPagina(String descricao, int pagina) {
        if (descricao == null || descricao.isBlank()) {
            return itemInventarioMapper.toDtoPagination(
                    itemInventarioRepository.findAll(
                            PageRequest.of(pagina,12)
                    )
            );
        }
        return itemInventarioMapper.toDtoPagination(
                itemInventarioRepository.findAllByDescricaoContainingIgnoreCase(
                        descricao,
                        PageRequest.of(pagina,12)
                )
        );
    }

    public ItemVisualizacaoDto buscarPorItemPeloNumeroDeSerie(String numeroDeSerie) {
        return itemInventarioMapper.toDto(
                itemInventarioRepository.findByNumeroDeSerie(numeroDeSerie).orElseThrow(IllegalArgumentException::new)
        );
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletar(final String numeroDeSerie) {
        if (numeroDeSerie == null || numeroDeSerie.isBlank()) {
            throw new IllegalArgumentException("Deve ser informado o código do item para realizar a exclução do mesmo");
        }
        itemInventarioRepository.softDeleteById(numeroDeSerie);
    }

    public boolean existeItemComCodigo(String codigoItem) {
        return itemInventarioRepository.existsByCodigoItem(codigoItem);
    }


//    public ItemVisualizacaoDto atualizar(final ItemInventarioDto itemInventarioDto) {
//        defaultValidations(itemInventarioDto);
//
//    }

}
