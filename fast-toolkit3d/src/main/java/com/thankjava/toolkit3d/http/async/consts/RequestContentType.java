package com.thankjava.toolkit3d.http.async.consts;

/**
 * @Author: acexy@thankjava.com
 * 2018/9/29
 * @Description:
 **/
public enum RequestContentType {

    APPLICATION_ATOM_XML("application/atom+xml"),
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
    APPLICATION_JSON("application/json"),
    APPLICATION_SVG_XML("application/svg+xml"),
    APPLICATION_XHTML_XML("application/xhtml+xml"),
    APPLICATION_XML("application/xml"),
    MULTIPART_FORM_DATA("multipart/form-data"),
    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain"),
    TEXT_XML("text/xml");

    public String code;

    RequestContentType(String code) {
        this.code = code;
    }
}
