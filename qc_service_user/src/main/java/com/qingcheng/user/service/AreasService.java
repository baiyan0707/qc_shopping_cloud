package com.qingcheng.user.service;

import com.aliyun.oss.OSSClient;
import com.qingcheng.framework.domain.user.Areas;
import com.qingcheng.user.dao.AreasMapper;
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
public class AreasService {

    @Autowired
    private AreasMapper areasMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<Areas> findAll(){
        return areasMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param areaid
     * @return
     */
    public Areas findById(String areaid){
		 //参数校验
        if (areaid == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  areasMapper.selectByPrimaryKey(areaid);
    }

    /**
     * 增加
     * @param areas
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(Areas areas) {
        if (areas == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		areasMapper.insert(areas);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param areas
     */
	@Transactional
    public ResponseResult update(Areas areas) {
        if (areas==null||areas.getAreaid() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = areasMapper.updateByPrimaryKeySelective(areas);
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
    public ResponseResult deleteAreas(String id) {
        int result = areasMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<Areas> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<Areas> areas = (Page<Areas>) areasMapper.selectAll();
        QueryResult<Areas> queryResult = new QueryResult<>();
        List<Areas> result = areas.getResult();
        long total = areas.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<Areas> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return areasMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Areas.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 区域ID
            if (StringUtils.isNotEmpty((String) searchMap.get("areaid"))) {
                criteria.andLike("areaid","%"+searchMap.get("areaid")+"%");
           	}
            // 区域名称
            if (StringUtils.isNotEmpty((String) searchMap.get("area"))) {
                criteria.andLike("area","%"+searchMap.get("area")+"%");
           	}
            // 城市ID
            if (StringUtils.isNotEmpty((String) searchMap.get("cityid"))) {
                criteria.andLike("cityid","%"+searchMap.get("cityid")+"%");
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
    public QueryBrandResult<Areas> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<Areas> brands = (Page<Areas>) areasMapper.selectByExample(example);
        QueryResult<Areas> queryResult = new QueryResult<>();
        List<Areas> result = brands.getResult();
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
