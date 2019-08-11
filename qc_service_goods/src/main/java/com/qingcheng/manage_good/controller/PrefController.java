package com.qingcheng.manage_good.controller;


import com.qingcheng.framework.domain.goods.Pref;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.manage_good.controller.api.PrefControllerApi;
import com.qingcheng.manage_good.service.PrefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pref")
public class PrefController implements PrefControllerApi {


    @Autowired
    private PrefService prefService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Pref> findAll(){
        return prefService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Pref findPrefById(@PathVariable("id") Integer id) {
        return prefService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addPref(@RequestBody Pref pref) {
        return prefService.add(pref);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updatePref(@RequestBody Pref pref) {
        return prefService.update(pref);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deletePref(@PathVariable("id") Integer id) {
        return prefService.deletePref(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Pref> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return prefService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Pref> findList(@RequestBody Map searchMap) {
        return prefService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Pref> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return prefService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return prefService.ossUpdate(file,folder);
    }

}
