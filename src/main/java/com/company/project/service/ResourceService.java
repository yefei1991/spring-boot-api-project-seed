package com.company.project.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.dao.ResourceMapper;
import com.company.project.model.Resource;
import com.company.project.service.commonl.DictionaryCache;
import com.company.project.util.Conditions;
import com.company.project.util.Utils;
import com.company.project.vo.Dic;
import com.company.project.vo.DicConst;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * Created by CodeGenerator on 2019/08/10.
 */
@Service
@Transactional
public class ResourceService extends AbstractService<Resource> {
    @Autowired
    private ResourceMapper resourceMapper;
    
    @Autowired
    private DictionaryCache dictionaryCache;
    
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
    
    public Result dictionary() {
    	JSONObject json=new JSONObject();
    	List<Dic> dictionarys=new ArrayList<>();
    	Conditions con=Conditions.instance(Resource.class);
    	con.notDeleted().andEqualTo("type", Type.Dictionary.getCode());
    	con.orderBy("sort desc");
    	List<Resource> resources=this.findByCondition(con);
		dictionarys.add(Dic.build("根目录", "0"));
    	for(Resource r:resources) {
    		JSONObject dJson=new JSONObject();
    		dJson.put("label", r.getName());
    		dJson.put("value", r.getId());
    		dictionarys.add(Dic.build(r.getName(), r.getId()+""));
    	}
    	json.put("types", dictionaryCache.findByCode(DicConst.RESOURCETYPE));
    	json.put("dictionarys", dictionarys);
    	return Utils.success(json);
    }
    
    public static enum Type{
    	Dictionary(0,"目录"),Menu(1,"菜单");
    	private int code;
    	private String text;
    	private Type(int code,String text) {
    		this.code=code;
    		this.text=text;
    	}
		public int getCode() {
			return code;
		}
		public void setCode(int code) {
			this.code = code;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
    	
    }

}
