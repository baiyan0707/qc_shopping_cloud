package com.qingcheng.manage_good.controller.api;


import com.qingcheng.framework.domain.business.Ad;
import com.qingcheng.framework.domain.goods.Sku;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "sku接口", description = "提供sku查询及管理", tags = {"sku接口"})
public interface SkuControllerApi{


	@ApiOperation("查询所有")
     List<Sku> findAll();

	@ApiOperation("根据id查询")
     Sku findSkuById(String id) ;
	
	@ApiOperation("添加")
     ResponseResult addSku(Sku sku) ;
	
	@ApiOperation("修改")
     ResponseResult updateSku(Sku sku);

	@ApiOperation("删除")
     ResponseResult deleteSku(String id) ;
	
	@ApiOperation("分页查询")
     QueryBrandResult<Sku> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Sku> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Sku> findPageList(Integer page, Integer size, Map searchMap);

}
