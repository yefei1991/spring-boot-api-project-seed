package com.company.project.service;

import com.company.project.dao.ResourceMapper;
import com.company.project.model.Resource;
import com.company.project.service.ResourceService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by CodeGenerator on 2019/08/10.
 */
@Service
@Transactional
public class ResourceService extends AbstractService<Resource> {
    @Autowired
    private ResourceMapper resourceMapper;

}
