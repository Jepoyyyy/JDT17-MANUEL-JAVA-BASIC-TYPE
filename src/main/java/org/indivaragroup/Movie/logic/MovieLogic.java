package org.indivaragroup.Movie.logic;

import org.indivaragroup.Movie.DTO.MovieDTO;

import java.util.ArrayList;

public class MovieLogic {
    public static void MoviePrint() {

        // List object MovieDTO
        ArrayList<MovieDTO> daftarRilis = new ArrayList<>();

        // Tambah data
        daftarRilis.add(new MovieDTO("ACTN-1001", "2026-05-20"));
        daftarRilis.add(new MovieDTO("DRAM-2045", "2025-06-01"));
        daftarRilis.add(new MovieDTO("COMD-3099", "2024-01-15"));
        daftarRilis.add(new MovieDTO("HORR-4012", "2023-03-12"));
        daftarRilis.add(new MovieDTO("SCFI-5055", "2023-08-25"));
        daftarRilis.add(new MovieDTO("ADVN-6088", "2024-04-10"));
        daftarRilis.add(new MovieDTO("ROMN-7033", "2024-09-18"));
        daftarRilis.add(new MovieDTO("THRL-8010", "2024-12-25"));
        daftarRilis.add(new MovieDTO("MYST-9022", "2025-02-11"));
        daftarRilis.add(new MovieDTO("FANT-1099", "2025-04-05"));
        daftarRilis.add(new MovieDTO("DOCU-2050", "2025-07-22"));
        daftarRilis.add(new MovieDTO("ANIM-3066", "2025-10-30"));
        daftarRilis.add(new MovieDTO("KIDS-4077", "2025-11-15"));
        daftarRilis.add(new MovieDTO("CRIM-5034", "2026-01-10"));
        daftarRilis.add(new MovieDTO("BIOG-6091", "2026-03-05"));
        daftarRilis.add(new MovieDTO("HIST-7023", "2026-04-17"));
        daftarRilis.add(new MovieDTO("MUSC-8044", "2026-06-12"));
        daftarRilis.add(new MovieDTO("SPRT-9011", "2026-08-29"));
        daftarRilis.add(new MovieDTO("WEST-1055", "2026-11-01"));
        daftarRilis.add(new MovieDTO("EPIC-2088", "2026-12-20"));

        // Panggil function
        pisahkanBerdasarkanTahun(daftarRilis);
    }


    public static void pisahkanBerdasarkanTahun(ArrayList<MovieDTO> semuaData) {
        ArrayList<MovieDTO> rilis2026 = new ArrayList<>();
        ArrayList<MovieDTO> rilisLainnya = new ArrayList<>();

        for (MovieDTO data : semuaData) {

            String tahun = data.getReleaseDate().substring(0, 4);

            if (tahun.equals("2026")) {
                rilis2026.add(data);
            } else {
                rilisLainnya.add(data);
            }
        }

        System.out.println("=== 2026 ===");
        for (MovieDTO d : rilis2026) {
            System.out.println(d.getReleaseCode() + " -> " + d.getReleaseDate());
        }
    }
}
