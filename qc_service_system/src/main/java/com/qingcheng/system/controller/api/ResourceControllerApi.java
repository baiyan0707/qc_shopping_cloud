package com.qingcheng.system.controller.api;


import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.domain.system.Resource;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "resource接口", description = "提供resource查询及管理", tags = {"resource接口"})
public interface ResourceControllerApi{


	@ApiOperation("查询所有")
     List<Resource> findAll();

	@ApiOperation("根据id查询")
     Resource findResourceById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addResource(Resource resource) ;
	
	@ApiOperation("修改")
     ResponseResult updateResource(Resource resource);

	@ApiOperation("删除")
     ResponseResult deleteResource(Integer id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<Resource> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Resource> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Resource> findPageList(Integer page, Integer size, Map searchMap);

}
