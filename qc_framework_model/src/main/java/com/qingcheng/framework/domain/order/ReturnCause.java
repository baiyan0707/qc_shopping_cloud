package com.qingcheng.framework.domain.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * returnCause实体类
 * @author Bai
 *
 */
@Table(name="tb_return_cause")
@Data
public class ReturnCause implements Serializable{

	@Id
	@ApiModelProperty("ID")
	private Integer id;//ID


	@ApiModelProperty("原因")
	private String cause;//原因
	@ApiModelProperty("排序")
	private Integer seq;//排序
	@ApiModelProperty("是否启用")
	private String status;//是否启用


}
