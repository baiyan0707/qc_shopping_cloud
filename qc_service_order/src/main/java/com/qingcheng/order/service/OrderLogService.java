package com.qingcheng.order.service;

import com.aliyun.oss.OSSClient;

import com.qingcheng.framework.domain.order.OrderLog;
import com.qingcheng.order.dao.OrderLogMapper;
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
public class OrderLogService {

    @Autowired
    private OrderLogMapper orderLogMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<OrderLog> findAll(){
        return orderLogMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public OrderLog findById(String id){
		 //参数校验
        if (id == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  orderLogMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     * @param orderLog
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(OrderLog orderLog) {
        if (orderLog == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		orderLogMapper.insert(orderLog);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param orderLog
     */
	@Transactional
    public ResponseResult update(OrderLog orderLog) {
        if (orderLog==null||orderLog.getId() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = orderLogMapper.updateByPrimaryKeySelective(orderLog);
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
    public ResponseResult deleteOrderLog(String id) {
        int result = orderLogMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<OrderLog> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<OrderLog> orderLog = (Page<OrderLog>) orderLogMapper.selectAll();
        QueryResult<OrderLog> queryResult = new QueryResult<>();
        List<OrderLog> result = orderLog.getResult();
        long total = orderLog.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<OrderLog> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return orderLogMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(OrderLog.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // ID
            if (StringUtils.isNotEmpty((String) searchMap.get("id"))) {
                criteria.andLike("id","%"+searchMap.get("id")+"%");
           	}
            // 操作员
            if (StringUtils.isNotEmpty((String) searchMap.get("operater"))) {
                criteria.andLike("operater","%"+searchMap.get("operater")+"%");
           	}
            // 订单ID
            if (StringUtils.isNotEmpty((String) searchMap.get("order_id"))) {
                criteria.andLike("order_id","%"+searchMap.get("order_id")+"%");
           	}
            // 订单状态
            if (StringUtils.isNotEmpty((String) searchMap.get("order_status"))) {
                criteria.andLike("order_status","%"+searchMap.get("order_status")+"%");
           	}
            // 付款状态
            if (StringUtils.isNotEmpty((String) searchMap.get("pay_status"))) {
                criteria.andLike("pay_status","%"+searchMap.get("pay_status")+"%");
           	}
            // 发货状态
            if (StringUtils.isNotEmpty((String) searchMap.get("consign_status"))) {
                criteria.andLike("consign_status","%"+searchMap.get("consign_status")+"%");
           	}
            // 备注
            if (StringUtils.isNotEmpty((String) searchMap.get("remarks"))) {
                criteria.andLike("remarks","%"+searchMap.get("remarks")+"%");
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
    public QueryBrandResult<OrderLog> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<OrderLog> brands = (Page<OrderLog>) orderLogMapper.selectByExample(example);
        QueryResult<OrderLog> queryResult = new QueryResult<>();
        List<OrderLog> result = brands.getResult();
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
