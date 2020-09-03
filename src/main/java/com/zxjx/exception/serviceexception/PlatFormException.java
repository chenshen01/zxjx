package com.zxjx.exception.serviceexception;

/**
 * description 平台异常
 *
 * @author liuzhixiang 2020/05/24 20:34
 */
public class PlatFormException extends ServiceException {
    private static final long serialVersionUID = -8400239090334588012L;

    public static final String PLAT_FORM_ACCOUNT_NOT_ENOUGH = "平台账户余额不足请选择其它方式支付！";

    public PlatFormException(String message){
        super(message);
    }

    public PlatFormException(int code,String message){
        super(code,message);
    }

}
