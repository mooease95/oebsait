package com.mueez;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "landing-page";
    }

    @GetMapping("/about")
    public String about() {
        return "default-error-page";
    }

    @GetMapping("/engineering")
    public String engineering() {
        return "default-error-page";
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

    @GetMapping("/contact")
    public String contact() {
        return "default-error-page";
    }
}
