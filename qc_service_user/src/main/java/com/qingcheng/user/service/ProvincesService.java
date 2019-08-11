package com.qingcheng.user.service;

import com.aliyun.oss.OSSClient;
import com.qingcheng.framework.domain.user.Provinces;
import com.qingcheng.user.dao.ProvincesMapper;
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
public class ProvincesService {

    @Autowired
    private ProvincesMapper provincesMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<Provinces> findAll(){
        return provincesMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param provinceid
     * @return
     */
    public Provinces findById(String provinceid){
		 //参数校验
        if (provinceid == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  provincesMapper.selectByPrimaryKey(provinceid);
    }

    /**
     * 增加
     * @param provinces
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(Provinces provinces) {
        if (provinces == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		provincesMapper.insert(provinces);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param provinces
     */
	@Transactional
    public ResponseResult update(Provinces provinces) {
        if (provinces==null||provinces.getProvinceid() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = provincesMapper.updateByPrimaryKeySelective(provinces);
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
    public ResponseResult deleteProvinces(String id) {
        int result = provincesMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<Provinces> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<Provinces> provinces = (Page<Provinces>) provincesMapper.selectAll();
        QueryResult<Provinces> queryResult = new QueryResult<>();
        List<Provinces> result = provinces.getResult();
        long total = provinces.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<Provinces> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return provincesMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Provinces.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 省份ID
            if (StringUtils.isNotEmpty((String) searchMap.get("provinceid"))) {
                criteria.andLike("provinceid","%"+searchMap.get("provinceid")+"%");
           	}
            // 省份名称
            if (StringUtils.isNotEmpty((String) searchMap.get("province"))) {
                criteria.andLike("province","%"+searchMap.get("province")+"%");
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
    public QueryBrandResult<Provinces> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<Provinces> brands = (Page<Provinces>) provincesMapper.selectByExample(example);
        QueryResult<Provinces> queryResult = new QueryResult<>();
        List<Provinces> result = brands.getResult();
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
