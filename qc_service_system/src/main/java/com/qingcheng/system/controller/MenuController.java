package com.qingcheng.system.controller;

import com.qingcheng.framework.domain.system.Menu;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.system.controller.api.MenuControllerApi;
import com.qingcheng.system.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController implements MenuControllerApi {


    @Autowired
    private MenuService menuService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Menu> findAll(){
        return menuService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Menu findMenuById(@PathVariable("id") String id) {
        return menuService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addMenu(@RequestBody Menu menu) {
        return menuService.add(menu);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateMenu(@RequestBody Menu menu) {
        return menuService.update(menu);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteMenu(@PathVariable("id") String id) {
        return menuService.deleteMenu(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Menu> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return menuService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Menu> findList(@RequestBody Map searchMap) {
        return menuService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Menu> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return menuService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return menuService.ossUpdate(file,folder);
    }

}
