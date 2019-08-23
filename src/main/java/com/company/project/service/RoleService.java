package com.company.project.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
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

	public Result list(String name, Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		Conditions con = Conditions.instance(Role.class);
		con.notDeleted();
		if (StringUtils.isNotEmpty(name)) {
			con.firstCriteria().andLike("roledesc", "%" + name + "%");
		}
		List<Role> list = this.findByCondition(con);
		PageInfo pageResult = new PageInfo(list);
		return Utils.success(pageResult);
	}

	public Result saveOrUpdateUser(Role role) {
		Role roleName = this.findByRoleName(role.getName());
		if (roleName != null && !roleName.getId().equals(role.getId())) {
			Utils.ServiceException("角色名不能重复");
		}
		if (role.getId() == null) {
			role.setDeleted(false);
		}
		this.saveOrUpdate(role);
		return Utils.success();
	}

	public Role findByRoleName(String name) {
		Conditions con = Conditions.instance(User.class);
		con.notDeleted().andEqualTo("name", name);
		Role role = this.findOneByCondition(con);
		return role;
	}

	public Result findRoleResources(Integer roleId) {
		//    	t1.id value,
		//		t1.name title,
		//		t1.parentId,
    	List<Map<String,Object>> result=roleMapper.roleResources(roleId);
    	Map<String,Map<String,Object>> maps=new HashMap<>();
    	List<Map<String,Object>> root=new ArrayList<>();
    	List<Integer> checkedList=new ArrayList<>();
    	List<Integer> submitList=new ArrayList<>();
    	for(Map<String,Object> map:result) {
    		map.put("key", map.get("value"));
    		maps.put(map.get("value").toString(), map);
    	}
    	for(Map<String,Object> map:result) {
    		Map<String,Object> parent=maps.get(map.get("parentId").toString());
    		if(parent==null) {
    			root.add(map);
    		}else {
    			if(parent.get("children")==null) {
    				parent.put("children", new ArrayList<Map<String,Object>>());
    			}
    			((ArrayList<Map<String,Object>>)parent.get("children")).add(map);
    		}
    	}
    	for(Map<String,Object> map:result) {
    		boolean checked="0".equals(map.get("checked").toString())?false:true;
    		if(checked&&map.get("children")==null) {
    			checkedList.add(Integer.parseInt(map.get("value").toString()));
    		}
    		if(checked) {
    			submitList.add(Integer.parseInt(map.get("value").toString()));
    		}
    	}
    	JSONObject json=new JSONObject();
    	json.put("checked", checkedList);
    	json.put("treeData", root);
    	json.put("submited", submitList);
    	return Utils.success(json);
    }

	public Result allocateResource(Integer roleId, String resourceIdList) {
		roleMapper.deleteRoleResource(roleId);
		if (StringUtils.isNotEmpty(resourceIdList)) {
			roleMapper.allocateResource(roleId, Utils.toList(resourceIdList));
		}
		return Utils.success();
	}

}
