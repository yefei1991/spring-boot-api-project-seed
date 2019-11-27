package com.company.project.core;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.company.project.util.Utils;

import tk.mybatis.mapper.entity.Condition;

/**
 * 基于通用MyBatis Mapper插件的Service接口的实现
 */
public abstract class AbstractService<T extends IdModel> {

    @Autowired
    protected Mapper<T> mapper;

    private Class<T> modelClass;    // 当前泛型真实类型的Class

    public AbstractService() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }

    public void save(T model) {
        mapper.insertSelective(model);
    }

    public void save(List<T> models) {
        mapper.insertList(models);
    }

    public void deleteById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }
    
    public void logicDeleteById(Integer id) {
    	T model=null;
		try {
			model = modelClass.newInstance();
			Assert.state(model instanceof AbstractModel, "model must extends AbstractModel");
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
    	model.setId(id);
    	if(model instanceof AbstractModel) {
    		((AbstractModel)model).setDeleted(true);
    	}
    	mapper.updateByPrimaryKeySelective(model);
    	
    }

    public void deleteByIds(String ids) {
        mapper.deleteByIds(ids);
    }

    public void update(T model) {
        mapper.updateByPrimaryKeySelective(model);
    }

    public T findById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public T findBy(String fieldName, Object value) throws TooManyResultsException {
        try {
            T model = modelClass.newInstance();
            Field field = modelClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(model, value);
            return mapper.selectOne(model);
        } catch (ReflectiveOperationException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<T> findByIds(String ids) {
        return mapper.selectByIds(ids);
    }

    public List<T> findByCondition(Condition condition) {
        return mapper.selectByCondition(condition);
    }
    
    public T findOneByCondition(Condition condition) {
    	List<T> list=mapper.selectByCondition(condition);
    	if(list.size()>1) {
    		Utils.ServiceException("数据库查询不唯一");
    	}
    	if(list.size()==0) {
    		return null;
    	}
    	return list.get(0);
    }
    
    public List<T> findAll() {
        return mapper.selectAll();
    }
    
    public void saveOrUpdate(T t) {
    	Assert.state(t instanceof AbstractModel, "model must extends AbstractModel");
    	if(t.getId()==null) {
    		//((AbstractModel)t).setCreatetime(new Date());
    		mapper.insertSelective(t);
    	}else {
    		//((AbstractModel)t).setUpdatetime(new Date());
    		mapper.updateByPrimaryKeySelective(t);
    	}
    }
}
