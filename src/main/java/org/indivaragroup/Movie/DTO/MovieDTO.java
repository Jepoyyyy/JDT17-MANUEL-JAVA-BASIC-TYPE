package org.indivaragroup.Movie.DTO;

import java.util.ArrayList;

public class MovieDTO {
    String releaseCode;
    String releaseDate;

    public MovieDTO(String releaseCode, String releaseDate) {
        this.releaseDate = releaseDate;
        this.releaseCode = releaseCode;
    }

    // Getter untuk mengambil data
    public String getReleaseCode() {
        return releaseCode;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


    }

