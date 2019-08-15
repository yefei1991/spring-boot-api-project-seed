package com.company.project.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.AbstractController;
import com.company.project.core.Result;
import com.company.project.service.UserService;
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
    	Page page=this.getRequestPage();
    	return userService.userList(name, page);
    }

}
