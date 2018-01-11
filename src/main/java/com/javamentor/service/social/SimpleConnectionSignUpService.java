package com.javamentor.service.social;

import com.javamentor.model.Role;
import com.javamentor.model.User;
import com.javamentor.service.RoleService;
import com.javamentor.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;

import java.util.Arrays;

public class SimpleConnectionSignUpService implements ConnectionSignUp {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleConnectionSignUpService.class);

    private UserService userService;
    private RoleService roleService;

    public SimpleConnectionSignUpService(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public String execute(Connection<?> connection) {

        LOGGER.debug("--- Profile URL: {}", connection.getProfileUrl());

        UserProfile profile = connection.fetchUserProfile();

        LOGGER.debug("--- Fetched profile username: {}", profile.getUsername());

        Role userRole = new Role();
        userRole.setName("USER");
        roleService.saveOrUpdate(userRole);

        User user = new User();
        user.setUsername(profile.getUsername());
        user.setPassword("");
        user.setFullName(String.format("%s %s", profile.getFirstName(), profile.getLastName()));
        user.setRoles(Arrays.asList(userRole));

        userService.saveOrUpdate(user);

        return profile.getUsername();

    }

}
