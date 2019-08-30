package com.company.project.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Converts {
	
	public static <T> List<Map<String,Object>> toMapList(List<T> tList,Function<T,Map<String,Object>> f){
		List<Map<String,Object>> list=new ArrayList<>();
		for(T t:tList) {
			list.add(f.apply(t));
		}
		return list;
	}

}
