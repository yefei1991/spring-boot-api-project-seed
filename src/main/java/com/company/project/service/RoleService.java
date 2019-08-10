package com.company.project.service;

import com.company.project.dao.RoleMapper;
import com.company.project.model.Role;
import com.company.project.service.RoleService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by CodeGenerator on 2019/08/10.
 */
@Service
@Transactional
public class RoleService extends AbstractService<Role> {
    @Autowired
    private RoleMapper roleMapper;

}
