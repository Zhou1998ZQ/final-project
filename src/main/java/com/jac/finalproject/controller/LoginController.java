package com.jac.finalproject.controller;


import com.jac.finalproject.entity.User;
import com.jac.finalproject.service.UserPaymentInfoService;
import com.jac.finalproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {

    private final UserService userService;
    private final UserPaymentInfoService userPaymentInfoService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginController(UserService userService, UserPaymentInfoService userPaymentInfoService,PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userPaymentInfoService = userPaymentInfoService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/showMyLoginPage")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @GetMapping("/user/access-denied")
    public String accessDenied(Model model) {
        model.addAttribute("user", new User());
        return "user/access-denied";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        String email = user.getEmail();
        String encoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoded);
        user.setEnabled(Boolean.TRUE);
        List<User> users = userService.getAllUsers();
        if (users.stream().map(User::getEmail).anyMatch(obj->obj.equalsIgnoreCase(email))){
            return "redirect:/showMyLoginPage";
        }else {
            userService.createUser(user);
            return "redirect:/users";
        }

    }


}
