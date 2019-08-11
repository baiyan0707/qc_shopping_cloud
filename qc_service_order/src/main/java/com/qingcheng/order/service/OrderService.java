package com.qingcheng.order.service;

import com.aliyun.oss.OSSClient;
import com.qingcheng.framework.domain.order.Order;
import com.qingcheng.order.dao.OrderMapper;
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
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<Order> findAll(){
        return orderMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Order findById(String id){
		 //参数校验
        if (id == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  orderMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     * @param order
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(Order order) {
        if (order == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		orderMapper.insert(order);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param order
     */
	@Transactional
    public ResponseResult update(Order order) {
        if (order==null||order.getId() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = orderMapper.updateByPrimaryKeySelective(order);
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
    public ResponseResult deleteOrder(String id) {
        int result = orderMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<Order> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<Order> order = (Page<Order>) orderMapper.selectAll();
        QueryResult<Order> queryResult = new QueryResult<>();
        List<Order> result = order.getResult();
        long total = order.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<Order> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return orderMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(Order.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 订单id
            if (StringUtils.isNotEmpty((String) searchMap.get("id"))) {
                criteria.andLike("id","%"+searchMap.get("id")+"%");
           	}
            // 支付类型，1、在线支付、0 货到付款
            if (StringUtils.isNotEmpty((String) searchMap.get("pay_type"))) {
                criteria.andLike("pay_type","%"+searchMap.get("pay_type")+"%");
           	}
            // 物流名称
            if (StringUtils.isNotEmpty((String) searchMap.get("shipping_name"))) {
                criteria.andLike("shipping_name","%"+searchMap.get("shipping_name")+"%");
           	}
            // 物流单号
            if (StringUtils.isNotEmpty((String) searchMap.get("shipping_code"))) {
                criteria.andLike("shipping_code","%"+searchMap.get("shipping_code")+"%");
           	}
            // 用户名称
            if (StringUtils.isNotEmpty((String) searchMap.get("username"))) {
                criteria.andLike("username","%"+searchMap.get("username")+"%");
           	}
            // 买家留言
            if (StringUtils.isNotEmpty((String) searchMap.get("buyer_message"))) {
                criteria.andLike("buyer_message","%"+searchMap.get("buyer_message")+"%");
           	}
            // 是否评价
            if (StringUtils.isNotEmpty((String) searchMap.get("buyer_rate"))) {
                criteria.andLike("buyer_rate","%"+searchMap.get("buyer_rate")+"%");
           	}
            // 收货人
            if (StringUtils.isNotEmpty((String) searchMap.get("receiver_contact"))) {
                criteria.andLike("receiver_contact","%"+searchMap.get("receiver_contact")+"%");
           	}
            // 收货人手机
            if (StringUtils.isNotEmpty((String) searchMap.get("receiver_mobile"))) {
                criteria.andLike("receiver_mobile","%"+searchMap.get("receiver_mobile")+"%");
           	}
            // 收货人地址
            if (StringUtils.isNotEmpty((String) searchMap.get("receiver_address"))) {
                criteria.andLike("receiver_address","%"+searchMap.get("receiver_address")+"%");
           	}
            // 订单来源：1:web，2：app，3：微信公众号，4：微信小程序  5 H5手机页面
            if (StringUtils.isNotEmpty((String) searchMap.get("source_type"))) {
                criteria.andLike("source_type","%"+searchMap.get("source_type")+"%");
           	}
            // 交易流水号
            if (StringUtils.isNotEmpty((String) searchMap.get("transaction_id"))) {
                criteria.andLike("transaction_id","%"+searchMap.get("transaction_id")+"%");
           	}
            // 订单状态
            if (StringUtils.isNotEmpty((String) searchMap.get("order_status"))) {
                criteria.andLike("order_status","%"+searchMap.get("order_status")+"%");
           	}
            // 支付状态
            if (StringUtils.isNotEmpty((String) searchMap.get("pay_status"))) {
                criteria.andLike("pay_status","%"+searchMap.get("pay_status")+"%");
           	}
            // 发货状态
            if (StringUtils.isNotEmpty((String) searchMap.get("consign_status"))) {
                criteria.andLike("consign_status","%"+searchMap.get("consign_status")+"%");
           	}
            // 是否删除
            if (StringUtils.isNotEmpty((String) searchMap.get("is_delete"))) {
                criteria.andLike("is_delete","%"+searchMap.get("is_delete")+"%");
           	}

            // 数量合计
            if(searchMap.get("totalNum")!=null ){
                criteria.andEqualTo("totalNum",searchMap.get("totalNum"));
            }
            // 金额合计
            if(searchMap.get("totalMoney")!=null ){
                criteria.andEqualTo("totalMoney",searchMap.get("totalMoney"));
            }
            // 优惠金额
            if(searchMap.get("preMoney")!=null ){
                criteria.andEqualTo("preMoney",searchMap.get("preMoney"));
            }
            // 邮费
            if(searchMap.get("postFee")!=null ){
                criteria.andEqualTo("postFee",searchMap.get("postFee"));
            }
            // 实付金额
            if(searchMap.get("payMoney")!=null ){
                criteria.andEqualTo("payMoney",searchMap.get("payMoney"));
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
    public QueryBrandResult<Order> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<Order> brands = (Page<Order>) orderMapper.selectByExample(example);
        QueryResult<Order> queryResult = new QueryResult<>();
        List<Order> result = brands.getResult();
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
