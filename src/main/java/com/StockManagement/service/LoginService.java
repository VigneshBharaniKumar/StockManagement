package com.StockManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpSession;

import com.StockManagement.model.Login;
import com.StockManagement.repository.LoginRepository;

@Service
public class LoginService {
    
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private HttpSession httpSession;

    public boolean authenticate(String username, String password) {
        Login user = loginRepository.findByUsernameAndPassword(username, password);
        if (user != null) { return true;} else { return false;}
    }
    
    public boolean isUserLoggedIn() {      
        System.out.println(httpSession.getAttribute("username"));
        System.out.println(httpSession.getAttribute("password"));
        return httpSession.getAttribute("username") != null && httpSession.getAttribute("password") != null;
    }
    
    public void setSession(String username, String password) {
        httpSession.setAttribute("username", username);
        httpSession.setAttribute("password", password);
        httpSession.setMaxInactiveInterval(30 * 24 * 60 * 60); // 30 days
    }
    
    public void clearSession() {
        httpSession.removeAttribute("username");
        httpSession.removeAttribute("password");
    }
    
}