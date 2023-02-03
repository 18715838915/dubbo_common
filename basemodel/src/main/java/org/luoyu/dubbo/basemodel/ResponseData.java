package org.luoyu.dubbo.basemodel;

import org.luoyu.dubbo.basemodel.enums.ResponseCodeEnum;

import java.io.Serializable;

/**
 * 统一接口响应数据结构
 * @param <T>
 */
public class ResponseData<T> implements Serializable {


    /**
     * 响应码
     */
    private int code;


    /**
     * 错误消息提示
     */
    private String msg;


    /**
     * 响应数据
     */
    private T data;


    public ResponseData(T data) {
        this.data = data;
        this.msg = "OK";
        this.code = ResponseCodeEnum.SUCCESS.getCode();
    }

    public ResponseData(String msg) {
        this.msg = msg;
        this.code = ResponseCodeEnum.ERROR.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> ResponseData<T> success(T data) {
        return new ResponseData<>(data);
    }


    public static <T> ResponseData<T> error(String msg) {
        ResponseData<T> data = new ResponseData<>(msg);
        return data;
    }


    public boolean isSuccess() {
        return ResponseCodeEnum.SUCCESS.getCode().equals(this.code);
    }

}
