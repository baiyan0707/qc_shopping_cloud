package com.qingcheng.manage_good.controller.api;

import com.qingcheng.framework.domain.business.Ad;
import com.qingcheng.framework.domain.goods.Album;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "album接口", description = "提供album查询及管理", tags = {"album接口"})
public interface AlbumControllerApi{


	@ApiOperation("查询所有")
     List<Album> findAll();

	@ApiOperation("根据id查询")
     Album findAlbumById(Long id) ;
	
	@ApiOperation("添加")
     ResponseResult addAlbum(Album album) ;
	
	@ApiOperation("修改")
     ResponseResult updateAlbum(Album album);

	@ApiOperation("删除")
     ResponseResult deleteAlbum(Long id) ;
	
	@ApiOperation("分页查询")
     QueryBrandResult<Album> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Album> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Album> findPageList(Integer page, Integer size, Map searchMap);

}
