package com.qingcheng.user.controller;


import com.qingcheng.framework.domain.user.Areas;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.user.controller.api.AreasControllerApi;
import com.qingcheng.user.service.AreasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/areas")
public class AreasController implements AreasControllerApi {


    @Autowired
    private AreasService areasService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Areas> findAll(){
        return areasService.findAll();
    }

	
    @Override
    @GetMapping("/get/{areaid}")
    public Areas findAreasById(@PathVariable("areaid") String areaid) {
        return areasService.findById(areaid);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addAreas(@RequestBody Areas areas) {
        return areasService.add(areas);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateAreas(@RequestBody Areas areas) {
        return areasService.update(areas);
    }
	

    @Override
    @DeleteMapping("/delete/{areaid}")
    public ResponseResult deleteAreas(@PathVariable("areaid") String areaid) {
        return areasService.deleteAreas(areaid);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Areas> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return areasService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Areas> findList(@RequestBody Map searchMap) {
        return areasService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Areas> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return areasService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return areasService.ossUpdate(file,folder);
    }

}
