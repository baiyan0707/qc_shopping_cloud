package com.qingcheng.framework.exception;


import com.qingcheng.framework.exception.model.response.ResultCode;

/**
 * 自定义异常类
 */
public class CustomException extends RuntimeException {

    //错误代码
    private ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode(){
        return resultCode;
    }
}
