
package com.websiteshop.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websiteshop.entity.Role;
import com.websiteshop.service.RoleService;

@RequestMapping("/rest/roles")
public class RoleRestController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public List<Role> getAll() {
        return roleService.findAll();
    }

}