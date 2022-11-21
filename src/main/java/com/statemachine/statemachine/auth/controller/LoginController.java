package com.statemachine.statemachine.auth.controller;

import com.statemachine.statemachine.auth.entities.LoginDTO;
import com.statemachine.statemachine.auth.entities.LoginRTO;
import com.statemachine.statemachine.auth.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public LoginRTO login(@RequestBody LoginDTO loginDTO) throws Exception{
        LoginRTO loginRto = loginService.login(loginDTO);
        if(loginRto == null) throw new Exception("Cannot login");
        return loginRto;
    }
}
