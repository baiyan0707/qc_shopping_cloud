package com.qingcheng.framework.domain.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * role实体类
 * @author Bai
 *
 */
@Table(name="tb_role")
@Data
public class Role implements Serializable{

	@Id
	@ApiModelProperty("ID")
	private Integer id;//ID


	@ApiModelProperty("角色名称")
	private String name;//角色名称


}
