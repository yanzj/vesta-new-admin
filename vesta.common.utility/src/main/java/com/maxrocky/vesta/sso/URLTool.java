package com.maxrocky.vesta.sso;

import java.net.URLDecoder;
import java.net.URLEncoder;

class URLTool {
    URLTool() {
    }

    public static String encodeURL(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (Exception var2) {
            SsoLogger.error(var2);
            return url;
        }
    }

    public static String decodeURL(String url) {
        try {
            return URLDecoder.decode(url, "UTF-8");
        } catch (Exception var2) {
            SsoLogger.error(var2);
            return url;
        }
    }
}
