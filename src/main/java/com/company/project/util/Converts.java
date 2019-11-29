package com.company.project.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.alibaba.fastjson.JSONObject;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.github.pagehelper.PageInfo;

public class Converts {
	
	public static <T> List<Map<String,Object>> toMapList(List<T> tList,Function<T,Map<String,Object>> f){
		List<Map<String,Object>> list=new ArrayList<>();
		for(T t:tList) {
			list.add(f.apply(t));
		}
		return list;
	}
	
	public static <T> Result pageToJson(PageInfo<T> page,Function<T,Map<String,Object>> f) {
		JSONObject json=new JSONObject();
		json.put("total", page.getTotal());
		List<Map<String,Object>> jsonList=new ArrayList<>();
		for(T t:page.getList()) {
			jsonList.add(f.apply(t));
		}
		json.put("list", jsonList);
		json.put("pageNum", page.getPageNum());
		json.put("pageSize", page.getPageSize());
		return ResultGenerator.genSuccessResult(json);
	}

}
