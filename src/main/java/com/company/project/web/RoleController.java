package com.company.project.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.AbstractController;
import com.company.project.core.Result;
import com.company.project.model.Role;
import com.company.project.service.RoleService;
import com.company.project.util.Utils;

/**
* Created by CodeGenerator on 2019/08/10.
*/
@RestController
@RequestMapping("/role")
public class RoleController extends AbstractController{
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public Result userList(String name) {
    	return roleService.list(name, getRequestPage());
    }
    
    @PostMapping("/save")
    public Result saveOrUpdate(Role role) {
    	return roleService.saveOrUpdateUser(role);
    }
    
    @GetMapping("/info")
    public Result info(Integer id) {
    	return Utils.success(roleService.findById(id));
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
    	roleService.logicDeleteById(id);
    	return Utils.success();
    }

    @GetMapping("/roleResources")
    public Result roleResources(Integer roleId) {
    	return roleService.findRoleResources(roleId);
    }
    
    @PostMapping("/allocateResource")
    public Result allocateResource(Integer roleId,String resourceIdList) {
    	return roleService.allocateResource(roleId, resourceIdList);
    }
}
