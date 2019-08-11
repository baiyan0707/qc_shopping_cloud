package com.qingcheng.business.controller;

import com.qingcheng.business.controller.api.ActivityControllerApi;
import com.qingcheng.business.service.ActivityService;
import com.qingcheng.framework.domain.business.Activity;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/activity")
public class ActivityController implements ActivityControllerApi {


    @Autowired
    private ActivityService activityService;

	
	
	@Override
    @GetMapping("/findAll")
    public List<Activity> findAll(){
        return activityService.findAll();
    }

	
    @Override
    @GetMapping("/get/{id}")
    public Activity findActivityById(@PathVariable("id") Integer id) {
        return activityService.findById(id);
    }
	
    @Override
    @PostMapping("/add")
    public ResponseResult addActivity(@RequestBody Activity activity) {
        return activityService.add(activity);
    }
	
	@Override
    @PutMapping("/update")
    public ResponseResult updateActivity(@RequestBody Activity activity) {
        return activityService.update(activity);
    }
	

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseResult deleteActivity(@PathVariable("id") Integer id) {
        return activityService.deleteActivity(id);
    }
	
    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryBrandResult<Activity> findPage(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return activityService.findPage(page,size);
    }
   
    @Override
    @PostMapping("/findList")
    public List<Activity> findList(@RequestBody Map searchMap) {
        return activityService.findList(searchMap);
    }
 
    @Override
    @PostMapping("/findPageList/{page}/{size}")
    public QueryBrandResult<Activity> findPageList(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody Map searchMap) {
        return activityService.findPageList(searchMap,page,size);
    }
	
	@PostMapping("/oss")
    public String getOss(@RequestParam("file") MultipartFile file, String folder){
	    return activityService.ossUpdate(file,folder);
    }

}
