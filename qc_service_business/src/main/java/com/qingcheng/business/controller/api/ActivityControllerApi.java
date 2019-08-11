package com.qingcheng.business.controller.api;


import com.qingcheng.framework.domain.business.Activity;
import com.qingcheng.framework.domain.business.Ad;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "activity接口", description = "提供activity查询及管理", tags = {"activity接口"})
public interface ActivityControllerApi{


	@ApiOperation("查询所有")
     List<Activity> findAll();

	@ApiOperation("根据id查询")
     Activity findActivityById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addActivity(Activity activity) ;
	
	@ApiOperation("修改")
     ResponseResult updateActivity(Activity activity);

	@ApiOperation("删除")
     ResponseResult deleteActivity(Integer id) ;
	
	@ApiOperation("分页查询")
     QueryBrandResult<Activity> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Activity> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Activity> findPageList(Integer page, Integer size, Map searchMap);

}
