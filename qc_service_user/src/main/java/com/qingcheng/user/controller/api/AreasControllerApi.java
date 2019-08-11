package com.qingcheng.user.controller.api;


import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.domain.user.Areas;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "areas接口", description = "提供areas查询及管理", tags = {"areas接口"})
public interface AreasControllerApi{


	@ApiOperation("查询所有")
     List<Areas> findAll();

	@ApiOperation("根据id查询")
     Areas findAreasById(String areaid) ;
	
	@ApiOperation("添加")
     ResponseResult addAreas(Areas areas) ;
	
	@ApiOperation("修改")
     ResponseResult updateAreas(Areas areas);

	@ApiOperation("删除")
     ResponseResult deleteAreas(String areaid) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<Areas> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Areas> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Areas> findPageList(Integer page, Integer size, Map searchMap);

}
