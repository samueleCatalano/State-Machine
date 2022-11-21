package com.statemachine.statemachine.auth.controller;

import com.statemachine.statemachine.auth.entities.SignupActivationDTO;
import com.statemachine.statemachine.auth.entities.SignupDTO;
import com.statemachine.statemachine.auth.services.SignupService;
import com.statemachine.statemachine.user.entities.Role;
import com.statemachine.statemachine.user.entities.User;
import com.statemachine.statemachine.user.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @PostMapping("/signup")
    public User signup(@RequestBody SignupDTO signupDTO) throws Exception {
        return signupService.signup(signupDTO);
    }

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/signup/{role}")
    public User signup(@RequestBody SignupDTO signupDTO, @PathVariable String role) throws Exception {
        return signupService.signup(signupDTO, role);
    }

    @PostMapping("/signup/activation")
    public User signup(@RequestBody SignupActivationDTO signupActivationDTO) throws Exception {
        return signupService.activate(signupActivationDTO);
    }

    @PostMapping("/roles") //for populate the role's table, else i can't create the user
    public Role createRoles(@RequestBody Role role){
        return roleRepository.save(role);
    }
}
