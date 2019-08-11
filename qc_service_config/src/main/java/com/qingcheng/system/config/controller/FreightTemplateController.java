package com.qingcheng.system.config.controller;

import com.qingcheng.system.config.controller.api.FreightTemplateControllerApi;
import com.qingcheng.system.config.service.FreightTemplateService;
import com.qingcheng.framework.domain.config.FreightTemplate;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/freightTemplate")
public class FreightTemplateController implements FreightTemplateControllerApi {


    @Autowired
    private FreightTemplateService freightTemplateService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<FreightTemplate> findAll(){
        return freightTemplateService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public FreightTemplate findFreightTemplateById(@PathVariable("id") Integer id) {
        return freightTemplateService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addFreightTemplate(@RequestBody FreightTemplate freightTemplate) {
        return freightTemplateService.add(freightTemplate);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateFreightTemplate(@RequestBody FreightTemplate freightTemplate) {
        return freightTemplateService.update(freightTemplate);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteFreightTemplate(@PathVariable("id") Integer id) {
        return freightTemplateService.deleteFreightTemplate(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<FreightTemplate> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return freightTemplateService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<FreightTemplate> findList(@RequestBody Map searchMap) {
        return freightTemplateService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<FreightTemplate> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return freightTemplateService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return freightTemplateService.ossUpdate(file,folder);
    }

}
