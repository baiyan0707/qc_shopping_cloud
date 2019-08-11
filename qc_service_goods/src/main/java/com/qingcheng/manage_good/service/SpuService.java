package com.qingcheng.manage_good.service;

import com.aliyun.oss.OSSClient;
import com.qingcheng.framework.domain.goods.Spu;
import com.qingcheng.manage_good.dao.SpuMapper;
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
public class SpuService {

    @Autowired
    private SpuMapper spuMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<Spu> findAll(){
        return spuMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Spu findById(String id){
		 //参数校验
        if (id == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  spuMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     * @param spu
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(Spu spu) {
        if (spu == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		spuMapper.insert(spu);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param spu
     */
	@Transactional
    public ResponseResult update(Spu spu) {
        if (spu==null||spu.getId() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = spuMapper.updateByPrimaryKeySelective(spu);
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
    public ResponseResult deleteSpu(String id) {
        int result = spuMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<Spu> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<Spu> spu = (Page<Spu>) spuMapper.selectAll();
        QueryResult<Spu> queryResult = new QueryResult<>();
        List<Spu> result = spu.getResult();
        long total = spu.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<Spu> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return spuMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 主键
            if (StringUtils.isNotEmpty((String) searchMap.get("id"))) {
                criteria.andLike("id","%"+searchMap.get("id")+"%");
           	}
            // 货号
            if (StringUtils.isNotEmpty((String) searchMap.get("sn"))) {
                criteria.andLike("sn","%"+searchMap.get("sn")+"%");
           	}
            // SPU名
            if (StringUtils.isNotEmpty((String) searchMap.get("name"))) {
                criteria.andLike("name","%"+searchMap.get("name")+"%");
           	}
            // 副标题
            if (StringUtils.isNotEmpty((String) searchMap.get("caption"))) {
                criteria.andLike("caption","%"+searchMap.get("caption")+"%");
           	}
            // 图片
            if (StringUtils.isNotEmpty((String) searchMap.get("image"))) {
                criteria.andLike("image","%"+searchMap.get("image")+"%");
           	}
            // 图片列表
            if (StringUtils.isNotEmpty((String) searchMap.get("images"))) {
                criteria.andLike("images","%"+searchMap.get("images")+"%");
           	}
            // 售后服务
            if (StringUtils.isNotEmpty((String) searchMap.get("sale_service"))) {
                criteria.andLike("sale_service","%"+searchMap.get("sale_service")+"%");
           	}
            // 介绍
            if (StringUtils.isNotEmpty((String) searchMap.get("introduction"))) {
                criteria.andLike("introduction","%"+searchMap.get("introduction")+"%");
           	}
            // 规格列表
            if (StringUtils.isNotEmpty((String) searchMap.get("spec_items"))) {
                criteria.andLike("spec_items","%"+searchMap.get("spec_items")+"%");
           	}
            // 参数列表
            if (StringUtils.isNotEmpty((String) searchMap.get("para_items"))) {
                criteria.andLike("para_items","%"+searchMap.get("para_items")+"%");
           	}
            // 是否上架
            if (StringUtils.isNotEmpty((String) searchMap.get("is_marketable"))) {
                criteria.andLike("is_marketable","%"+searchMap.get("is_marketable")+"%");
           	}
            // 是否启用规格
            if (StringUtils.isNotEmpty((String) searchMap.get("is_enable_spec"))) {
                criteria.andLike("is_enable_spec","%"+searchMap.get("is_enable_spec")+"%");
           	}
            // 是否删除
            if (StringUtils.isNotEmpty((String) searchMap.get("is_delete"))) {
                criteria.andLike("is_delete","%"+searchMap.get("is_delete")+"%");
           	}
            // 审核状态
            if (StringUtils.isNotEmpty((String) searchMap.get("status"))) {
                criteria.andLike("status","%"+searchMap.get("status")+"%");
           	}

            // 品牌ID
            if(searchMap.get("brandId")!=null ){
                criteria.andEqualTo("brandId",searchMap.get("brandId"));
            }
            // 一级分类
            if(searchMap.get("category1Id")!=null ){
                criteria.andEqualTo("category1Id",searchMap.get("category1Id"));
            }
            // 二级分类
            if(searchMap.get("category2Id")!=null ){
                criteria.andEqualTo("category2Id",searchMap.get("category2Id"));
            }
            // 三级分类
            if(searchMap.get("category3Id")!=null ){
                criteria.andEqualTo("category3Id",searchMap.get("category3Id"));
            }
            // 模板ID
            if(searchMap.get("templateId")!=null ){
                criteria.andEqualTo("templateId",searchMap.get("templateId"));
            }
            // 运费模板id
            if(searchMap.get("freightId")!=null ){
                criteria.andEqualTo("freightId",searchMap.get("freightId"));
            }
            // 销量
            if(searchMap.get("saleNum")!=null ){
                criteria.andEqualTo("saleNum",searchMap.get("saleNum"));
            }
            // 评论数
            if(searchMap.get("commentNum")!=null ){
                criteria.andEqualTo("commentNum",searchMap.get("commentNum"));
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
    public QueryBrandResult<Spu> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<Spu> brands = (Page<Spu>) spuMapper.selectByExample(example);
        QueryResult<Spu> queryResult = new QueryResult<>();
        List<Spu> result = brands.getResult();
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
