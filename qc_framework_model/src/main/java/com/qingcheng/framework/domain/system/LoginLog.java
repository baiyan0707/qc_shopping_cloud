package com.qingcheng.framework.domain.system;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * loginLog实体类
 * @author Bai
 *
 */
@Table(name="tb_login_log")
@Data
public class LoginLog implements Serializable{

	@Id
	@ApiModelProperty("id")
	private Integer id;//id


	@ApiModelProperty("login_name")
	private String loginName;//login_name
	@ApiModelProperty("ip")
	private String ip;//ip
	@ApiModelProperty("browser_name")
	private String browserName;//browser_name
	@ApiModelProperty("地区")
	private String location;//地区
	@ApiModelProperty("登录时间")
	private java.util.Date loginTime;//登录时间


}
