package org.example.naco.controller;

import org.example.naco.domain.Problem;
import org.example.naco.service.ProblemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/problems")
public class ProblemController {
    private final ProblemService service;

    public ProblemController(ProblemService service) {
        this.service = service;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String q,
                       @RequestParam(required = false) String status,
                       @RequestParam(required = false) String difficulty,
                       @RequestParam(required = false) String topic,
                       Model model) {
        model.addAttribute("items", service.list(q, status, difficulty, topic));
        model.addAttribute("q", q);
        model.addAttribute("status", status);
        model.addAttribute("difficulty", difficulty);
        model.addAttribute("topic", topic);
        return "problem/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("problem", new Problem());
        return "problem/form";
    }

    @PostMapping
    public String create(@ModelAttribute Problem problem) {
        service.create(problem);
        return "redirect:/problems";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("problem", service.get(id));
        return "problem/form";
    }

    @GetMapping("/{id}/timer")
    public String timer(@PathVariable Long id, Model model) {
        model.addAttribute("problem", service.get(id));
        return "problem/timer";
    }

    @PostMapping("/{id}/finish")
    public String finish(@PathVariable Long id, @RequestParam int solvedTimeSec) {
        service.finishSolve(id, solvedTimeSec);
        return "redirect:/problems";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Problem problem) {
        problem.setId(id);
        service.update(problem);
        return "redirect:/problems";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/problems";
    }

    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam String status) {
        service.updateStatus(id, status);
        return "redirect:/problems";
    }

    @PostMapping("/{id}/time")
    public String updateTime(@PathVariable Long id, @RequestParam int solvedTimeSec) {
        service.updateSolvedTime(id, solvedTimeSec);
        return "redirect:/problems";
    }
}
