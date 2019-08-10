package com.company.project.dao;

import java.util.List;

import com.company.project.core.Mapper;
import com.company.project.model.Resource;
import com.company.project.model.User;

public interface UserMapper extends Mapper<User> {
	
	List<Resource> selectResourceByUser(Integer userId);
}