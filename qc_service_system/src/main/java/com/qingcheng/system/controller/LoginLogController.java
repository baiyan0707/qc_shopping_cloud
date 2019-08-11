package com.qingcheng.system.controller;

import com.qingcheng.framework.domain.system.LoginLog;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.system.controller.api.LoginLogControllerApi;
import com.qingcheng.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/loginLog")
public class LoginLogController implements LoginLogControllerApi {


    @Autowired
    private LoginLogService loginLogService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<LoginLog> findAll(){
        return loginLogService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public LoginLog findLoginLogById(@PathVariable("id") Integer id) {
        return loginLogService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addLoginLog(@RequestBody LoginLog loginLog) {
        return loginLogService.add(loginLog);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateLoginLog(@RequestBody LoginLog loginLog) {
        return loginLogService.update(loginLog);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteLoginLog(@PathVariable("id") Integer id) {
        return loginLogService.deleteLoginLog(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<LoginLog> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return loginLogService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<LoginLog> findList(@RequestBody Map searchMap) {
        return loginLogService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<LoginLog> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return loginLogService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return loginLogService.ossUpdate(file,folder);
    }

}
