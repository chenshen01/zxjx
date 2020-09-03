package com.zxjx.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zxjx.exception.ExceptionEntity;
import com.zxjx.exception.serviceexception.PlatFormException;
import com.zxjx.exception.serviceexception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * description 业务异常处理
 *
 * @author liuzhixiang 2020/05/23 20:19
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ExceptionEntity serviceException(HttpServletRequest request, final Exception e, HttpServletResponse response){
        response.setStatus(HttpStatus.OK.value());
        // 业务异常
        if (e instanceof PlatFormException) {
            // 平台信息异常
            PlatFormException platFormException=(PlatFormException)e;
            log.debug("发生了 [" + PlatFormException.class.getName() +"]异常，具体是：" + platFormException.getMessage());
            return new ExceptionEntity(platFormException.getCode(),platFormException.getMessage());
        } else {
            return new ExceptionEntity();
        }
    }
}
