package com.anvesh.springsecurityjpa.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {


    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome USER</h1>");
    }

    @GetMapping("/")
    public String all() {
        return ("<h1>Welcome ALL</h1>");
    }

    @GetMapping("/admin")
    public String admin() {
        return ("<h1>Welcome ADMIN</h1>");
    }
}
