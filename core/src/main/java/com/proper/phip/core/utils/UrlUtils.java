package com.proper.phip.core.utils;

import org.apache.commons.lang.StringUtils;

/**
 * URL工具类
 */
public class UrlUtils {

    /**
     * 判断url和带restful通配符的url是否一致
     * @param url 请求的url,例:/users/1
     * @param restUrl 带restful通配符的url,例:/users/${id}
     */
    public static boolean isEqualRestfulUrl(String url, String restUrl) {
        boolean isEqual = false;
        if (url.equals(restUrl)) {
            isEqual = true;
        } else {
            String[] urls = url.split("/");
            String[] restUrls = restUrl.split("/");
            if (urls.length == restUrls.length) {
                int count = 0;
                for (int i = 0; i < urls.length; i++) {
                    if (restUrls[i].equals(urls[i])) {
                        count++;
                    } else if (restUrls[i].startsWith("${") && restUrls[i].endsWith("}") && StringUtils.isNotEmpty(urls[i])) {
                        count++;
                    }
                }
                if (count == urls.length) {
                    isEqual = true;
                }
            }
        }
        return isEqual;
    }
}
