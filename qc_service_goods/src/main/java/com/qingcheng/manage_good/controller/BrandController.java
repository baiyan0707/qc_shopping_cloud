package com.qingcheng.manage_good.controller;


import com.qingcheng.framework.domain.goods.Brand;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;

import com.qingcheng.manage_good.controller.api.BrandControllerApi;
import com.qingcheng.manage_good.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController implements BrandControllerApi {


    @Autowired
    private BrandService brandService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Brand> findAll(){
        return brandService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Brand findBrandById(@PathVariable("id") Integer id) {
        return brandService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addBrand(@RequestBody Brand brand) {
        return brandService.add(brand);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateBrand(@RequestBody Brand brand) {
        return brandService.update(brand);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteBrand(@PathVariable("id") Integer id) {
        return brandService.deleteBrand(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Brand> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return brandService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Brand> findList(@RequestBody Map searchMap) {
        return brandService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Brand> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return brandService.findPageList(page,size,searchMap);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return brandService.ossUpdate(file,folder);
    }

}
