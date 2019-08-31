package com.company.project.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.project.core.AbstractController;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.downloader.DingDianDownloader;
import com.company.project.service.NovelService;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;

/**
 * Created by CodeGenerator on 2019/08/27.
 */
@RestController
@RequestMapping("/novel")
public class NovelController extends AbstractController {
	@Autowired
	private NovelService novelService;

	@Autowired
	private DingDianDownloader downloader;

	@GetMapping("/test")
	public Result test() {
//		Spider spider = downloader.getSpider();
//		List<Request> requests = new ArrayList<>();
//		for (int i = 1; i <= 1; i++) {
//			Request request = new Request(
//					"https://www.x23us.com/modules/article/search.php?searchtype=keywords&searchkey=%C8%FD%B9%FA&page="
//							+ i);
//			request.putExtra("type", "novels");
//			requests.add(request);
//			spider.addRequest(request);
//		}
//		spider.runAsync();
		return ResultGenerator.genSuccessResult();
	}
	
	@GetMapping("/list")
    public Result list(String name) {
    	return novelService.list(name, getRequestPage());
    }
	
	@GetMapping("/chapters")
	public Result chapters(int novelId) {
		return novelService.findChapterByNovelId(novelId);
	}
	
	@GetMapping("/detail")
	public Result detail(int chapterId) {
		return novelService.findDetailByChapterId(chapterId);
	}
}
