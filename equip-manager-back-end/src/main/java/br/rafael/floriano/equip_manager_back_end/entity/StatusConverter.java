package br.rafael.floriano.equip_manager_back_end.entity;

import br.rafael.floriano.equip_manager_back_end.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, Character> {

    @Override
    public Character convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }

    @Override
    public Status convertToEntityAttribute(Character dbData) {
        if (dbData == null) {
            return null;
        }
        for (Status status : Status.values()) {
            if (status.getValue() == dbData) {
                return status;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Status: " + dbData);
    }
}
