package org.indivaragroup.todoactivity.DTO;

import java.time.LocalDate;

public class ToDoActivityDTO {
    public String id;
    public String project;
    public String title;
    public String description;
    public String assignee;
    public String priority;
    public double estimatedHour;
    public double weight;
    public String reviewer;
    public LocalDate dueDate;
    public int totalSubtasks;
    public int completedSubtasks;

    public ToDoActivityDTO(String id, String project, String title, String description, String assignee,
                           String priority, double estimatedHour, double weight, String reviewer,
                           LocalDate dueDate, int totalSubtasks, int completedSubtasks) {
        this.id = id;
        this.project = project;
        this.title = title;
        this.description = description;
        this.assignee = assignee;
        this.priority = priority;
        this.estimatedHour = estimatedHour;
        this.weight = weight;
        this.reviewer = reviewer;
        this.dueDate = dueDate;
        this.totalSubtasks = totalSubtasks;
        this.completedSubtasks = completedSubtasks;
    }
}