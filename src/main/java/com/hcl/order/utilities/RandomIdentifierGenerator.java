package com.hcl.order.utilities;

import java.io.Serializable;
import java.security.SecureRandom;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.stereotype.Component;


@Component
public class RandomIdentifierGenerator {

	private final static String label = "ORD";
	  private final static SecureRandom sr = new SecureRandom();

	  public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {
	    long val = sr.nextLong();
	    return label + Long.toString(Math.abs(val), Character.MAX_RADIX);
	  }
}
