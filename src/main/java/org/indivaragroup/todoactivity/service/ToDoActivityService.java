package org.indivaragroup.todoactivity.service;

import org.indivaragroup.todoactivity.DTO.ToDoActivityDTO;
import org.indivaragroup.todoactivity.Logic.ToDoActivityLogic;
import org.indivaragroup.todoactivity.repository.ToDoActivityRepository;

import java.util.List;
import java.util.ArrayList; // Wajib di-import agar dikenali Java

public class ToDoActivityService {
    //Repository Initialized
    private final ToDoActivityRepository repository;
    //Assigning Max Weekly WorkLoad
    private static final double MAX_WEEKLY_CAPACITY = 50.0;

    public ToDoActivityService(ToDoActivityRepository repository) {
        this.repository = repository;
    }


    public boolean createTask(ToDoActivityDTO dto) {
        double currentWorkload = calculateAssigneeWorkload(dto.assignee);
        if (currentWorkload + dto.estimatedHour > MAX_WEEKLY_CAPACITY) {
            System.out.printf("Gagal: Beban kerja %s melebihi kapasitas maksimal (Maks: %.0f jam, Saat ini: %.1f jam).\n",
                    dto.assignee, MAX_WEEKLY_CAPACITY, currentWorkload);
            return false;
        }
        ToDoActivityLogic task = new ToDoActivityLogic(dto);
        repository.save(task);
        return true;
    }

    public String transitStatus(String id, String newStatus, String actorRole) {
        ToDoActivityLogic task = repository.findById(id);
        if (task == null) return "NOT_FOUND";

        String currentStatus = task.getStatus();

        //IF COndition When Canceled on Done State
        if (newStatus.equalsIgnoreCase("Cancelled")) {
            if (currentStatus.equalsIgnoreCase("Done")) return "CANNOT_CANCEL_DONE_TASK";
            task.setStatus("Cancelled");
            return "SUCCESS";
        }
        //Make Sure Step After Open Is On Progress
        if (currentStatus.equalsIgnoreCase("Open")) {
            if (newStatus.equalsIgnoreCase("In Progress")) {
                task.setStatus("In Progress");
                return "SUCCESS";
            }
            return "INVALID_TRANSITION_FROM_OPEN (Harus ke In Progress)";
        }

        //Make Sure Status After In Progress is Ready Review
        if (currentStatus.equalsIgnoreCase("In Progress")) {
            if (newStatus.equalsIgnoreCase("Ready Review")) {
                task.setStatus("Ready Review");
                return "SUCCESS";
            }
            return "INVALID_TRANSITION_FROM_IN_PROGRESS (Harus ke Ready Review)";
        }

        //Make Done Status Have Boundaries To be True
        if (currentStatus.equalsIgnoreCase("Ready Review")) {
            if (newStatus.equalsIgnoreCase("Done")) {
                if (!actorRole.equalsIgnoreCase("PM")) return "ONLY_PM_CAN_DONE";
                if (!task.isApprovedByReviewer()) return "NEED_REVIEWER_APPROVAL";
                if (!task.isSubmittedByTeamLead()) return "NEED_TEAM_LEAD_SUBMISSION";
                if (task.getActualHour() <= 0) return "NEED_ACTUAL_HOUR_INPUT";

                double currentWl = calculateAssigneeWorkload(task.getAssignee());
                if (currentWl > MAX_WEEKLY_CAPACITY) return "WORKLOAD_OVERLOAD";

                task.setStatus("Done");
                return "SUCCESS";
            }
            return "INVALID_TRANSITION_FROM_READY_REVIEW";
        }

        if (currentStatus.equalsIgnoreCase("Done") || currentStatus.equalsIgnoreCase("Cancelled")) {
            return "FINAL_STATUS_CANNOT_CHANGE";
        }
        return "INVALID_TRANSITION";
    }

    public boolean approveByReviewer(String id, String reviewerName) {
        ToDoActivityLogic task = repository.findById(id);

        if (task != null && task.getReviewer().trim().equalsIgnoreCase(reviewerName.trim())) {
            String status = task.getStatus();
            if (!status.equalsIgnoreCase("Ready Review")) {
                return false;
            }
            if (task.getTotalSubtasks() > 0 && task.getCompletedSubtasks() < task.getTotalSubtasks()) {
                System.out.println("Gagal: Subtask belum selesai sepenuhnya.");
                return false;
            }
            task.setApprovedByReviewer(true);
            return true;
        }
        return false;
    }

