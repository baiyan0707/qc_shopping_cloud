package com.qingcheng.framework.exception;


import com.qingcheng.framework.exception.model.response.ResultCode;

/**
 * 异常抛出类
 */
public class ExceptionCast {
    //使用此静态方法抛出自定义异常
    public static void cast(ResultCode resultCode) {
        throw new CustomException(resultCode);
    }
}
