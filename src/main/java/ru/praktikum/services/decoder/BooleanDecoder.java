package ru.praktikum.services.decoder;

public class BooleanDecoder {
    public static boolean booleanDecoder(String value) {
        if (value.equalsIgnoreCase("false")) {
            return false;
        } else if (value.equalsIgnoreCase("true")) {
            return true;
        } else {
            System.out.println("String value not true or false");
            return false;
        }
    }
}
