package com.qingcheng.manage_good.controller;


import com.qingcheng.framework.domain.goods.Spu;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.manage_good.controller.api.SpuControllerApi;
import com.qingcheng.manage_good.service.SpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/spu")
public class SpuController implements SpuControllerApi {


    @Autowired
    private SpuService spuService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Spu> findAll(){
        return spuService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Spu findSpuById(@PathVariable("id") String id) {
        return spuService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addSpu(@RequestBody Spu spu) {
        return spuService.add(spu);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateSpu(@RequestBody Spu spu) {
        return spuService.update(spu);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteSpu(@PathVariable("id") String id) {
        return spuService.deleteSpu(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Spu> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return spuService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Spu> findList(@RequestBody Map searchMap) {
        return spuService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Spu> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return spuService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return spuService.ossUpdate(file,folder);
    }

}
