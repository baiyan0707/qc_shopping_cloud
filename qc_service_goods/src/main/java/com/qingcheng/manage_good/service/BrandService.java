package com.qingcheng.manage_good.service;

import com.aliyun.oss.OSSClient;
import com.qingcheng.framework.domain.goods.Brand;
import com.qingcheng.manage_good.dao.BrandMapper;
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
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<Brand> findAll(){
        return brandMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Brand findById(Integer id){
		 //参数校验
        if (id == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     * @param brand
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(Brand brand) {
        if (brand == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		brandMapper.insert(brand);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param brand
     */
	@Transactional
    public ResponseResult update(Brand brand) {
        if (brand==null||brand.getId() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = brandMapper.updateByPrimaryKeySelective(brand);
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
    public ResponseResult deleteBrand(Integer id) {
        int result = brandMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<Brand> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<Brand> brand = (Page<Brand>) brandMapper.selectAll();
        QueryResult<Brand> queryResult = new QueryResult<>();
        List<Brand> result = brand.getResult();
        long total = brand.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<Brand> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return brandMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 品牌名称
            if (StringUtils.isNotEmpty((String) searchMap.get("name"))) {
                criteria.andLike("name","%"+searchMap.get("name")+"%");
           	}
            // 品牌图片地址
            if (StringUtils.isNotEmpty((String) searchMap.get("image"))) {
                criteria.andLike("image","%"+searchMap.get("image")+"%");
           	}
            // 品牌的首字母
            if (StringUtils.isNotEmpty((String) searchMap.get("letter"))) {
                criteria.andLike("letter","%"+searchMap.get("letter")+"%");
           	}

            // 品牌id
            if(searchMap.get("id")!=null ){
                criteria.andEqualTo("id",searchMap.get("id"));
            }
            // 排序
            if(searchMap.get("seq")!=null ){
                criteria.andEqualTo("seq",searchMap.get("seq"));
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
    public QueryBrandResult<Brand> findPageList( Integer page, Integer size,Map<String,Object> searchMap){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<Brand> brands = (Page<Brand>) brandMapper.selectByExample(example);
        QueryResult<Brand> queryResult = new QueryResult<>();
        List<Brand> result = brands.getResult();
        long total = brands.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
//        return queryResult;
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
