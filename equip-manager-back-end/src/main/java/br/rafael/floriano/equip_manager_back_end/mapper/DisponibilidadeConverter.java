package br.rafael.floriano.equip_manager_back_end.mapper;

import br.rafael.floriano.equip_manager_back_end.enums.Disponibilidade;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DisponibilidadeConverter implements AttributeConverter<Disponibilidade, Character> {

    @Override
    public Character convertToDatabaseColumn(Disponibilidade disponibilidade) {
        if (disponibilidade == null) {
            return null;
        }
        return disponibilidade.getValue();
    }

    @Override
    public Disponibilidade convertToEntityAttribute(Character dbData) {
        if (dbData == null) {
            return null;
        }
        for (Disponibilidade disponibilidade : Disponibilidade.values()) {
            if (disponibilidade.getValue() == dbData) {
                return disponibilidade;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Status: " + dbData);
    }

}
