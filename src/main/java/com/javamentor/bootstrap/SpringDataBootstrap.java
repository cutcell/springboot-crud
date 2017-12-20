package com.javamentor.bootstrap;

import com.javamentor.model.Role;
import com.javamentor.model.User;
import com.javamentor.service.RoleService;
import com.javamentor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SpringDataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Role adminRole = new Role();
        adminRole.setName("ADMIN");

        Role userRole = new Role();
        userRole.setName("USER");

        roleService.saveOrUpdate(adminRole);
        roleService.saveOrUpdate(userRole);

        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setFullName("Administrator");
        admin.setRoles(Arrays.asList(adminRole, userRole));

        userService.saveOrUpdate(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword("");
        user.setFullName("User");
        user.setRoles(Arrays.asList(userRole));

        userService.saveOrUpdate(user);


    }
}
