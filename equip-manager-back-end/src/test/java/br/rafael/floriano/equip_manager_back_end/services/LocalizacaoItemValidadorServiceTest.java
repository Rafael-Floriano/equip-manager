package br.rafael.floriano.equip_manager_back_end.services;

import br.rafael.floriano.equip_manager_back_end.services.validadores.LocalizacaoItemValidadorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LocalizacaoItemValidadorServiceTest {

    private LocalizacaoItemValidadorService service;

    @BeforeEach
    void setUp() {
        service = new LocalizacaoItemValidadorService();
    }

    @Test
    void testLocalizacaoItemNula() {
        String localizacao = null;
        assertThrows(IllegalArgumentException.class, () -> service.localizacaoItemIsValid(localizacao));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ABC", "São Paulo", "Rio de Janeiro"})
    void testLocalizacaoItemTamanhoMinimoValido(String localizacao) {
        assertDoesNotThrow(() -> service.localizacaoItemIsValid(localizacao));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "A", "AB"})
    void testLocalizacaoItemTamanhoMinimoInvalido(String localizacao) {
        assertThrows(IllegalArgumentException.class, () -> service.localizacaoItemIsValid(localizacao));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua." // Exemplo com até 250 caracteres
    })
    void testLocalizacaoItemTamanhoMaximoValido(String localizacao) {
        assertDoesNotThrow(() -> service.localizacaoItemIsValid(localizacao));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nulla facilisi. Proin auctor.  Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Nulla facilisi. Proin auctor."
    })
    void testLocalizacaoItemTamanhoMaximoInvalido(String localizacao) {
        assertThrows(IllegalArgumentException.class, () -> service.localizacaoItemIsValid(localizacao));
    }

}
