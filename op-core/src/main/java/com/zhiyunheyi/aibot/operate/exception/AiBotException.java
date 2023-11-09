package com.zhiyunheyi.aibot.operate.exception;

import com.zhiyunheyi.aibot.domain.core.enumeration.ResultEnum;
import lombok.Getter;

/**
 * 自定义异常类
 */
@Getter
public class AiBotException extends RuntimeException {
	
	private Integer code;

	public AiBotException(ResultEnum resultEnum) {
		super(resultEnum.getMessage());
		
		this.code = resultEnum.getCode();
	}
	
	public AiBotException(Integer code, String message) {
		super(message);
		this.code = code;
	}

	public AiBotException(ResultEnum resultEnum, String message) {
		super(message);
		this.code = resultEnum.getCode();
	}
}
