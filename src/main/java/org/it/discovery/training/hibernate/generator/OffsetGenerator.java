package org.it.discovery.training.hibernate.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class OffsetGenerator implements IdentifierGenerator {

    private int offset = 1000;
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return offset++;
    }
}
