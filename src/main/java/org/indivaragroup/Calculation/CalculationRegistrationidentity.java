package org.indivaragroup.Calculation;

public class CalculationRegistrationidentity {
    public static void PrintRegistration() {
    int uang = 10000;
    int biayaKtp =5000;


    if (uang >= 10000) {

        System.out.println("Kamu Harus Buat KTP");
        int total = uang - biayaKtp;
        System.out.println("Sisa Uang Anda = "+total);
    }
        else {

        System.out.println("Kamu Belum Harus Buat KTP");
    }
    }
}
