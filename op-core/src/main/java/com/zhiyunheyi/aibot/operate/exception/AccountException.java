package com.zhiyunheyi.aibot.operate.exception;

import com.zhiyunheyi.aibot.domain.core.enumeration.ResultEnum;
import lombok.Getter;

/**
 * @menu:
 * @ClassName: AccountException
 * @Author: Vincent
 * @Description:
 * @Created Date: 2023/10/25 14:54
 * @Version: 1.0.0-SNAPSHOT
 */
@Getter
public class AccountException extends RuntimeException {

    private Integer code;

    public AccountException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public AccountException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public AccountException(ResultEnum resultEnum, String message) {
        super(message);
        this.code = resultEnum.getCode();
    }
}
