package com.qingcheng.user.controller.api;


import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.domain.user.Address;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;

/**
 * @author Bai
 */
@Api(value = "address接口", description = "提供address查询及管理", tags = {"address接口"})
public interface AddressControllerApi{


	@ApiOperation("查询所有")
     List<Address> findAll();

	@ApiOperation("根据id查询")
     Address findAddressById(Integer id) ;
	
	@ApiOperation("添加")
     ResponseResult addAddress(Address address) ;
	
	@ApiOperation("修改")
     ResponseResult updateAddress(Address address);

	@ApiOperation("删除")
     ResponseResult deleteAddress(Integer id) ;
	
	@ApiOperation("分页查询")
    QueryBrandResult<Address> findPage(Integer page, Integer size);
   
	@ApiOperation("条件查询")
     List<Address> findList(Map searchMap) ;

	@ApiOperation("综合查询")
     QueryBrandResult<Address> findPageList(Integer page, Integer size, Map searchMap);

}
