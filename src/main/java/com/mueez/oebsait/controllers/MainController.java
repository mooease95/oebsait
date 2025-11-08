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

    @GetMapping(value = {"/", "/home"})
    public String home() {
        return "landing-page";
    }

    @GetMapping("/about")
    public String about() {
        return "about-me-page";
    }

    @GetMapping("/engineering")
    public String engineering(Model model) {
        // Load, parse, and convert the Markdown file to HTML
        String engineeringHtmlContent = markdownService.loadAndRender("engineering-page-contents.md");

        model.addAttribute("engineeringContent", engineeringHtmlContent);
        model.addAttribute("pageTitle", "Engineering");

        return "engineering";
    }

    @GetMapping("/sports")
    public String sports(Model model) {
        // Load, parse, and convert the Markdown file to HTML
        String sportsHtmlContent = markdownService.loadAndRender("sports-page-contents.md");

        model.addAttribute("sportsContent", sportsHtmlContent);
        model.addAttribute("pageTitle", "SPORTS");

        return "sports";
    }

    @GetMapping("/food")
    public String food(Model model) {
        // Load, parse, and convert the Markdown file to HTML
        String foodHtmlContent = markdownService.loadAndRender("food-page-contents.md");

        model.addAttribute("foodContent", foodHtmlContent);
        model.addAttribute("pageTitle", "Food");

        return "food";
    }

    @GetMapping("/politics")
    public String politics(Model model) {
        // Load, parse, and convert the Markdown file to HTML
        String politicsHtmlContent = markdownService.loadAndRender("politics-page-contents.md");

        model.addAttribute("politicsContent", politicsHtmlContent);
        model.addAttribute("pageTitle", "Politics");

        return "politics";
    }

    @GetMapping("/mueezings")
    public String mueezings(Model model) {
        // Load, parse, and convert the Markdown file to HTML
        String mueezingsHtmlContent = markdownService.loadAndRender("mueezings-page-contents.md");

        model.addAttribute("mueezingsContent", mueezingsHtmlContent);
        model.addAttribute("pageTitle", "Mueezings");

        return "mueezings";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        // Load, parse, and convert the Markdown file to HTML
        String contactHtmlContent = markdownService.loadAndRender("contact-page-contents.md");

        model.addAttribute("contactContent", contactHtmlContent);
        model.addAttribute("pageTitle", "Contact");

        return "contact";
    }
}
