package com.company.project.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.dao.RoleMapper;
import com.company.project.model.Role;
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
public class RoleService extends AbstractService<Role> {
	
    @Autowired
    private RoleMapper roleMapper;

    public Result list(String name,Page page) {
    	PageHelper.startPage(page.getPageNum(),page.getPageSize());
    	Conditions con=Conditions.instance(Role.class);
    	con.notDeleted();
    	if(StringUtils.isNotEmpty(name)) {
    		con.firstCriteria().andLike("roledesc", "%"+name+"%");
    	}
    	List<Role> list=this.findByCondition(con);
    	PageInfo pageResult = new PageInfo(list);
    	return Utils.success(pageResult);
    }
    
    public Result saveOrUpdateUser(Role role) {
    	Role roleName=this.findByRoleName(role.getName());
    	if(roleName!=null&&!roleName.getId().equals(role.getId())) {
    		Utils.ServiceException("角色名不能重复");
    	}
    	if(role.getId()==null) {
    		role.setDeleted(false);
    	}
    	this.saveOrUpdate(role);
    	return Utils.success();
    }
    
    public Role findByRoleName(String name) {
    	Conditions con=Conditions.instance(User.class);
    	con.notDeleted().andEqualTo("name", name);
    	Role role=this.findOneByCondition(con);
    	return role;
    }
    
}
