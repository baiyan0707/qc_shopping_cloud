package com.qingcheng.framework.domain.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * provinces实体类
 * @author Bai
 *
 */
@Table(name="tb_provinces")
@Data
public class Provinces implements Serializable{

	@Id
	@ApiModelProperty("省份ID")
	private String provinceid;//省份ID


	@ApiModelProperty("省份名称")
	private String province;//省份名称


}
