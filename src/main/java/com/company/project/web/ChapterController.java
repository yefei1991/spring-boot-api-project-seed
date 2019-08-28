package com.company.project.web;
import com.company.project.core.Result;
import com.company.project.core.ResultGenerator;
import com.company.project.core.AbstractController;
import com.company.project.service.ChapterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

/**
* Created by CodeGenerator on 2019/08/27.
*/
@RestController
@RequestMapping("/chapter")
public class ChapterController extends AbstractController{
    @Autowired
    private ChapterService chapterService;

    @GetMapping("/test")
    public Result test() {
        return ResultGenerator.genSuccessResult(chapterService.findAll());
    }

}
