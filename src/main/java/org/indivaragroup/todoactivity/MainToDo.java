package org.indivaragroup.todoactivity;

import org.indivaragroup.todoactivity.DTO.ToDoActivityDTO;
import org.indivaragroup.todoactivity.Logic.ToDoActivityLogic;
import org.indivaragroup.todoactivity.repository.ToDoActivityRepository;
import org.indivaragroup.todoactivity.service.ToDoActivityService;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

public class MainToDo {
    public static void TodoMainApp(String[] args) {
        //Initialized
        ToDoActivityRepository repository = new ToDoActivityRepository();
        ToDoActivityService service = new ToDoActivityService(repository);
        Scanner scanner = new Scanner(System.in);

        //Dummy Data
        service.createTask(new ToDoActivityDTO("T1", "Project A", "Backend API", "Buat API", "Budi", "High", 8, 40, "Alice", LocalDate.now().plusDays(5), 0, 0));
        service.createTask(new ToDoActivityDTO("T2", "Project A", "Frontend UI", "Buat Slice", "Siti", "Medium", 6, 30, "Alice", LocalDate.now().plusDays(3), 0, 0));
        service.createTask(new ToDoActivityDTO("T3", "Project B", "Database Design", "Buat ERD", "Andi", "High", 5, 100, "Alice", LocalDate.now().plusDays(2), 0, 0));

        //Menu
        while (true) {
            System.out.println("\n=================================");
            System.out.println("   TO DO LIST MANAGEMENT SYSTEM  ");
            System.out.println("=================================");
            System.out.println("1. Tambah Task Baru (PM Action)");
            System.out.println("2. Lihat Semua Task, Workload & Progress Project");
            System.out.println("3. Update Status Task (Assignee Action)");
            System.out.println("4. Update Progress Subtask Task");
            System.out.println("5. Approve Task (Reviewer Action)");
            System.out.println("6. Diajukan / Propose Task (Team Lead Action)");
            System.out.println("7. Setujui & Selesaikan Task / DONE (PM Action)");
            System.out.println("8. Input Actual Hour Pekerjaan");
            System.out.println("9. Lihat Laporan Performa");
            System.out.println("10. Keluar");
            System.out.print("Pilih menu (1-10): ");

            //Scanner
            int menu = scanner.nextInt();
            scanner.nextLine();

            //Menu Per Choice
            switch (menu) {
                case 1:
                    System.out.print("Task ID: "); String id = scanner.nextLine();
                    System.out.print("Project Name: "); String proj = scanner.nextLine();
                    System.out.print("Title: "); String title = scanner.nextLine();
                    System.out.print("Description: "); String desc = scanner.nextLine();
                    System.out.print("Assignee/PIC: "); String pic = scanner.nextLine();
                    System.out.print("Priority: "); String prior = scanner.nextLine();
                    System.out.print("Estimated Hour: "); double est = scanner.nextDouble();
                    System.out.print("Weight (Bobot): "); double weight = scanner.nextDouble();
                    System.out.print("Due Date (Hari ke depan, misal: 5): "); int days = scanner.nextInt();
                    System.out.print("Total Subtasks (Ketik 0 jika tidak ada): "); int totSub = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Reviewer Name: "); String rev = scanner.nextLine();

                    boolean success = service.createTask(new ToDoActivityDTO(id, proj, title, desc, pic, prior, est, weight, rev, LocalDate.now().plusDays(days), totSub, 0));
                    if (success) System.out.println("Status: Task sukses ditambahkan oleh PM!");
                    break;

                case 2:
                    System.out.println("\n--- DAFTAR TASK ---");
                    Set<String> activePics = new HashSet<>();

                    for (ToDoActivityLogic t : service.getAllTasks()) {
                        activePics.add(t.getAssignee().trim());
                        double wlDinamis = service.calculateAssigneeWorkload(t.getAssignee());

                        System.out.printf("[%s] Project: %s | Title: %s | PIC: %s (Workload: %.1f/50 Jam) | Status: %s | Subtask: %d/%d | Rev Approved: %b | TL Submitted: %b | Progress: %.1f%%\n",
                                t.getId(), t.getProject(), t.getTitle(), t.getAssignee(), wlDinamis, t.getStatus(), t.getCompletedSubtasks(), t.getTotalSubtasks(), t.isApprovedByReviewer(), t.isSubmittedByTeamLead(), t.getProgressPercentage());
                    }

                    System.out.println("\n>> PROGRESS PER PROJECT (BOBOT) <<");
                    List<String> projectList = service.getUniqueProjects();
                    for (String pName : projectList) {
                        double projProgress = service.calculateProjectProgress(pName);
                        System.out.printf(" - %s : %.2f%%\n", pName, projProgress);
                    }

                    System.out.println("\n--- RINGKASAN WORKLOAD PIC ---");
                    for (String namePIC : activePics) {
                        double wl = service.calculateAssigneeWorkload(namePIC);
                        String infoBatas = (wl > 50.0) ? "[OVERLOAD!]" : "[AMAN]";
                        System.out.printf(" - %s: %.1f Jam / 50 Jam Maksimal %s\n", namePIC, wl, infoBatas);
                    }
                    break;

                case 3:
                    System.out.print("Masukkan Task ID: "); String uid = scanner.nextLine();
                    System.out.print("Status Baru (In Progress / Ready Review / Cancelled): "); String ustat = scanner.nextLine();

                    String res = service.transitStatus(uid, ustat, "Assignee");
                    if (res.equals("SUCCESS")) System.out.println("Status: Berhasil update status!");
                    else System.out.println("Error Terjadi: " + res);
                    break;

                case 4:
                    System.out.print("Masukkan Task ID: "); String subId = scanner.nextLine();
                    System.out.print("Total Subtask Baru: "); int tot = scanner.nextInt();
                    System.out.print("Jumlah Subtask Selesai (Done): "); int comp = scanner.nextInt();

                    if (service.updateSubtaskProgress(subId, tot, comp)) System.out.println("Status: Progress subtask berhasil diperbarui!");
                    else System.out.println("Error: Validasi angka subtask salah atau ID tidak ditemukan.");
                    break;

                case 5:
                    System.out.print("Masukkan Task ID: "); String apId = scanner.nextLine();
                    System.out.print("Nama Reviewer: "); String rName = scanner.nextLine();

                    if (service.approveByReviewer(apId, rName)) System.out.println("Status: Sukses! Reviewer memberikan approval.");
                    else System.out.println("Error: Nama reviewer tidak cocok dengan database atau status tidak valid!");
                    break;

                case 6:
                    System.out.print("Masukkan Task ID untuk diajukan Team Lead: "); String tlId = scanner.nextLine();
                    if (service.submitByTeamLead(tlId)) System.out.println("Status: Sukses! Team Lead berhasil mengajukan berkas ke PM.");
                    else System.out.println("Error: Gagal mengajukan! Pastikan status task sudah 'Ready Review'.");
                    break;

                case 7:
                    System.out.print("Masukkan Task ID yang disetujui Selesai: "); String pmId = scanner.nextLine();
                    String pmRes = service.transitStatus(pmId, "Done", "PM");
                    if (pmRes.equals("SUCCESS")) {
                        System.out.println("Status: Sukses! PM menyetujui Proyek dan status resmi 'Done' (100%). Workload PIC berkurang otomatis.");
                    } else {
                        System.out.println("Error Gagal Setuju PM: " + pmRes);
                    }
                    break;

                case 8:
                    System.out.print("Masukkan Task ID: "); String aid = scanner.nextLine();
                    System.out.print("Masukkan Actual Hour Terpakai: "); double hours = scanner.nextDouble();
                    if(service.inputActualHour(aid, hours)) System.out.println("Status: Actual hour dicatat!");
                    else System.out.println("Error: ID tidak ditemukan.");
                    break;

                case 9:
                    service.displayPerformanceReport();
                    break;

                case 10:
                    System.out.println("Sesi CLI berakhir.");
                    System.exit(0);

                default:
                    System.out.println("Menu salah!");
            }
        }
    }
}