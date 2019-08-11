package com.qingcheng.user.controller;

import com.qingcheng.framework.domain.user.Cities;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.user.controller.api.CitiesControllerApi;
import com.qingcheng.user.service.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cities")
public class CitiesController implements CitiesControllerApi {


    @Autowired
    private CitiesService citiesService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Cities> findAll(){
        return citiesService.findAll();
    }

	
    @Override
    @GetMapping("/get/{cityid}")
    public Cities findCitiesById(@PathVariable("cityid") String cityid) {
        return citiesService.findById(cityid);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addCities(@RequestBody Cities cities) {
        return citiesService.add(cities);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateCities(@RequestBody Cities cities) {
        return citiesService.update(cities);
    }
	

    @Override
    @DeleteMapping("/delete/{cityid}")
    public ResponseResult deleteCities(@PathVariable("cityid") String cityid) {
        return citiesService.deleteCities(cityid);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Cities> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return citiesService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Cities> findList(@RequestBody Map searchMap) {
        return citiesService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Cities> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return citiesService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return citiesService.ossUpdate(file,folder);
    }

}
