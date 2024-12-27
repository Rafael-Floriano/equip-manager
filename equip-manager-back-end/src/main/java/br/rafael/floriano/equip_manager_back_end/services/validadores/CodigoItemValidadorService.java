package br.rafael.floriano.equip_manager_back_end.services.validadores;

import br.rafael.floriano.equip_manager_back_end.enums.MensagensDeErro;
import com.google.common.base.Preconditions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodigoItemValidadorService {

    public void codigoItemIsValid(final String codigoItem) {
        Preconditions.checkNotNull(
                codigoItem,
                MensagensDeErro.CODIGO_ITEM_NULO.getMessage()
        );
        Preconditions.checkArgument(
                codigoItem.length() == 7,
                MensagensDeErro.CODIGO_ITEM_INVALIDO_DEVE_TER_SETE_CARACTERS.getMessage()
        );
        Preconditions.checkArgument(
                !isAlfanumerico(codigoItem),
                MensagensDeErro.CODIGO_ITEM_INVALIDO_DEVE_SER_ALFANUMERICO.getMessage()
        );
        Preconditions.checkArgument(
                !isSequencial(codigoItem),
                MensagensDeErro.CODIGO_ITEM_INVALIDO_NAO_PODE_SER_SEQUENCIAL.getMessage()
        );
    }

    public boolean isAlfanumerico(final String codigoItem) {
        return  (!codigoItem.matches("^(?=.*[a-zA-Z])(?=.*\\d).+$"));
    }

    public boolean isSequencial(String codigo) {
        if (!codigo.matches("\\d+")) {
            return false;
        }
        return isSequenciaNumerica(codigo);
    }

    private boolean isSequenciaNumerica(String codigo) {
        for (int i = 0; i < codigo.length() - 1; i++) {
            if (codigo.charAt(i) + 1 != codigo.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

}
