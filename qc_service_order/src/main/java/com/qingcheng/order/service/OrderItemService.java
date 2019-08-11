package com.qingcheng.order.service;

import com.aliyun.oss.OSSClient;

import com.qingcheng.framework.domain.order.OrderItem;
import com.qingcheng.order.dao.OrderItemMapper;
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
public class OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<OrderItem> findAll(){
        return orderItemMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public OrderItem findById(String id){
		 //参数校验
        if (id == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  orderItemMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     * @param orderItem
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(OrderItem orderItem) {
        if (orderItem == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		orderItemMapper.insert(orderItem);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param orderItem
     */
	@Transactional
    public ResponseResult update(OrderItem orderItem) {
        if (orderItem==null||orderItem.getId() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = orderItemMapper.updateByPrimaryKeySelective(orderItem);
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
    public ResponseResult deleteOrderItem(String id) {
        int result = orderItemMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<OrderItem> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<OrderItem> orderItem = (Page<OrderItem>) orderItemMapper.selectAll();
        QueryResult<OrderItem> queryResult = new QueryResult<>();
        List<OrderItem> result = orderItem.getResult();
        long total = orderItem.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<OrderItem> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return orderItemMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(OrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // ID
            if (StringUtils.isNotEmpty((String) searchMap.get("id"))) {
                criteria.andLike("id","%"+searchMap.get("id")+"%");
           	}
            // SPU_ID
            if (StringUtils.isNotEmpty((String) searchMap.get("spu_id"))) {
                criteria.andLike("spu_id","%"+searchMap.get("spu_id")+"%");
           	}
            // SKU_ID
            if (StringUtils.isNotEmpty((String) searchMap.get("sku_id"))) {
                criteria.andLike("sku_id","%"+searchMap.get("sku_id")+"%");
           	}
            // 订单ID
            if (StringUtils.isNotEmpty((String) searchMap.get("order_id"))) {
                criteria.andLike("order_id","%"+searchMap.get("order_id")+"%");
           	}
            // 商品名称
            if (StringUtils.isNotEmpty((String) searchMap.get("name"))) {
                criteria.andLike("name","%"+searchMap.get("name")+"%");
           	}
            // 图片地址
            if (StringUtils.isNotEmpty((String) searchMap.get("image"))) {
                criteria.andLike("image","%"+searchMap.get("image")+"%");
           	}
            // 是否退货
            if (StringUtils.isNotEmpty((String) searchMap.get("is_return"))) {
                criteria.andLike("is_return","%"+searchMap.get("is_return")+"%");
           	}

            // 1级分类
            if(searchMap.get("categoryId1")!=null ){
                criteria.andEqualTo("categoryId1",searchMap.get("categoryId1"));
            }
            // 2级分类
            if(searchMap.get("categoryId2")!=null ){
                criteria.andEqualTo("categoryId2",searchMap.get("categoryId2"));
            }
            // 3级分类
            if(searchMap.get("categoryId3")!=null ){
                criteria.andEqualTo("categoryId3",searchMap.get("categoryId3"));
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
            // 实付金额
            if(searchMap.get("payMoney")!=null ){
                criteria.andEqualTo("payMoney",searchMap.get("payMoney"));
            }
            // 重量
            if(searchMap.get("weight")!=null ){
                criteria.andEqualTo("weight",searchMap.get("weight"));
            }
            // 运费
            if(searchMap.get("postFee")!=null ){
                criteria.andEqualTo("postFee",searchMap.get("postFee"));
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
    public QueryBrandResult<OrderItem> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<OrderItem> brands = (Page<OrderItem>) orderItemMapper.selectByExample(example);
        QueryResult<OrderItem> queryResult = new QueryResult<>();
        List<OrderItem> result = brands.getResult();
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
