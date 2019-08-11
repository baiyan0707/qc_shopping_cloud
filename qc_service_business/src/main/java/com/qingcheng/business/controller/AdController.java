package com.qingcheng.business.controller;

import com.qingcheng.business.controller.api.AdControllerApi;
import com.qingcheng.business.service.AdService;
import com.qingcheng.framework.domain.business.Ad;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ad")
public class AdController implements AdControllerApi {


    @Autowired
    private AdService adService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Ad> findAll(){
        return adService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Ad findAdById(@PathVariable("id") Integer id) {
        return adService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addAd(@RequestBody Ad ad) {
        return adService.add(ad);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateAd(@RequestBody Ad ad) {
        return adService.update(ad);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteAd(@PathVariable("id") Integer id) {
        return adService.deleteAd(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Ad> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return adService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Ad> findList(@RequestBody Map searchMap) {
        return adService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Ad> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return adService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return adService.ossUpdate(file,folder);
    }

}
