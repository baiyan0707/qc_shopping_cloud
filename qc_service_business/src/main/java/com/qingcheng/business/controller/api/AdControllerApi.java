package com.qingcheng.business.controller.api;


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
@Api(value = "ad接口", description = "提供ad查询及管理", tags = {"ad接口"})
public interface AdControllerApi{


	@ApiOperation("查询所有")
     List<Ad> findAll();

	@ApiOperation("根据id查询")
     Ad findAdById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addAd(Ad ad) ;
	
	@ApiOperation("修改")
     ResponseResult updateAd(Ad ad);

	@ApiOperation("删除")
     ResponseResult deleteAd(Integer id) ;
	
	@ApiOperation("分页查询")
     QueryBrandResult<Ad> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Ad> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Ad> findPageList(Integer page, Integer size, Map searchMap);

}
