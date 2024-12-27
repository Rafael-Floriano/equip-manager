package br.rafael.floriano.equip_manager_back_end.services;

import br.rafael.floriano.equip_manager_back_end.services.validadores.CodigoItemValidadorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CodigoItemValidadorServiceTest {

    @Autowired
    private CodigoItemValidadorService service;

    @ParameterizedTest
    @ValueSource(strings = {"AB68476", "684ABCD", "68467AB", "594ABCD"})
    void testCodigoItemValido(String codigoItem) {
        assertDoesNotThrow(() -> service.codigoItemIsValid(codigoItem));
    }

    @Test
    void testCodigoItemNulo() {
        String codigoItem = null;
        assertThrows(NullPointerException.class, () -> service.codigoItemIsValid(codigoItem));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123456", "12345678", "A1234"})
    void testCodigoItemComprimentoInvalido(String codigoItem) {
        assertThrows(IllegalArgumentException.class, () -> service.codigoItemIsValid(codigoItem));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234567", "abcdefg", "1111111"})
    void testCodigoItemNaoAlfanumerico(String codigoItem) {
        assertThrows(IllegalArgumentException.class, () -> service.codigoItemIsValid(codigoItem));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234567", "2345678", "3456789"})
    void testCodigoItemSequencial(String codigoItem) {
        assertThrows(IllegalArgumentException.class, () -> service.codigoItemIsValid(codigoItem));
    }

    @ParameterizedTest
    @ValueSource(strings = {"11A2B3C", "AB12345", "ZXY7890"})
    void testCodigoItemNaoSequencial(String codigoItem) {
        assertDoesNotThrow(() -> service.codigoItemIsValid(codigoItem));
    }

}
