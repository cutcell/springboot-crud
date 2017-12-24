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
    public List<Role> getRolesList() {
        return roleService.listAll();
    }

    @GetMapping("{id}")
    public Role getRoleById(@PathVariable Integer id) {
        return roleService.getById(id);
    }

    @PostMapping("new")
    @ResponseStatus(HttpStatus.CREATED)
    public Role addNewRole(@RequestBody Role newRole) {
        return roleService.saveOrUpdate(newRole);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Role updateRole(@PathVariable Integer id, @RequestBody Role updatedRole) {

        Role foundRole = roleService.getById(id);
        foundRole.setName(updatedRole.getName());
        foundRole.setUsers(updatedRole.getUsers());

        return roleService.saveOrUpdate(foundRole);

    }

    @DeleteMapping("{id}")
    public void deleteRole(@PathVariable Integer id) {
        roleService.delete(id);
    }

}
