package com.qingcheng.user.service;

import com.aliyun.oss.OSSClient;
import com.qingcheng.framework.domain.user.Cities;
import com.qingcheng.user.dao.CitiesMapper;
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
public class CitiesService {

    @Autowired
    private CitiesMapper citiesMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<Cities> findAll(){
        return citiesMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param cityid
     * @return
     */
    public Cities findById(String cityid){
		 //参数校验
        if (cityid == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  citiesMapper.selectByPrimaryKey(cityid);
    }

    /**
     * 增加
     * @param cities
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(Cities cities) {
        if (cities == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		citiesMapper.insert(cities);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param cities
     */
	@Transactional
    public ResponseResult update(Cities cities) {
        if (cities==null||cities.getCityid() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = citiesMapper.updateByPrimaryKeySelective(cities);
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
    public ResponseResult deleteCities(String id) {
        int result = citiesMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<Cities> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<Cities> cities = (Page<Cities>) citiesMapper.selectAll();
        QueryResult<Cities> queryResult = new QueryResult<>();
        List<Cities> result = cities.getResult();
        long total = cities.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<Cities> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return citiesMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Cities.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 城市ID
            if (StringUtils.isNotEmpty((String) searchMap.get("cityid"))) {
                criteria.andLike("cityid","%"+searchMap.get("cityid")+"%");
           	}
            // 城市名称
            if (StringUtils.isNotEmpty((String) searchMap.get("city"))) {
                criteria.andLike("city","%"+searchMap.get("city")+"%");
           	}
            // 省份ID
            if (StringUtils.isNotEmpty((String) searchMap.get("provinceid"))) {
                criteria.andLike("provinceid","%"+searchMap.get("provinceid")+"%");
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
    public QueryBrandResult<Cities> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<Cities> brands = (Page<Cities>) citiesMapper.selectByExample(example);
        QueryResult<Cities> queryResult = new QueryResult<>();
        List<Cities> result = brands.getResult();
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
