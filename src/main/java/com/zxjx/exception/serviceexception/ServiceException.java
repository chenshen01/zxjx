package com.zxjx.exception.serviceexception;

import com.zxjx.exception.ExceptionEntity;

/**
 * description 业务代码异常的基类，所有业务异常都继承这个类
 *
 * @author liuzhixiang 2020/05/24 20:31
 */
public class ServiceException extends RuntimeException{
    private static final long serialVersionUID = -8400239090334588012L;
    private int code;
    public ServiceException(){
        super();
    }

    public ServiceException(int code,String message){
        super(message);
        this.setCode(code);
    }
    public ServiceException(String message){
        super(message);
        this.setCode(ExceptionEntity.FAIL);
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
