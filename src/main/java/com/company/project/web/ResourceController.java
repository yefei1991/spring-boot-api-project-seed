package com.company.project.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.AbstractController;
import com.company.project.core.Result;
import com.company.project.model.Resource;
import com.company.project.service.ResourceService;
import com.company.project.util.Utils;

/**
* Created by CodeGenerator on 2019/08/10.
*/
@RestController
@RequestMapping("/resource")
public class ResourceController extends AbstractController{
    @Autowired
    private ResourceService resourceService;

    @GetMapping("/list")
    public Result userList(String name) {
    	return resourceService.list(name, getRequestPage());
    }
    
    @PostMapping("/save")
    public Result saveOrUpdate(Resource resource) {
    	return resourceService.saveOrUpdateUser(resource);
    }
    
    @GetMapping("/info")
    public Result info(Integer id) {
    	return Utils.success(resourceService.findById(id));
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
    	resourceService.logicDeleteById(id);
    	return Utils.success();
    }

}
