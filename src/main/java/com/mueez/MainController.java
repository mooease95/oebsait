package com.mueez;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "landing-page.html";
    }

    @GetMapping("/about")
    public String about() {
        return "default-error-page.html";
    }

    @GetMapping("/engineering")
    public String engineering() {
        return "default-error-page.html";
    }

    @GetMapping("/sports")
    public String sports() {
        return "default-error-page.html";
    }

    @GetMapping("/food")
    public String food() {
        return "default-error-page.html";
    }

    @GetMapping("/politics")
    public String politics() {
        return "default-error-page.html";
    }

    @GetMapping("/contact")
    public String contact() {
        return "default-error-page.html";
    }
}
