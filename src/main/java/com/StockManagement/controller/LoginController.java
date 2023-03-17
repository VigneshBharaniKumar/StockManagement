package com.StockManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.StockManagement.model.Login;
import com.StockManagement.service.LoginService;
import org.springframework.ui.Model;


@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/")
    public String showLoginForm(Model model) {
        if (loginService.isUserLoggedIn()) {
            return "home.html";
        }
        model.addAttribute("user", new Login());
        return "login.html";
    }

    @GetMapping("/home")
    public String showIndex() {
        return "home.html";
    }

    @GetMapping("/login")
    public String showLogin(Model model) {
        // model.addAttribute("user", new Login());
        return "login.html";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Login login, Model model) {
        boolean isAuthenticated = loginService.authenticate(login.getUsername(), login.getPassword());

        if (isAuthenticated) {
            loginService.setSession(login.getUsername(), login.getPassword());
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "login";
    }

    @RequestMapping(value = { "/logout" }, method = RequestMethod.POST)
    public String logout() {
        loginService.clearSession();
        return "redirect:/";
    }

}