package com.javamentor.controller.rest;

import com.javamentor.model.User;
import com.javamentor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsersList() {
        return userService.listAll();
    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping("new")
    @ResponseStatus(HttpStatus.CREATED)
    public User addNewUser(@RequestBody User newUser) {
        return userService.saveOrUpdate(newUser);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {

        User foudUser = userService.getById(id);
        foudUser.setUsername(updatedUser.getUsername());
        foudUser.setFullName(updatedUser.getFullName());
        foudUser.setPassword(updatedUser.getPassword());
        foudUser.setRoles(updatedUser.getRoles());

        return userService.saveOrUpdate(foudUser);

    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.delete(id);
    }

}
