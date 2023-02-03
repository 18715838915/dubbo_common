package org.luoyu.dubbo.basemodel.enums;

public enum ResponseCodeEnum {
    SUCCESS(200,"请求成功"),ERROR(500,"发生错误");


    ResponseCodeEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    private Integer code;

    private String text;


    public Integer getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

}
