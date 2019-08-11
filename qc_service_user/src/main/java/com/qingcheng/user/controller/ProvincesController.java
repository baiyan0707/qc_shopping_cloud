package com.qingcheng.user.controller;

import com.qingcheng.framework.domain.user.Provinces;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.user.controller.api.ProvincesControllerApi;
import com.qingcheng.user.service.ProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/provinces")
public class ProvincesController implements ProvincesControllerApi {


    @Autowired
    private ProvincesService provincesService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Provinces> findAll(){
        return provincesService.findAll();
    }

	
    @Override
    @GetMapping("/get/{provinceid}")
    public Provinces findProvincesById(@PathVariable("provinceid") String provinceid) {
        return provincesService.findById(provinceid);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addProvinces(@RequestBody Provinces provinces) {
        return provincesService.add(provinces);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateProvinces(@RequestBody Provinces provinces) {
        return provincesService.update(provinces);
    }
	

    @Override
    @DeleteMapping("/delete/{provinceid}")
    public ResponseResult deleteProvinces(@PathVariable("provinceid") String provinceid) {
        return provincesService.deleteProvinces(provinceid);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Provinces> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return provincesService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Provinces> findList(@RequestBody Map searchMap) {
        return provincesService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Provinces> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return provincesService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return provincesService.ossUpdate(file,folder);
    }

}
