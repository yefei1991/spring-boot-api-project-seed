package com.company.project.web;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.AbstractController;
import com.company.project.core.Result;
import com.company.project.model.User;
import com.company.project.service.UserService;
import com.company.project.util.Utils;
import com.github.pagehelper.Page;

/**
* Created by CodeGenerator on 2019/08/10.
*/
@RestController
@RequestMapping("/user")
public class UserController extends AbstractController{
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(String username,String password) {
        return userService.login(username,password);
    }
    
    @GetMapping("/list")
    public Result userList(String name) {
    	return userService.userList(getParamMap(), getRequestPage());
    }
    
    @PostMapping("/save")
    public Result saveOrUpdate(User user) {
    	return userService.saveOrUpdateUser(user);
    }
    
    @GetMapping("/info")
    public Result info(Integer id) {
    	return Utils.success(userService.findById(id));
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
    	userService.logicDeleteById(id);
    	return Utils.success();
    }
    
    @GetMapping("/userRoles")
    public Result userRoles(Integer userId) {
    	return userService.findUserRoles(userId);
    }
    
    @PostMapping("/allocateRole")
    public Result allocateRole(Integer userId,String roleIdList) {
    	return userService.allocateRole(userId, roleIdList);
    }
}
