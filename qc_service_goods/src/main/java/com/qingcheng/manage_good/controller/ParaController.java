package com.qingcheng.manage_good.controller;


import com.qingcheng.framework.domain.goods.Para;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;

import com.qingcheng.manage_good.controller.api.ParaControllerApi;
import com.qingcheng.manage_good.service.ParaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/para")
public class ParaController implements ParaControllerApi {


    @Autowired
    private ParaService paraService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Para> findAll(){
        return paraService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Para findParaById(@PathVariable("id") Integer id) {
        return paraService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addPara(@RequestBody Para para) {
        return paraService.add(para);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updatePara(@RequestBody Para para) {
        return paraService.update(para);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deletePara(@PathVariable("id") Integer id) {
        return paraService.deletePara(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Para> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return paraService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Para> findList(@RequestBody Map searchMap) {
        return paraService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Para> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return paraService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return paraService.ossUpdate(file,folder);
    }

}
