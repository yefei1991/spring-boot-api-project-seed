package com.company.project.service.common.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.company.project.model.Dictionary;
import com.company.project.service.DictionaryService;
import com.company.project.service.commonl.DictionaryCache;
import com.company.project.util.Conditions;
import com.company.project.vo.Dic;

@Component
public class DictionaryRedisCache implements DictionaryCache{
	
	@Autowired
	private RedisTemplate<Object,Object> redisTemplate;
	
	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public void cacheAll() {
		Conditions con=Conditions.instance(Dictionary.class);
		//this.findByCode("sex")
		con.notDeleted();
		con.orderBy("sort").asc();
		List<Dictionary> dictionarys=dictionaryService.findByCondition(con);
		Map<String,List<Dic>> codeDicsMap=new HashMap<>();
		for(Dictionary dictionary:dictionarys) {
			String code=dictionary.getCode();
			if(codeDicsMap.get(code)==null) {
				codeDicsMap.put(code, new ArrayList<Dic>());
			}
			codeDicsMap.get(code).add(convertToDic(dictionary));
		}
		for(String key:codeDicsMap.keySet()) {
			deleteByCode(key);
			redisTemplate.opsForValue().set(key, codeDicsMap.get(key));
		}
	}

	private Dic convertToDic(Dictionary dictionary) {
		Dic dic=new Dic();
		dic.setLabel(dictionary.getOptiondesc());
		dic.setValue(dictionary.getOption());
		return dic;
	}
	
	@Override
	public void refreshByCode(String code) {
		deleteByCode(code);
		Conditions con=Conditions.instance(Dictionary.class);
		con.notDeleted().andEqualTo("code", code);
		con.orderBy("sort").asc();
		List<Dictionary> dictionarys=dictionaryService.findByCondition(con);
		List<Dic> dics=new ArrayList<>();
		for(Dictionary dictionary:dictionarys) {
			dics.add(convertToDic(dictionary));
		}
		redisTemplate.opsForValue().set(code, dics);
	}

	@Override
	public void deleteByCode(String code) {
		redisTemplate.delete(code);
	}

	@Override
	public List<Dic> findByCode(String code) {
		List<Dic> dicList=(List<Dic>)redisTemplate.opsForValue().get(code);
		if(dicList!=null&&dicList.size()==0) {
			return new ArrayList<Dic>();
		}
		return dicList;
	}

	@PostConstruct
    public void init() {
        cacheAll();
    }

	@Override
	public String findByCode(List<Dic> dics, String code) {
		if(dics.size()==0) {
			return "未知选项";
		}
		for(Dic dic:dics) {
			if(code.equals(dic.getValue())) {
				return dic.getLabel();
			}
		}
		return "未知选项";
	}
}
