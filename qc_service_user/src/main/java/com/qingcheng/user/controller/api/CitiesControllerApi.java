package com.qingcheng.user.controller.api;


import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.domain.user.Cities;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "cities接口", description = "提供cities查询及管理", tags = {"cities接口"})
public interface CitiesControllerApi{


	@ApiOperation("查询所有")
     List<Cities> findAll();

	@ApiOperation("根据id查询")
     Cities findCitiesById(String cityid) ;
	
	@ApiOperation("添加")
     ResponseResult addCities(Cities cities) ;
	
	@ApiOperation("修改")
     ResponseResult updateCities(Cities cities);

	@ApiOperation("删除")
     ResponseResult deleteCities(String cityid) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<Cities> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Cities> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Cities> findPageList(Integer page, Integer size, Map searchMap);

}
