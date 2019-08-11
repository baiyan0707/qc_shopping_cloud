package com.qingcheng.framework.domain.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * resource实体类
 * @author Bai
 *
 */
@Table(name="tb_resource")
@Data
public class Resource implements Serializable{

	@Id
	@ApiModelProperty("id")
	private Integer id;//id


	@ApiModelProperty("res_key")
	private String resKey;//res_key
	@ApiModelProperty("res_name")
	private String resName;//res_name
	@ApiModelProperty("parent_id")
	private Integer parentId;//parent_id


}
