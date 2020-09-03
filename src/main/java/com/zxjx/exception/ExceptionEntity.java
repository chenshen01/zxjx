package com.zxjx.exception;

import java.io.Serializable;

/**
 * description
 *
 * @author liuzhixiang 2020/05/23 20:23
 */
public class ExceptionEntity implements Serializable {
    public final static int SUCCESS = 200;
    public final static int FAIL = 500;

    private int code;
    private String message;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public ExceptionEntity() {
    }
    public ExceptionEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
