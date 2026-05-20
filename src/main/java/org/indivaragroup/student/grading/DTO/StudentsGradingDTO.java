package org.indivaragroup.student.grading.DTO;

public class StudentsGradingDTO {
    private String nama;
    private int nilai;

    // PERBAIKAN: Nama harus sama dengan Class dan hapus kata 'void'
    public StudentsGradingDTO(String nama, int nilai) {
        this.nama = nama;
        this.nilai = nilai;
    }

    public String getNama() {
        return nama;
    }

    public int getNilai() {
        return nilai;
    }
}