package com.qingcheng.framework.domain.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * admin实体类
 * @author Bai
 *
 */
@Table(name="tb_admin")
@Data
public class Admin implements Serializable{

	@Id
	@ApiModelProperty("id")
	private Integer id;//id


	@ApiModelProperty("用户名")
	private String loginName;//用户名
	@ApiModelProperty("密码")
	private String password;//密码
	@ApiModelProperty("状态")
	private String status;//状态


}
