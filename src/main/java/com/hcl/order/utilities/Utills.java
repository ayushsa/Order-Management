package com.hcl.order.utilities;

import java.security.SecureRandom;

public class Utills {

    private static volatile SecureRandom numberGenerator = null;
    private static final long MSB = 0x8000000000000000L;
    
    
    public static String unique() {
        SecureRandom ng = numberGenerator;
        if (ng == null) {
            numberGenerator = ng = new SecureRandom();
        }

        return Long.toHexString(MSB | ng.nextLong()) + Long.toHexString(MSB | ng.nextLong());
    }       
}
