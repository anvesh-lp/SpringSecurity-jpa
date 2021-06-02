package com.anvesh.springsecurityjpa.controllers;


import com.anvesh.springsecurityjpa.model.User;
import com.anvesh.springsecurityjpa.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class ManageUser {

    private final UserService userrepository;

    private final PasswordEncoder encoder;

    public ManageUser(UserService userDetails, PasswordEncoder encoder) {
        this.userrepository = userDetails;
        this.encoder = encoder;
    }


    @GetMapping("login")
    public String login() {
        return "/login";
    }

    @GetMapping("registration")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "/registration";
    }

    @PostMapping("registration")
    public String registernew(@Valid @ModelAttribute User newUser, BindingResult result) {
        if (result.hasErrors() || userrepository.userAlreadyExists(newUser.getUsername())) {
            if (userrepository.userAlreadyExists(newUser.getUsername()))
                result.addError(new FieldError("user", "username", "User already exists " + newUser.getUsername()));
            return "/registration";
        }
        newUser.setActive(true);
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        userrepository.saveUSer(newUser);
        System.out.println(newUser.getUsername());
        System.out.println(newUser.getPassword());
        return "redirect:/login";
    }
}
