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
    IMAGE_BMP("image/bmp"),
    IMAGE_GIF("image/gif"),
    IMAGE_JPEG("image/jpeg"),
    IMAGE_PNG("image/png"),
    IMAGE_SVG("image/svg+xml"),
    IMAGE_TIFF("image/tiff"),
    IMAGE_WEBP("image/webp"),
    MULTIPART_FORM_DATA("multipart/form-data"),
    TEXT_HTML("text/html"),
    TEXT_PLAIN("text/plain"),
    TEXT_XML("text/xml"),
    APPLICATION_OCTET_STREAM("application/octet-stream");
    public String code;

    RequestContentType(String code) {
        this.code = code;
    }
}
