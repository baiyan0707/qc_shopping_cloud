package com.qingcheng.order.service;

import com.aliyun.oss.OSSClient;
import com.qingcheng.framework.domain.order.ReturnOrder;
import com.qingcheng.order.dao.ReturnOrderMapper;
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
public class ReturnOrderService {

    @Autowired
    private ReturnOrderMapper returnOrderMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<ReturnOrder> findAll(){
        return returnOrderMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public ReturnOrder findById(Long id){
		 //参数校验
        if (id == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  returnOrderMapper.selectByPrimaryKey(id);
    }

    /**
     * 增加
     * @param returnOrder
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(ReturnOrder returnOrder) {
        if (returnOrder == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		returnOrderMapper.insert(returnOrder);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param returnOrder
     */
	@Transactional
    public ResponseResult update(ReturnOrder returnOrder) {
        if (returnOrder==null||returnOrder.getId() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = returnOrderMapper.updateByPrimaryKeySelective(returnOrder);
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
    public ResponseResult deleteReturnOrder(Long id) {
        int result = returnOrderMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<ReturnOrder> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<ReturnOrder> returnOrder = (Page<ReturnOrder>) returnOrderMapper.selectAll();
        QueryResult<ReturnOrder> queryResult = new QueryResult<>();
        List<ReturnOrder> result = returnOrder.getResult();
        long total = returnOrder.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<ReturnOrder> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return returnOrderMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(ReturnOrder.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 用户账号
            if (StringUtils.isNotEmpty((String) searchMap.get("user_account"))) {
                criteria.andLike("user_account","%"+searchMap.get("user_account")+"%");
           	}
            // 联系人
            if (StringUtils.isNotEmpty((String) searchMap.get("linkman"))) {
                criteria.andLike("linkman","%"+searchMap.get("linkman")+"%");
           	}
            // 联系人手机
            if (StringUtils.isNotEmpty((String) searchMap.get("linkman_mobile"))) {
                criteria.andLike("linkman_mobile","%"+searchMap.get("linkman_mobile")+"%");
           	}
            // 类型
            if (StringUtils.isNotEmpty((String) searchMap.get("type"))) {
                criteria.andLike("type","%"+searchMap.get("type")+"%");
           	}
            // 是否退运费
            if (StringUtils.isNotEmpty((String) searchMap.get("is_return_freight"))) {
                criteria.andLike("is_return_freight","%"+searchMap.get("is_return_freight")+"%");
           	}
            // 申请状态
            if (StringUtils.isNotEmpty((String) searchMap.get("status"))) {
                criteria.andLike("status","%"+searchMap.get("status")+"%");
           	}
            // 凭证图片
            if (StringUtils.isNotEmpty((String) searchMap.get("evidence"))) {
                criteria.andLike("evidence","%"+searchMap.get("evidence")+"%");
           	}
            // 问题描述
            if (StringUtils.isNotEmpty((String) searchMap.get("description"))) {
                criteria.andLike("description","%"+searchMap.get("description")+"%");
           	}
            // 处理备注
            if (StringUtils.isNotEmpty((String) searchMap.get("remark"))) {
                criteria.andLike("remark","%"+searchMap.get("remark")+"%");
           	}

            // 退款金额
            if(searchMap.get("returnMoney")!=null ){
                criteria.andEqualTo("returnMoney",searchMap.get("returnMoney"));
            }
            // 退货退款原因
            if(searchMap.get("returnCause")!=null ){
                criteria.andEqualTo("returnCause",searchMap.get("returnCause"));
            }
            // 管理员id
            if(searchMap.get("adminId")!=null ){
                criteria.andEqualTo("adminId",searchMap.get("adminId"));
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
    public QueryBrandResult<ReturnOrder> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<ReturnOrder> brands = (Page<ReturnOrder>) returnOrderMapper.selectByExample(example);
        QueryResult<ReturnOrder> queryResult = new QueryResult<>();
        List<ReturnOrder> result = brands.getResult();
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
