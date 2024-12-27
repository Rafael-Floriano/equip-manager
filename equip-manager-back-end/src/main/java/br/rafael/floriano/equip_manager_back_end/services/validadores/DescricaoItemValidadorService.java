package br.rafael.floriano.equip_manager_back_end.services.validadores;

import br.rafael.floriano.equip_manager_back_end.enums.MensagensDeErro;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

@Service
public class DescricaoItemValidadorService {

    public void descricaoItemIsValid(final String descricaoItem) {
        Preconditions.checkNotNull(
                descricaoItem,
                MensagensDeErro.DESCRICAO_ITEM_NULA.getMessage()
        );
        Preconditions.checkArgument(
                descricaoItem.length() <= 100,
                MensagensDeErro.DESCRICAO_ITEM_TAMANHO_INVALIDO.getMessage()
        );
    }

}
