package com.zhiyunheyi.aibot.operate.vo;

/**
 * @author wangyong
 * @description
 * @create 2023-06-26 15:10
 */

public class UserContext {
    private static final ThreadLocal<AuthTokenVO> USER_HOLDER = new ThreadLocal<>();

    public static void setUser(AuthTokenVO user) {
        USER_HOLDER.set(user);
    }

    public static void remove() {
        USER_HOLDER.remove();
    }

    public static AuthTokenVO getUser() {
        return USER_HOLDER.get();
    }
}
