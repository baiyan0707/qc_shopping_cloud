package com.qingcheng.system.controller;

import com.qingcheng.framework.domain.system.Resource;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.system.controller.api.ResourceControllerApi;
import com.qingcheng.system.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/resource")
public class ResourceController implements ResourceControllerApi {


    @Autowired
    private ResourceService resourceService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Resource> findAll(){
        return resourceService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Resource findResourceById(@PathVariable("id") Integer id) {
        return resourceService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addResource(@RequestBody Resource resource) {
        return resourceService.add(resource);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateResource(@RequestBody Resource resource) {
        return resourceService.update(resource);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteResource(@PathVariable("id") Integer id) {
        return resourceService.deleteResource(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Resource> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return resourceService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Resource> findList(@RequestBody Map searchMap) {
        return resourceService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Resource> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return resourceService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return resourceService.ossUpdate(file,folder);
    }

}
