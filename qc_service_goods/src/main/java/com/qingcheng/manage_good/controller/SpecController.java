package com.qingcheng.manage_good.controller;


import com.qingcheng.framework.domain.goods.Spec;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.manage_good.controller.api.SpecControllerApi;
import com.qingcheng.manage_good.service.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spec")
public class SpecController implements SpecControllerApi {


    @Autowired
    private SpecService specService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Spec> findAll(){
        return specService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Spec findSpecById(@PathVariable("id") Integer id) {
        return specService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addSpec(@RequestBody Spec spec) {
        return specService.add(spec);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateSpec(@RequestBody Spec spec) {
        return specService.update(spec);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteSpec(@PathVariable("id") Integer id) {
        return specService.deleteSpec(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Spec> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return specService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Spec> findList(@RequestBody Map searchMap) {
        return specService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Spec> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return specService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return specService.ossUpdate(file,folder);
    }

}
