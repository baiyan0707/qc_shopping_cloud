package com.qingcheng.manage_good.controller;


import com.qingcheng.framework.domain.goods.Category;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;

import com.qingcheng.manage_good.controller.api.CategoryControllerApi;
import com.qingcheng.manage_good.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryControllerApi {


    @Autowired
    private CategoryService categoryService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Category> findAll(){
        return categoryService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Category findCategoryById(@PathVariable("id") Integer id) {
        return categoryService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addCategory(@RequestBody Category category) {
        return categoryService.add(category);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateCategory(@RequestBody Category category) {
        return categoryService.update(category);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteCategory(@PathVariable("id") Integer id) {
        return categoryService.deleteCategory(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Category> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return categoryService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Category> findList(@RequestBody Map searchMap) {
        return categoryService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Category> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return categoryService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return categoryService.ossUpdate(file,folder);
    }

}
