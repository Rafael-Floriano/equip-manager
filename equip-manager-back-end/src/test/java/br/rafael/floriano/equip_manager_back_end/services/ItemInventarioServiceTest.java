package br.rafael.floriano.equip_manager_back_end.services;
import br.rafael.floriano.equip_manager_back_end.dto.ItemInventarioDto;
import br.rafael.floriano.equip_manager_back_end.enums.MensagensDeErro;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ItemInventarioServiceTest {

    @Autowired
    private ItemInventarioService itemInventarioService;

    @Nested
    class CriarItemInventarioTestes {

        private static Stream<TestArguments> provideInvalidFields() {
            return Stream.of(
                    new TestArguments(null, "Descrição", "12345", "Localização", MensagensDeErro.CODIGO_ITEM_NULO.getMessage()),
                    new TestArguments("AB12CD3", null, "12345", "Localização", MensagensDeErro.DESCRICAO_ITEM_NULA.getMessage()),
                    new TestArguments("AB12CD3", "Descrição", null, "Localização", MensagensDeErro.NUMERO_SERIE_NULO.getMessage()),
                    new TestArguments("AB12CD3", "Descrição", "12345", null, MensagensDeErro.LOCALIZACAO_NULA.getMessage())
            );
        }

        @ParameterizedTest
        @MethodSource("provideInvalidFields")
        void deveriaLancarUmaExceptionParaCamposInvalidos(TestArguments args) {

            NullPointerException exception = assertThrows(
                    NullPointerException.class,
                    () -> itemInventarioService.criarItemInventario(
                            new ItemInventarioDto(
                                    args.codigo,
                                    args.numeroSerie,
                                    args.descricao,
                                    args.localizacao,
                                    null
                            )
                    )
            );

            assertEquals(args.expectedMessage, exception.getMessage());
        }

        private static class TestArguments {
            String codigo;
            String descricao;
            String numeroSerie;
            String localizacao;
            String expectedMessage;

            TestArguments(String codigo, String descricao, String numeroSerie, String localizacao, String expectedMessage) {
                this.codigo = codigo;
                this.descricao = descricao;
                this.numeroSerie = numeroSerie;
                this.localizacao = localizacao;
                this.expectedMessage = expectedMessage;
            }
        }
    }

}
