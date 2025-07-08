package com.movieapp.securestream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("videoList", new String[]{"movie1.mp4", "movie2.mp4"});
        return "home";
    }
}
