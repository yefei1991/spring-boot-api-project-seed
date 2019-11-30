package com.company.project.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.dao.DictionaryMapper;
import com.company.project.model.Dictionary;
import com.company.project.model.Role;
import com.company.project.service.commonl.DictionaryCache;
import com.company.project.util.Conditions;
import com.company.project.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * Created by CodeGenerator on 2019/11/27.
 */
@Service
@Transactional
public class DictionaryService extends AbstractService<Dictionary> {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    
    @Autowired
    private DictionaryCache dictionaryCache;
    
    private final Logger logger = LoggerFactory.getLogger(DictionaryService.class);

    public Result list(String name, Page page) {
    	logger.info("heheda...");
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		Conditions con = Conditions.instance(Dictionary.class);
		con.notDeleted();
		if (StringUtils.isNotEmpty(name)) {
			con.firstCriteria().andLike("name", "%" + name + "%");
		}
		con.orderBy("code").asc().orderBy("sort").asc();
		List<Dictionary> list = this.findByCondition(con);
		PageInfo pageResult = new PageInfo(list);
		return Utils.success(pageResult);
	}

	public Result saveOrUpdateObj(Dictionary dictionary) {
		this.saveOrUpdate(dictionary);
		dictionaryCache.refreshByCode(dictionary.getCode());
		return Utils.success();
	}
	
	public Result deleteAndRefreshCache(Integer id) {
		this.logicDeleteById(id);
		Dictionary dictionary=this.findById(id);
		dictionaryCache.refreshByCode(dictionary.getCode());
		return Utils.success();
	}
	
}
