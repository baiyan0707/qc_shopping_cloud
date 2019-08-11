package com.qingcheng.order.service;

import com.aliyun.oss.OSSClient;
import com.qingcheng.framework.domain.order.ReturnOrderItem;
import com.qingcheng.order.dao.ReturnOrderItemMapper;
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
public class ReturnOrderItemService {

    @Autowired
    private ReturnOrderItemMapper returnOrderItemMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<ReturnOrderItem> findAll(){
        return returnOrderItemMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public ReturnOrderItem findById(Long id){
		 //参数校验
        if (id == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  returnOrderItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     * @param returnOrderItem
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(ReturnOrderItem returnOrderItem) {
        if (returnOrderItem == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		returnOrderItemMapper.insert(returnOrderItem);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param returnOrderItem
     */
	@Transactional
    public ResponseResult update(ReturnOrderItem returnOrderItem) {
        if (returnOrderItem==null||returnOrderItem.getId() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = returnOrderItemMapper.updateByPrimaryKeySelective(returnOrderItem);
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
    public ResponseResult deleteReturnOrderItem(Long id) {
        int result = returnOrderItemMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<ReturnOrderItem> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<ReturnOrderItem> returnOrderItem = (Page<ReturnOrderItem>) returnOrderItemMapper.selectAll();
        QueryResult<ReturnOrderItem> queryResult = new QueryResult<>();
        List<ReturnOrderItem> result = returnOrderItem.getResult();
        long total = returnOrderItem.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<ReturnOrderItem> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return returnOrderItemMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(ReturnOrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 标题
            if (StringUtils.isNotEmpty((String) searchMap.get("title"))) {
                criteria.andLike("title","%"+searchMap.get("title")+"%");
           	}
            // 图片地址
            if (StringUtils.isNotEmpty((String) searchMap.get("image"))) {
                criteria.andLike("image","%"+searchMap.get("image")+"%");
           	}

            // 单价
            if(searchMap.get("price")!=null ){
                criteria.andEqualTo("price",searchMap.get("price"));
            }
            // 数量
            if(searchMap.get("num")!=null ){
                criteria.andEqualTo("num",searchMap.get("num"));
            }
            // 总金额
            if(searchMap.get("money")!=null ){
                criteria.andEqualTo("money",searchMap.get("money"));
            }
            // 支付金额
            if(searchMap.get("payMoney")!=null ){
                criteria.andEqualTo("payMoney",searchMap.get("payMoney"));
            }
            // 重量
            if(searchMap.get("weight")!=null ){
                criteria.andEqualTo("weight",searchMap.get("weight"));
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
    public QueryBrandResult<ReturnOrderItem> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<ReturnOrderItem> brands = (Page<ReturnOrderItem>) returnOrderItemMapper.selectByExample(example);
        QueryResult<ReturnOrderItem> queryResult = new QueryResult<>();
        List<ReturnOrderItem> result = brands.getResult();
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
