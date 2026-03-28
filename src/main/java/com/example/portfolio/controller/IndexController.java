package com.example.portfolio.controller;

import com.example.portfolio.model.ContactMessage;
import com.example.portfolio.repository.ContactMessageRepository;
import com.example.portfolio.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    private final ProjectService projectService;
    private final ContactMessageRepository contactMessageRepository;

    @Autowired
    public IndexController(ProjectService projectService, ContactMessageRepository contactMessageRepository) {
        this.projectService = projectService;
        this.contactMessageRepository = contactMessageRepository;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(value = "success", required = false) String success) {
        model.addAttribute("projects", projectService.getAllProjects());
        if (success != null) {
            model.addAttribute("successMessage", "Harika! Mesajınız başarıyla veritabanına kaydedildi. En kısa sürede dönüş yapacağım.");
        }
        return "index";
    }

    @PostMapping("/contact")
    public String submitContact(@RequestParam String name, @RequestParam String email, @RequestParam String message) {
        contactMessageRepository.save(new ContactMessage(name, email, message));
        return "redirect:/?success=true#contact";
    }
}
