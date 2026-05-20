package org.indivaragroup;

import org.indivaragroup.Movie.logic.MovieLogic;
import org.indivaragroup.house.floor.dto.CeramicDTO;
import org.indivaragroup.Calculation.CalculationRegistrationidentity;
import org.indivaragroup.house.floor.logic.CeramicLogic;
import org.indivaragroup.student.grading.Logic.StudentGradingLogic;
import org.indivaragroup.typedata.array.CalculationTypeDataArray;
import org.indivaragroup.typedata.conversion.ConversionNumberdatatype;
import org.indivaragroup.typedata.primitive.CalculationChardatatype;
import org.indivaragroup.typedata.primitive.CalculationTypeDataNonPrimitive;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int helloInteger = 25;
        System.out.println("HELLO, JDT-17"+ helloInteger);        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        char helloChar = 'H';
        System.out.println("HELLO, JDT-17"+ helloChar);
        double helloDouble = 25.5;
        System.out.println("HELLO, JDT-17"+ helloDouble);
        boolean helloBoolean = true;
        System.out.println("HELLO, JDT-17"+ helloBoolean);

        //1.ceramic
        CeramicDTO ceramicDTO = new CeramicDTO();
        ceramicDTO.setNameCeramic("MARMER");
        CeramicLogic ceramicLogic = new CeramicLogic();
        ceramicLogic.PrintCeramic();

        //2. Registration

        CalculationRegistrationidentity registration = new  CalculationRegistrationidentity();
        registration.PrintRegistration();

        //3.Student Grading

        StudentGradingLogic grading = new StudentGradingLogic();
        grading.PrintStudentGrading(args);

        //4.casting data number

        ConversionNumberdatatype conversion = new ConversionNumberdatatype();
        ConversionNumberdatatype.NumberConversion();

        //5.primitive data
        CalculationChardatatype charDat = new CalculationChardatatype();
        charDat.CharDataType();
        CalculationTypeDataNonPrimitive nonPrimitive = new CalculationTypeDataNonPrimitive();
        nonPrimitive.NonPrimitiveDataType();

        //6.array
        CalculationTypeDataArray arrayCal = new CalculationTypeDataArray();
        CalculationTypeDataArray.arrayCalcu();


        //7.Favorite Movie
        MovieLogic Movie = new MovieLogic();
        Movie.MoviePrint();

        //8.Todo Activity







    }
    }
