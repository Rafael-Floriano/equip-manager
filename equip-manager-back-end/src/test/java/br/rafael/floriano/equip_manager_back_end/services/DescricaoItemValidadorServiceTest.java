package br.rafael.floriano.equip_manager_back_end.services;

import br.rafael.floriano.equip_manager_back_end.services.validadores.DescricaoItemValidadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DescricaoItemValidadorServiceTest {

    private DescricaoItemValidadorService service;

    @BeforeEach
    void setUp() {
        service = new DescricaoItemValidadorService();
    }

    @Test
    void testDescricaoItemNula() {
        String descricaoItem = null;
        assertThrows(NullPointerException.class, () -> service.descricaoItemIsValid(descricaoItem));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent vestibulum feugiat auctor"
    })
    void testDescricaoItemTamanhoValido(String descricaoItem) {
        assertDoesNotThrow(() -> service.descricaoItemIsValid(descricaoItem));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent vestibulum feugiat auctor. Curabitur egestas. Donec vehicula euismod."
    })
    void testDescricaoItemTamanhoInvalido(String descricaoItem) {
        assertThrows(IllegalArgumentException.class, () -> service.descricaoItemIsValid(descricaoItem));
    }

}
