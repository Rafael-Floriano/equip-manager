package br.rafael.floriano.equip_manager_back_end.services;

import br.rafael.floriano.equip_manager_back_end.dto.ItemInventarioDto;
import br.rafael.floriano.equip_manager_back_end.enums.MensagensDeErro;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

@Service
public class ItemInventarioService {

    public Object criarItemInventario(ItemInventarioDto itemInventarioDto) {
        Preconditions.checkNotNull(itemInventarioDto.codigoItem(), MensagensDeErro.CODIGO_ITEM_NULO.getMessage());
        Preconditions.checkNotNull(itemInventarioDto.descricao(), MensagensDeErro.DESCRICAO_ITEM_NULA.getMessage());
        Preconditions.checkNotNull(itemInventarioDto.numeroDeSerie(), MensagensDeErro.NUMERO_SERIE_NULO.getMessage());
        Preconditions.checkNotNull(itemInventarioDto.localizacao(), MensagensDeErro.LOCALIZACAO_NULA.getMessage());
        return null;
    }


}
