package com.mueez.oebsait.controllers;

import com.mueez.oebsait.services.MarkdownService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    private final MarkdownService markdownService;

    public MainController(MarkdownService markdownService) {
        this.markdownService = markdownService;
    }

    @GetMapping("/")
    public String home() {
        return "landing-page";
    }

    @GetMapping("/about")
    public String about() {
        return "about-me-page";
    }

    @GetMapping("/engineering")
    public String engineering(Model model) {
        // Load, parse, and convert the engineering.md file to HTML
        String engineeringHtmlContent = markdownService.loadAndRender("engineering-page-contents.md");

        model.addAttribute("engineeringContent", engineeringHtmlContent);
        // model.addAttribute("pageTitle", "Engineering Log: Low-Latency Systems");

        // Returns src/main/resources/templates/engineering.html
        return "engineering";
    }

    @GetMapping("/sports")
    public String sports() {
        return "default-error-page";
    }

    @GetMapping("/food")
    public String food() {
        return "default-error-page";
    }

    @GetMapping("/politics")
    public String politics() {
        return "default-error-page";
    }

    @GetMapping("/mueezings")
    public String mueezings() {
        return "default-error-page";
    }

    @GetMapping("/contact")
    public String contact() {
        return "default-error-page";
    }
}
