package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.AbstractController;
import com.company.project.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

/**
* Created by CodeGenerator on 2019/08/10.
*/
@RestController
@RequestMapping("/role")
public class RoleController extends AbstractController{
    @Autowired
    private RoleService roleService;

    @GetMapping("/test")
    public Result test() {
        return ResultGenerator.genSuccessResult(roleService.findAll());
    }

}
