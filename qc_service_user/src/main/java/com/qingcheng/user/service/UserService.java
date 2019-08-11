package com.qingcheng.user.service;

import com.aliyun.oss.OSSClient;
import com.qingcheng.framework.domain.user.User;
import com.qingcheng.user.dao.UserMapper;
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
public class UserService {

    @Autowired
    private UserMapper userMapper;
	
	@Autowired
    OSSClient ossClient;

    /**
     * 全部数据
     * @return
     */
    public List<User> findAll(){
        return userMapper.selectAll();
    }

    /**
     * 根据ID查询
     * @param username
     * @return
     */
    public User findById(String username){
		 //参数校验
        if (username == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        return  userMapper.selectByPrimaryKey(username);
    }

    /**
     * 增加
     * @param user
	 * @return 添加品牌信息 并返回操作结果
     */
	@Transactional
    public ResponseResult add(User user) {
        if (user == null) {
            ExceptionCast.cast(GoodsCode.ADD_ERROR);
        }
		userMapper.insert(user);
        return new ResponseResult(GoodsCode.SUCCESS);
    }
	
    /**
     * 修改
     * @param user
     */
	@Transactional
    public ResponseResult update(User user) {
        if (user==null||user.getUsername() == null) {
            ExceptionCast.cast(GoodsCode.INVALID_PARAM);
        }
        int count = userMapper.updateByPrimaryKeySelective(user);
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
    public ResponseResult deleteUser(String id) {
        int result = userMapper.deleteByPrimaryKey(id);
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
    public QueryBrandResult<User> findPage( int page, int size){
		if (page <= 0) {
            page = 1;
        }
        if (size <= 0) {
            page = 7;
        }
        PageHelper.startPage(page,size);
		Page<User> user = (Page<User>) userMapper.selectAll();
        QueryResult<User> queryResult = new QueryResult<>();
        List<User> result = user.getResult();
        long total = user.getTotal();
        queryResult.setList(result);
        queryResult.setTotal(total);
        return new QueryBrandResult<>(GoodsCode.SUCCESS,queryResult);
    }

    /**
     * 条件查询
     * @param searchMap 搜索条件
     * @return
     */
    public List<User> findList(Map<String,Object> searchMap) {
        /*获取品牌查询条件对象*/
        Example example = this.createExample(searchMap);
        return userMapper.selectByExample(example);
    }


    /**
     * 构建查询对象
     * @param searchMap
     * @return
     */
    private Example createExample(Map<String,Object> searchMap){
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(searchMap!=null){
            // 用户名
            if (StringUtils.isNotEmpty((String) searchMap.get("username"))) {
                criteria.andLike("username","%"+searchMap.get("username")+"%");
           	}
            // 密码，加密存储
            if (StringUtils.isNotEmpty((String) searchMap.get("password"))) {
                criteria.andLike("password","%"+searchMap.get("password")+"%");
           	}
            // 注册手机号
            if (StringUtils.isNotEmpty((String) searchMap.get("phone"))) {
                criteria.andLike("phone","%"+searchMap.get("phone")+"%");
           	}
            // 注册邮箱
            if (StringUtils.isNotEmpty((String) searchMap.get("email"))) {
                criteria.andLike("email","%"+searchMap.get("email")+"%");
           	}
            // 会员来源：1:PC，2：H5，3：Android，4：IOS
            if (StringUtils.isNotEmpty((String) searchMap.get("source_type"))) {
                criteria.andLike("source_type","%"+searchMap.get("source_type")+"%");
           	}
            // 昵称
            if (StringUtils.isNotEmpty((String) searchMap.get("nick_name"))) {
                criteria.andLike("nick_name","%"+searchMap.get("nick_name")+"%");
           	}
            // 真实姓名
            if (StringUtils.isNotEmpty((String) searchMap.get("name"))) {
                criteria.andLike("name","%"+searchMap.get("name")+"%");
           	}
            // 使用状态（1正常 0非正常）
            if (StringUtils.isNotEmpty((String) searchMap.get("status"))) {
                criteria.andLike("status","%"+searchMap.get("status")+"%");
           	}
            // 头像地址
            if (StringUtils.isNotEmpty((String) searchMap.get("head_pic"))) {
                criteria.andLike("head_pic","%"+searchMap.get("head_pic")+"%");
           	}
            // QQ号码
            if (StringUtils.isNotEmpty((String) searchMap.get("qq"))) {
                criteria.andLike("qq","%"+searchMap.get("qq")+"%");
           	}
            // 手机是否验证 （0否  1是）
            if (StringUtils.isNotEmpty((String) searchMap.get("is_mobile_check"))) {
                criteria.andLike("is_mobile_check","%"+searchMap.get("is_mobile_check")+"%");
           	}
            // 邮箱是否检测（0否  1是）
            if (StringUtils.isNotEmpty((String) searchMap.get("is_email_check"))) {
                criteria.andLike("is_email_check","%"+searchMap.get("is_email_check")+"%");
           	}
            // 性别，1男，0女
            if (StringUtils.isNotEmpty((String) searchMap.get("sex"))) {
                criteria.andLike("sex","%"+searchMap.get("sex")+"%");
           	}

            // 会员等级
            if(searchMap.get("userLevel")!=null ){
                criteria.andEqualTo("userLevel",searchMap.get("userLevel"));
            }
            // 积分
            if(searchMap.get("points")!=null ){
                criteria.andEqualTo("points",searchMap.get("points"));
            }
            // 经验值
            if(searchMap.get("experienceValue")!=null ){
                criteria.andEqualTo("experienceValue",searchMap.get("experienceValue"));
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
    public QueryBrandResult<User> findPageList(Map<String,Object> searchMap, Integer page, Integer size){
        PageHelper.startPage(page,size);
        Example example = this.createExample(searchMap);
        Page<User> brands = (Page<User>) userMapper.selectByExample(example);
        QueryResult<User> queryResult = new QueryResult<>();
        List<User> result = brands.getResult();
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
