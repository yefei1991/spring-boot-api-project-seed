package com.company.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.company.project.core.Mapper;
import com.company.project.model.Resource;
import com.company.project.model.User;

public interface UserMapper extends Mapper<User> {
	
	List<Resource> findResourceByUser(Integer userId);
	
	List<Map<String,Object>> userList(Map<String,String> params);
	
	List<Map<String,Object>> userRoles(Integer userId);
	
	void deleteUserRole(Integer userId);
	
	void allocateRole(@Param("userId")Integer userId,@Param("roleIdList")List<Integer> roleIdList);
}