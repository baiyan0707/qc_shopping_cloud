package com.qingcheng.order.controller;


import com.qingcheng.framework.domain.order.Preferential;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.order.controller.api.PreferentialControllerApi;
import com.qingcheng.order.service.PreferentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/preferential")
public class PreferentialController implements PreferentialControllerApi {


    @Autowired
    private PreferentialService preferentialService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Preferential> findAll(){
        return preferentialService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Preferential findPreferentialById(@PathVariable("id") Integer id) {
        return preferentialService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addPreferential(@RequestBody Preferential preferential) {
        return preferentialService.add(preferential);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updatePreferential(@RequestBody Preferential preferential) {
        return preferentialService.update(preferential);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deletePreferential(@PathVariable("id") Integer id) {
        return preferentialService.deletePreferential(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Preferential> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return preferentialService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Preferential> findList(@RequestBody Map searchMap) {
        return preferentialService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Preferential> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return preferentialService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return preferentialService.ossUpdate(file,folder);
    }

}
