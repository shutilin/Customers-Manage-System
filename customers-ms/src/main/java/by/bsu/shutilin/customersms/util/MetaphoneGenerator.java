package by.bsu.shutilin.customersms.util;

import org.apache.commons.codec.language.Metaphone;

public class MetaphoneGenerator {
    public static String getMetaphone(String data) {
        Metaphone met = new Metaphone();
        met.setMaxCodeLen(20);
        return met.encode(data);
    }

}
