package com.company.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.project.core.AbstractService;
import com.company.project.core.Result;
import com.company.project.dao.NovelMapper;
import com.company.project.model.Chapter;
import com.company.project.model.Novel;
import com.company.project.util.Conditions;
import com.company.project.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * Created by CodeGenerator on 2019/08/27.
 */
@Service
@Transactional
public class NovelService extends AbstractService<Novel> {
    @Autowired
    private NovelMapper novelMapper;
    
    @Autowired
    private ChapterService chapterService;
    
    public Result list(String name, Page page) {
		PageHelper.startPage(page.getPageNum(), page.getPageSize());
		Conditions con = Conditions.instance(Novel.class);
		if (StringUtils.isNotEmpty(name)) {
			con.firstCriteria().andLike("name", "%" + name + "%");
		}
		List<Novel> list = this.findByCondition(con);
		PageInfo pageResult = new PageInfo(list);
		return Utils.success(pageResult);
	}
    
    public Result findChapterByNovelId(int novelId) {
    	Novel novel=this.findById(novelId);
    	Map<String,Object> map=new HashMap<>();
    	map.put("novel", novel.getName());
    	map.put("author", novel.getAuthor());
    	map.put("chapters", chapterService.getChaptersByNovelId(novelId));
    	return Utils.success(map);
    }

    public Result findDetailByChapterId(int chapterId) {
    	Map<String,Object> map=new HashMap<>();
    	Chapter current=chapterService.findById(chapterId);
    	map.put("content", current.getContent());
    	map.put("novelId", current.getNovelid());
    	map.put("title", current.getTitle());
    	map.put("prev", this.findChapterByNovelAndSort(current.getNovelid(),current.getSort()-1));
    	map.put("next", this.findChapterByNovelAndSort(current.getNovelid(),current.getSort()+1));
    	return Utils.success(map);
    }
    
    public Integer findChapterByNovelAndSort(int novelId,int sort) {
    	Conditions con=Conditions.instance(Chapter.class);
    	con.selectProperties("id");
    	con.firstCriteria().andEqualTo("novelid",novelId).andEqualTo("sort",sort);
    	Chapter chapter=chapterService.findOneByCondition(con);
    	if(chapter==null) {
    		return null;
    	}
    	return chapter.getId();
    }
}
