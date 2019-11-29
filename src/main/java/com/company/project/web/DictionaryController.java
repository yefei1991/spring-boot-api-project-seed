package com.company.project.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.AbstractController;
import com.company.project.core.Result;
import com.company.project.model.Dictionary;
import com.company.project.service.DictionaryService;
import com.company.project.util.Utils;

/**
* Created by CodeGenerator on 2019/11/27.
*/
@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends AbstractController{
    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/list")
    public Result userList(String name) {
    	return dictionaryService.list(name, getRequestPage());
    }
    
    @PostMapping("/save")
    public Result saveOrUpdate(Dictionary dictionary) {
    	return dictionaryService.saveOrUpdateObj(dictionary);
    }
    
    @GetMapping("/info")
    public Result info(Integer id) {
    	return Utils.success(dictionaryService.findById(id));
    }

    @PostMapping("/delete")
    public Result delete(Integer id) {
    	return dictionaryService.deleteAndRefreshCache(id);
    }

}
