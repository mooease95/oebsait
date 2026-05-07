package com.mueez.oebsait.controllers;

import com.mueez.oebsait.services.MarkdownService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {

    private static final Logger LOGGER = LogManager.getLogger(MainController.class);

    private record PageConfig(String markdownFile, String title) {}

    private static final Map<String, PageConfig> PAGES = Map.of(
        "engineering", new PageConfig("engineering-page-contents.md", "Engineering"),
        "sports",      new PageConfig("sports-page-contents.md",      "SPORTS"),
        "food",        new PageConfig("food-page-contents.md",        "Food"),
        "politics",    new PageConfig("politics-page-contents.md",    "Politics"),
        "mueezings",   new PageConfig("mueezings-page-contents.md",   "Mueezings"),
        "contact",     new PageConfig("contact-page-contents.md",     "Contact")
    );

    private final MarkdownService markdownService;

    public MainController(MarkdownService markdownService) {
        this.markdownService = markdownService;
    }

    @GetMapping(value = {"/", "/home"})
    public String home() {
        LOGGER.info("Home page requested");
        return "landing-page";
    }

    @GetMapping("/about")
    public String about() {
        return "about-me-page";
    }

    @GetMapping({"/engineering", "/sports", "/food", "/politics", "/mueezings", "/contact"})
    public String contentPage(HttpServletRequest request, Model model) {
        String page = request.getRequestURI().substring(1);
        LOGGER.info("{} page requested", page);
        PageConfig config = PAGES.get(page);
        model.addAttribute("pageContent", markdownService.loadAndRender(config.markdownFile()));
        model.addAttribute("pageTitle", config.title());
        return "markdown-page";
    }
}
