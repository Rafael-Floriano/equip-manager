package br.rafael.floriano.equip_manager_back_end.controller;

import br.rafael.floriano.equip_manager_back_end.dto.ItemInventarioDto;
import br.rafael.floriano.equip_manager_back_end.dto.ItemVisualizacaoDto;
import br.rafael.floriano.equip_manager_back_end.services.ItemInventarioService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
