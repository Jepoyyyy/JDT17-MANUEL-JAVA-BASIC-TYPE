package org.indivaragroup.typedata.array;

public class CalculationTypeDataArray {
    public static void arrayCalcu() {
        int[][] data ={
                {1,2,3},
                {4,5,6}
        };

        int[] nilai = new int[]{data[1][2]};
        System.out.println(nilai);
    }

}
