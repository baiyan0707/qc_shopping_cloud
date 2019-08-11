package com.qingcheng.framework.domain.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * cities实体类
 * @author Bai
 *
 */
@Table(name="tb_cities")
@Data
public class Cities implements Serializable{

	@Id
	@ApiModelProperty("城市ID")
	private String cityid;//城市ID


	@ApiModelProperty("城市名称")
	private String city;//城市名称
	@ApiModelProperty("省份ID")
	private String provinceid;//省份ID


}
