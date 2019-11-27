package com.company.project.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.dao.DictionaryMapper;
import com.company.project.model.Dictionary;
import com.company.project.model.Role;
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

    public Result list(String name, Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		Conditions con = Conditions.instance(Role.class);
		con.notDeleted();
		if (StringUtils.isNotEmpty(name)) {
			con.firstCriteria().andLike("name", "%" + name + "%");
		}
		List<Dictionary> list = this.findByCondition(con);
		PageInfo pageResult = new PageInfo(list);
		return Utils.success(pageResult);
	}

	public Result saveOrUpdateObj(Dictionary dictionary) {
		this.saveOrUpdate(dictionary);
		return Utils.success();
	}
}
