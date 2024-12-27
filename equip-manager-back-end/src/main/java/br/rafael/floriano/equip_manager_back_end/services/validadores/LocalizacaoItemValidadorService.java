package br.rafael.floriano.equip_manager_back_end.services.validadores;

import br.rafael.floriano.equip_manager_back_end.enums.MensagensDeErro;
import com.google.common.base.Preconditions;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoItemValidadorService {

    public void localizacaoItemIsValid(final String localizacao) {
        Preconditions.checkArgument(
                localizacao != null,
                MensagensDeErro.LOCALIZACAO_NULA.getMessage()
        );
        Preconditions.checkArgument(
                localizacao.length() >= 3,
                MensagensDeErro.LOCALIZACAO_ITEM_TAMANHO_MINIMO.getMessage()
        );
        Preconditions.checkArgument(
                localizacao.length() < 250,
                MensagensDeErro.LOCALIZACAO_ITEM_TAMANHO_MAXIMO.getMessage()
        );
    }

}
