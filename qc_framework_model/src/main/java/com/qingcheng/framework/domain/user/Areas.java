package com.qingcheng.framework.domain.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * areas实体类
 * @author Bai
 *
 */
@Table(name="tb_areas")
@Data
public class Areas implements Serializable{

	@Id
	@ApiModelProperty("区域ID")
	private String areaid;//区域ID


	@ApiModelProperty("区域名称")
	private String area;//区域名称
	@ApiModelProperty("城市ID")
	private String cityid;//城市ID


}
