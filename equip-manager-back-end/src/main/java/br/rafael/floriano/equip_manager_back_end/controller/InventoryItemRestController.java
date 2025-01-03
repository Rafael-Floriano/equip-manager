package br.rafael.floriano.equip_manager_back_end.controller;

import br.rafael.floriano.equip_manager_back_end.dto.ItemInventarioDto;
import br.rafael.floriano.equip_manager_back_end.dto.ItemVisualizacaoDto;
import br.rafael.floriano.equip_manager_back_end.services.ItemInventarioService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class InventoryItemRestController {

    private final ItemInventarioService itemInventarioService;

    public InventoryItemRestController(ItemInventarioService itemInventarioService) {
        this.itemInventarioService = itemInventarioService;
    }

    @PostMapping
    public ItemVisualizacaoDto create(@RequestBody ItemInventarioDto itemInventarioDto) {
        return itemInventarioService.criarItemInventario(
                itemInventarioDto
        );
    }

    @GetMapping
    public Page<ItemVisualizacaoDto> getPagination(@RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
                                                   @RequestParam(name = "descricao", required = false) String descricao,
                                                   @RequestParam(name = "qtdItens", defaultValue = "12") int qtdItens) {
        return itemInventarioService.buscaPagina(descricao, pagina, qtdItens);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") String numeroDeSerie) {
        itemInventarioService.deletar(numeroDeSerie);
    }

    @GetMapping("/{numeroDeSerie}")
    public ItemVisualizacaoDto findItemInventarioByNumeroDeSerie(@PathVariable("numeroDeSerie") String numeroDeSerie) {
        return itemInventarioService.buscarPorItemPeloNumeroDeSerie(numeroDeSerie);
    }

    @PutMapping
    public ItemVisualizacaoDto atualizar(@RequestParam("numeroDeSerie") String numeroDeSerie, @RequestBody ItemInventarioDto itemInventarioDto) {
        return itemInventarioService.atualizarItemPeloNumeroDeSerie(numeroDeSerie, itemInventarioDto);
    }

}
