package com.statemachine.statemachine.user.controllers;

import com.statemachine.statemachine.auth.entities.LoginRTO;
import com.statemachine.statemachine.auth.services.LoginService;
import com.statemachine.statemachine.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;
@RestController
public class UserController {

    @Autowired
    LoginService loginService;

    @GetMapping("/profile")
    public LoginRTO getProfile(Principal principal ){
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        LoginRTO rto = new LoginRTO();
        rto.setUser(user);
        rto.setJWT(loginService.generateJWT(user));
        return rto;

    }
}
