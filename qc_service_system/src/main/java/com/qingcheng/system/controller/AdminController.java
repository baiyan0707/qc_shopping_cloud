package com.qingcheng.system.controller;

import com.qingcheng.framework.domain.system.Admin;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.system.controller.api.AdminControllerApi;
import com.qingcheng.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController implements AdminControllerApi {


    @Autowired
    private AdminService adminService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Admin> findAll(){
        return adminService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Admin findAdminById(@PathVariable("id") Integer id) {
        return adminService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addAdmin(@RequestBody Admin admin) {
        return adminService.add(admin);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateAdmin(@RequestBody Admin admin) {
        return adminService.update(admin);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteAdmin(@PathVariable("id") Integer id) {
        return adminService.deleteAdmin(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Admin> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return adminService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Admin> findList(@RequestBody Map searchMap) {
        return adminService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Admin> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return adminService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return adminService.ossUpdate(file,folder);
    }

}
