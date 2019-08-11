package com.qingcheng.manage_good.controller;


import com.qingcheng.framework.domain.goods.Sku;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.manage_good.controller.api.SkuControllerApi;
import com.qingcheng.manage_good.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sku")
public class SkuController implements SkuControllerApi {


    @Autowired
    private SkuService skuService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Sku> findAll(){
        return skuService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Sku findSkuById(@PathVariable("id") String id) {
        return skuService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addSku(@RequestBody Sku sku) {
        return skuService.add(sku);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateSku(@RequestBody Sku sku) {
        return skuService.update(sku);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteSku(@PathVariable("id") String id) {
        return skuService.deleteSku(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Sku> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return skuService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Sku> findList(@RequestBody Map searchMap) {
        return skuService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Sku> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return skuService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return skuService.ossUpdate(file,folder);
    }

}
