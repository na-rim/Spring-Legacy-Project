package org.example.naco.controller;

import org.example.naco.domain.Task;
import org.example.naco.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("tasks", taskService.findAll());
        return "task/list";
    }

    @PostMapping
    public String create(@RequestParam String title) {
        if (title != null) {
            title = title.trim();
        }
        if (title != null && !title.isEmpty()) {
            taskService.create(title);
        }
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/toggle")
    public String toggle(@PathVariable Long id) {
        taskService.toggleDone(id);
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Task task = taskService.findById(id);
        if (task == null) {
            return "redirect:/tasks";
        }
        model.addAttribute("task", task);
        return "task/form";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id,
                       @RequestParam String title,
                       @RequestParam(defaultValue = "false") boolean done) {
        if (title != null) {
            title = title.trim();
        }
        if (title != null && !title.isEmpty()) {
            taskService.update(id, title, done);
        }
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }
}
