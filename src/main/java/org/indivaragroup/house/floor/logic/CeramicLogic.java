package org.indivaragroup.house.floor.logic;
import org.indivaragroup.house.floor.dto.CeramicDTO;

public class CeramicLogic {
    public static void PrintCeramic() {


        CeramicDTO ceramic = new CeramicDTO();
        ceramic.setNameCeramic("MARMER");

        if (ceramic.getNameCeramic().equals("Batu")) {
            System.out.println("Ini adalah Marmer");
        }
        else  {
            System.out.println("Ini Bukan Marmer");
        }
    }
}
