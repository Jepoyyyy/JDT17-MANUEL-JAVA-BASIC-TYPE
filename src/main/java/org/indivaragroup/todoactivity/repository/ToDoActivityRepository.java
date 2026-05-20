package org.indivaragroup.todoactivity.repository;

import org.indivaragroup.todoactivity.Logic.ToDoActivityLogic;
import java.util.ArrayList;
import java.util.List;

public class ToDoActivityRepository {
    private List<ToDoActivityLogic> tasks = new ArrayList<>();

    public void save(ToDoActivityLogic task) {
        tasks.add(task);
    }

    public List<ToDoActivityLogic> findAll() {
        return tasks;
    }

    public ToDoActivityLogic findById(String id) {
        for (ToDoActivityLogic task : tasks) {
            if (task.getId().equalsIgnoreCase(id.trim())) {
                return task;
            }
        }
        return null;
    }
}