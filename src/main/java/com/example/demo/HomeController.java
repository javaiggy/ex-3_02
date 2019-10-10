package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController
{
    @Autowired
    TaskRepository taskRepository;

    @RequestMapping("/")
    public String listToDoItems(Model model)
    {
        model.addAttribute("tasks", taskRepository.findAll() );
        return "list";
    }

    @GetMapping("/add")
    public String toDoForm(Model model)
    {
        model.addAttribute("task", new Task()  );
        return "todoform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Task task, BindingResult result)
    {
        if (result.hasErrors() )
        {
            return "todoform";
        }

        taskRepository.save(task);
        return "redirect:/";

    }
}
