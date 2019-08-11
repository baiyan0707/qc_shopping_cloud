package com.qingcheng.manage_good.controller.api;

import com.qingcheng.framework.domain.business.Ad;
import com.qingcheng.framework.domain.goods.Category;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "category接口", description = "提供category查询及管理", tags = {"category接口"})
public interface CategoryControllerApi{


	@ApiOperation("查询所有")
     List<Category> findAll();

	@ApiOperation("根据id查询")
     Category findCategoryById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addCategory(Category category) ;
	
	@ApiOperation("修改")
     ResponseResult updateCategory(Category category);

	@ApiOperation("删除")
     ResponseResult deleteCategory(Integer id) ;
	
	@ApiOperation("分页查询")
     QueryBrandResult<Category> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Category> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Category> findPageList(Integer page, Integer size, Map searchMap);

}
