package com.company.project.service;

import com.company.project.dao.ResourceMapper;
import com.company.project.model.Resource;
import com.company.project.model.Resource;
import com.company.project.service.ResourceService;
import com.company.project.util.Conditions;
import com.company.project.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.company.project.core.AbstractService;
import com.company.project.core.Result;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by CodeGenerator on 2019/08/10.
 */
@Service
@Transactional
public class ResourceService extends AbstractService<Resource> {
    @Autowired
    private ResourceMapper resourceMapper;
    
    public Result list(String name,Page page) {
    	PageHelper.startPage(page.getPageNum(),page.getPageSize());
    	Conditions con=Conditions.instance(Resource.class);
    	con.notDeleted();
    	if(StringUtils.isNotEmpty(name)) {
    		con.firstCriteria().andLike("name", "%"+name+"%");
    	}
    	List<Resource> list=this.findByCondition(con);
    	PageInfo pageResult = new PageInfo(list);
    	return Utils.success(pageResult);
    }
    
    public Result saveOrUpdateUser(Resource resource) {
    	if(resource.getId()==null) {
    		resource.setDeleted(false);
    	}
    	this.saveOrUpdate(resource);
    	return Utils.success();
    }

}
