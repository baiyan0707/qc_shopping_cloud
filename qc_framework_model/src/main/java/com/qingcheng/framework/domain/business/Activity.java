package com.qingcheng.framework.domain.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * activity实体类
 * @author Bai
 *
 */
@Table(name="tb_activity")
@Data
public class Activity implements Serializable{

	@Id
	@ApiModelProperty("ID")
	private Integer id;//ID


	@ApiModelProperty("活动标题")
	private String title;//活动标题
	@ApiModelProperty("开始时间")
	private java.util.Date startTime;//开始时间
	@ApiModelProperty("结束时间")
	private java.util.Date endTime;//结束时间
	@ApiModelProperty("状态")
	private String status;//状态
	@ApiModelProperty("活动内容")
	private String content;//活动内容


}
