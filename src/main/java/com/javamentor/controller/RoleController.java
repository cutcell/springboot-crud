package com.javamentor.controller;

import com.javamentor.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequestMapping({"/", "/list"})
    public String listRoles(Model model) {

        model.addAttribute("roles", roleService.listAll());
        return "roles/list";

    }


}
