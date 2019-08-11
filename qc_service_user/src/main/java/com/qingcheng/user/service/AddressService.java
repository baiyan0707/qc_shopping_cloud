package com.qingcheng.user.service;

import com.aliyun.oss.OSSClient;
import com.qingcheng.framework.domain.user.Address;
import com.qingcheng.user.dao.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import com.qingcheng.framework.domain.request.QueryBrandResult;
import com.qingcheng.framework.exception.ExceptionCast;
import com.qingcheng.framework.exception.model.response.GoodsCode;
import com.qingcheng.framework.exception.model.response.QueryResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;

import java.io.IOException;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import java.util.Map;
import java.util.UUID;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class AddressService {

    @Autowired
    private AddressMapper addressMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<Address> findAll(){
        return addressMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Address findById(Integer id){
		 //参数校验
        if (id == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  addressMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     * @param address
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(Address address) {
        if (address == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		addressMapper.insert(address);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param address
     */
	@Transactional
    public ResponseResult update(Address address) {
        if (address==null||address.getId() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = addressMapper.updateByPrimaryKeySelective(address);
        if (count != 1) {
            ExceptionCast.cast(GoodsCode.UPDATE_ERROR);
        }
        return ResponseResult.SUCCESS();
    }

    /**
     * @param id 所有品牌id集合
     * @return 根据所有品牌id 删除对应的品牌信息 并返回操作结果
     */
    @Transactional
    public ResponseResult deleteAddress(Integer id) {
        int result = addressMapper.deleteByPrimaryKey(id);
        if(result > 0){
            return new ResponseResult(GoodsCode.SUCCESS);
        }
        return new ResponseResult(GoodsCode.DELETE_ERROR);
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    public QueryBrandResult<Address> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<Address> address = (Page<Address>) addressMapper.selectAll();
        QueryResult<Address> queryResult = new QueryResult<>();
        List<Address> result = address.getResult();
        long total = address.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<Address> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return addressMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Address.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 用户名
            if (StringUtils.isNotEmpty((String) searchMap.get("username"))) {
                criteria.andLike("username","%"+searchMap.get("username")+"%");
           	}
            // 省
            if (StringUtils.isNotEmpty((String) searchMap.get("provinceid"))) {
                criteria.andLike("provinceid","%"+searchMap.get("provinceid")+"%");
           	}
            // 市
            if (StringUtils.isNotEmpty((String) searchMap.get("cityid"))) {
                criteria.andLike("cityid","%"+searchMap.get("cityid")+"%");
           	}
            // 县/区
            if (StringUtils.isNotEmpty((String) searchMap.get("areaid"))) {
                criteria.andLike("areaid","%"+searchMap.get("areaid")+"%");
           	}
            // 电话
            if (StringUtils.isNotEmpty((String) searchMap.get("phone"))) {
                criteria.andLike("phone","%"+searchMap.get("phone")+"%");
           	}
            // 详细地址
            if (StringUtils.isNotEmpty((String) searchMap.get("address"))) {
                criteria.andLike("address","%"+searchMap.get("address")+"%");
           	}
            // 联系人
            if (StringUtils.isNotEmpty((String) searchMap.get("contact"))) {
                criteria.andLike("contact","%"+searchMap.get("contact")+"%");
           	}
            // 是否是默认 1默认 0否
            if (StringUtils.isNotEmpty((String) searchMap.get("is_default"))) {
                criteria.andLike("is_default","%"+searchMap.get("is_default")+"%");
           	}
            // 别名
            if (StringUtils.isNotEmpty((String) searchMap.get("alias"))) {
                criteria.andLike("alias","%"+searchMap.get("alias")+"%");
           	}

            // id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }

        }
        return example;
    }

    /**
     * 条件+分页查询
     * @param searchMap 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    public QueryBrandResult<Address> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<Address> brands = (Page<Address>) addressMapper.selectByExample(example);
        QueryResult<Address> queryResult = new QueryResult<>();
        List<Address> result = brands.getResult();
        long total = brands.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }
	
	/**
     * 将图片上传到oss服务器
     * @param file
     * @param folder
     * @return
     */
    public String ossUpdate(MultipartFile file, String folder){
        String bucketName = "qingcheng0707";
        String fileName = folder + "/" + UUID.randomUUID() + file.getOriginalFilename();

        try {
            ossClient.putObject(bucketName,fileName,file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "http://" + bucketName + "oss-ap-northeast-1.aliyuncs.com/" + fileName;
    }
}
