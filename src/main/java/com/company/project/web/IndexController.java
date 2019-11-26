package com.company.project.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;

@RestController
@RequestMapping("/")
public class IndexController {
	@RequestMapping("/unauth")
	
    public Result unauth(){
        Result result = ResultGenerator.genFailResult("未授权!请调整或重新登录");
        result.setCode(ResultCode.UNAUTHORIZED);
        return result;
    }
}
