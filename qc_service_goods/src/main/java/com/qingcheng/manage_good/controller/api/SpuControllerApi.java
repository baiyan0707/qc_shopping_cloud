package com.qingcheng.manage_good.controller.api;


import com.qingcheng.framework.domain.business.Ad;
import com.qingcheng.framework.domain.goods.Spu;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "spu接口", description = "提供spu查询及管理", tags = {"spu接口"})
public interface SpuControllerApi{


	@ApiOperation("查询所有")
     List<Spu> findAll();

	@ApiOperation("根据id查询")
     Spu findSpuById(String id) ;
	
	@ApiOperation("添加")
     ResponseResult addSpu(Spu spu) ;
	
	@ApiOperation("修改")
     ResponseResult updateSpu(Spu spu);

	@ApiOperation("删除")
     ResponseResult deleteSpu(String id) ;
	
	@ApiOperation("分页查询")
     QueryBrandResult<Spu> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Spu> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Spu> findPageList(Integer page, Integer size, Map searchMap);

}
