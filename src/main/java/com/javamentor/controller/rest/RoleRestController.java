package com.javamentor.controller.rest;

import com.javamentor.model.Role;
import com.javamentor.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/roles")
public class RoleRestController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getUsersList() {
        return roleService.listAll();
    }

    @GetMapping("{id}")
    public Role getUserById(@PathVariable Integer id) {
        return roleService.getById(id);
    }

    @PostMapping("new")
    @ResponseStatus(HttpStatus.CREATED)
    public Role addNewUser(@RequestBody Role newRole) {
        return roleService.saveOrUpdate(newRole);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Role updateUser(@PathVariable Integer id, @RequestBody Role updatedRole) {

        Role foudRole = roleService.getById(id);
        foudRole.setName(updatedRole.getName());
        foudRole.setUsers(updatedRole.getUsers());

        return roleService.saveOrUpdate(foudRole);

    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id) {
        roleService.delete(id);
    }

}
