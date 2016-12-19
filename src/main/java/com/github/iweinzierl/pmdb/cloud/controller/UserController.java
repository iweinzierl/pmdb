package com.github.iweinzierl.pmdb.cloud.controller;

import com.github.iweinzierl.pmdb.cloud.domain.User;
import com.github.iweinzierl.pmdb.cloud.security.GoogleAuthentication;
import com.github.iweinzierl.pmdb.cloud.security.GoogleUserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    public User getCurrentUser(Principal principal) {
        GoogleAuthentication googleAuth = (GoogleAuthentication) principal;
        GoogleUserDetails googleUser = (GoogleUserDetails) googleAuth.getDetails();
        return User.builder()
                .name(googleUser.getName())
                .email(googleUser.getEmail())
                .profileImage(googleUser.getProfileImage())
                .build();
    }
}
