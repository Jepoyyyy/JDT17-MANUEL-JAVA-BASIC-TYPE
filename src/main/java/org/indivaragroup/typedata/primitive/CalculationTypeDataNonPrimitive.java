package org.indivaragroup.typedata.primitive;

public class CalculationTypeDataNonPrimitive {
    public void NonPrimitiveDataType() {
        byte primitifByte = 10;
        short primitifShort = 20;
        int primitifInteger = 30;
        long primitifLong = 40L;
        float primitifFloat = 50.5f;
        double primitifDouble = 60.75;
        char primitifCharacter = 'A';
        boolean primitifBoolean = true;

        Byte wrapperByte = primitifByte;
        Short wrapperShort = primitifShort;
        Integer wrapperInteger = primitifInteger;
        Long wrapperLong = primitifLong;
        Float wrapperFloat = primitifFloat;
        Double wrapperDouble = primitifDouble;
        Character wrapperCharacter = primitifCharacter;
        Boolean wrapperBoolean = primitifBoolean;

        System.out.println("Primitif vs Wrapper Class");
        System.out.println("byte    -> Byte      : " + primitifByte + " / " + wrapperByte);
        System.out.println("short   -> Short     : " + primitifShort + " / " + wrapperShort);
        System.out.println("int     -> Integer   : " + primitifInteger + " / " + wrapperInteger);
        System.out.println("long    -> Long      : " + primitifLong + " / " + wrapperLong);
        System.out.println("float   -> Float     : " + primitifFloat + " / " + wrapperFloat);
        System.out.println("double  -> Double    : " + primitifDouble + " / " + wrapperDouble);
        System.out.println("char    -> Character : " + primitifCharacter + " / " + wrapperCharacter);
        System.out.println("boolean -> Boolean   : " + primitifBoolean + " / " + wrapperBoolean);
    }
}
