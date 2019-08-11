package com.qingcheng.framework.domain.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * returnOrder实体类
 * @author Bai
 *
 */
@Table(name="tb_return_order")
@Data
public class ReturnOrder implements Serializable{

	@Id
	@ApiModelProperty("服务单号")
	private Long id;//服务单号


	@ApiModelProperty("订单号")
	private Long orderId;//订单号
	@ApiModelProperty("申请时间")
	private java.util.Date applyTime;//申请时间
	@ApiModelProperty("用户ID")
	private Long userId;//用户ID
	@ApiModelProperty("用户账号")
	private String userAccount;//用户账号
	@ApiModelProperty("联系人")
	private String linkman;//联系人
	@ApiModelProperty("联系人手机")
	private String linkmanMobile;//联系人手机
	@ApiModelProperty("类型")
	private String type;//类型
	@ApiModelProperty("退款金额")
	private Integer returnMoney;//退款金额
	@ApiModelProperty("是否退运费")
	private String isReturnFreight;//是否退运费
	@ApiModelProperty("申请状态")
	private String status;//申请状态
	@ApiModelProperty("处理时间")
	private java.util.Date disposeTime;//处理时间
	@ApiModelProperty("退货退款原因")
	private Integer returnCause;//退货退款原因
	@ApiModelProperty("凭证图片")
	private String evidence;//凭证图片
	@ApiModelProperty("问题描述")
	private String description;//问题描述
	@ApiModelProperty("处理备注")
	private String remark;//处理备注
	@ApiModelProperty("管理员id")
	private Integer adminId;//管理员id


}
