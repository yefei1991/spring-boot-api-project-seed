package com.company.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.dao.UserMapper;
import com.company.project.model.User;
import com.company.project.util.Conditions;
import com.company.project.util.Utils;

import tk.mybatis.mapper.entity.Condition;


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
    	if(user==null||password.equals(user.getPassword())) {
    		Utils.ServiceException("用户名或密码错误");
    	}
    	if(!user.getEnable()) {
    		Utils.ServiceException("用户已被锁定,请联系管理员");
    	}
    	return null;
    }
}
