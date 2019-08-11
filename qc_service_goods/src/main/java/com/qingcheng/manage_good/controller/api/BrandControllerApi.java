package com.qingcheng.manage_good.controller.api;

import com.qingcheng.framework.domain.business.Ad;
import com.qingcheng.framework.domain.goods.Brand;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.QueryResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "brand接口", description = "提供brand查询及管理", tags = {"brand接口"})
public interface BrandControllerApi{


	@ApiOperation("查询所有")
     List<Brand> findAll();

	@ApiOperation("根据id查询")
     Brand findBrandById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addBrand(Brand brand) ;
	
	@ApiOperation("修改")
     ResponseResult updateBrand(Brand brand);

	@ApiOperation("删除")
     ResponseResult deleteBrand(Integer id) ;
	
	@ApiOperation("分页查询")
     QueryBrandResult<Brand> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Brand> findList(Map searchMap) ;

	@ApiOperation("综合查询")
    QueryBrandResult<Brand> findPageList(Integer page, Integer size, Map searchMap);

}
