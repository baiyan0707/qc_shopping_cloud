package com.qingcheng.framework.domain.business;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * ad实体类
 * @author Bai
 *
 */
@Table(name="tb_ad")
@Data
public class Ad implements Serializable{

	@Id
	@ApiModelProperty("ID")
	private Integer id;//ID


	@ApiModelProperty("广告名称")
	private String name;//广告名称
	@ApiModelProperty("广告位置")
	private String position;//广告位置
	@ApiModelProperty("开始时间")
	private java.util.Date startTime;//开始时间
	@ApiModelProperty("到期时间")
	private java.util.Date endTime;//到期时间
	@ApiModelProperty("状态")
	private String status;//状态
	@ApiModelProperty("图片地址")
	private String image;//图片地址
	@ApiModelProperty("URL")
	private String url;//URL
	@ApiModelProperty("备注")
	private String remarks;//备注


}
