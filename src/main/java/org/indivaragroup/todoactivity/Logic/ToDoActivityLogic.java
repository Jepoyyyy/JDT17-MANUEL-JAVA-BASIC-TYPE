package org.indivaragroup.todoactivity.Logic;

import org.indivaragroup.todoactivity.DTO.ToDoActivityDTO;
import java.time.LocalDate;

public class ToDoActivityLogic {
    private String id;
    private String project;
    private String title;
    private String description;
    private String assignee;
    private String priority;
    private double estimatedHour;
    private double actualHour;
    private double weight;
    private String reviewer;
    private LocalDate dueDate;
    private int totalSubtasks;
    private int completedSubtasks;

    // Status Case
    private String status;
    private boolean approvedByReviewer;
    private boolean submittedByTeamLead;

    public ToDoActivityLogic(ToDoActivityDTO dto) {
        this.id = dto.id;
        this.project = dto.project;
        this.title = dto.title;
        this.description = dto.description;
        this.assignee = dto.assignee;
        this.priority = dto.priority;
        this.estimatedHour = dto.estimatedHour;
        this.weight = dto.weight;
        this.reviewer = dto.reviewer;
        this.dueDate = dto.dueDate;
        this.totalSubtasks = dto.totalSubtasks;
        this.completedSubtasks = dto.completedSubtasks;

        // Default values
        this.status = "Open";
        this.approvedByReviewer = false;
        this.submittedByTeamLead = false;
        this.actualHour = 0.0;
    }

    // GETTER SETTER
    public String getId() { return id; }
    public String getProject() { return project; }
    public String getTitle() { return title; }
    public String getAssignee() { return assignee; }
    public double getEstimatedHour() { return estimatedHour; }
    public double getActualHour() { return actualHour; }
    public void setActualHour(double actualHour) { this.actualHour = actualHour; }
    public double getWeight() { return weight; }
    public String getReviewer() { return reviewer; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getTotalSubtasks() { return totalSubtasks; }
    public void setTotalSubtasks(int totalSubtasks) { this.totalSubtasks = totalSubtasks; }

    public int getCompletedSubtasks() { return completedSubtasks; }
    public void setCompletedSubtasks(int completedSubtasks) { this.completedSubtasks = completedSubtasks; }

    public boolean isApprovedByReviewer() { return approvedByReviewer; }
    public void setApprovedByReviewer(boolean approved) { this.approvedByReviewer = approved; }

    public boolean isSubmittedByTeamLead() { return submittedByTeamLead; }
    public void setSubmittedByTeamLead(boolean submitted) { this.submittedByTeamLead = submitted; }


    public double getProgressPercentage() {
        if (this.status.equalsIgnoreCase("Done")) return 100.0;
        if (this.status.equalsIgnoreCase("Cancelled")) return 0.0;

        double baseProgress = 0.0;
        if (this.totalSubtasks > 0) {
            baseProgress = ((double) this.completedSubtasks / this.totalSubtasks) * 100;
        } else {
            if (this.status.equalsIgnoreCase("In Progress")) baseProgress = 50.0;
            else if (this.status.equalsIgnoreCase("Ready Review")) baseProgress = 80.0;
        }

        //Lock The Progress Until Finished
        if (baseProgress >= 100.0) {
            return 80.0;
        }
        return baseProgress;
    }

    public String getSlaStatus() {
        if (this.actualHour == 0) return "N/A (Belum selesai)";
        if (this.actualHour > this.estimatedHour) return "DELAY (Melebihi Estimasi)";
        return "ON TRACK";
    }
}