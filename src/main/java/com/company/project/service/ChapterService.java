package com.company.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.AbstractService;
import com.company.project.dao.ChapterMapper;
import com.company.project.model.Chapter;
import com.company.project.util.Conditions;
import com.company.project.util.Converts;


/**
 * Created by CodeGenerator on 2019/08/27.
 */
@Service
@Transactional
public class ChapterService extends AbstractService<Chapter> {
    @Autowired
    private ChapterMapper chapterMapper;
    
    public List<Map<String,Object>> getChaptersByNovelId(int novelId){
    	Conditions con=Conditions.instance(Chapter.class);
    	con.selectProperties("id","title");
    	con.firstCriteria().andEqualTo("novelid",novelId);
    	return Converts.toMapList(this.findByCondition(con), (t)->{
    		Map<String,Object> map=new HashMap<>();
    		map.put("id", t.getId());
    		map.put("title", t.getTitle());
    		return map;
    	});
    }

}
