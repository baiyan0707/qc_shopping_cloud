package com.qingcheng.system.controller;


import com.qingcheng.framework.domain.system.Role;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.system.controller.api.RoleControllerApi;
import com.qingcheng.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController implements RoleControllerApi {


    @Autowired
    private RoleService roleService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Role> findAll(){
        return roleService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Role findRoleById(@PathVariable("id") Integer id) {
        return roleService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addRole(@RequestBody Role role) {
        return roleService.add(role);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateRole(@RequestBody Role role) {
        return roleService.update(role);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteRole(@PathVariable("id") Integer id) {
        return roleService.deleteRole(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Role> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return roleService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Role> findList(@RequestBody Map searchMap) {
        return roleService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Role> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return roleService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return roleService.ossUpdate(file,folder);
    }

}
