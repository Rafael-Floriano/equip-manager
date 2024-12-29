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
    public ItemVisualizacaoDto create(ItemInventarioDto itemInventarioDto) {
        return itemInventarioService.criarItemInventario(
                itemInventarioDto
        );
    }

    @GetMapping("/all/{paginaAtual}")
    public Page<ItemVisualizacaoDto> getPagination(@PathVariable(name = "paginaAtual") Integer paginaAtual) {
        return itemInventarioService.buscaPagina(paginaAtual);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable("id") String codigoItem) {
        itemInventarioService.deletar(codigoItem);
    }

    @PutMapping
    public void deletar(@RequestBody ItemInventarioDto itemInventarioDto) {
        itemInventarioService.deletar(itemInventarioDto);
    }

}