    public boolean submitByTeamLead(String id) {
        ToDoActivityLogic task = repository.findById(id);
        if (task != null && task.getStatus().equalsIgnoreCase("Ready Review")) {
            task.setSubmittedByTeamLead(true);
            return true;
        }
        return false;
    }

    public boolean updateSubtaskProgress(String id, int total, int completed) {
        ToDoActivityLogic task = repository.findById(id);
        if (task != null && total >= completed && total >= 0 && completed >= 0) {
            task.setTotalSubtasks(total);
            task.setCompletedSubtasks(completed);
            return true;
        }
        return false;
    }

    public boolean inputActualHour(String id, double hours) {
        ToDoActivityLogic task = repository.findById(id);
        if (task != null) {
            task.setActualHour(hours);
            return true;
        }
        return false;
    }

    public List<ToDoActivityLogic> getAllTasks() {
        return repository.findAll();
    }

    public double calculateAssigneeWorkload(String assignee) {
        double totalWorkload = 0;
        for (ToDoActivityLogic task : repository.findAll()) {
            if (task.getAssignee().trim().equalsIgnoreCase(assignee.trim())) {
                String status = task.getStatus().trim();
                if (status.equalsIgnoreCase("Open") || status.equalsIgnoreCase("In Progress") || status.equalsIgnoreCase("Ready Review")) {
                    totalWorkload += task.getEstimatedHour();
                }
            }
        }
        return totalWorkload;
    }

    public List<String> getUniqueProjects() {
        ArrayList<String> projects = new ArrayList<>();
        for (ToDoActivityLogic task : repository.findAll()) {
            String projectName = task.getProject().trim();
            boolean isExist = false;
            for (String p : projects) {
                if (p.equalsIgnoreCase(projectName)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) projects.add(projectName);
        }
        return projects;
    }

    public double calculateProjectProgress(String projectName) {
        double totalWeightAll = 0;
        double totalWeightDone = 0;

        for (ToDoActivityLogic task : repository.findAll()) {
            if (task.getProject().trim().equalsIgnoreCase(projectName.trim())) {
                if (task.getStatus().equalsIgnoreCase("Cancelled")) continue;

                totalWeightAll += task.getWeight();
                totalWeightDone += (task.getWeight() * (task.getProgressPercentage() / 100.0));
            }
        }

        if (totalWeightAll == 0) return 0;
        return (totalWeightDone / totalWeightAll) * 100;
    }

    public void displayPerformanceReport() {
        System.out.println("\n=== PERFORMANCE REPORT ===");
        List<String> allAssignees = new ArrayList<>();
        for (ToDoActivityLogic task : repository.findAll()) {
            String name = task.getAssignee().trim();
            boolean isExist = false;
            for (String a : allAssignees) {
                if (a.equalsIgnoreCase(name)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) allAssignees.add(name);
        }

        for (String pic : allAssignees) {
            System.out.println("\nNama PIC: " + pic);
            boolean hasFinishedTask = false;

            for (ToDoActivityLogic task : repository.findAll()) {
                if (task.getAssignee().trim().equalsIgnoreCase(pic)) {
                    if (task.getActualHour() == 0) continue;

                    hasFinishedTask = true;
                    double variance = task.getActualHour() - task.getEstimatedHour();
                    double productivity = (task.getEstimatedHour() / task.getActualHour()) * 100;

                    System.out.printf("  - Task ID: %s | Title: %s\n", task.getId(), task.getTitle());
                    if (variance == 0) System.out.printf("    * Variance: 0.0 jam\n");
                    else System.out.printf("    * Variance: %+.1f jam\n", variance);
                    System.out.printf("    * Productivity: %.1f%%\n", productivity);
                    System.out.printf("    * SLA Status: %s\n", task.getSlaStatus());
                }
            }

            if (!hasFinishedTask) {
                System.out.println("  - [Belum ada data pengerjaan / Actual Hour masih kosong]");
            }
        }
    }
}