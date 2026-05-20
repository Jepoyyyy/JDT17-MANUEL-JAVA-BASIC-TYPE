package org.indivaragroup.student.grading.Logic;

import org.indivaragroup.student.grading.DTO.StudentsGradingDTO;

public class StudentGradingLogic {

    public static void PrintStudentGrading(String[] args) {

        String[] namaSiswa = {
                "Jordi El Nino", "Alex", "Budi", "Candra", "Dedi",
                "Eno", "Fahri", "Gandi", "Hendra", "Indra",
                "Joni", "Kevin", "Luki", "Mahendra", "Niko",
                "Oki", "Putra"
        };

        int[] nilaiSiswa = {
                84,99,100,75,90,70,80,90,94,88,17,89,94,50,96,80,70
        };

        StudentsGradingDTO[] dataSiswa = new StudentsGradingDTO[17];

        // isi DTO
        for (int i = 0; i < dataSiswa.length; i++) {

            dataSiswa[i] = new StudentsGradingDTO(
                    namaSiswa[i],
                    nilaiSiswa[i]
            );
        }

        // logic
        for (int i = 0; i < dataSiswa.length; i++) {

            int nilai = dataSiswa[i].getNilai();
            String nama = dataSiswa[i].getNama();
            String statusSiswa;

            if (nilai == 100) {
                statusSiswa = "JAGO BANGET";
            }
            else if (nilai >= 90) {
                statusSiswa = "BELUM JAGO BANGET, JAGO AJA";
            }
            else if (nilai >= 70) {
                statusSiswa = "BIASA AJA";
            }
            else {
                statusSiswa = "Belajar Lagi";
            }

            System.out.println(nama + " : " + statusSiswa);
        }
    }
}