package com.company.project.configurer.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultCode;
import com.company.project.core.ResultGenerator;

public class ShiroLoginFilter extends UserFilter{
	@Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
//        super.redirectToLogin(request, response);
        response.setContentType("application/json; charset=utf-8");//返回json
        Result result=ResultGenerator.genFailResult("用户未登录，请先登录");
        result.setCode(ResultCode.UNAUTHORIZED);
        response.getWriter().write(JSONObject.toJSONString(result));
    }
}
