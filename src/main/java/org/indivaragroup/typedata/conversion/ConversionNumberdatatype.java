package org.indivaragroup.typedata.conversion;

public class ConversionNumberdatatype {
    public static void NumberConversion() {
        byte byteValue =10;
        int intValue= byteValue;
        double doubleValue = intValue;

        System.out.println("Widening Casting:");
        System.out.println("Byte -> int = " + intValue);
        System.out.println(" ->double = " +doubleValue);

        double nilaiDouble =9.78;
        int nilaiInt = (int) nilaiDouble;
        byte nilaiByte = (byte) nilaiInt;

        System.out.println();
        System.out.println("Narrowing Casting:");
        System.out.println("double -> int = " + nilaiDouble);
        System.out.println("int ->double = " + nilaiByte);

    }
}
