package com.company.project.service;

import com.company.project.dao.NovelMapper;
import com.company.project.model.Novel;
import com.company.project.service.NovelService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by CodeGenerator on 2019/08/27.
 */
@Service
@Transactional
public class NovelService extends AbstractService<Novel> {
    @Autowired
    private NovelMapper novelMapper;

}
