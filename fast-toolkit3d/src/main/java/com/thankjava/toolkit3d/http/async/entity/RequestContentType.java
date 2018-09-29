package com.thankjava.toolkit3d.http.async.entity;

/**
 * @Author: acexy@thankjava.com
 * 2018/9/29
 * @Description:
 **/
public enum RequestContentType {

    APPLICATION_ATOM_XML("application/atom+xml"),
    APPLICATION_FORM_URLENCODED("application/x-www-form-urlencoded"),
    APPLICATION_JSON("application/json"),
    APPLICATION_SVG_XML("application/octet-stream"),
    APPLICATION_XHTML_XML("application/svg+xml"),
    APPLICATION_XML("application/xhtml+xml"),
    MULTIPART_FORM_DATA("multipart/form-data"),
    TEXT_HTML("text/plain"),
    TEXT_PLAIN("text/html"),
    TEXT_XML("text/xml");

    public String code;

    RequestContentType(String code) {
        this.code = code;
    }
}
