package com.qingcheng.framework.domain.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * orderLog实体类
 * @author Bai
 *
 */
@Table(name="tb_order_log")
@Data
public class OrderLog implements Serializable{

	@Id
	@ApiModelProperty("ID")
	private String id;//ID


	@ApiModelProperty("操作员")
	private String operater;//操作员
	@ApiModelProperty("操作时间")
	private java.util.Date operateTime;//操作时间
	@ApiModelProperty("订单ID")
	private String orderId;//订单ID
	@ApiModelProperty("订单状态")
	private String orderStatus;//订单状态
	@ApiModelProperty("付款状态")
	private String payStatus;//付款状态
	@ApiModelProperty("发货状态")
	private String consignStatus;//发货状态
	@ApiModelProperty("备注")
	private String remarks;//备注


}
