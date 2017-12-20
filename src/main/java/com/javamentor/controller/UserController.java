package com.javamentor.controller;

import com.javamentor.model.User;
import com.javamentor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping({"/list", "/"})
    public String listUsers(Model model) {

        model.addAttribute("users", userService.listAll());
        return "users/list";

    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id, Model model) {

        model.addAttribute("user", userService.getById(id));
        return "/users/user-form";

    }

    @RequestMapping("/new")
    public String newUser(Model model) {

        model.addAttribute("user", new User());
        return "/users/user-form";

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String saveUpdateUser(User user) {

        userService.saveOrUpdate(user);
        return "redirect:/users/list";

    }

    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {

        userService.delete(id);
        return "redirect:/users/list";

    }


}
