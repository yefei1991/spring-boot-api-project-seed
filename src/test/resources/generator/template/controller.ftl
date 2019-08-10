package ${basePackage}.web;
import ${basePackage}.core.Result;
import ${basePackage}.core.ResultGenerator;
import ${basePackage}.core.AbstractController;
import ${basePackage}.service.${modelNameUpperCamel}Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller extends AbstractController{
    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @GetMapping("/test")
    public Result test() {
        return ResultGenerator.genSuccessResult(${modelNameLowerCamel}Service.findAll());
    }

}
