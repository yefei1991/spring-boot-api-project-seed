package com.company.project.service.commonl;

import java.util.List;

import com.company.project.vo.Dic;

public interface DictionaryCache {

	void cacheAll();
	
	void refreshByCode(String code);
	
	void deleteByCode(String code);

	List<Dic> findByCode(String code);
}
