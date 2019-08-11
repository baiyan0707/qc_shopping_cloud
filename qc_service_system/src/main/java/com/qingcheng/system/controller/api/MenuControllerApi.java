package com.qingcheng.system.controller.api;


import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.domain.system.Menu;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "menu接口", description = "提供menu查询及管理", tags = {"menu接口"})
public interface MenuControllerApi{


	@ApiOperation("查询所有")
     List<Menu> findAll();

	@ApiOperation("根据id查询")
     Menu findMenuById(String id) ;
	
	@ApiOperation("添加")
     ResponseResult addMenu(Menu menu) ;
	
	@ApiOperation("修改")
     ResponseResult updateMenu(Menu menu);

	@ApiOperation("删除")
     ResponseResult deleteMenu(String id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<Menu> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Menu> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Menu> findPageList(Integer page, Integer size, Map searchMap);

}
