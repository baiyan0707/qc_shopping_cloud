package com.qingcheng.order.controller;


import com.qingcheng.framework.domain.order.ReturnCause;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.order.controller.api.ReturnCauseControllerApi;

import com.qingcheng.order.service.ReturnCauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/returnCause")
public class ReturnCauseController implements ReturnCauseControllerApi {


    @Autowired
    private ReturnCauseService returnCauseService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<ReturnCause> findAll(){
        return returnCauseService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public ReturnCause findReturnCauseById(@PathVariable("id") Integer id) {
        return returnCauseService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addReturnCause(@RequestBody ReturnCause returnCause) {
        return returnCauseService.add(returnCause);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateReturnCause(@RequestBody ReturnCause returnCause) {
        return returnCauseService.update(returnCause);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteReturnCause(@PathVariable("id") Integer id) {
        return returnCauseService.deleteReturnCause(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<ReturnCause> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return returnCauseService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<ReturnCause> findList(@RequestBody Map searchMap) {
        return returnCauseService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<ReturnCause> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return returnCauseService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return returnCauseService.ossUpdate(file,folder);
    }

}
