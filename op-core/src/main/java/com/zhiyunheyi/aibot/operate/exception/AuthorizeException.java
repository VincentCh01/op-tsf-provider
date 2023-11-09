package com.zhiyunheyi.aibot.operate.exception;

import com.zhiyunheyi.aibot.domain.core.enumeration.ResultEnum;
import lombok.Getter;

/**
 * 未登录异常类
 */
@Getter
public class AuthorizeException extends RuntimeException {
	
	private Integer code;
	
	public AuthorizeException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		this.code = resultEnum.getCode();
	}
}
