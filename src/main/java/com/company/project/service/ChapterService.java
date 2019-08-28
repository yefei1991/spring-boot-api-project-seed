package com.company.project.service;

import com.company.project.dao.ChapterMapper;
import com.company.project.model.Chapter;
import com.company.project.service.ChapterService;
import com.company.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by CodeGenerator on 2019/08/27.
 */
@Service
@Transactional
public class ChapterService extends AbstractService<Chapter> {
    @Autowired
    private ChapterMapper chapterMapper;

}
