package com.zhiyunheyi.aibot.operate.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * HttpServletRequest工具类
 */
public class RequestUtil {
	
	/**
	 * 获取完整的请求url
	 * @param request
	 * @return
	 */
	public static String getRequestUrl(HttpServletRequest request){
		String url = request.getRequestURL().toString();
		String param = request.getQueryString();
		
		if (StringUtils.isBlank(param)) {
			String queryString = "";
			Map<String, String[]> params = request.getParameterMap();
			for (String key : params.keySet()) {
				String[] values = params.get(key);
				for (int i = 0; i < values.length; i++) {
					String value = values[i];
					queryString += key + "=" + value + "&";
				}
			}
			if (StringUtils.isNotBlank(queryString)) {
				// 去掉最后一个空格
				queryString = queryString.substring(0, queryString.length() - 1);
				url = url + "?" + queryString;
			}
		} else {
			url = url + "?" + param;
		}
		return url;
	}
}
