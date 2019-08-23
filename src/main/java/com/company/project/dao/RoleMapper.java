package com.company.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Role;

public interface RoleMapper extends Mapper<Role> {
	
	List<Map<String,Object>> roleResources(Integer roleId);
	
	void deleteRoleResource(Integer roleId);
	
	void allocateResource(@Param("roleId")Integer roleId,@Param("resourceIdList")List<Integer> resourceIdList);
}