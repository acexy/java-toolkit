package com.thankjava.toolkit3d.bean.mail;

/**
 * 邮箱供应服务商
 * <p>Function: ServiceType</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2014年10月29日 下午5:29:30
 */
public enum MailService {

    /**
     * 腾讯QQ邮箱发件服务器配置 (包括 Foxmail )
     */
    TENCENT_QQ("25", "465", "smtp.qq.com"),
    /**
     * 腾讯企业邮箱
     */
    TENCENT_ENTERPRISE("25", "465", "smtp.exmail.qq.com"),

    /**
     * 网易 163
     */
    EASYNET_163("25", "465", "smtp.163.com"),

    /**
     * 阿里巴巴 阿里云
     */
    ALBABA_ALIYUN("25", "465", "smtp.aliyun.com"),

    /**
     * 新浪
     */
    SINA("25", "465", "smtp.sina.com"),
    ;

    private String port;
    private String sslPort;
    private String service;

    private MailService(String port, String sslPort, String service) {
        this.port = port;
        this.sslPort = sslPort;
        this.service = service;
    }

    public String getPort() {
        return port;
    }

    public String getService() {
        return service;
    }

    public String getSslPort() {
        return sslPort;
    }
}
