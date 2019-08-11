package com.qingcheng.manage_good.controller;


import com.qingcheng.framework.domain.goods.Template;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.manage_good.controller.api.TemplateControllerApi;
import com.qingcheng.manage_good.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/template")
public class TemplateController implements TemplateControllerApi {


    @Autowired
    private TemplateService templateService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Template> findAll(){
        return templateService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Template findTemplateById(@PathVariable("id") Integer id) {
        return templateService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addTemplate(@RequestBody Template template) {
        return templateService.add(template);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateTemplate(@RequestBody Template template) {
        return templateService.update(template);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteTemplate(@PathVariable("id") Integer id) {
        return templateService.deleteTemplate(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Template> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return templateService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Template> findList(@RequestBody Map searchMap) {
        return templateService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Template> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return templateService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return templateService.ossUpdate(file,folder);
    }

}
