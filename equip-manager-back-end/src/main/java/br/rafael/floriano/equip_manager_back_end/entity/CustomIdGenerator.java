package br.rafael.floriano.equip_manager_back_end.entity;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.text.DecimalFormat;

public class CustomIdGenerator implements IdentifierGenerator {

    private static final String SEQUENCE_NAME = "numero_serie_seq";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Long sequenceValue = (Long) session.createNativeQuery("SELECT nextval('numero_serie_seq')").getSingleResult();
        return new DecimalFormat("00000").format(sequenceValue);
    }

}
