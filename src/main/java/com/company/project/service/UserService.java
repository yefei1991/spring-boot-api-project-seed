package com.company.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.dao.UserMapper;
import com.company.project.model.Resource;
import com.company.project.model.User;
import com.company.project.util.Conditions;
import com.company.project.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * Created by CodeGenerator on 2019/08/10.
 */
@Service
@Transactional
public class UserService extends AbstractService<User> {
    @Autowired
    private UserMapper userMapper;

    public Result login(String username,String password) {
    	Conditions con=Conditions.instance(User.class);
    	con.notDeleted().andEqualTo("username", username);
    	User user=this.findOneByCondition(con);
    	if(user==null||!password.equals(user.getPassword())) {
    		Utils.ServiceException("用户名或密码错误");
    	}
    	if(!user.getEnable()) {
    		Utils.ServiceException("用户已被锁定,请联系管理员");
    	}
    	List<Resource> resources=userMapper.findResourceByUser(user.getId());
    	JSONObject currentUser=new JSONObject();
    	currentUser.put("id", user.getId());
    	currentUser.put("name", user.getUsername());
    	List<JSONObject> currentMenu=new ArrayList<>();
    	Map<Integer,JSONObject> maps=new HashMap<>();
    	for(Resource r:resources) {
    		JSONObject json=new JSONObject();
    		json.put("exact", true);
			json.put("name", r.getName());
			json.put("path", r.getResurl());
			maps.put(r.getId(), json);
    	}
    	for(Resource r:resources) {
    		if(r.getParentid()==0) {
    			currentMenu.add(maps.get(r.getId()));
    		}else {
    			JSONObject parent=maps.get(r.getParentid());
    			if(parent.get("children")==null) {
    				parent.put("children", new ArrayList<JSONObject>());
    			}
    			((List<JSONObject>)parent.get("children")).add(maps.get(r.getId()));
    		}
    		
    	}
    	JSONObject result=new JSONObject();
    	result.put("currentUser", currentUser);
    	result.put("currentMenu", currentMenu);
    	return Utils.success(result);
    }
    
    public Result userList(Map<String,String> params,Page page) {
    	PageHelper.startPage(page.getPageNum(),page.getPageSize());
    	List<Map<String,Object>> list=userMapper.userList(params);
    	PageInfo pageResult = new PageInfo(list);
    	return Utils.success(pageResult);
    }
    
    public Result saveOrUpdateUser(User user) {
    	User userName=this.findByUserName(user.getUsername());
    	if(userName!=null&&!userName.getId().equals(user.getId())) {
    		Utils.ServiceException("用户名不能重复");
    	}
    	if(user.getId()==null) {
    		user.setPassword("123456");
    		user.setEnable(true);
    		user.setDeleted(false);
    	}
    	this.saveOrUpdate(user);
    	return Utils.success();
    }
    
    public User findByUserName(String username) {
    	Conditions con=Conditions.instance(User.class);
    	con.notDeleted().andEqualTo("username", username);
    	User user=this.findOneByCondition(con);
    	return user;
    }
    
    public Result findUserRoles(Integer userId) {
    	List<Map<String,Object>> result=userMapper.userRoles(userId);
    	return Utils.success(result);
    }
    
    public Result allocateRole(Integer userId,String roleIdList) {
    	userMapper.deleteUserRole(userId);
    	userMapper.allocateRole(userId,Utils.toList(roleIdList));
    	return Utils.success();
    }
}
