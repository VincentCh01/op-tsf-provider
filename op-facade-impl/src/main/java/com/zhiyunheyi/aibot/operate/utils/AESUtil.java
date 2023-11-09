package com.zhiyunheyi.aibot.operate.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;


public class AESUtil {

    private static final byte[] KEY_DEFAULT = "8234mxwru34eruhf".getBytes(StandardCharsets.UTF_8);

    /**
     * @Description 加密为16进制表示
     * @Param content
     * @param keyStr	可以为空
     * @return java.lang.String
     **/
    public static String encryptHex(String content,String keyStr) throws Exception{

    	byte[] keyByte = KEY_DEFAULT;
    	if(StringUtils.isNotBlank(keyStr)){
			keyByte = keyStr.getBytes(StandardCharsets.UTF_8);
		}

		return SecureUtil.aes(SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(),keyByte).getEncoded()).encryptHex(content);
	}

	/**
	 * @Description 解密16进制
	 * @Param contentHex
	 * @param keyStr
	 * @return java.lang.String
	 **/
	public static String decryptStrHex(String contentHex,String keyStr) throws Exception{

		byte[] keyByte = KEY_DEFAULT;
		if(StringUtils.isNotBlank(keyStr)){
			keyByte = keyStr.getBytes(StandardCharsets.UTF_8);
		}

		return SecureUtil.aes(SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(),keyByte).getEncoded()).decryptStr(contentHex);
	}

}
