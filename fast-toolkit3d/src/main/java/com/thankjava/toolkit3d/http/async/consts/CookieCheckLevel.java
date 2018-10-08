package com.thankjava.toolkit3d.http.async.consts;

/**
 * @Author: acexy@thankjava.com
 * 2018/9/30
 * @Description: cookie校验等级
 **/
public enum CookieCheckLevel {

    /**
     * 与常见的 HTTP 应用保持较高兼容性的Cookie管理方案
     */
    BROWSER_COMPATIBILITY("compatibility"),

    /**
     * 符合 netscape Cookie 标准
     */
    NETSCAPE("netscape"),


    /**
     * 符合RFC 6265的策略（互操作性配置文件）
     */
    STANDARD("standard"),


    /**
     * 符合RFC 6265的政策（严格的配置文件）
     */
    STANDARD_STRICT("standard-strict"),

    BEST_MATCH("best-match"),

    /**
     * 默认策略。 此策略提供了与非标准（Netscape样式）cookie的常用HTTP代理的常见cookie管理的更高程度的兼容性
     */
    DEFAULT("default"),

    /**
     * 忽略掉cookie 不会处理任何Cookie信息
     */
    IGNORE_COOKIES("ignoreCookies");

    public String code;

    CookieCheckLevel(String code) {
        this.code = code;
    }

}
