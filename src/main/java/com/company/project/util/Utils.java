package com.company.project.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.ServiceException;

public class Utils {

	public static Map<String,String> toMap(Object o){
		try {
			Map<String,String> info=BeanUtils.describe(o);
			info.remove("class");
			return info;
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
			Utils.ServiceException("数据转换异常");
		}
		return null;
	}
	public static void ServiceException(String message) {
		throw new ServiceException(message);
	}

	public static Result success() {
		return ResultGenerator.genSuccessResult();
	}
	
	public static Result success(Object data) {
		return ResultGenerator.genSuccessResult(data);
	}
	
	public static Result failure(String message) {
		return ResultGenerator.genFailResult(message);
	}
	
	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Integer> toList(String ids){
		List<Integer> idList=new ArrayList<Integer>();
		if(StringUtils.isEmpty(ids)) {
			return idList;
		}
		String[] idStrList=ids.split(",");
		for(String s:idStrList) {
			idList.add(Integer.parseInt(s));
		}
		return idList;
	}
}
