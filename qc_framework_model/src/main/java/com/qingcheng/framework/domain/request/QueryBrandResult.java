package com.qingcheng.framework.domain.request;

import com.qingcheng.framework.exception.model.response.QueryResult;
import com.qingcheng.framework.exception.model.response.ResponseResult;
import com.qingcheng.framework.exception.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
public class QueryBrandResult<T> extends ResponseResult implements Serializable {

    QueryResult queryResult;

    public QueryBrandResult(ResultCode resultCode,QueryResult queryResult){
        super(resultCode);
        this.queryResult = queryResult;
    }
}
