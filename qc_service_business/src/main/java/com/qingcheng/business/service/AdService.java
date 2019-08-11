package com.qingcheng.business.service;

import com.aliyun.oss.OSSClient;
import com.qingcheng.business.dao.AdMapper;
import com.qingcheng.framework.domain.business.Ad;
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
public class AdService {

    @Autowired
    private AdMapper adMapper;

    @Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<Ad> findAll(){
        return adMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Ad findById(Integer id){
		 //参数校验
        if (id == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  adMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     * @param ad
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(Ad ad) {
        if (ad == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		adMapper.insert(ad);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param ad
     */
	@Transactional
    public ResponseResult update(Ad ad) {
        if (ad==null||ad.getId() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = adMapper.updateByPrimaryKeySelective(ad);
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
    public ResponseResult deleteAd(Integer id) {
        int result = adMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<Ad> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<Ad> ad = (Page<Ad>) adMapper.selectAll();
        QueryResult<Ad> queryResult = new QueryResult<>();
        List<Ad> result = ad.getResult();
        long total = ad.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<Ad> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return adMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Ad.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 广告名称
            if (StringUtils.isNotEmpty((String) searchMap.get("name"))) {
                criteria.andLike("name","%"+searchMap.get("name")+"%");
           	}
            // 广告位置
            if (StringUtils.isNotEmpty((String) searchMap.get("position"))) {
                criteria.andLike("position","%"+searchMap.get("position")+"%");
           	}
            // 状态
            if (StringUtils.isNotEmpty((String) searchMap.get("status"))) {
                criteria.andLike("status","%"+searchMap.get("status")+"%");
           	}
            // 图片地址
            if (StringUtils.isNotEmpty((String) searchMap.get("image"))) {
                criteria.andLike("image","%"+searchMap.get("image")+"%");
           	}
            // URL
            if (StringUtils.isNotEmpty((String) searchMap.get("url"))) {
                criteria.andLike("url","%"+searchMap.get("url")+"%");
           	}
            // 备注
            if (StringUtils.isNotEmpty((String) searchMap.get("remarks"))) {
                criteria.andLike("remarks","%"+searchMap.get("remarks")+"%");
           	}

            // ID
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
    public QueryBrandResult<Ad> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<Ad> brands = (Page<Ad>) adMapper.selectByExample(example);
        QueryResult<Ad> queryResult = new QueryResult<>();
        List<Ad> result = brands.getResult();
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
